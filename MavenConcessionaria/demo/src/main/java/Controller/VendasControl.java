package Controller;

import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Vendas;

public class VendasControl {
    private List<Vendas> vendas;
    private DefaultTableModel tableModel;
    private JTable table;

    public VendasControl(List<Vendas> vendas, DefaultTableModel tableModel, JTable table) {
        this.vendas = vendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        vendas = new VendasDAO().listarTodos();
        for (Vendas venda : vendas) {
            tableModel.addRow(new Object[] { venda.getValorVenda(), venda.getVeiculoVendido(),
                venda.getDataVenda(), venda.getCpfComprador(), venda.getCepComprador() });
        }
    }

    public void cadastrar(double valorVenda, String veiculoVendido, Date dataVenda, String cpfComprador, String cepComprador) {
        new VendasDAO().cadastrar(valorVenda, veiculoVendido, dataVenda, cpfComprador, cepComprador);
        atualizarTabela();
    }

    public void atualizar(double valorVenda, String veiculoVendido, Date dataVenda, String cpfComprador, String cepComprador) {
        new VendasDAO().atualizar(valorVenda, veiculoVendido, dataVenda, cpfComprador, cepComprador);
        atualizarTabela();
    }

    public void apagar(String cpfComprador) {
        new VendasDAO().apagar(cpfComprador);
        atualizarTabela();
    }

    public void cadastrar(String text, String text2, String text3, String text4) {
    }

    public void atualizar(String text, String text2, String text3, String text4) {
    }
}
