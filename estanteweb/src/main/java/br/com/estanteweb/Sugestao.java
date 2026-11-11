package br.com.estanteweb;

import java.util.Map;

public class Sugestao {

    private Long id;
    private String nomeCliente;
    private String livroSugerido;

    public Sugestao() {
    }

    public Sugestao(Long id, String nomeCliente, String livroSugerido) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.livroSugerido = livroSugerido;
    }

    public Sugestao(String nomeCliente, String livroSugerido) {
        this.nomeCliente = nomeCliente;
        this.livroSugerido = livroSugerido;
    }

    public static Sugestao deMap(Map<String, Object> map) {
        Long id = ((Number) map.get("id")).longValue();
        String nomeCliente = (String) map.get("nome_cliente");
        String livroSugerido = (String) map.get("livro_sugerido");

        return new Sugestao(id, nomeCliente, livroSugerido);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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