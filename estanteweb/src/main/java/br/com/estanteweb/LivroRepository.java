package br.com.estanteweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LivroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Livro> listarTodos() {
        String sql = "SELECT * FROM livro";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);

        List<Livro> resultado = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            resultado.add(Livro.deMap(linha));
        }
        return resultado;
    }

    public int contarLivros() {
        String sql = "SELECT COUNT(*) FROM livro";
        Integer total = jdbcTemplate.queryForObject(sql, Integer.class);
        return total != null ? total : 0;
    }

    public List<Livro> listarAtePreco(double precoMaximo) {
        String sql = "SELECT * FROM livro WHERE preco <= ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, precoMaximo);

        List<Livro> resultado = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            resultado.add(Livro.deMap(linha));
        }
        return resultado;
    }

    public Double somarValorAcervo() {
        String sql = "SELECT SUM(preco) FROM livro";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public void salvar(Livro livro){
        String sql = "INSERT INTO livro (titulo, autor, ano, preco) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getPreco());
    }

    public Livro buscarPorId(Long id){
        String sql = "SELECT * FROM livro WHERE id = ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, id);

        if (linhas.isEmpty()) {
            return null;
        }

        return Livro.deMap(linhas.get(0));
    }

    public List<Livro> listarOrdenadoPor(String coluna) {
        // 1. Verifica se a coluna é permitida
        if (coluna != null &&
            (coluna.equals("titulo") ||
            coluna.equals("autor") ||
            coluna.equals("ano") ||
            coluna.equals("preco"))) {

            // 2. Monta a consulta SQL com a coluna validada
            String sql = "SELECT * FROM livro ORDER BY " + coluna;

            // 3. Executa a consulta no banco
            List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql);

            // 4. Converte os resultados em objetos Livro
            List<Livro> listaLivros = new ArrayList<>();

            for (Map<String, Object> linha : linhas) {
                listaLivros.add(Livro.deMap(linha));
            }

            return listaLivros;
        }

        // 5. Se a coluna for inválida ou nula, usa a listagem padrão
        return listarTodos();
    }

    public List<Livro> listarDoMesmoAutor(String autor, Long idAtual) {

        String sql = "SELECT * FROM livro WHERE autor = ? AND id <> ?";

        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, autor, idAtual);

        List<Livro> listaLivros = new ArrayList<>();

        for (Map<String, Object> linha : linhas) {
            listaLivros.add(Livro.deMap(linha));
        }

        return listaLivros;
    }

    public void atualizar(Livro livro) {

        String sql = "UPDATE livro SET titulo = ?, autor = ?, ano = ?, preco = ? WHERE id = ?";

        jdbcTemplate.update(sql, livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getPreco(), livro.getId());
    }
}