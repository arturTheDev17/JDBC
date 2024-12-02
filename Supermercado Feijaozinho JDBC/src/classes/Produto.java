package classes;

public class Produto {

    private String nomeProduto;
    private String categoriaProduto;
    private String marca;
    private float precoProduto;
    private int quantidadeEstoque;

    public Produto(String nomeProduto, String categoriaProduto, String marca, float precoProduto, int quantidadeEstoque) {
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.marca = marca;
        this.precoProduto = precoProduto;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public String getMarca() {
        return marca;
    }

    public float getPrecoProduto() {
        return precoProduto;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public String toString () {
        return "\nNome do produto: " + getNomeProduto() +
               "\nCategoria do produto: " + getCategoriaProduto() +
               "\nMarca do produto: " + getMarca() +
               "\nPreço do produto: " + getPrecoProduto() +
               "\nQuantidade em estoque do produto: " + getPrecoProduto();
    }
}
