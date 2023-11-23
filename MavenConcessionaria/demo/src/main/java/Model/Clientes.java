package Model;

public class Clientes {
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private String rendaFixa;
    private String pretensao;

    // Construtor
    public Clientes(String nome, String email, String cpf, String rg, String rendaFixa, String pretensao) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.rendaFixa = rendaFixa;
        this.pretensao = pretensao;
    }

    // Métodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRendaFixa() {
        return rendaFixa;
    }

    public void setRendaFixa(String rendaFixa) {
        this.rendaFixa = rendaFixa;
    }

    public String getPretensao() {
        return pretensao;
    }

    public void setPretensao(String pretensao) {
        this.pretensao = pretensao;
    }

    // Outros métodos, se necessário

    @Override
    public String toString() {
        return "Clientes [nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", rg=" + rg
                + ", rendaFixa=" + rendaFixa + ", pretensao=" + pretensao + "]";
    }
}
