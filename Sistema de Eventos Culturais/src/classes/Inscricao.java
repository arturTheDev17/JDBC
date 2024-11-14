package classes;

public class Inscricao {
    private int id;
    private Evento evento;
    private Participante participante;

    public Inscricao(int id, Evento evento, Participante participante) {
        this.id = id;
        this.evento = evento;
        this.participante = participante;
    }
    public Inscricao( Evento evento, Participante participante) {
        this.evento = evento;
        this.participante = participante;
    }

    public int getId() {
        return id;
    }

    public Evento getEvento() {
        return evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    @Override
    public String toString() {
        return """
                Inscrição de id: %d
                Nome do evento: %s
                Nome do participante: %s
                """.formatted( this.id ,
                this.evento.getNome(),
                this.participante.getNome());
    }
}
