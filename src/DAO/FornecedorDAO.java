package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;

import java.sql.ResultSet;

import model.Fornecedor;
import model.Produto;

public class FornecedorDAO {

    private Connection conn;

    public FornecedorDAO(Connection conn) {
        this.conn = conn;
    }
// salvar
    public void salvar(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores (nome_empresa, cnpj, telefone, tipo_produto) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNomeEmpresa());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getTipoProduto());

            stmt.executeUpdate();
            System.out.println("Fornecedor salvo no banco com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar fornecedor: " + e.getMessage());
        }
    }
// atualizar
    public void atualizar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedores SET nome_empresa = ?, telefone = ?, tipo_produto = ? WHERE cnpj = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNomeEmpresa());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getTipoProduto());
            stmt.setString(4, fornecedor.getCnpj());

            stmt.executeUpdate();
            System.out.println("Fornecedor atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }
    //listar todos
    public List<Fornecedor> listarTodos() {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedores";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                    rs.getString("nome_empresa"),
                    rs.getString("cnpj"),
                    rs.getString("telefone"),
                    rs.getString("tipo_produto")
                );
                lista.add(fornecedor);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar fornecedores: " + e.getMessage());
        }
        return lista;
    }
    //excluir
    public void excluir(String cnpj) {
        String sql = "DELETE FROM fornecedores WHERE cnpj = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            stmt.executeUpdate();
            System.out.println("Fornecedor excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir fornecedor: " + e.getMessage());
        }
    }
//buscar cnpj
    public Fornecedor buscarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM fornecedores WHERE cnpj = ?";
        Fornecedor fornecedor = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fornecedor = new Fornecedor(
                    rs.getString("nome_empresa"),
                    rs.getString("cnpj"),
                    rs.getString("telefone"),
                    rs.getString("tipo_produto")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar fornecedor: " + e.getMessage());
        }
        return fornecedor;
    }
}
