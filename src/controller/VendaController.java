package controller;

import DAO.VendaDAO;
import model.Venda;
import model.Funcionario;
import model.Cliente;
import model.Produto;
import model.Pagamento;

import java.sql.Connection;

public class VendaController {
    
    private VendaDAO vendaDAO;
    private Venda venda;
    private Connection conn;

    public VendaController(Connection conn) {
        this.conn = conn;
    }

    public void iniciarVenda(Funcionario funcionario, Cliente cliente, Pagamento pagamento) {
        venda = new Venda(funcionario, cliente, pagamento);
        vendaDAO = new VendaDAO(conn);
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        if (venda == null) {
            System.out.println("Nenhuma venda iniciada.");
            return;
        }
        venda.adicionarProduto(produto, quantidade);
    }
    
    public void registrarVenda(Venda venda) {
        if (vendaDAO == null) {
            vendaDAO = new VendaDAO(conn);
        }
        vendaDAO.salvar(venda);
    }
    
    public void finalizarVenda() {
        if (venda == null) {
            System.out.println("Nenhuma venda iniciada.");
            return;
        }
        venda.finalizarVenda();
        vendaDAO.salvar(venda);
        venda = null; 
    }
}
