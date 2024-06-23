import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {
    private Connection conn;

    public EditoraDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarEditora(Editora editora) throws SQLException {
        String sql = "INSERT INTO Editora (nome, endereco, telefone) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, editora.getNome());
        stmt.setString(2, editora.getEndereco());
        stmt.setString(3, editora.getTelefone());
        stmt.executeUpdate();
    }

    public List<Editora> listarEditoras() throws SQLException {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM Editora";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Editora editora = new Editora(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("endereco"),
                rs.getString("telefone")
            );
            editoras.add(editora);
        }
        return editoras;
    }

    public void atualizarEditora(Editora editora) throws SQLException {
        String sql = "UPDATE Editora SET nome = ?, endereco = ?, telefone = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, editora.getNome());
        stmt.setString(2, editora.getEndereco());
        stmt.setString(3, editora.getTelefone());
        stmt.setInt(4, editora.getId());
        stmt.executeUpdate();
    }

    public void removerEditora(int id) throws SQLException {
        String sql = "DELETE FROM Editora WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
