package classes.banco;

import classes.Clube;
import classes.Jogador;
import classes.Liga;
import classes.Treinador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubeCRUD {

    public static void createClube( Clube clube ) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_clube ( nome , dataFundacao ) VALUES ( ? , ? )" );
            ps.setString( 1 , clube.getNome() );
            ps.setString( 2 , clube.getDataFundacao() );
            ps.execute();
            System.out.println( "Novo clube criado" );
        } catch (SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static Clube readClube( String nome ) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clube WHERE nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Clube( rs.getInt( "idClube" ),
                        rs.getString( "nome" ),
                        rs.getString( "dataFundacao" ));
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        throw new RuntimeException( "nao achou" );
    }

    public static Clube readClube(int idClube) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clube WHERE idClube = ?");
            ps.setInt(1, idClube);
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Clube( rs.getInt( "idClube" ),
                        rs.getString( "nome" ),
                        rs.getString( "dataFundacao" ));
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        throw new RuntimeException( "nao achou" );
    }

    public static List<Clube> readAllClubes() {
        List<Clube> clubes = new ArrayList<>();
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clube");
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                clubes.add( new Clube( rs.getInt( "idClube" ),
                        rs.getString( "nome" ),
                        rs.getString( "dataFundacao" ) ));
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        return clubes;
    }

    public static void updateClube(Clube clube) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE tb_clube SET nome = ? , dataFundacao = ? WHERE idClube = ?");
            ps.setString( 1 , clube.getNome() );
            ps.setString( 2 , clube.getDataFundacao() );
            ps.setInt(3, clube.getIdClube());
            ps.execute();
            System.out.println( "Clube atualizado" );

        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static void removeClube(int id) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_clube WHERE idClube = ?");
            ps.setInt(1, id);
            ps.execute();
            System.out.println( "Clube removido" );

        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static List<Jogador> readJogadoresClube( int id ) {
        List<Jogador> jogadores = new ArrayList<>();
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_jogador LEFT JOIN db_clubesesportivos.tb_clube tc " +
                    "on tb_jogador.idClube = tc.idClube where tc.idClube = ?;");
            ps.setInt(1, id );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                jogadores.add( new Jogador( rs.getInt( "idJogador" ),
                        ClubeCRUD.readClube( rs.getInt( "idClube" ) ),
                        rs.getInt( "idade" ),
                        rs.getString( "nome" ),
                        rs.getString( "posicao" )));
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        return jogadores;
    }
}
