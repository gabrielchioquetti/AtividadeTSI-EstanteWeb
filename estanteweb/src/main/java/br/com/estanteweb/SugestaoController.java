package br.com.estanteweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class SugestaoController {

    @Autowired
    private SugestaoRepository sugestaoRepository;

    @GetMapping("/sugestoes/nova")
    public String novaSugestao() {
        return "sugestao-nova";
    }

    @PostMapping("/sugestoes")
    public String adicionarSugestao(
            Model model,
            @RequestParam String nomeCliente,
            @RequestParam String livroSugerido) {

        if (nomeCliente.isBlank() || livroSugerido.isBlank()) {
            model.addAttribute("erro", "Nome do cliente ou Livro sugerido em branco!!!");
            model.addAttribute("nomeCliente", nomeCliente);
            model.addAttribute("livroSugerido", livroSugerido);
            return "sugestao-nova";
        }

        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);

        sugestaoRepository.salvar(sugestao);

        return "redirect:/sugestoes?enviada=true";
    }

    @GetMapping("/sugestoes")
    public String listarSugestoes(
            Model model,
            @RequestParam(required = false) Boolean enviada) {

        model.addAttribute("enviada", enviada);
        model.addAttribute("sugestoes", sugestaoRepository.listarTodas());

        return "sugestoes";
    }

    @GetMapping("/sugestoes/{id}/editar")
    public String editarSugestao(@PathVariable Long id, Model model) {

        Sugestao sugestao = sugestaoRepository.buscarPorId(id);

        if (sugestao == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("sugestao", sugestao);

        return "sugestao-editar";
    }

    @PostMapping("/sugestoes/{id}")
    public String atualizarSugestao(
            @PathVariable Long id,
            @RequestParam String nomeCliente,
            @RequestParam String livroSugerido,
            Model model) {

        Sugestao sugestao = sugestaoRepository.buscarPorId(id);

        if (sugestao == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        sugestao.setNomeCliente(nomeCliente);
        sugestao.setLivroSugerido(livroSugerido);

        if (nomeCliente.isBlank() || livroSugerido.isBlank()) {
            model.addAttribute("erro", "Nome do cliente ou Livro sugerido em branco!!!");
            model.addAttribute("sugestao", sugestao);
            return "sugestao-editar";
        }

        sugestaoRepository.atualizar(sugestao);

        return "redirect:/sugestoes";
    }

    @GetMapping("/sugestoes/{id}/excluir")
    public String confirmarExclusao(@PathVariable Long id, Model model) {

        Sugestao sugestao = sugestaoRepository.buscarPorId(id);

        if (sugestao == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("sugestao", sugestao);

        return "sugestao-excluir";
    }

    @PostMapping("/sugestoes/{id}/excluir")
    public String excluirSugestao(@PathVariable Long id) {

        Sugestao sugestao = sugestaoRepository.buscarPorId(id);

        if (sugestao == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        sugestaoRepository.excluir(id);

        return "redirect:/sugestoes";
    }
}