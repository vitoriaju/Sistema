package model;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Produto {
    private int produto_id; 
    private String nome;
    private double preco;
    private int quantidade;
    private String categoria;
    private Date validade;

    public Produto(int produto_id, String nome, double preco, int quantidade, String categoria,Date validade) {
        this.produto_id = produto_id; 
        this.nome = nome;
        this.preco = preco >= 0 ? preco : 0;
        this.quantidade = quantidade;
        this.validade = validade;
        this.categoria = categoria;
       
    }


	


	public void vender(int quantidade) {
        if (quantidade > 0 && quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
        } else {
            System.out.println("Quantidade inválida!");
        }
    }
	
 
    public int getId() {
        return produto_id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public Date getValidade() {
        return validade;
    }
    @Override
    public String toString() {
        return "Produto{" +
               "produtoId=" + produto_id +
               ", nome='" + nome + '\'' +
               ", preco=" + preco +
               ", quantidade=" + quantidade +
               ", categoria='" + categoria + '\'' +
               ", validade='" + validade + '\'' +
               '}';
    }
}

