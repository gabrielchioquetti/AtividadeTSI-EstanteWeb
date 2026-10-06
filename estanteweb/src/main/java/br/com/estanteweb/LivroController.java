package br.com.estanteweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String paginaDesafio(Model model){
        model.addAttribute("livros", livroRepository.listarTodos());
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

}