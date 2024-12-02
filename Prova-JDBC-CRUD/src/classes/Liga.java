package classes;

import classes.Clube;

import java.util.ArrayList;
import java.util.List;

public class Liga {
    private int idLiga;
    private String nome;
    private int anoFundacao;
    private List<Clube> clubes;

    public Liga(int idLiga, String nome, int anoFundacao) {
        this.idLiga = idLiga;
        this.nome = nome;
        this.anoFundacao = anoFundacao;
        this.clubes = new ArrayList<>();
    }

    public Liga(String nome, int anoFundacao) {
        this.nome = nome;
        this.anoFundacao = anoFundacao;
        this.clubes = new ArrayList<>();
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public List<Clube> getClubes() {
        return clubes;
    }

    public void setClubes(List<Clube> clubes) {
        this.clubes = clubes;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "idLiga=" + idLiga +
                ", nome='" + nome + '\'' +
                ", anoFundacao=" + anoFundacao +
                '}';
    }
}
