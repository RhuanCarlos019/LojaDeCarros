package Model;

public class Carro {

    // Atributos
    private String marca;
    private String modelo;
    private String ano;
    private String preco;
    private String cor;
    private String placa;

    // Construtor
    public Carro(String marca, String modelo, String ano, String preco, String cor, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.cor = cor;
        this.placa = placa;
    }

    // Métodos de acesso e modificação para Marca
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Métodos de acesso e modificação para Modelo
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Métodos de acesso e modificação para Ano
    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    // Métodos de acesso e modificação para Preço
    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    // Métodos de acesso e modificação para Cor
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    // Métodos de acesso e modificação para Placa
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    // Método adicional para exibir informações detalhadas do carro
    public void exibirDetalhes() {
        System.out.println("Detalhes do Carro:");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Preço: " + preco);
        System.out.println("Cor: " + cor);
        System.out.println("Placa: " + placa);
    }
}
