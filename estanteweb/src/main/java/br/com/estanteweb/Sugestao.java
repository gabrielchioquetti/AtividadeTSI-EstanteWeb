package br.com.estanteweb;

public class Sugestao {
    private String nomeCliente;
    private String livroSugerido;

    public Sugestao(){

    }

    public Sugestao(String nomeCliente, String livroSugerido) {
        this.nomeCliente = nomeCliente;
        this.livroSugerido = livroSugerido;
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
