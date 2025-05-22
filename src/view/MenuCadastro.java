package view;

import controller.ClienteController;
import controller.ProdutoController;
import model.Cliente;
import model.Produto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuCadastro {
    private Scanner sc;
    private controller.ClienteController clienteController;
    private controller.ProdutoController produtoController;

    public MenuCadastro(controller.ClienteController clienteController, controller.ProdutoController produtoController) {
        this.sc = new Scanner(System.in);
        this.clienteController = clienteController;
        this.produtoController = produtoController;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n--- Menu de Cadastro ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarProduto();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        System.out.print("Data Cadastro (dd/MM/yyyy): ");
        String data = sc.nextLine();

        if (!validarData(data)) {
            System.out.println("Formato de data inválido. A data deve estar no formato dd/MM/yyyy.");
            return;
        }

        Cliente cliente = new Cliente(0, nome, cpf, telefone, endereco, data);
        clienteController.cadastrarCliente(cliente);
    }

    private void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nomeProd = sc.nextLine();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = sc.nextInt();
        sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Data de validade (dd-MM-yyyy): ");
        String validadeStr = sc.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        Date validade;

        try {
            validade = sdf.parse(validadeStr);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. A data deve estar no formato dd-MM-yyyy.");
            return;
        }

        Produto produto = new Produto(0, nomeProd, preco, quantidade, categoria, validade);
        produtoController.salvarProduto(produto);
    }

    private boolean validarData(String dataStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dataStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
