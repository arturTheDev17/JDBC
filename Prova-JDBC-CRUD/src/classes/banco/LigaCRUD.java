package classes.banco;

import classes.Clube;
import classes.Liga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LigaCRUD {
    public static void createLiga( Liga liga ) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_liga ( nome , anoFundacao ) VALUES ( ? , ? )" );
            ps.setString( 1 , liga.getNome() );
            ps.setInt( 2 , liga.getAnoFundacao() );
            ps.execute();
            System.out.println( "liga criada" );
        } catch (SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static Liga readLiga(String nomeLiga) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_liga WHERE nome = ?");
            ps.setString(1, nomeLiga);
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Liga( rs.getInt( "idLiga" ),
                        rs.getString( "nome" ),
                        rs.getInt( "anoFundacao" ) );
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        throw new RuntimeException( "nao achou" );
    }

    public static Liga readLiga(int id) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_liga WHERE idLiga = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Liga( rs.getInt( "idLiga" ),
                        rs.getString( "nome" ),
                        rs.getInt( "anoFundacao" ) );
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        throw new RuntimeException( "nao achou" );
    }

    public static List<Liga> readAllLigas() {
        List<Liga> ligas = new ArrayList<>();
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_liga");
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                ligas.add( new Liga( rs.getInt( "idliga" ),
                        rs.getString( "nome" ),
                        rs.getInt( "anoFundacao" )));
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        return ligas;
    }

    public static void updateLiga(Liga liga) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE tb_liga SET nome = ? , anoFundacao = ?  WHERE idLiga = ?");
            ps.setString( 1 , liga.getNome() );
            ps.setInt( 2 , liga.getAnoFundacao() );
            ps.setInt( 3 , liga.getIdLiga() );
            ps.execute();
            System.out.println( "liga atualizada" );
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static void removeLiga(int id) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_liga WHERE idLiga = ?");
            ps.setInt(1, id);
            ps.execute();
            System.out.println( "liga removida" );

        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static List<Clube> readClubesLiga(int idLiga) {
        List<Clube> clubes = new ArrayList<>();
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clube\n" +
                    "    LEFT JOIN db_clubesesportivos.tb_clubeliga tc on\n" +
                    "    tb_clube.idClube = tc.idClube LEFT JOIN tb_liga t on tc.idLiga = t.idLiga where t.idLiga = ?;");
            ps.setInt( 1 , idLiga );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                clubes.add( new Clube( rs.getInt( "idClube" ),
                        rs.getString( "nome" ),
                        rs.getString( "dataFundacao" )));
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        return clubes;
    }

    public static void relacionarClubeLiga( int idLiga , int idClube ) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_clubeliga (idLiga, idClube) VALUES (? , ?)");
            ps.setInt(1, idLiga);
            ps.setInt(2, idClube);
            ps.execute();
            System.out.println( "Relacao adicionada" );
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static void removerRelacaoClubeLiga( int idLiga , int idClube ) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_clubeliga WHERE idLiga = ? AND idClube = ?");
            ps.setInt(1, idLiga);
            ps.setInt(2, idClube);
            ps.execute();
            System.out.println( "Relacao removida" );

        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }
}
