package classes.CRUD;

import classes.Cliente;
import classes.DatabaseConnection;
import classes.Plano;

import java.sql.*;
import java.util.ArrayList;

public class ClienteCRUD {

    public static void createCliente( Cliente cliente ) {

        try (Connection connection = DatabaseConnection.getConnection()) {
            PlanoCRUD.readPlano( cliente.getPlano().getId() );
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_cliente ( nome, email, telefone, id_plano ) VALUES ( ? , ? , ? , ? )" );
            ps.setString( 1 , cliente.getNome() );
            ps.setString( 2 , cliente.getEmail() );
            ps.setString( 3 , cliente.getTelefone() );
            ps.setInt( 4 , cliente.getPlano().getId() );
            ps.execute();

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        } catch ( RuntimeException e ) {
            System.err.println( "O plano não existe!\n" + e.getMessage() );
        }

    }
    public static Cliente readCliente( int id ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_cliente WHERE id = ?" );
            ps.setInt( 1 , id );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Cliente( rs.getInt( 1 ) , rs.getString( 2 ) ,
                        rs.getString( 3 ) , rs.getString( 4 ) ,
                        PlanoCRUD.readPlano( rs.getInt( 5 ) ));
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Cliente não encontrado..." );
    }

    public static Cliente readCliente( String nome ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_cliente WHERE nome = ?" );
            ps.setString( 1 , nome );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Cliente( rs.getInt( 1 ) , rs.getString( 2 ) ,
                        rs.getString( 3 ) , rs.getString( 4 ) ,
                        PlanoCRUD.readPlano( rs.getInt( 5 ) ));
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Cliente não encontrado..." );
    }

    public static Cliente readCliente( Plano plano ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_cliente WHERE id_plano = ?" );
            ps.setInt( 1 , plano.getId() );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Cliente( rs.getInt( 1 ) , rs.getString( 2 ) ,
                        rs.getString( 3 ) , rs.getString( 4 ) ,
                        PlanoCRUD.readPlano( rs.getInt( 5 ) ));
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Cliente não encontrado..." );
    }

    public static ArrayList<Cliente> readAllClientes( ) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_cliente" , Statement.RETURN_GENERATED_KEYS );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                clientes.add( new Cliente( rs.getInt( 1 ) , rs.getString( 2 ) ,
                        rs.getString( 3 ) , rs.getString( 4 ) ,
                        PlanoCRUD.readPlano( rs.getInt( 5 ) )) );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        return clientes;
    }
    public static void updateCliente( Cliente cliente ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PlanoCRUD.readPlano( cliente.getPlano().getId() );
            PreparedStatement ps = connection.prepareStatement( "UPDATE tb_cliente SET nome = ? , email = ? , telefone = ? , id_plano = ? WHERE id = ?" );
            ps.setString( 1 , cliente.getNome() );
            ps.setString( 2 , cliente.getEmail() );
            ps.setString( 3 , cliente.getTelefone() );
            ps.setInt( 4 , cliente.getPlano().getId() );
            ps.setInt( 5 , cliente.getId() );

            ps.execute();

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        } catch ( RuntimeException e ) {
            System.err.println( "O plano não existe!\n" + e.getMessage() );
        }

    }
    public static void deleteCliente( int id ) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "DELETE FROM tb_cliente WHERE id = ?");
            ps.setInt( 1 , id );
            ps.execute();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );

        }
    }
}
