package View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Controller.CarrosControl;
import View.ClientesPainel; // Importe a classe ClientesPainel
import Controller.ClientesControl;

public class JanelaPrincipal extends JFrame {
    // Criação do JTabbedPane para incluir as abas
    private JTabbedPane jTPane;

    public JanelaPrincipal() {
        jTPane = new JTabbedPane();
        add(jTPane);

        // Criando as abas
        // Aba 1 - Carros
        CarrosPainel tab1 = new CarrosPainel();
        jTPane.addTab("Carros", tab1);

        // Aba 2 - Clientes
        ClientesControl tab2 = new ClientesControl();
        jTPane.addTab("Clientes", tab2);

         // Aba 3 - Vendas
        VendasPainel tab3 = new VendasPainel();
        jTPane.addTab("Clientes", tab2);

        setBounds(100, 100, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Método para tornar a janela visível
    public void run() {
        this.setVisible(true);
    }
}
