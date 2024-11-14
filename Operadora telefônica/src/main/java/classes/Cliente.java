package classes;

public class Cliente {
    private int id; //(inteiro, chave primária, autoincremento)
    private String nome; //(texto)
    private String email; //(texto)
    private String telefone; //(texto)
    private Plano plano; //(inteiro, chave estrangeira para `Classes.Plano`)

    public Cliente(int id, String nome, String email, String telefone, Plano plano) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.plano = plano;
    }

    public Cliente(String nome, String email, String telefone, Plano plano) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.plano = plano;
    }

    public int getId() {
        return id;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    @Override
    public String toString() {
        return """
                id: #%d
                Nome: %s
                E-mail: %s
                Telefone: +55 (47) %s
                Nome do plano: %s
                Valor: R$%.2f

                """.formatted( id , nome , email ,
                telefone , getPlano().getNome() , getPlano().getValor() );
    }
}
