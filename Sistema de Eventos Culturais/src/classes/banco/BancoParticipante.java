package classes.banco;

import classes.Evento;
import classes.Participante;

import java.sql.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BancoParticipante {
    public static void adicionarParticipante( Participante participante ) {

        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_participante " +
                    "( nome , email ) VALUES ( ? , ?)" );

            ps.setString( 1 , participante.getNome() );
            ps.setString( 2 , participante.getEmail() );

            ps.execute();
        }catch ( SQLIntegrityConstraintViolationException email ) {
            System.err.println( "Esse e-mail já está cadastrado!" );
        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

    }
    public static void removerParticipante( int id ) throws SQLException {

        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "DELETE FROM tb_participante WHERE id = ?" );

            ps.setInt( 1 , id );
            ps.execute();

        }
    }
    public static Participante buscarParticipantePorId( int id ) {

        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_participante WHERE id = ?" );

            ps.setInt( 1 , id );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                return new Participante(
                        rs.getInt( "id" ) ,
                        rs.getString( "nome" ) ,
                        rs.getString( "email"));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        throw new RuntimeException( "Nenhum participante com esse id encontrado..." );

    }
    public static ArrayList<Participante> buscarTodos() {
        ArrayList<Participante> arrayList = new ArrayList<>();
        try( Connection connection = BancoConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_participante" );

            ResultSet rs = ps.executeQuery();


            while ( rs.next() ) {
                arrayList.add( new Participante(
                        rs.getInt( "id" ) ,
                        rs.getString( "nome" ) ,
                        rs.getString( "email")));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        return arrayList;
    }
    public static Participante buscarParticipantePorEmail( String email ) {
        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_participante WHERE email = ?" );

            ps.setString( 1 , email );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                return new Participante(
                        rs.getInt( "id" ) ,
                        rs.getString( "nome" ) ,
                        rs.getString( "email"));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        throw new NoSuchElementException( "Nenhum participante com esse nome encontrado..." );

    }
}
