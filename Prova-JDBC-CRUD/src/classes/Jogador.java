package classes;

import classes.Clube;

public class Jogador {
    private int idJogador;
    private Clube clube;
    private int idade;
    private String nome;
    private String posicao;

    public Jogador(Clube clube, int idade, String nome, String posicao) {
        this.clube = clube;
        this.idade = idade;
        this.nome = nome;
        this.posicao = posicao;
    }

    public Jogador(int idJogador, Clube clube, int idade, String nome, String posicao) {
        this.idJogador = idJogador;
        this.clube = clube;
        this.idade = idade;
        this.nome = nome;
        this.posicao = posicao;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "idJogador=" + idJogador +
                ", clube=" + clube +
                ", idade=" + idade +
                ", nome='" + nome + '\'' +
                ", posicao='" + posicao + '\'' +
                '}' + "\n";
    }
}
