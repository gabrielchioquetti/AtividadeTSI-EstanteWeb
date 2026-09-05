package br.com.estanteweb;

import java.util.List;

public class LivroRepository {
    private List<Livro> livros = List.of(
        new Livro("Dom Casmurro", "Machado de Assis", 1899, 15.00),
        new Livro("1984", "George Orwell", 1949, 35.00),
        new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943, 20.00),
        new Livro("O Alquimista", "Paulo Coelho", 1988, 40.00),
        new Livro("Uma Breve História da Humanidade", "Yuval Noah Harari", 2011, 60.00),
        new Livro("O <b>Grande</b> Gatsby", "Teste", 2026, 100.00)
    );

    public List<Livro> listarTodos(){
        return livros;
    }

    public int contarLivros(){
        return livros.size();
    }

    public List<Livro> listarAtePreco(double precoMaximo) {
    return livros.stream()
                 .filter(livro -> livro.getPreco() <= precoMaximo)
                 .toList();
    }

}
