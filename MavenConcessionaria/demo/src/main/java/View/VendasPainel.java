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

import Controller.VendasControl;
import Controller.VendasDAO;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Vendas;

public class VendasPainel extends JPanel {
    // Atributos (componentes)
    private JButton cadastrar, apagar, editar;
    private JTextField vendaClienteField, vendaProdutoField, vendaQuantidadeField, vendaValorField;
    private List<Vendas> vendas;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor (GUI-JPanel)
    public VendasPainel() {
        super();

        // entrada de dados
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Cadastro Vendas"));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Cliente"));
        vendaClienteField = new JTextField(20);
        inputPanel.add(vendaClienteField);
        inputPanel.add(new JLabel("Produto"));
        vendaProdutoField = new JTextField(20);
        inputPanel.add(vendaProdutoField);
        inputPanel.add(new JLabel("Quantidade"));
        vendaQuantidadeField = new JTextField(20);
        inputPanel.add(vendaQuantidadeField);
        inputPanel.add(new JLabel("Valor"));
        vendaValorField = new JTextField(20);
        inputPanel.add(vendaValorField);
        add(inputPanel);

        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(editar = new JButton("Editar"));
        botoes.add(apagar = new JButton("Apagar"));
        add(botoes);

        // tabela de vendas
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Cliente", "Produto", "Quantidade", "Valor" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);
        table.setCellSelectionEnabled(false);

        // Cria o banco de dados caso não tenha sido criado
        new VendasDAO().criaTabela();

        // incluindo elementos do banco na criação do painel
        atualizarTabela();

        // tratamento de Eventos
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    vendaClienteField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    vendaProdutoField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    vendaQuantidadeField.setText((String) table.getValueAt(linhaSelecionada, 2));
                    vendaValorField.setText((String) table.getValueAt(linhaSelecionada, 3));
                }
            }
        });

        // Cria um objeto operacoes da classe VendasControl para executar operações no
        // banco de dados
        VendasControl operacoes = new VendasControl(vendas, tableModel, table);

        // Configura a ação do botão "cadastrar" para adicionar um novo registro no
        // banco de dados
        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.cadastrar(vendaClienteField.getText(), vendaProdutoField.getText(),
                        vendaQuantidadeField.getText(), vendaValorField.getText());

                // Limpa os campos de entrada após a operação de cadastro
                vendaClienteField.setText("");
                vendaProdutoField.setText("");
                vendaQuantidadeField.setText("");
                vendaValorField.setText("");
            }
        });

        // Configura a ação do botão "editar" para atualizar um registro no banco de
        // dados
        editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.atualizar(vendaClienteField.getText(), vendaProdutoField.getText(),
                        vendaQuantidadeField.getText(), vendaValorField.getText());

                // Limpa os campos de entrada após a operação de atualização
                vendaClienteField.setText("");
                vendaProdutoField.setText("");
                vendaQuantidadeField.setText("");
                vendaValorField.setText("");
            }
        });

        // Configura a ação do botão "apagar" para excluir um registro no banco de dados
        apagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacoes.apagar(vendaClienteField.getText());

                // Limpa os campos de entrada após a operação de exclusão
                vendaClienteField.setText("");
                vendaProdutoField.setText("");
                vendaQuantidadeField.setText("");
                vendaValorField.setText("");
            }
        });
    }

    // atualizar Tabela de Vendas com o Banco de Dados
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        vendas = new VendasDAO().listarTodos();
        for (Vendas venda : vendas) {
            tableModel.addRow(new Object[] { venda.getCliente(), venda.getProduto(), venda.getQuantidade(),
                    venda.getValor() });
        }
    }
}
