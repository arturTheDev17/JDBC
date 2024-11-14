import java.sql.*;
import java.util.ArrayList;

public class CRUD {

    public static void createItem( Item item ) {

        try (Connection connection = BancoConnection.getConnection()) {

            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_item ( nome ) VALUES ( ? )" );
            ps.setString( 1 , item.getNome() );
            ps.execute();

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }

    }
    public static Item readItem( int id ) {
        try ( Connection connection = BancoConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_item WHERE id = ?" );
            ps.setInt( 1 , id );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Item( rs.getInt( 1 ) , rs.getString( 2 ) );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Item não encontrado..." );
    }

    public static Item readItem( String nome ) {
        try ( Connection connection = BancoConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_item WHERE nome = ?" );
            ps.setString( 1 , nome );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Item( rs.getInt( 1 ) , rs.getString( 2 ) );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Item não encontrado..." );
    }

    public static ArrayList<Item> readAllItens( ) {
        ArrayList<Item> items = new ArrayList<>();

        try ( Connection connection = BancoConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_item" , Statement.RETURN_GENERATED_KEYS );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                items.add( new Item( rs.getInt( 1 ) , rs.getString( 2 )) );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        return items;
    }
    public static void updateItem( Item item ) {
        try ( Connection connection = BancoConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "UPDATE tb_item SET nome = ? WHERE id = ?" );
            ps.setString( 1 , item.getNome() );
            ps.setInt( 2 , item.getId() );

            ps.execute();

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }

    }
    public static void deleteItem( int id ) {
        try (Connection connection = BancoConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "DELETE FROM tb_item WHERE id = ?");
            ps.setInt( 1 , id );
            ps.execute();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
    }

}
