package classes.banco;

import classes.Clube;
import classes.Treinador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreinadorCRUD {

    public static void createTreinador( Treinador treinador ) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_treinador ( nome , idClube , experiencia ) VALUES ( ? , ? , ? )" );
            ps.setString( 1 , treinador.getNome() );
            ps.setInt( 2 , treinador.getClube().getIdClube() );
            ps.setInt( 3 , treinador.getExperiencia() );
            ps.execute();
            System.out.println( "treinador criado" );
        } catch (SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static Treinador readTreinador(String nome) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_treinador WHERE nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Treinador( rs.getInt( "idTreinador" ) , rs.getString( "nome" ) , ClubeCRUD.readClube( rs.getInt( "idClube" ) ) , rs.getInt( "experiencia" ) );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new RuntimeException( "nao achou" );
    }

    public static Treinador readTreinador(int idClube) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_treinador WHERE idClube = ?");
            ps.setInt(1, idClube);
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Treinador( rs.getInt( "idTreinador" ) , rs.getString( "nome" ) , rs.getInt( "experiencia" ) );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new RuntimeException( "nao achou" );
    }

    public static List<Treinador> readAllTreinadores() {
        List<Treinador> treinadores = new ArrayList<>();
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_treinador");
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                treinadores.add( new Treinador( rs.getInt( "idTreinador" ) , rs.getString( "nome" ) , ClubeCRUD.readClube( rs.getInt( "idClube" )) , rs.getInt( "experiencia" ) ) );
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        return treinadores;
    }

    public static void updateTreinador(Treinador treinador) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE tb_treinador SET nome = ? , experiencia = ? , idClube = ? WHERE idTreinador = ?");
            ps.setString( 1 , treinador.getNome() );
            ps.setInt( 2 , treinador.getExperiencia() );
            ps.setInt(3, treinador.getClube().getIdClube());
            ps.setInt( 4 , treinador.getIdTreinador() );
            ps.execute();
            System.out.println( "treinador atualizado" );
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static void removeTreinador(int id) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_treinador WHERE tb_treinador.idTreinador = ?");
            ps.setInt(1, id);
            ps.execute();
            System.out.println( "treinador removido" );

        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }
}
