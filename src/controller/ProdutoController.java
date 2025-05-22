package controller;

import DAO.ProdutoDAO;
import model.Produto;

import java.sql.Connection;
import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController(Connection conn) {
        this.produtoDAO = new ProdutoDAO(conn);
    }

    public Produto buscarPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }

    public void salvarProduto(Produto produto) {
        if (produto != null && produto.getNome() != null && !produto.getNome().isEmpty()) {
            produtoDAO.salvar(produto);
        } else {
            System.out.println("❌ Produto inválido para salvar.");
        }
    }

    public void atualizarEstoqueProduto(int produtoId, int novaQuantidade) {
        if (novaQuantidade >= 0) {
            produtoDAO.atualizarEstoque(produtoId, novaQuantidade);
        } else {
            System.out.println("❌ Quantidade inválida para atualizar estoque.");
        }
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarTodos();
    }

    
    public void venderProduto(int produtoId, int quantidade) {
        Produto produto = buscarPorId(produtoId);
        if (produto != null) {
            if (quantidade > 0 && quantidade <= produto.getQuantidade()) {
                int novaQuantidade = produto.getQuantidade() - quantidade;
                atualizarEstoqueProduto(produtoId, novaQuantidade);
                System.out.println("✅ Venda realizada com sucesso!");
            } else {
                System.out.println("❌ Quantidade para venda inválida ou maior que estoque.");
            }
        } else {
            System.out.println("❌ Produto não encontrado para venda.");
        }
    }

	
	
}
