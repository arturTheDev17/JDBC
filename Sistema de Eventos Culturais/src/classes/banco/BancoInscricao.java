package classes.banco;

import classes.Evento;
import classes.Inscricao;
import classes.Participante;

import java.sql.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BancoInscricao {
    public static void inscreverParticipante( int eventoId , int participanteId ) {
        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_inscricao " +
                    "( idParticipante ,idEvento ) VALUES ( ? , ? )" );

            ps.setInt( 1 , participanteId );
            ps.setInt( 2 , eventoId );

            ps.execute();
        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

    }
    public static void removerInscricao( int id ) {

        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "DELETE FROM tb_inscricao WHERE id = ?" );

            ps.setInt( 1 , id );
            ps.execute();

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }
    }
    public static Inscricao buscarInscricaoPorId(int id ) {

        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_inscricao WHERE id = ?" );

            ps.setInt( 1 , id );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                return new Inscricao(
                        rs.getInt( "id" ) ,
                        BancoEvento.buscarEventoPorId(rs.getInt( "idEvento" )),
                        BancoParticipante.buscarParticipantePorId(rs.getInt( "idParticipante" )));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        throw new RuntimeException( "Nenhum evento com esse id encontrado..." );

    }
    public static ArrayList<Inscricao> buscarTodas() {
        ArrayList<Inscricao> arrayList = new ArrayList<>();
        try( Connection connection = BancoConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_inscricao" );

            ResultSet rs = ps.executeQuery();


            while ( rs.next() ) {
                arrayList.add( new Inscricao(
                        rs.getInt( "id" ) ,
                        BancoEvento.buscarEventoPorId(rs.getInt( "idEvento" )),
                        BancoParticipante.buscarParticipantePorId
                                (rs.getInt( "idParticipante" ))));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        return arrayList;
    }
    public static Inscricao buscarInscricaoPorEmailDoParticipante( String emailParticipante ) {
        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement
                    ( "select * from tb_inscricao left join " +
                            "tb_participante tp on tp.id = tb_inscricao.idParticipante " +
                            "where email = ?" );

            ps.setString( 1 , emailParticipante );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                return new Inscricao(
                        rs.getInt( "tb_inscricao.id" ) ,
                        BancoEvento.buscarEventoPorId(rs.getInt( "idEvento" )),
                        BancoParticipante.buscarParticipantePorEmail(
                                rs.getString( "email" )));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        throw new NoSuchElementException( "Nenhuma inscricao com esse email encontrado..." );

    }
    public static Inscricao buscarInscricaoPorNomeDoEvento( String nomeEvento ) {
        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement
                    ( "select * from tb_inscricao left join" +
                            " tb_eventos te on te.id = tb_inscricao.idEvento " +
                            "where nome = ?" );

            ps.setString( 1 , nomeEvento );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                return new Inscricao(
                        rs.getInt( "tb_inscricao.id" ) ,
                        BancoEvento.buscarEventoPorNome(rs.getString( "nome" )),
                        BancoParticipante.buscarParticipantePorId(
                                rs.getInt( "idParticipante" )));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        throw new RuntimeException( "Nenhuma inscricao com esse nome encontrada..." );

    }
}
