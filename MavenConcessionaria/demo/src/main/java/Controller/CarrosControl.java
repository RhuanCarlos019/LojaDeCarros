package Controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.Carros;
import DAO.CarrosDAO;

public class CarrosControl {

    private List<Carros> carros;
    private DefaultTableModel tableModel;
    private JTable table;

    public CarrosControl(List<Carros> carros, DefaultTableModel tableModel, JTable table) {
        this.carros = carros;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Atualiza a tabela com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        carros = new CarrosDAO().listarTodos();

        for (Carros carro : carros) {
            tableModel.addRow(new Object[] { carro.getMarca(), carro.getModelo(),
                    carro.getAno(), carro.getPreco(), carro.getCor(), carro.getPlaca() });
        }
    }

    // Exibe uma mensagem de ação concluída
    private void acaoFeita(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Ação Concluída", JOptionPane.INFORMATION_MESSAGE);
    }

    // Valida o ano como número
    private boolean verificarAno(String ano) {
        try {
            Integer.parseInt(ano);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Valida o valor como número
    private boolean verificarPreco(String preco) {
        try {
            Double.parseDouble(preco);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Valida se a cor é um valor alfabético
    private boolean verificarCor(String cor) {
        return cor.chars().noneMatch(Character::isDigit);
    }

    // Cadastra um novo carro no banco de dados
    public void cadastrar(String marca, String modelo, String ano, String preco, String cor, String placa) {
        if (!verificarAno(ano) || !verificarPreco(preco) || !verificarCor(cor)) {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new CarrosDAO().cadastrar(marca, modelo, ano, preco, cor, placa);
        atualizarTabela();
        acaoFeita("Carro cadastrado!");
    }

    // Atualiza os dados de um carro no banco de dados
    public void atualizar(String marca, String modelo, String ano, String preco, String cor, String placa) {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente atualizar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            if (!verificarAno(ano) || !verificarPreco(preco) || !verificarCor(cor)) {
                JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            new CarrosDAO().atualizar(marca, modelo, ano, preco, cor, placa);
            atualizarTabela();
            acaoFeita("Cadastro atualizado com sucesso!");
        }
    }

    // Apaga um carro do banco de dados
    public void apagar(String placa) {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            new CarrosDAO().apagar(placa);
            atualizarTabela();
            acaoFeita("Cadastro apagado com sucesso!");
        }
    }
}
