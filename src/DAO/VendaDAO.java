package DAO;

import model.Venda;
import model.ItemVenda;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendaDAO {

    private Connection conn;

    public VendaDAO(Connection conn) {
        this.conn = conn;
    }
//salvar
    public void salvar(Venda venda) {
        if (conn == null) {
            System.out.println("Erro: Conexão é nula.");
            return;
        }

        String sqlVenda = "INSERT INTO vendas (data_venda, cliente_id, funcionario_id, valor_total, tipo_pagamento) VALUES (?, ?, ?, ?, ?)";
        String sqlItem = "INSERT INTO item_venda (venda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try {
            if (conn.isClosed()) {
                throw new SQLException("A conexão foi fechada antes da operação.");
            }

            conn.setAutoCommit(false); 

          
            PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtVenda.setDate(1, java.sql.Date.valueOf(venda.getDataVenda()));
            stmtVenda.setInt(2, venda.getCliente().getClienteId()); 
            stmtVenda.setInt(3, venda.getFuncionario().getId()); 
            stmtVenda.setDouble(4, venda.getValorTotal());
            stmtVenda.setString(5, venda.getPagamento().getTipo());

            stmtVenda.executeUpdate();

            
            ResultSet rs = stmtVenda.getGeneratedKeys();
            int idVenda = 0;
            if (rs.next()) {
                idVenda = rs.getInt(1); 
            }

            
            for (ItemVenda item : venda.getItens()) {
                Produto produto = item.getProduto();
                PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                stmtItem.setInt(1, idVenda); 
                stmtItem.setInt(2, produto.getId()); 
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.setDouble(4, produto.getPreco());
                stmtItem.executeUpdate();
            }

            conn.commit(); 
            System.out.println("Venda salva com sucesso!");
        } catch (Exception e) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback(); 
                    System.out.println("Rollback executado com sucesso.");
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fazer rollback: " + ex.getMessage());
                ex.printStackTrace();
            }
            System.out.println("Erro ao salvar venda: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
