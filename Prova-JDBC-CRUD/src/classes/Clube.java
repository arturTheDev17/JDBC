package classes;

import java.util.ArrayList;
import java.util.List;

public class Clube {
    private int idClube;
    private String nome;
    private List<Liga> ligas;
    private List<Jogador> jogadores;
    private Treinador treinador;
    private String dataFundacao;

    public Clube(String nome, String dataFundacao) {
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.ligas = new ArrayList<>();
        this.jogadores = new ArrayList<>();
    }

    public Clube(int idClube, String nome, String dataFundacao , Treinador treinador) {
        this.idClube = idClube;
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.treinador = treinador;
        this.ligas = new ArrayList<>();
        this.jogadores = new ArrayList<>();
    }

    public Clube(int idClube, String nome, String dataFundacao ) {
        this.idClube = idClube;
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.ligas = new ArrayList<>();
        this.jogadores = new ArrayList<>();
    }

    public int getIdClube() {
        return idClube;
    }

    public void setIdClube(int idClube) {
        this.idClube = idClube;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Liga> getLigas() {
        return ligas;
    }

    public void setLigas(List<Liga> ligas) {
        this.ligas = ligas;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return "Clube{" +
                "idClube=" + idClube +
                ", nome='" + nome + '\'' +
                ", dataFundacao='" + dataFundacao + '\'' +
                '}';
    }
}
