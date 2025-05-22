package view;

import controller.ClienteController;
import controller.FornecedorController;
import controller.FuncionarioController;
import controller.ProdutoController;
import controller.VendaController;

import java.sql.Connection;
import java.util.Scanner;

public class MenuPrincipal {

    private Connection conn;

    public MenuPrincipal(Connection conn) {
        this.conn = conn;
    }

    public static void exibirMenu(Connection conn) {
        Scanner sc = new Scanner(System.in);

        // Instanciar controllers uma vez e reaproveitar
        ClienteController clienteController = new ClienteController(conn);
        ProdutoController produtoController = new ProdutoController(conn);
        FornecedorController fornecedorController = new FornecedorController(conn);
        FuncionarioController funcionarioController = new FuncionarioController(conn);
        VendaController vendaController = new VendaController(conn);

        int opcao;

        do {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1. Menu de Cadastro");
            System.out.println("2. Menu de Consulta");
            System.out.println("3. Menu de Venda");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    MenuCadastro menuCadastro = new MenuCadastro(clienteController, produtoController);
                    menuCadastro.exibirMenu();
                    break;
                case 2:
                    MenuConsulta menuConsulta = new MenuConsulta(clienteController, produtoController, fornecedorController);
                    menuConsulta.exibirMenu();
                    break;
                case 3:
                    MenuVenda menuVenda = new MenuVenda(clienteController, funcionarioController, produtoController, vendaController);
                    menuVenda.exibirMenu();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
