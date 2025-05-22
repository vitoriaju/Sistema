package view;

import controller.ClienteController;
import controller.FuncionarioController;
import controller.ProdutoController;
import controller.VendaController;
import model.*;

import java.util.Scanner;

public class MenuVenda {
    private final ClienteController clienteController;
    private final FuncionarioController funcionarioController;
    private final ProdutoController produtoController;
    private final VendaController vendaController;

    public MenuVenda(ClienteController clienteController, FuncionarioController funcionarioController,
                     ProdutoController produtoController, VendaController vendaController) {
        this.clienteController = clienteController;
        this.funcionarioController = funcionarioController;
        this.produtoController = produtoController;
        this.vendaController = vendaController;
    }

    public void exibirMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== REGISTRO DE VENDA ===");

        System.out.print("ID do funcionário: ");
        int idFuncionario = Integer.parseInt(sc.nextLine());
        Funcionario funcionario = funcionarioController.buscarPorId(idFuncionario);
        if (funcionario == null) {
            System.out.println("❌ Funcionário não encontrado.");
            return;
        }

        System.out.print("ID do cliente: ");
        int idCliente = Integer.parseInt(sc.nextLine());
        Cliente cliente = clienteController.buscarPorId(idCliente);
        if (cliente == null) {
            System.out.println("❌ Cliente não encontrado.");
            return;
        }

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
                Produto produto = produtoController.buscarPorId(idProduto);
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
        vendaController.registrarVenda(venda);
        System.out.println("✅ Venda registrada com sucesso!");
    }
}
