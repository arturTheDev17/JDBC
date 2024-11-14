package classes.banco;

import classes.Evento;

import java.lang.classfile.Attribute;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BancoEvento {
    public static void adicionarEvento( Evento evento ) {

        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_eventos " +
                    "( nome , local , data , descricao ) VALUES (? , ? , ? , ?)" );

            ps.setString( 1 , evento.getNome() );
            ps.setString( 2 , evento.getLocal() );
            ps.setDate( 3 , Date.valueOf(evento.getData()));
            ps.setString( 4 , evento.getDescricao() );

            ps.execute();
        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

    }
    public static void removerEvento( int id ) throws SQLException {

        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "DELETE FROM tb_eventos WHERE id = ?" );

            ps.setInt( 1 , id );
            ps.execute();

        }
    }
    public static Evento buscarEventoPorId( int id ) {

        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_eventos WHERE id = ?" );

            ps.setInt( 1 , id );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                return new Evento(
                        rs.getInt( "id" ) ,
                        rs.getString( "nome" ) ,
                        rs.getString( "local") ,
                        rs.getDate( "data" ).toLocalDate(),
                        rs.getString( "descricao" ));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        throw new RuntimeException( "Nenhum evento com esse id encontrado..." );

    }
    public static ArrayList<Evento> buscarTodos() {
        ArrayList<Evento> arrayList = new ArrayList<>();
        try( Connection connection = BancoConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_eventos" );

            ResultSet rs = ps.executeQuery();


            while ( rs.next() ) {
                arrayList.add( new Evento(
                        rs.getInt( "id" ) ,
                        rs.getString( "nome" ) ,
                        rs.getString( "local") ,
                        rs.getDate( "data" ).toLocalDate(),
                        rs.getString( "descricao" )));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        return arrayList;
    }
    public static Evento buscarEventoPorNome( String nome ) {
        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_eventos WHERE nome = ?" );

            ps.setString( 1 , nome );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                return new Evento(
                        rs.getInt( "id" ) ,
                        rs.getString( "nome" ) ,
                        rs.getString( "local") ,
                        rs.getDate( "data" ).toLocalDate(),
                        rs.getString( "descricao" ));
            }

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        throw new NoSuchElementException( "Nenhum evento com esse nome encontrado..." );

    }
    public static ArrayList<Evento> buscarTodosEventosPorUmNome( String nome ) {
        try (Connection connection = BancoConnection.getConnection();) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_eventos WHERE nome = ?" );

            ps.setString( 1 , nome );
            ResultSet rs = ps.executeQuery();

            ArrayList<Evento> arrayList = new ArrayList<>();

            while ( rs.next() ) {
                arrayList.add( new Evento(
                        rs.getInt( "id" ) ,
                        rs.getString( "nome" ) ,
                        rs.getString( "local") ,
                        rs.getDate( "data" ).toLocalDate(),
                        rs.getString( "descricao" )));
            }

            return arrayList;

        } catch (SQLException e) {
            System.err.println( "DEU RUIM!!" );
            System.out.println( e.getMessage() );
        }

        throw new RuntimeException( "Nenhum evento com esse nome encontrado..." );

    }
}

