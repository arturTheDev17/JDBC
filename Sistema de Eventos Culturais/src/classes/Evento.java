package classes;

import java.time.LocalDate;

public class Evento {
    private int id;
    private String nome;
    private String local;
    private LocalDate data;
    private String descricao;

    public Evento(int id, String nome, String local, LocalDate data, String descricao) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.data = data;
        this.descricao = descricao;
    }
    public Evento(String nome, String local, LocalDate data, String descricao) {
        this.nome = nome;
        this.local = local;
        this.data = data;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return """
                Evento %s de id: %d
                Local: %s
                Data: %s/%d/%d
                Descrição: %s
                
                """.formatted( this.nome ,
                this.id ,
                this.local  ,
                this.data.getMonth().getValue() ,
                this.data.getDayOfMonth() ,
                this.data.getYear() , this.descricao );
    }
}
