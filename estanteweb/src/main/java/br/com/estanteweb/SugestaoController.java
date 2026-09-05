package br.com.estanteweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SugestaoController {
    private SugestaoRepository sugestaoRepository = new SugestaoRepository();

    @GetMapping("/sugestoes/nova")
    public String novaSugestao() {
        return "sugestao-nova";
    }

    @PostMapping("/sugestoes")
    public String adicionarSugestao(
            Model model,
            @RequestParam String nomeCliente,
            @RequestParam String livroSugerido) {
        
        if(nomeCliente.isBlank() || livroSugerido.isBlank()){
            model.addAttribute("erro", "Nome do cliente ou Livro sugerido em branco!!!");
            model.addAttribute("nomeCliente", nomeCliente);
            model.addAttribute("livroSugerido", livroSugerido);
            return "sugestao-nova";
        }
        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);

        sugestaoRepository.adicionar(sugestao);

        return "redirect:/sugestoes?enviada=true";
    }

    @GetMapping("/sugestoes")
    public String listarSugestoes(Model model, @RequestParam(required = false) Boolean enviada) {
        model.addAttribute("enviada", enviada);
        model.addAttribute("sugestoes", sugestaoRepository.listarTodas());

        return "sugestoes";
    }
}
