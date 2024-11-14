package classes.CRUD;

import classes.ServicoAdicional;
import classes.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class ServicoAdicionalCRUD {
    public static void createServicoAdicional( ServicoAdicional servico ) {

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_servico ( descricao, custo_mensal ) VALUES ( ? , ? )" );
            ps.setString( 1 , servico.getDescricao() );
            ps.setDouble( 2 , servico.getCusto_mensal() );
            ps.execute();

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }

    }

    public static void linkServicoAdicionalPlano( int planoId , int servicoAdicionalId ) {

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_planoservico ( id_plano, id_servico ) VALUES ( ? , ? )" );
            ps.setInt( 1 , planoId );
            ps.setInt( 2 , servicoAdicionalId );
            ps.execute();

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }

    }
    public static void deletelinkServicoAdicionalPlano( int planoId , int servicoAdicionalId ) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "DELETE FROM tb_planoservico WHERE id_plano = ? AND id_servico = ?");
            ps.setInt( 1 , planoId );
            ps.setInt( 2 , servicoAdicionalId );
            ps.execute();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
    }


    public static ServicoAdicional readServicoAdicional( int id ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_servico WHERE id = ?" );
            ps.setInt( 1 , id );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new ServicoAdicional( rs.getInt( 1 ) , rs.getString( 2 ) , rs.getDouble( 3 ) );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Servico Adicional não encontrado..." );
    }

    public static ServicoAdicional readServicoAdicional( String descricao ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_servico WHERE descricao = ?" );
            ps.setString( 1 , descricao );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new ServicoAdicional( rs.getInt( 1 ) , rs.getString( 2 ) , rs.getDouble( 3 ) );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Servico Adicional não encontrado..." );
    }

    public static ArrayList<ServicoAdicional> readAllServicosAdicionais( ) {
        ArrayList<ServicoAdicional> servicos = new ArrayList<>();

        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_servico" , Statement.RETURN_GENERATED_KEYS );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                servicos.add( new ServicoAdicional( rs.getInt( 1 ) , rs.getString( 2 ) , rs.getDouble( 3 ) ));;
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        return servicos;
    }
    public static void updateServicoAdicional( ServicoAdicional servico ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "UPDATE tb_servico SET descricao = ? , custo_mensal = ? WHERE id = ?" );
            ps.setString( 1 , servico.getDescricao() );
            ps.setDouble( 2 , servico.getCusto_mensal() );
            ps.setInt( 3 , servico.getId() );

            ps.execute();

        } catch ( SQLException e ) {
            System.err.println(e.getMessage());
        }

    }
    public static void deleteServicoAdicional( int id ) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "DELETE FROM tb_servico WHERE id = ?");
            ps.setInt( 1 , id );
            ps.execute();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
    }
}
