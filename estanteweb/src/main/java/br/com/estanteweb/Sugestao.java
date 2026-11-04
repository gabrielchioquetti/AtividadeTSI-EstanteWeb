package br.com.estanteweb;

import java.util.Map;

public class Sugestao {
    private String nomeCliente;
    private String livroSugerido;

    public Sugestao(){

    }

    public Sugestao(String nomeCliente, String livroSugerido) {
        this.nomeCliente = nomeCliente;
        this.livroSugerido = livroSugerido;
    }

    public static Sugestao deMap(Map<String, Object> map){
        String nomeCliente = (String) map.get("nome_cliente");
        String livroSugerido = (String) map.get("livro_sugerido");

        return new Sugestao(nomeCliente, livroSugerido);
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getLivroSugerido() {
        return livroSugerido;
    }

    public void setLivroSugerido(String livroSugerido) {
        this.livroSugerido = livroSugerido;
    }
}
