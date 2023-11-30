package Controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.Clientes;
import DAO.ClientesDAO;

public class ClientesControl {

    private List<Clientes> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    public ClientesControl(List<Clientes> clientes, DefaultTableModel tableModel, JTable table) {
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Atualiza a tabela com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        clientes = new ClientesDAO().listarTodos();

        for (Clientes cliente : clientes) {
            tableModel.addRow(new Object[] { cliente.getNome(), cliente.getCpf(),
                    cliente.getTelefone(), cliente.getEmail(), cliente.getEndereco() });
        }
    }

    // Exibe uma mensagem de ação concluída
    private void acaoFeita(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Ação Concluída", JOptionPane.INFORMATION_MESSAGE);
    }

    // Valida o telefone como número
    private boolean verificarTel(String telefone) {
        try {
            Long.parseLong(telefone); // Alterado para Long para suportar números maiores
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Valida o CPF como número
    private boolean verificarCPF(String cpf) {
        try {
            Long.parseLong(cpf); // Alterado para Long para suportar números maiores
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Verifica se o nome contém apenas caracteres alfabéticos
    private boolean verificarNome(String nome) {
        return nome.chars().allMatch(Character::isLetter);
    }

    // Cadastra um novo cliente no banco de dados
    public void cadastrar(String nome, String cpf, String telefone, String email, String endereco) {
        if (!verificarCPF(cpf) || !verificarTel(telefone) || !verificarNome(nome)) {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new ClientesDAO().cadastrar(nome, cpf, telefone, email, endereco);
        atualizarTabela();
        acaoFeita("Cliente cadastrado!");
    }

    // Atualiza os dados de um cliente no banco de dados
    public void atualizar(String nome, String cpf, String telefone, String email, String endereco) {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente atualizar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            if (!verificarCPF(cpf) || !verificarTel(telefone) || !verificarNome(nome)) {
                JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            new ClientesDAO().atualizar(nome, cpf, telefone, email, endereco);
            atualizarTabela();
            acaoFeita("Cadastro atualizado com sucesso!");
        }
    }

    // Apaga um cliente do banco de dados
    public void apagar(String cpf) {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            new ClientesDAO().apagar(cpf);
            atualizarTabela();
            acaoFeita("Cadastro apagado com sucesso!");
        }
    }
}
