package view;

import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import DAO.FornecedorDAO;

import java.sql.Connection;
import java.util.Scanner;

public class MenuConsulta {
    private Connection conn;

    public MenuConsulta(Connection conn) {
        this.conn = conn;
    }

    public void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        ProdutoDAO produtoDAO = new ProdutoDAO(conn);
        FornecedorDAO fornecedorDAO = new FornecedorDAO(conn);

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
                    clienteDAO.listarTodos().forEach(System.out::println);
                    break;
                case 2:
                    produtoDAO.listarTodos().forEach(System.out::println);
                    break;
                case 3:
                    fornecedorDAO.listarTodos().forEach(System.out::println);
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
