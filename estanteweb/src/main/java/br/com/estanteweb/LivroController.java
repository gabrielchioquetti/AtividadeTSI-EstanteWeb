package br.com.estanteweb;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LivroController {
    private LivroRepository livroRepository = new LivroRepository();

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

    @GetMapping(value = "/contato", produces = "text/html")
    public String formContato() {
        return "<form method=\"post\" action=\"/contato\">"
                + "<label>Nome: <input type=\"text\" name=\"nome\"></label>"
                + "<label>Mensagem: <input type=\"text\" name=\"mensagem\"></label>"
                + "<button type=\"submit\">Enviar mensagem</button>"
                + "</form>";
    }

    @PostMapping("/contato")
    public ResponseEntity<String> formContato(
            @RequestParam String nome,
            @RequestParam String mensagem) {

        if (mensagem == null || mensagem.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro: a mensagem não pode estar em branco.");
        }

        return ResponseEntity.ok(
                "Obrigado, " + nome + "! Sua mensagem foi recebida."
        );
    }

}