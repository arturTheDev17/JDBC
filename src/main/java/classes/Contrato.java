package classes;

import java.time.LocalDate;

public class Contrato {
    private int id; //(inteiro, chave primária, autoincremento)
    private Plano plano; //(inteiro, chave estrangeira única para `Classes.Plano`)
    private String termos; //(texto)
    private LocalDate data_inicio; //(data ou texto)
    private LocalDate data_fim; //(data ou texto)


    public Contrato(int id, Plano plano, String termos, LocalDate data_inicio, LocalDate data_fim) {
        this.id = id;
        this.plano = plano;
        this.termos = termos;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public Contrato(String termos, LocalDate data_inicio, LocalDate data_fim) {
        this.termos = termos;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public int getId() {
        return id;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public String getTermos() {
        return termos;
    }

    public void setTermos(String termos) {
        this.termos = termos;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    @Override
    public String toString() {
        return """
                Contrato #%d
                Termos: %s
                Data de início: %s/%d/%d
                Data de fim: %s/%d/%d
                Plano do contrato: %s""".formatted(
                        this.id,
                        this.termos,
                        this.data_inicio.getMonth().getValue(),
                        this.data_inicio.getDayOfMonth(),
                        this.data_inicio.getYear(),
                        this.data_fim.getMonth().getValue(),
                        this.data_fim.getDayOfMonth(),
                        this.data_fim.getYear(),
                        this.plano.toString());
    }
}
