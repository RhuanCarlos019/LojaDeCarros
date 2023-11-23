package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Vendas;

public class VendasDAO {
    private Connection connection;
    private List<Vendas> vendas;

    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS vendas_lojavendas (VALOR_VENDA DOUBLE,"
                + "VEICULO_VENDIDO VARCHAR(255), DATA_VENDA DATE, CPF_COMPRADOR VARCHAR(255),"
                + "CEP_COMPRADOR VARCHAR(255))";

        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de vendas criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de vendas: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public List<Vendas> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        vendas = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM vendas_lojavendas");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas venda = new Vendas(
                    rs.getDouble("valor_venda"),
                    rs.getString("veiculo_vendido"),
                    rs.getDate("data_venda"),
                    rs.getString("cpf_comprador"),
                    rs.getString("cep_comprador")
                );
                vendas.add(venda);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return vendas;
    }

    public void cadastrar(double valorVenda, String veiculoVendido, Date dataVenda, String cpfComprador, String cepComprador) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO vendas_lojavendas (valor_venda, veiculo_vendido, data_venda, cpf_comprador, cep_comprador)"
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = connection.prepareStatement(sql);

            stmt.setDouble(1, valorVenda);
            stmt.setString(2, veiculoVendido);
            stmt.setDate(3, new java.sql.Date(dataVenda.getTime()));
            stmt.setString(4, cpfComprador);
            stmt.setString(5, cepComprador);

            stmt.executeUpdate();
            System.out.println("Venda cadastrada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar venda no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void atualizar(double valorVenda, String veiculoVendido, Date dataVenda, String cpfComprador, String cepComprador) {
        PreparedStatement stmt = null;
        String sql = "UPDATE vendas_lojavendas SET valor_venda = ?, veiculo_vendido = ?, data_venda = ?, cpf_comprador = ?, cep_comprador = ?"
                + "WHERE cpf_comprador = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, valorVenda);
            stmt.setString(2, veiculoVendido);
            stmt.setDate(3, new java.sql.Date(dataVenda.getTime()));
            stmt.setString(4, cpfComprador);
            stmt.setString(5, cepComprador);

            stmt.executeUpdate();
            System.out.println("Venda atualizada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void apagar(String cpfComprador) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM vendas_lojavendas WHERE cpf_comprador = ?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpfComprador);

            stmt.executeUpdate();
            System.out.println("Venda apagada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar venda no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
