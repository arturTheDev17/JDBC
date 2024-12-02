package classes;

import classes.Clube;
import classes.banco.BancoConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Treinador {
    private int idTreinador;
    private String nome;
    private Clube clube;
    private int experiencia;

    public Treinador(String nome, Clube clube, int experiencia) {
        this.nome = nome;
        this.clube = clube;
        this.experiencia = experiencia;
    }

    public Treinador(int idTreinador , String nome, int experiencia) {
        this.idTreinador = idTreinador;
        this.nome = nome;
        this.experiencia = experiencia;
    }


    public Treinador(int idTreinador, String nome, Clube clube, int experiencia) {
        this.idTreinador = idTreinador;
        this.nome = nome;
        this.clube = clube;
        this.experiencia = experiencia;
    }

    public int getIdTreinador() {
        return idTreinador;
    }

    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "Treinador{" +
                "idTreinador=" + idTreinador +
                ", nome='" + nome + '\'' +
                ", clube=" + clube.getNome() +
                ", experiencia=" + experiencia +
                '}';
    }
}
