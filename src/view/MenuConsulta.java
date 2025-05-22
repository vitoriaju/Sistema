package view;

import controller.ClienteController;
import controller.ProdutoController;
import controller.FornecedorController;

import java.util.Scanner;

public class MenuConsulta {
    private ClienteController clienteController;
    private ProdutoController produtoController;
    private FornecedorController fornecedorController;

    public MenuConsulta(
        ClienteController clienteController,
        ProdutoController produtoController,
        FornecedorController fornecedorController
    ) {
        this.clienteController = clienteController;
        this.produtoController = produtoController;
        this.fornecedorController = fornecedorController;
    }

    public void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU CONSULTA ===");
            System.out.println("1. Listar clientes");
            System.out.println("2. Listar produtos");
            System.out.println("3. Listar fornecedores");
            System.out.println("0. Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    clienteController.listarClientes().forEach(System.out::println);
                    break;
                case 2:
                    produtoController.listarProdutos().forEach(System.out::println);
                    break;
                case 3:
                    fornecedorController.listarFornecedores().forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
