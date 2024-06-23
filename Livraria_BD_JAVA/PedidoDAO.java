import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private Connection conn;

    public PedidoDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedido (cliente_id, data, total, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, pedido.getClienteId());
        stmt.setDate(2, pedido.getData());
        stmt.setDouble(3, pedido.getTotal());
        stmt.setString(4, pedido.getStatus());
        stmt.executeUpdate();
    }

    public List<Pedido> listarPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Pedido pedido = new Pedido(
                rs.getInt("id"),
                rs.getInt("cliente_id"),
                rs.getDate("data"),
                rs.getDouble("total"),
                rs.getString("status")
            );
            pedidos.add(pedido);
        }
        return pedidos;
    }

    public void atualizarPedido(Pedido pedido) throws SQLException {
        String sql = "UPDATE Pedido SET cliente_id = ?, data = ?, total = ?, status = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, pedido.getClienteId());
        stmt.setDate(2, pedido.getData());
        stmt.setDouble(3, pedido.getTotal());
        stmt.setString(4, pedido.getStatus());
        stmt.setInt(5, pedido.getId());
        stmt.executeUpdate();
    }

    public void removerPedido(int id) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
