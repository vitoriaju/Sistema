package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.Produto;

public class ProdutoDAO {
    private Connection conn;

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    // Buscar produto por ID
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE produto_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Produto(
                    rs.getInt("produto_id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade"),
                    rs.getString("categoria"),
                    rs.getDate("validade")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar produto por ID: " + e.getMessage());
        }
        return null;
    }

    // Salvar novo produto
    public void salvar(Produto produto) {
        String sql = "INSERT INTO produtos (produto_id, nome, preco, quantidade, categoria, validade) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setString(5, produto.getCategoria());
            stmt.setDate(6, new java.sql.Date(produto.getValidade().getTime()));
            stmt.executeUpdate();
            System.out.println("✅ Produto salvo no banco com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao salvar produto: " + e.getMessage());
        }
    }

    // Atualizar estoque de um produto
    public void atualizarEstoque(int produtoId, int novaQuantidade) {
        String sql = "UPDATE produtos SET quantidade = ? WHERE produto_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, produtoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("produto_id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade"),
                    rs.getString("categoria"),
                    rs.getDate("validade")
                );
                lista.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar produtos: " + e.getMessage());
        }
        return lista;
    }
}
