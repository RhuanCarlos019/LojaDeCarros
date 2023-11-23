package Model;

import java.util.Date;

public class Vendas {
    private double valorVenda;
    private String veiculoVendido;
    private Date dataVenda;
    private String cpfComprador;
    private String cepComprador;

    // Construtor
    public Vendas(double valorVenda, String veiculoVendido, Date dataVenda, String cpfComprador, String cepComprador) {
        this.valorVenda = valorVenda;
        this.veiculoVendido = veiculoVendido;
        this.dataVenda = dataVenda;
        this.cpfComprador = cpfComprador;
        this.cepComprador = cepComprador;
    }

    // Métodos getters e setters
    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getVeiculoVendido() {
        return veiculoVendido;
    }

    public void setVeiculoVendido(String veiculoVendido) {
        this.veiculoVendido = veiculoVendido;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getCpfComprador() {
        return cpfComprador;
    }

    public void setCpfComprador(String cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    public String getCepComprador() {
        return cepComprador;
    }

    public void setCepComprador(String cepComprador) {
        this.cepComprador = cepComprador;
    }

    public Object getCliente() {
        return null;
    }

    public Object getProduto() {
        return null;
    }

    public Object getQuantidade() {
        return null;
    }

    public Object getValor() {
        return null;
    }

    // Outros métodos, se necessário
}
