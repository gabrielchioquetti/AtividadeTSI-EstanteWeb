package br.com.estanteweb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SugestaoController {
    private SugestaoRepository sugestaoRepository = new SugestaoRepository();

    @GetMapping(value = "/sugestoes/nova", produces = "text/html")
    @ResponseBody
    public String novaSugestao() {
        return "<form method=\"post\" action=\"/sugestoes\">"
                + "<label>Nome do cliente: "
                + "<input type=\"text\" name=\"nomeCliente\">"
                + "</label><br>"
                + "<label>Livro sugerido: "
                + "<input type=\"text\" name=\"livroSugerido\">"
                + "</label><br>"
                + "<button type=\"submit\">Enviar sugestão</button>"
                + "</form>";
    }

    @PostMapping("/sugestoes")
    public ResponseEntity<String> adicionarSugestao(
            @RequestParam String nomeCliente,
            @RequestParam String livroSugerido) {

        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);

        sugestaoRepository.adicionar(sugestao);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("X-Total-Sugestoes",
                        String.valueOf(sugestaoRepository.total()))
                .body("Sugestão recebida com sucesso!");
    }

    @GetMapping(value = "/sugestoes")
    public String listarSugestoes(Model model) {

        model.addAttribute("sugestoes", sugestaoRepository.listarTodas());

        return "sugestoes";
    }
}
