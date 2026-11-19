package br.com.estanteweb;

import java.util.Map;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    private Usuario(){

    }

    private Usuario(Long id, String nome, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public static Usuario deMap(Map<String, Object> map) {
        Long id = ((Number) map.get("id")).longValue();
        String nome = (String) map.get("nome");
        String email = (String) map.get("email");
        String senha = (String) map.get("senha");

        return new Usuario(id, nome, email, senha);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
