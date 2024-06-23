import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Connection conn;

    public AutorDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarAutor(Autor autor) throws SQLException {
        String sql = "INSERT INTO Autor (nome, biografia) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, autor.getNome());
        stmt.setString(2, autor.getBiografia());
        stmt.executeUpdate();
    }

    public List<Autor> listarAutores() throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Autor autor = new Autor(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("biografia")
            );
            autores.add(autor);
        }
        return autores;
    }

    public void atualizarAutor(Autor autor) throws SQLException {
        String sql = "UPDATE Autor SET nome = ?, biografia = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, autor.getNome());
        stmt.setString(2, autor.getBiografia());
        stmt.setInt(3, autor.getId());
        stmt.executeUpdate();
    }

    public void removerAutor(int id) throws SQLException {
        String sql = "DELETE FROM Autor WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
