package br.com.estanteweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/livros")
    @ResponseBody
    public String paginaLivros(){
        String detalheLivro = "Acervo EstanteWeb: ";
        for (Livro livro : livroRepository.listarTodos()) {
            detalheLivro += livro.getTitulo() + ", de " + livro.getAutor() + ", publicado em " + livro.getAno() + " (R$ " + livro.getPreco() + ") | ";
        }
        return detalheLivro;
    }

    @GetMapping("/livros/total")
    @ResponseBody
    public String livrosTotal(){
        return "O acervo tem " + livroRepository.contarLivros() + " livros";
    }

    @GetMapping("/livros/valor-total")
    @ResponseBody
    public String somarValorAcervo(){
        Double total = livroRepository.somarValorAcervo();
        if (total == null) {
            total = 0.0;
        }
        return "O valor total do acervo é R$ " + String.format("%.2f", total);
    }

    @GetMapping(value = "/livros/html")
    public String paginaDesafio(Model model,
            @RequestParam(required = false) String ordem) {

        if (ordem != null && !ordem.isBlank()) {
            model.addAttribute("livros", livroRepository.listarOrdenadoPor(ordem));
        } else {
            model.addAttribute("livros", livroRepository.listarTodos());
        }

        return "livros";
    }

    @GetMapping("/livros/busca")
    public ResponseEntity<String> buscaLivro(@RequestParam(defaultValue = "999") double precoMaximo){
        List<Livro> livros = livroRepository.listarAtePreco(precoMaximo);
        
        if(livros.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum livro encontrado com o preço máximo de: " + precoMaximo);
        }
        
        String detalheLivro = "Acervo EstanteWeb: ";
        
        for (Livro livro : livros) {
            detalheLivro += livro.getTitulo() + ", de " + livro.getAutor() + ", publicado em " + livro.getAno() + " (R$ " + livro.getPreco() + ") | ";
        }

        return ResponseEntity.ok()
                .header("X-Total-Encontrados", String.valueOf(livros.size()))
                .body(detalheLivro);
    }

    @GetMapping("/livros/quem-acessa")
    @ResponseBody
    public String quemAcessa(@RequestHeader("User-Agent") String userAgent){
        return "Requisição recebida de: " + userAgent;
    }

    @GetMapping("/livros/novo")
    public String cadastroLivro(){
        return "livro-novo";
    }

    @PostMapping("/livros")
    public String novoLivro(Model model, @RequestParam String titulo, @RequestParam String autor, @RequestParam int ano, @RequestParam double preco){
        
        if (titulo.isBlank()) {
            model.addAttribute("erro", "Informe o titulo do livro.");
            model.addAttribute("autor", autor);
            model.addAttribute("ano", ano);
            model.addAttribute("preco", preco);
            return "livro-novo";
        }else if(autor.isBlank()){
            model.addAttribute("erro", "Informe o nome do autor.");
            model.addAttribute("titulo", titulo);
            model.addAttribute("ano", ano);
            model.addAttribute("preco", preco);
            return "livro-novo";
        }else if (preco <= 0) {
            model.addAttribute("erro", "O preço tem que ser maior que 0.");
            model.addAttribute("titulo", titulo);
            model.addAttribute("autor", autor);
            model.addAttribute("ano", ano);
            return "livro-novo";
        }

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);

        livroRepository.salvar(livro);

        return "redirect:/livros/html";
    }

    @GetMapping("/livros/{id}")
    public String detalhesLivro(@PathVariable Long id, Model model){
        Livro livro = livroRepository.buscarPorId(id);

        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("livro", livro);
        model.addAttribute("outrosLivros", livroRepository.listarDoMesmoAutor(livro.getAutor(), livro.getId()));
        return "livro";
    }

    @GetMapping("/livros/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Livro livro = livroRepository.buscarPorId(id);
        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("livro", livro);
        return "livro-editar";
    }

    
    @PostMapping("/livros/{id}")
    public String salvarEdicao(@PathVariable Long id, @RequestParam String titulo, @RequestParam String autor, @RequestParam int ano, @RequestParam double preco, Model model) {

        Livro livro = livroRepository.buscarPorId(id);
        
        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        livro.setId(id);
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);

        if (titulo.isBlank()) {
            model.addAttribute("erro", "Informe o título do livro.");
            model.addAttribute("livro", livro);
            return "livro-editar";
        }

        if (autor.isBlank()) {
            model.addAttribute("erro", "Informe o nome do autor.");
            model.addAttribute("livro", livro);
            return "livro-editar";
        }

        if (preco <= 0) {
            model.addAttribute("erro", "O preço tem que ser maior que 0.");
            model.addAttribute("livro", livro);
            return "livro-editar";
        }

        livroRepository.atualizar(livro);

        return "redirect:/livros/" + id;
    }

    @GetMapping("/livros/{id}/excluir")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Livro livro = livroRepository.buscarPorId(id);
        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("livro", livro);
        return "livro-excluir";
    }

    @PostMapping("/livros/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        livroRepository.excluir(id);
        return "redirect:/livros/html";
    }

}