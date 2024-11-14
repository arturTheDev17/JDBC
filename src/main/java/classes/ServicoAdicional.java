package classes;

public class ServicoAdicional {
    private int id; //(inteiro, chave primária, autoincremento)
    private String descricao; //(texto)
    private double custo_mensal; //(decimal)

    public ServicoAdicional(int id, String descricao, double custo_mensal) {
        this.id = id;
        this.descricao = descricao;
        this.custo_mensal = custo_mensal;
    }

    public ServicoAdicional(String descricao, double custo_mensal) {
        this.descricao = descricao;
        this.custo_mensal = custo_mensal;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto_mensal() {
        return custo_mensal;
    }

    public void setCusto_mensal(double custo_mensal) {
        this.custo_mensal = custo_mensal;
    }
}
