package controller;

import DAO.ClienteDAO;
import model.Cliente;

import java.sql.Connection;
import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController(Connection conn) {
        this.clienteDAO = new ClienteDAO(conn);
    }

    public void cadastrarCliente(Cliente cliente) {
        if (clienteDAO.clienteExiste(cliente.getCpf())) {
            System.out.println("Erro: Cliente com CPF " + cliente.getCpf() + " já está cadastrado.");
            return;
        }

        boolean sucesso = clienteDAO.salvar(cliente);
        if (sucesso) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar cliente.");
        }
    }

    public Cliente buscarPorId(int id) {
        return clienteDAO.buscarPorId(id);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }
}
