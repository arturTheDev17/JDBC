public class Item {
    private int id;
    private String nome;

    public Item(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Item(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return """
                Nome do item: %s
                ID%d
                """.formatted( getNome() , getId() );
    }
}
