import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection conn;

    public LivroDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livro (ISBN, titulo, preco, ano, categoria) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, livro.getIsbn());
        stmt.setString(2, livro.getTitulo());
        stmt.setDouble(3, livro.getPreco());
        stmt.setInt(4, livro.getAno());
        stmt.setString(5, livro.getCategoria());
        stmt.executeUpdate();
    }

    public List<Livro> listarLivros() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Livro livro = new Livro(
                rs.getString("ISBN"),
                rs.getString("titulo"),
                rs.getDouble("preco"),
                rs.getInt("ano"),
                rs.getString("categoria")
            );
            livros.add(livro);
        }
        return livros;
    }

    public void atualizarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE Livro SET titulo = ?, preco = ?, ano = ?, categoria = ? WHERE ISBN = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, livro.getTitulo());
        stmt.setDouble(2, livro.getPreco());
        stmt.setInt(3, livro.getAno());
        stmt.setString(4, livro.getCategoria());
        stmt.setString(5, livro.getIsbn());
        stmt.executeUpdate();
    }

    public void removerLivro(String isbn) throws SQLException {
        String sql = "DELETE FROM Livro WHERE ISBN = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, isbn);
        stmt.executeUpdate();
    }
}
