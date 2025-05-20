package view;

import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import db.DB;
import model.Cliente;
import model.Produto;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class MenuCadastro {
    private Connection conn;
    private Scanner sc;

    public MenuCadastro(Connection conn) {
        this.conn = conn;
        this.sc = new Scanner(System.in);
    }

    public void exibirMenu() {
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        ProdutoDAO produtoDAO = new ProdutoDAO(conn);
        int opcao;

        do {
            System.out.println("\n--- Menu de Cadastro ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
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
                        break;
                    }

                    Cliente cliente = new Cliente(0, nome, cpf, telefone, endereco, data);
                    clienteDAO.salvar(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
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
                    Date validade = null;

                    try {
                        validade = sdf.parse(validadeStr);
                    } catch (ParseException e) {
                        System.out.println("Formato de data inválido. A data deve estar no formato dd-MM-yyyy.");
                        break;
                    }

                    Produto produto = new Produto(0, nomeProd, preco, quantidade, categoria, validade);
                    produtoDAO.salvar(produto);
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
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
