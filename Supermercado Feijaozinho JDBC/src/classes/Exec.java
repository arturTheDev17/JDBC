package classes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exec {

    private static final Scanner sc = new Scanner( System.in );
    public static void main(String[] args) throws SQLException {
        System.out.println("Bem vindo ao supermercado feijãozinho.");
        criarProduto();
    }

    private static void criarProduto () {

        System.out.println("Insira o nome do produto:");
        String nome = sc.next();

        System.out.println("Insira a categoria do produto:");
        String categoria = sc.next();

        System.out.println("Insira a marca do produto:");
        String marca = sc.next();

        System.out.println("Insira o preço do produto:");
        float preco = sc.nextFloat();

        System.out.println("Insira a quantidade de produtos em estoque:");
        int quantidade = sc.nextInt();

        Produto produto = new Produto(nome, categoria, marca, preco, quantidade);
        salvar(produto);
    }

    private static void salvar(Produto produto) {

        try (Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_produto (nome, categoria, marca, preço, quantidade) VALUES " + "(?, ?, ?, ?, ?)");
            ps.setString(1, produto.getNomeProduto());
            ps.setString(2, produto.getCategoriaProduto());
            ps.setString(3, produto.getMarca());
            ps.setDouble(4, produto.getPrecoProduto());
            ps.setInt(5, produto.getQuantidadeEstoque());
            ps.execute();

        } catch (Exception e) {
            System.out.println("Conexão com banco de dados deu errado e mercado faliu.");
            e.printStackTrace();
        }
    }

    private static void removerProduto () throws SQLException {

        System.out.println("Insira o nome do produto que deseja remover:");
        String nome = sc.next();
        remover(nome);

    }

    private static void remover(String nome) {

        try (Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_produto WHERE nome = ?");
            ps.setString(1, nome);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto removido com sucesso.");
            }

            else {
                System.out.println("Nenhum produto encontrado com esse número.");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
