package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Clientes;

public class ClientesDAO {
    private Connection connection;
    private List<Clientes> clientes;

    public ClientesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes (NOME VARCHAR(255) PRIMARY KEY, EMAIL VARCHAR(255), " +
                     "CPF VARCHAR(11), RG VARCHAR(20), RENDAFIXA VARCHAR(20), PRETENSAO VARCHAR(20))";

        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de clientes criada com sucesso.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de clientes: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public List<Clientes> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clientes = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM clientes");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes cliente = new Clientes(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("rg"),
                        rs.getString("rendaFixa"),
                        rs.getString("pretensao")
                );
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }

        return clientes;
    }

    public void cadastrar(String nome, String email) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar cliente no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void atualizar(String nome, String email) {
        PreparedStatement stmt = null;
        String sql = "UPDATE clientes SET email = ? WHERE nome = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, nome);
            stmt.executeUpdate();
            System.out.println("Dados do cliente atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados do cliente no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void apagar(String nome) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM clientes WHERE nome = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.executeUpdate();
            System.out.println("Cliente apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar cliente no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
