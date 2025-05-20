package view;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;
import model.*;

import java.sql.Connection;
import java.util.Scanner;

public class MenuVenda {
    private Connection conn;

    public MenuVenda(Connection conn) {
        this.conn = conn;
    }

    public void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);
        ProdutoDAO produtoDAO = new ProdutoDAO(conn);
        VendaDAO vendaDAO = new VendaDAO(conn);

        System.out.println("\n=== REGISTRO DE VENDA ===");

        System.out.print("ID do funcionário: ");
        int idFuncionario = Integer.parseInt(sc.nextLine());
        Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);

        System.out.print("ID do cliente: ");
        int idCliente = Integer.parseInt(sc.nextLine());
        Cliente cliente = clienteDAO.buscarPorId(idCliente);

        Pagamento pagamento = new PagamentoDinheiro();
        Venda venda = new Venda(funcionario, cliente, pagamento);

        int idProduto = -1;

        do {
            System.out.print("ID do produto (0 para finalizar): ");
            try {
                idProduto = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ ID inválido. Tente novamente.");
                continue;
            }

            if (idProduto != 0) {
                Produto produto = produtoDAO.buscarPorId(idProduto);
                if (produto == null) {
                    System.out.println("❌ Produto não encontrado.");
                    continue;
                }

                System.out.print("Quantidade: ");
                int qtd;
                try {
                    qtd = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("❌ Quantidade inválida.");
                    continue;
                }

                venda.adicionarProduto(produto, qtd);
            }

        } while (idProduto != 0);

        venda.finalizarVenda();
        vendaDAO.salvar(venda);
        System.out.println("✅ Venda registrada com sucesso!");
    }
}
