package br.com.estanteweb;

import java.util.Map;

public class Livro {
    private String titulo;
    private String autor;
    private int ano;
    private double preco;

    public Livro(){

    }

    public Livro(String titulo, String autor, int ano, double preco){
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.preco = preco;
    }

    public static Livro deMap(Map<String, Object> map) {
        String titulo = (String) map.get("titulo");
        String autor = (String) map.get("autor");
        int ano = ((Number) map.get("ano")).intValue();
        double preco = ((Number) map.get("preco")).doubleValue();

        return new Livro(titulo, autor, ano, preco);
    }
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
