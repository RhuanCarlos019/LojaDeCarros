package Model;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;

public class Carros extends JPanel {
    // Atributos
    private JTextField carMarcaField;
    private JTextField carModeloField;
    private JTextField carAnoField;
    private JTextField carPlacaField;
    private JTextField carValorField;

    private JButton cadastrar;
    private JButton editar;
    private JButton apagar;

    private JTable table;
    private DefaultTableModel tableModel;

    // Construtor(GUI-JPanel)
    public Carros(String string, String string2, String string3, String string4, String string5) {
        super();

        // Entrada de dados
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Cadastro Carros"));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Marca"));
        carMarcaField = new JTextField(20);
        inputPanel.add(carMarcaField);
        inputPanel.add(new JLabel("Modelo"));
        carModeloField = new JTextField(20);
        inputPanel.add(carModeloField);
        inputPanel.add(new JLabel("Ano"));
        carAnoField = new JTextField(20);
        inputPanel.add(carAnoField);
        inputPanel.add(new JLabel("Placa"));
        carPlacaField = new JTextField(20);
        inputPanel.add(carPlacaField);
        inputPanel.add(new JLabel("Valor"));
        carValorField = new JTextField(20);
        inputPanel.add(carValorField);

        add(inputPanel);

        JPanel botoes = new JPanel();
        cadastrar = new JButton("Cadastrar");
        editar = new JButton("Editar");
        apagar = new JButton("Apagar");

        botoes.add(cadastrar);
        botoes.add(editar);
        botoes.add(apagar);

        add(botoes);

        // Tabela de carros
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Marca", "Modelo", "Ano", "Placa", "Valor" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);
    }

    public Object getMarca() {
        return null;
    }

    public Object getModelo() {
        return null;
    }

    public Object getAno() {
        return null;
    }

    public Object getPlaca() {
        return null;
    }

    public Object getValor() {
        return null;
    }
}
