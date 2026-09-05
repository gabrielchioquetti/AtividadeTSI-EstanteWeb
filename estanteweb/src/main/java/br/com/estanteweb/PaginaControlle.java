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
public class PaginaControlle {
    
    @GetMapping("/")
    @ResponseBody
    public String paginaInicial(){
        return "EstanteWeb: livros usados com história";
    }

    @GetMapping("/sobre")
    @ResponseBody
    public String paginaSobre(){
        return "Detalhes da loja";
    }

    @GetMapping("/contato")
    public String formContato(Model model) {
        return "contato";
    }

    @PostMapping("/contato")
    public String formContato(
            Model model,
            @RequestParam String nome,
            @RequestParam String mensagem) {

        if (mensagem == null || mensagem.isBlank()) {
            model.addAttribute("erro", "Digite uma mensagem.");
            model.addAttribute("nome", nome);
            return "contato";
        }

        return "redirect:/contato/sucesso";
    }
    
    @GetMapping("/contato/sucesso")
    public String contatoSucesso() {
        return "contato-sucesso";
    }
    
}
