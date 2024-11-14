package classes;

public class Plano {
    private int id; //(inteiro, chave primária, autoincremento)
    private String operadora; //(texto)
    private String nome; //(texto)
    private double quantidade_dados; //(decimal)
    private double quantidade_dados_bonus; // (decimal, opcional)
    private String beneficios; //(texto)
    private double valor; //(decimal)

    public Plano(String operadora, String nome, double quantidade_dados,
                 double quantidade_dados_bonus, String beneficios,
                 double valor) {
        this.operadora = operadora;
        this.nome = nome;
        this.quantidade_dados = quantidade_dados;
        this.quantidade_dados_bonus = quantidade_dados_bonus;
        this.beneficios = beneficios;
        this.valor = valor;
    }

    public Plano(int id, String operadora, String nome,
                 double quantidade_dados, double quantidade_dados_bonus,
                 String beneficios, double valor) {
        this.id = id;
        this.operadora = operadora;
        this.nome = nome;
        this.quantidade_dados = quantidade_dados;
        this.quantidade_dados_bonus = quantidade_dados_bonus;
        this.beneficios = beneficios;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade_dados() {
        return quantidade_dados;
    }

    public void setQuantidade_dados(double quantidade_dados) {
        this.quantidade_dados = quantidade_dados;
    }

    public double getQuantidade_dados_bonus() {
        return quantidade_dados_bonus;
    }

    public void setQuantidade_dados_bonus(double quantidade_dados_bonus) {
        this.quantidade_dados_bonus = quantidade_dados_bonus;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return """
                id: #%d
                Operadora: %s
                Nome: %s
                Quantidade de dados:  %.1f GB
                Quantidade de dados 'bônus': %.1f GB
                Benefícios: %s
                Valor: R$%.2f

                """.formatted( id , operadora , nome , quantidade_dados ,
                quantidade_dados_bonus , beneficios , valor );
    }
}
