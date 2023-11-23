package View;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.ClientesControl;
import Controller.ClientesDAO;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Clientes;

public class ClientesPainel extends JPanel {
    // Atributos (componentes)
    private JButton cadastrar, apagar, editar;
    private JTextField clienteNomeField, clienteEmailField, clienteCpfField, clienteRgField, clienteRendaField,
            clientePretensaoField;
    private List<Clientes> clientes;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor (GUI-JPanel)
    public ClientesPainel() {
        super();

        // entrada de dados
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Cadastro Clientes"));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Nome"));
        clienteNomeField = new JTextField(20);
        inputPanel.add(clienteNomeField);
        inputPanel.add(new JLabel("E-mail"));
        clienteEmailField = new JTextField(20);
        inputPanel.add(clienteEmailField);
        inputPanel.add(new JLabel("CPF"));
        clienteCpfField = new JTextField(20);
        inputPanel.add(clienteCpfField);
        inputPanel.add(new JLabel("RG"));
        clienteRgField = new JTextField(20);
        inputPanel.add(clienteRgField);
        inputPanel.add(new JLabel("Renda Fixa"));
        clienteRendaField = new JTextField(20);
        inputPanel.add(clienteRendaField);
        inputPanel.add(new JLabel("Pretensão"));
        clientePretensaoField = new JTextField(20);
        inputPanel.add(clientePretensaoField);
        add(inputPanel);

        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(editar = new JButton("Editar"));
        botoes.add(apagar = new JButton("Apagar"));
        add(botoes);

        // tabela de clientes
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "E-mail", "CPF", "RG",
                "Renda Fixa", "Pretensão" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);
        table.setCellSelectionEnabled(false);

        // Cria o banco de dados caso não tenha sido criado
        new ClientesDAO().criaTabela();

        // incluindo elementos do banco na criação do painel
        atualizarTabela();

        // tratamento de Eventos
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    clienteNomeField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    clienteEmailField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    clienteCpfField.setText((String) table.getValueAt(linhaSelecionada, 2));
                    clienteRgField.setText((String) table.getValueAt(linhaSelecionada, 3));
                    clienteRendaField.setText((String) table.getValueAt(linhaSelecionada, 4));
                    clientePretensaoField.setText((String) table.getValueAt(linhaSelecionada, 5));
                }
            }
        });

        // Cria um objeto operacoes da classe ClientesControl para executar operações no
        // banco de dados
        ClientesControl operacoes = new ClientesControl();


        // Configura a ação do botão "cadastrar" para adicionar um novo registro no
        // banco de dados
        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.cadastrar(clienteNomeField.getText(), clienteEmailField.getText(), clienteCpfField.getText(),
                        clienteRgField.getText(), clienteRendaField.getText(), clientePretensaoField.getText());

                // Limpa os campos de entrada após a operação de cadastro
                clienteNomeField.setText("");
                clienteEmailField.setText("");
                clienteCpfField.setText("");
                clienteRgField.setText("");
                clienteRendaField.setText("");
                clientePretensaoField.setText("");
            }
        });

        // Configura a ação do botão "editar" para atualizar um registro no banco de
        // dados
        editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.atualizar(clienteNomeField.getText(), clienteEmailField.getText(), clienteCpfField.getText(),
                        clienteRgField.getText(), clienteRendaField.getText(), clientePretensaoField.getText());

                // Limpa os campos de entrada após a operação de atualização
                clienteNomeField.setText("");
                clienteEmailField.setText("");
                clienteCpfField.setText("");
                clienteRgField.setText("");
                clienteRendaField.setText("");
                clientePretensaoField.setText("");
            }
        });

        // Configura a ação do botão "apagar" para excluir um registro no banco de dados
        apagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.apagar(clienteCpfField.getText());

                // Limpa os campos de entrada após a operação de exclusão
                clienteNomeField.setText("");
                clienteEmailField.setText("");
                clienteCpfField.setText("");
                clienteRgField.setText("");
                clienteRendaField.setText("");
                clientePretensaoField.setText("");
            }
        });
    }

    // atualizar Tabela de Clientes com o Banco de Dados
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        clientes = new ClientesDAO().listarTodos();
        for (Clientes cliente : clientes) {
            tableModel.addRow(new Object[] { cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getRg(),
                    cliente.getRendaFixa(), cliente.getPretensao() });
        }
    }
}
