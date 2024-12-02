package classes.banco;

import classes.Clube;
import classes.Jogador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogadorCRUD {

    public static void createJogador(Jogador jogador) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_jogador ( nome , idade , idClube , posicao ) VALUES ( ? , ? , ? , ? )" );
            ps.setString( 1 , jogador.getNome() );
            ps.setInt( 2 , jogador.getIdade() );
            ps.setInt( 3 , jogador.getClube().getIdClube() );
            ps.setString( 4 , jogador.getPosicao() );
            ps.execute();
            System.out.println( "Jogador adicionado" );
        } catch (SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static Jogador readJogador( int id ) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_jogador WHERE idJogador = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Jogador( rs.getInt( "idJogador" ),
                        ClubeCRUD.readClube( rs.getInt( "idClube" ) ),
                        rs.getInt( "idade" ),
                        rs.getString( "nome" ),
                        rs.getString( "posicao" ));
            }
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
        throw new RuntimeException( "nao achou" );
    }

    public static List<Jogador> readAllJogadores() {
        List<Jogador> jogadores = new ArrayList<>();
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_jogador");
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

    public static void updateJogador(Jogador jogador) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE tb_jogador SET nome = ? , idClube = ? , idade = ? , posicao = ? WHERE idJogador = ?");
            ps.setString( 1 , jogador.getNome() );
            ps.setInt( 2 , jogador.getClube().getIdClube() );
            ps.setInt( 3 , jogador.getIdade() );
            ps.setString( 4 , jogador.getPosicao() );
            ps.setInt( 5 , jogador.getIdJogador() );
            ps.execute();
            System.out.println( "Jogador atualizado" );
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }

    public static void removeJogador(int id) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_jogador WHERE idJogador = ?");
            ps.setInt(1, id);
            ps.execute();
            System.out.println( "Jogador removido" );
        } catch ( SQLException e ) {
            System.out.println( e.getMessage() );
        }
    }
}
