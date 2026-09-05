package br.com.estanteweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaginaControlle {
    
    @GetMapping("/")
    public String paginaInicial(){
        return "EstanteWeb: livros usados com história";
    }

    @GetMapping("/sobre")
    public String paginaSobre(){
        return "Detalhes da loja";
    }
    
}
