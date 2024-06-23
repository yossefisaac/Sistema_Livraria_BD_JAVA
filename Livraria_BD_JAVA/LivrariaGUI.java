import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class LivrariaGUI {
    private JFrame frame;
    private JTextField isbnField, tituloField, precoField, anoField, categoriaField;
    private JTextField nomeClienteField, enderecoClienteField, telefoneClienteField, emailClienteField;
    private JTextField dataPedidoField, totalPedidoField, statusPedidoField, clienteIdPedidoField;
    private JTextField nomeEditoraField, enderecoEditoraField, telefoneEditoraField;
    private JTextField nomeAutorField, biografiaAutorField;
    private LivroDAO livroDAO;
    private ClienteDAO clienteDAO;
    private PedidoDAO pedidoDAO;
    private EditoraDAO editoraDAO;
    private AutorDAO autorDAO;

    public LivrariaGUI(LivroDAO livroDAO, ClienteDAO clienteDAO, PedidoDAO pedidoDAO, EditoraDAO editoraDAO, AutorDAO autorDAO) {
        this.livroDAO = livroDAO;
        this.clienteDAO = clienteDAO;
        this.pedidoDAO = pedidoDAO;
        this.editoraDAO = editoraDAO;
        this.autorDAO = autorDAO;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Livraria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel livroPanel = new JPanel(new GridLayout(7, 2));
        livroPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        livroPanel.add(isbnField);
        livroPanel.add(new JLabel("Título:"));
        tituloField = new JTextField();
        livroPanel.add(tituloField);
        livroPanel.add(new JLabel("Preço:"));
        precoField = new JTextField();
        livroPanel.add(precoField);
        livroPanel.add(new JLabel("Ano:"));
        anoField = new JTextField();
        livroPanel.add(anoField);
        livroPanel.add(new JLabel("Categoria:"));
        categoriaField = new JTextField();
        livroPanel.add(categoriaField);

        JButton addLivroButton = new JButton("Adicionar Livro");
        livroPanel.add(addLivroButton);
        addLivroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String isbn = isbnField.getText();
                    String titulo = tituloField.getText();
                    double preco = Double.parseDouble(precoField.getText());
                    int ano = Integer.parseInt(anoField.getText());
                    String categoria = categoriaField.getText();
                    Livro livro = new Livro(isbn, titulo, preco, ano, categoria);
                    livroDAO.adicionarLivro(livro);
                    JOptionPane.showMessageDialog(frame, "Livro adicionado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar livro: " + ex.getMessage());
                }
            }
        });

        JButton listLivroButton = new JButton("Listar Livros");
        livroPanel.add(listLivroButton);
        listLivroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Livro> livros = livroDAO.listarLivros();
                    StringBuilder builder = new StringBuilder();
                    for (Livro livro : livros) {
                        builder.append(livro.getTitulo()).append(" - ").append(livro.getIsbn()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, builder.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao listar livros: " + ex.getMessage());
                }
            }
        });

        JButton updateLivroButton = new JButton("Atualizar Livro");
        livroPanel.add(updateLivroButton);
        updateLivroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String isbn = isbnField.getText();
                    String titulo = tituloField.getText();
                    double preco = Double.parseDouble(precoField.getText());
                    int ano = Integer.parseInt(anoField.getText());
                    String categoria = categoriaField.getText();
                    Livro livro = new Livro(isbn, titulo, preco, ano, categoria);
                    livroDAO.atualizarLivro(livro);
                    JOptionPane.showMessageDialog(frame, "Livro atualizado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao atualizar livro: " + ex.getMessage());
                }
            }
        });

        JButton removeLivroButton = new JButton("Remover Livro");
        livroPanel.add(removeLivroButton);
        removeLivroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String isbn = isbnField.getText();
                    livroDAO.removerLivro(isbn);
                    JOptionPane.showMessageDialog(frame, "Livro removido com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover livro: " + ex.getMessage());
                }
            }
        });

        tabbedPane.addTab("Livros", livroPanel);

        // Painel de Clientes
        JPanel clientePanel = new JPanel(new GridLayout(7, 2));
        clientePanel.add(new JLabel("Nome do Cliente:"));
        nomeClienteField = new JTextField();
        clientePanel.add(nomeClienteField);
        clientePanel.add(new JLabel("Endereço do Cliente:"));
        enderecoClienteField = new JTextField();
        clientePanel.add(enderecoClienteField);
        clientePanel.add(new JLabel("Telefone do Cliente:"));
        telefoneClienteField = new JTextField();
        clientePanel.add(telefoneClienteField);
        clientePanel.add(new JLabel("Email do Cliente:"));
        emailClienteField = new JTextField();
        clientePanel.add(emailClienteField);

        JButton addClienteButton = new JButton("Adicionar Cliente");
        clientePanel.add(addClienteButton);
        addClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeClienteField.getText();
                    String endereco = enderecoClienteField.getText();
                    String telefone = telefoneClienteField.getText();
                    String email = emailClienteField.getText();
                    Cliente cliente = new Cliente(0, nome, endereco, telefone, email);
                    clienteDAO.adicionarCliente(cliente);
                    JOptionPane.showMessageDialog(frame, "Cliente adicionado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar cliente: " + ex.getMessage());
                }
            }
        });

        JButton listClienteButton = new JButton("Listar Clientes");
        clientePanel.add(listClienteButton);
        listClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Cliente> clientes = clienteDAO.listarClientes();
                    StringBuilder builder = new StringBuilder();
                    for (Cliente cliente : clientes) {
                        builder.append(cliente.getNome()).append(" - ").append(cliente.getEmail()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, builder.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao listar clientes: " + ex.getMessage());
                }
            }
        });

        JButton updateClienteButton = new JButton("Atualizar Cliente");
        clientePanel.add(updateClienteButton);
        updateClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "ID do Cliente:"));
                    String nome = nomeClienteField.getText();
                    String endereco = enderecoClienteField.getText();
                    String telefone = telefoneClienteField.getText();
                    String email = emailClienteField.getText();
                    Cliente cliente = new Cliente(id, nome, endereco, telefone, email);
                    clienteDAO.atualizarCliente(cliente);
                    JOptionPane.showMessageDialog(frame, "Cliente atualizado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao atualizar cliente: " + ex.getMessage());
                }
            }
        });

        JButton removeClienteButton = new JButton("Remover Cliente");
        clientePanel.add(removeClienteButton);
        removeClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "ID do Cliente:"));
                    clienteDAO.removerCliente(id);
                    JOptionPane.showMessageDialog(frame, "Cliente removido com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover cliente: " + ex.getMessage());
                }
            }
        });

        tabbedPane.addTab("Clientes", clientePanel);

        // Painel de Pedidos
        JPanel pedidoPanel = new JPanel(new GridLayout(7, 2));
        pedidoPanel.add(new JLabel("Data do Pedido (yyyy-mm-dd):"));
        dataPedidoField = new JTextField();
        pedidoPanel.add(dataPedidoField);
        pedidoPanel.add(new JLabel("Total do Pedido:"));
        totalPedidoField = new JTextField();
        pedidoPanel.add(totalPedidoField);
        pedidoPanel.add(new JLabel("Status do Pedido:"));
        statusPedidoField = new JTextField();
        pedidoPanel.add(statusPedidoField);
        pedidoPanel.add(new JLabel("ID do Cliente:"));
        clienteIdPedidoField = new JTextField();
        pedidoPanel.add(clienteIdPedidoField);

        JButton addPedidoButton = new JButton("Adicionar Pedido");
        pedidoPanel.add(addPedidoButton);
        addPedidoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int clienteId = Integer.parseInt(clienteIdPedidoField.getText());
                    Date data = Date.valueOf(dataPedidoField.getText());
                    double total = Double.parseDouble(totalPedidoField.getText());
                    String status = statusPedidoField.getText();
                    Pedido pedido = new Pedido(0, clienteId, data, total, status);
                    pedidoDAO.adicionarPedido(pedido);
                    JOptionPane.showMessageDialog(frame, "Pedido adicionado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar pedido: " + ex.getMessage());
                }
            }
        });

        JButton listPedidoButton = new JButton("Listar Pedidos");
        pedidoPanel.add(listPedidoButton);
        listPedidoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Pedido> pedidos = pedidoDAO.listarPedidos();
                    StringBuilder builder = new StringBuilder();
                    for (Pedido pedido : pedidos) {
                        builder.append("Pedido ID: ").append(pedido.getId())
                                .append(", Cliente ID: ").append(pedido.getClienteId())
                                .append(", Data: ").append(pedido.getData())
                                .append(", Total: ").append(pedido.getTotal())
                                .append(", Status: ").append(pedido.getStatus())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, builder.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao listar pedidos: " + ex.getMessage());
                }
            }
        });

        JButton updatePedidoButton = new JButton("Atualizar Pedido");
        pedidoPanel.add(updatePedidoButton);
        updatePedidoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "ID do Pedido:"));
                    int clienteId = Integer.parseInt(clienteIdPedidoField.getText());
                    Date data = Date.valueOf(dataPedidoField.getText());
                    double total = Double.parseDouble(totalPedidoField.getText());
                    String status = statusPedidoField.getText();
                    Pedido pedido = new Pedido(id, clienteId, data, total, status);
                    pedidoDAO.atualizarPedido(pedido);
                    JOptionPane.showMessageDialog(frame, "Pedido atualizado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao atualizar pedido: " + ex.getMessage());
                }
            }
        });

        JButton removePedidoButton = new JButton("Remover Pedido");
        pedidoPanel.add(removePedidoButton);
        removePedidoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "ID do Pedido:"));
                    pedidoDAO.removerPedido(id);
                    JOptionPane.showMessageDialog(frame, "Pedido removido com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover pedido: " + ex.getMessage());
                }
            }
        });

        tabbedPane.addTab("Pedidos", pedidoPanel);

        // Painel de Editoras
        JPanel editoraPanel = new JPanel(new GridLayout(5, 2));
        editoraPanel.add(new JLabel("Nome da Editora:"));
        nomeEditoraField = new JTextField();
        editoraPanel.add(nomeEditoraField);
        editoraPanel.add(new JLabel("Endereço da Editora:"));
        enderecoEditoraField = new JTextField();
        editoraPanel.add(enderecoEditoraField);
        editoraPanel.add(new JLabel("Telefone da Editora:"));
        telefoneEditoraField = new JTextField();
        editoraPanel.add(telefoneEditoraField);

        JButton addEditoraButton = new JButton("Adicionar Editora");
        editoraPanel.add(addEditoraButton);
        addEditoraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeEditoraField.getText();
                    String endereco = enderecoEditoraField.getText();
                    String telefone = telefoneEditoraField.getText();
                    Editora editora = new Editora(0, nome, endereco, telefone);
                    editoraDAO.adicionarEditora(editora);
                    JOptionPane.showMessageDialog(frame, "Editora adicionada com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar editora: " + ex.getMessage());
                }
            }
        });

        JButton listEditoraButton = new JButton("Listar Editoras");
        editoraPanel.add(listEditoraButton);
        listEditoraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Editora> editoras = editoraDAO.listarEditoras();
                    StringBuilder builder = new StringBuilder();
                    for (Editora editora : editoras) {
                        builder.append(editora.getNome()).append(" - ").append(editora.getTelefone()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, builder.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao listar editoras: " + ex.getMessage());
                }
            }
        });

        JButton updateEditoraButton = new JButton("Atualizar Editora");
        editoraPanel.add(updateEditoraButton);
        updateEditoraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "ID da Editora:"));
                    String nome = nomeEditoraField.getText();
                    String endereco = enderecoEditoraField.getText();
                    String telefone = telefoneEditoraField.getText();
                    Editora editora = new Editora(id, nome, endereco, telefone);
                    editoraDAO.atualizarEditora(editora);
                    JOptionPane.showMessageDialog(frame, "Editora atualizada com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao atualizar editora: " + ex.getMessage());
                }
            }
        });

        JButton removeEditoraButton = new JButton("Remover Editora");
        editoraPanel.add(removeEditoraButton);
        removeEditoraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "ID da Editora:"));
                    editoraDAO.removerEditora(id);
                    JOptionPane.showMessageDialog(frame, "Editora removida com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover editora: " + ex.getMessage());
                }
            }
        });

        tabbedPane.addTab("Editoras", editoraPanel);

        // Painel de Autores
        JPanel autorPanel = new JPanel(new GridLayout(4, 2));
        autorPanel.add(new JLabel("Nome do Autor:"));
        nomeAutorField = new JTextField();
        autorPanel.add(nomeAutorField);
        autorPanel.add(new JLabel("Biografia do Autor:"));
        biografiaAutorField = new JTextField();
        autorPanel.add(biografiaAutorField);

        JButton addAutorButton = new JButton("Adicionar Autor");
        autorPanel.add(addAutorButton);
        addAutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeAutorField.getText();
                    String biografia = biografiaAutorField.getText();
                    Autor autor = new Autor(0, nome, biografia);
                    autorDAO.adicionarAutor(autor);
                    JOptionPane.showMessageDialog(frame, "Autor adicionado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar autor: " + ex.getMessage());
                }
            }
        });

        JButton listAutorButton = new JButton("Listar Autores");
        autorPanel.add(listAutorButton);
        listAutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Autor> autores = autorDAO.listarAutores();
                    StringBuilder builder = new StringBuilder();
                    for (Autor autor : autores) {
                        builder.append(autor.getNome()).append(" - ").append(autor.getBiografia()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, builder.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao listar autores: " + ex.getMessage());
                }
            }
        });

        JButton updateAutorButton = new JButton("Atualizar Autor");
        autorPanel.add(updateAutorButton);
        updateAutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "ID do Autor:"));
                    String nome = nomeAutorField.getText();
                    String biografia = biografiaAutorField.getText();
                    Autor autor = new Autor(id, nome, biografia);
                    autorDAO.atualizarAutor(autor);
                    JOptionPane.showMessageDialog(frame, "Autor atualizado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao atualizar autor: " + ex.getMessage());
                }
            }
        });

        JButton removeAutorButton = new JButton("Remover Autor");
        autorPanel.add(removeAutorButton);
        removeAutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "ID do Autor:"));
                    autorDAO.removerAutor(id);
                    JOptionPane.showMessageDialog(frame, "Autor removido com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover autor: " + ex.getMessage());
                }
            }
        });

        tabbedPane.addTab("Autores", autorPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Connection conn = DatabaseConnection.connect();
        LivroDAO livroDAO = new LivroDAO(conn);
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        PedidoDAO pedidoDAO = new PedidoDAO(conn);
        EditoraDAO editoraDAO = new EditoraDAO(conn);
        AutorDAO autorDAO = new AutorDAO(conn);
        new LivrariaGUI(livroDAO, clienteDAO, pedidoDAO, editoraDAO, autorDAO);
    }
}
