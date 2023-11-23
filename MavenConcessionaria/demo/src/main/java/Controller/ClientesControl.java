package Controller;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Clientes;

public class ClientesControl extends JPanel {
    private JTextField clienteNomeField;
    private JTextField clienteEmailField;
    private JTextField clienteCpfField;
    private JTextField clienteRgField;
    private JTextField clienteRendaField;
    private JTextField clientePretensaoField;
    private JButton cadastrarClienteButton;
    private JButton editarClienteButton;
    private JButton apagarClienteButton;

    private List<Clientes> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    public ClientesControl(List<Clientes> clientes, DefaultTableModel tableModel, JTable table) {
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;

        JLabel labelNome = new JLabel("Nome");
        JLabel labelEmail = new JLabel("E-mail");
        JLabel labelCpf = new JLabel("CPF");
        JLabel labelRg = new JLabel("RG");
        JLabel labelRenda = new JLabel("Renda Fixa");
        JLabel labelPretensao = new JLabel("Pretensão");

        clienteNomeField = new JTextField(20);
        clienteEmailField = new JTextField(20);
        clienteCpfField = new JTextField(20);
        clienteRgField = new JTextField(20);
        clienteRendaField = new JTextField(20);
        clientePretensaoField = new JTextField(20);

        cadastrarClienteButton = new JButton("Cadastrar");
        editarClienteButton = new JButton("Editar");
        apagarClienteButton = new JButton("Apagar");

        add(labelNome);
        add(clienteNomeField);
        add(labelEmail);
        add(clienteEmailField);
        add(labelCpf);
        add(clienteCpfField);
        add(labelRg);
        add(clienteRgField);
        add(labelRenda);
        add(clienteRendaField);
        add(labelPretensao);
        add(clientePretensaoField);
        add(cadastrarClienteButton);
        add(editarClienteButton);
        add(apagarClienteButton);

        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        editarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });

        apagarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apagarCliente();
            }
        });
    }

    public ClientesControl() {
    }

    private void cadastrarCliente() {
        String nome = clienteNomeField.getText();
        String email = clienteEmailField.getText();
        String cpf = clienteCpfField.getText();
        String rg = clienteRgField.getText();
        String rendaFixa = clienteRendaField.getText();
        String pretensao = clientePretensaoField.getText();

        if (validarCampos(nome, email, cpf, rg, rendaFixa, pretensao)) {
            cadastrar(nome, email, cpf, rg, rendaFixa, pretensao);
            limparCampos();
        } else {
            // Adicione lógica para tratar campos inválidos
            System.out.println("Campos inválidos");
        }
    }

    private void editarCliente() {
        String nome = clienteNomeField.getText();
        String email = clienteEmailField.getText();
        String cpf = clienteCpfField.getText();
        String rg = clienteRgField.getText();
        String rendaFixa = clienteRendaField.getText();
        String pretensao = clientePretensaoField.getText();

        if (validarCampos(nome, email, cpf, rg, rendaFixa, pretensao)) {
            atualizar(nome, email, cpf, rg, rendaFixa, pretensao);
            limparCampos();
        } else {
            // Adicione lógica para tratar campos inválidos
            System.out.println("Campos inválidos");
        }
    }

    private void apagarCliente() {
        String nome = clienteNomeField.getText();
        apagar(nome);
        limparCampos();
    }

    private void limparCampos() {
        clienteNomeField.setText("");
        clienteEmailField.setText("");
        clienteCpfField.setText("");
        clienteRgField.setText("");
        clienteRendaField.setText("");
        clientePretensaoField.setText("");
    }

    private boolean validarCampos(String nome, String email, String cpf, String rg, String rendaFixa, String pretensao) {
        // Adicione lógica para validar os campos, se necessário
        return true; // Altere conforme necessário
    }

    public void cadastrar(String nome, String email, String cpf, String rg, String rendaFixa, String pretensao) {
        // Adicione a lógica de cadastro aqui
        Clientes novoCliente = new Clientes(nome, email, cpf, rg, rendaFixa, pretensao);
        clientes.add(novoCliente);

        // Atualiza a tabela
        updateTable();
    }

    public void atualizar(String nome, String email, String cpf, String rg, String rendaFixa, String pretensao) {
        // Adicione a lógica de atualização aqui
        for (Clientes cliente : clientes) {
            if (cliente.getNome().equals(nome)) {
                cliente.setEmail(email);
                cliente.setCpf(cpf);
                cliente.setRg(rg);
                cliente.setRendaFixa(rendaFixa);
                cliente.setPretensao(pretensao);
                break;
            }
        }

        // Atualiza a tabela
        updateTable();
    }

    public void apagar(String nome) {
        // Adicione a lógica de exclusão aqui
        clientes.removeIf(cliente -> cliente.getNome().equals(nome));

        // Atualiza a tabela
        updateTable();
    }

    private void updateTable() {
        // Atualiza o modelo da tabela com os dados atualizados
        tableModel.setRowCount(0); // Limpa as linhas existentes

        for (Clientes cliente : clientes) {
            Object[] rowData = {cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getRg(), cliente.getRendaFixa(), cliente.getPretensao()};
            tableModel.addRow(rowData);
        }
    }
}
