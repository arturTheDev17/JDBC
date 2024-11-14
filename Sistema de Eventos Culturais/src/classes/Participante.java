package classes;

public class Participante {
    private int id;
    private String nome;
    private String email;

    public Participante(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Participante(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return """
                Participante %s de id: %d
                E-mail: %s
                
                """.formatted( this.nome ,
                this.id ,
                this.email );
    }
}
