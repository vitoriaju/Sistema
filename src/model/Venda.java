package model;

import java.time.LocalDate;
import java.util.*;

import DAO.ProdutoDAO;
import db.DB;

public class Venda {
    private int cliente_id;
    private LocalDate dataVenda;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<ItemVenda> itens;
    private double valorTotal;
    private Pagamento pagamento;

    
    public Venda(Funcionario funcionario, Cliente cliente, Pagamento pagamento) {
        this.cliente_id = cliente.getClienteId();  
        this.dataVenda = LocalDate.now();          
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.itens = new ArrayList<>();            
        this.valorTotal = 0;                        
        this.pagamento = pagamento;
    }

    // Getter 
    public int getClienteId() {
        return cliente_id;
    }

    
    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

  
    public void adicionarProduto(Produto produto, int quantidade) {
        if (produto != null && quantidade > 0 && produto.getQuantidade() >= quantidade) {
            produto.vender(quantidade);
            ProdutoDAO produtoDAO = new ProdutoDAO(DB.getConnection());
            produtoDAO.atualizarEstoque(produto.getId(), produto.getQuantidade()); 
            itens.add(new ItemVenda(produto, quantidade)); 
            calcularTotal();
        } else {
            System.out.println("Estoque insuficiente ou produto inválido!");
        }
    }

   
    public void finalizarVenda() {
        if (itens.isEmpty()) {
            System.out.println("A venda não pode ser finalizada sem produtos.");
            return;
        }
        calcularTotal(); 

       
        pagamento.processarPagamento(valorTotal);

        System.out.println("Venda finalizada com sucesso!");
    }

   
    private void calcularTotal() {
        valorTotal = 0;
        for (ItemVenda item : itens) {
            valorTotal += item.getSubtotal(); 
        }
    }
}
