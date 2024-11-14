package classes.CRUD;

import classes.Contrato;
import classes.Plano;
import classes.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class PlanoCRUD {

    public static void createContrato(Plano plano , Contrato contrato) {
        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_contrato " +
                    "( id , termos, data_inicio, data_fim ) VALUES ( ? , ? , ? , ? )" );
            ps.setInt( 1 , plano.getId() );
            ps.setString( 2 , contrato.getTermos() );
            ps.setDate( 3 , Date.valueOf( contrato.getData_inicio() ) );
            ps.setDate( 4 , Date.valueOf( contrato.getData_fim() ) );
            ps.execute();

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }

    }

    public static int createPlano( Plano plano ) {
        int key = -1;
        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement ps = connection.prepareStatement( "INSERT INTO tb_plano " +
                    "( operadora, nome, quantidade_dados, quantidade_dados_bonus, beneficios, valor ) VALUES ( ? , ? , ? , ? , ? , ? )" , Statement.RETURN_GENERATED_KEYS );

            ps.setString( 1 , plano.getOperadora() );
            ps.setString( 2 , plano.getNome() );
            ps.setDouble( 3 , plano.getQuantidade_dados() );
            ps.setDouble( 4 , plano.getQuantidade_dados_bonus() );
            ps.setString( 5 , plano.getBeneficios() );
            ps.setDouble( 6 , plano.getValor() );
            ps.getGeneratedKeys();
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if ( rs.next() ) {
                key = rs.getInt( 1 );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        return key;

    }

    public static Plano readPlano( int id ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_plano WHERE id = ?" );
            ps.setInt( 1 , id );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Plano( rs.getInt( 1 ) , rs.getString( 2 ) , rs.getString( 3 )
                        , rs.getDouble( 4 ) , rs.getDouble( 5 ) , rs.getString( 6 ) , rs.getDouble( 7 ) );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Plano não encontrado..." );
    }

    public static Plano readPlano( String nome ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_plano WHERE nome = ?" );
            ps.setString( 1 , nome );

            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                return new Plano( rs.getInt( 1 ) , rs.getString( 2 ) , rs.getString( 3 )
                        , rs.getDouble( 4 ) , rs.getDouble( 5 ) , rs.getString( 6 ) , rs.getDouble( 7 ) );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Plano não encontrado..." );
    }

    public static ArrayList<Plano> readPlanos( String operadora ) {
        ArrayList<Plano> planos = new ArrayList<>();

        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_plano WHERE operadora = ?" );
            ps.setString( 1 , operadora );

            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                planos.add( new Plano( rs.getInt( 1 ) , rs.getString( 2 ) , rs.getString( 3 )
                        , rs.getDouble( 4 ) , rs.getDouble( 5 ) , rs.getString( 6 ) , rs.getDouble( 7 ) ));
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        return planos;
    }


    public static Contrato readPlanoEcontrato( int id ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_plano " +
                    "LEFT JOIN db_operadora.tb_contrato tc on tb_plano.id = tc.id" +
                    " WHERE tc.id = ?" );
            ps.setInt( 1 , id );

            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                return new Contrato( rs.getInt( 8 ) , new Plano( rs.getInt( 1 ) , rs.getString( 2 ) , rs.getString( 3 )
                        , rs.getDouble( 4 ) , rs.getDouble( 5 ) , rs.getString( 6 ) ,
                        rs.getDouble( 7 )), rs.getString( 9 ) , rs.getDate( 10 ).toLocalDate() , rs.getDate( 11 ).toLocalDate() );
            }


        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        throw new RuntimeException( "Plano ou contrato não encontrado..." );
    }

    public static ArrayList<Plano> readAllPlanos( ) {
        ArrayList<Plano> planos = new ArrayList<>();

        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "SELECT * FROM tb_plano" );
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                planos.add( new Plano( rs.getInt( 1 ) , rs.getString( 2 ) , rs.getString( 3 )
                        , rs.getDouble( 4 ) , rs.getDouble( 5 ) , rs.getString( 6 ) , rs.getDouble( 7 ) ) );
            }

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
        return planos;
    }

    public static void updatePlano( Plano plano ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {

            PreparedStatement ps = connection.prepareStatement( "UPDATE tb_plano SET operadora = ? , nome = ? , " +
                    "quantidade_dados = ? , quantidade_dados_bonus = ? , beneficios = ? , valor = ? WHERE id = ?" );
            ps.setString( 2 , plano.getNome() );
            ps.setString( 1 , plano.getOperadora() );
            ps.setDouble( 3 , plano.getQuantidade_dados() );
            ps.setDouble( 4 , plano.getQuantidade_dados_bonus() );
            ps.setString( 5 , plano.getBeneficios() );
            ps.setDouble( 6 , plano.getValor() );
            ps.setInt( 7 , plano.getId() );
            ps.execute();

        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }

    }

    public static void updateContrato( Contrato contrato ) {
        try ( Connection connection = DatabaseConnection.getConnection() ) {
            PreparedStatement ps = connection.prepareStatement( "UPDATE tb_contrato SET termos = ? , data_inicio = ? , data_fim = ?" );
            ps.setString( 1 , contrato.getTermos() );
            ps.setDate( 2 , Date.valueOf( contrato.getData_inicio() ) );
            ps.setDate( 3 ,  Date.valueOf( contrato.getData_fim() ) );

            ps.execute();

        } catch ( SQLException e ) {
            System.err.println(e.getMessage());
        }

    }

    public static void deleteContrato( int id ) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "DELETE FROM tb_contrato WHERE id = ?");
            ps.setInt( 1 , id );
            ps.execute();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );

        }
    }

    public static void deletePlano( int id ) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement( "DELETE FROM tb_plano WHERE id = ?");
            ps.setInt( 1 , id );
            ps.execute();
        } catch ( SQLException e ) {
            System.err.println( e.getMessage() );
        }
    }
}
