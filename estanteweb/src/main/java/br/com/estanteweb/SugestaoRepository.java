package br.com.estanteweb;

import java.util.ArrayList;
import java.util.List;

public class SugestaoRepository {
    private List<Sugestao> sugestoes = new ArrayList<>();

    public void adicionar(Sugestao s) {
        sugestoes.add(s);
    }

    public int total() {
        return sugestoes.size();
    }

    public List<Sugestao> listarTodas() {
        return sugestoes;
    }
}
