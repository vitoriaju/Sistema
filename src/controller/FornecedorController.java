package controller;

import DAO.FornecedorDAO;
import model.Fornecedor;

import java.sql.Connection;
import java.util.List;

public class FornecedorController {
    private FornecedorDAO fornecedorDAO;

    public FornecedorController(Connection conn) {
        this.fornecedorDAO = new FornecedorDAO(conn);
    }

    public Fornecedor buscarPorCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            System.out.println("❌ CNPJ inválido para busca.");
            return null;
        }
        return fornecedorDAO.buscarPorCnpj(cnpj);
    }

    public void salvarFornecedor(Fornecedor fornecedor) {
        if (fornecedor != null && fornecedor.getCnpj() != null && !fornecedor.getCnpj().isEmpty()) {
            fornecedorDAO.salvar(fornecedor);
        } else {
            System.out.println("❌ Fornecedor inválido para salvar.");
        }
    }

    public void atualizarFornecedor(Fornecedor fornecedor) {
        if (fornecedor != null && fornecedor.getCnpj() != null && !fornecedor.getCnpj().isEmpty()) {
            fornecedorDAO.atualizar(fornecedor);
        } else {
            System.out.println("❌ Fornecedor inválido para atualizar.");
        }
    }

    public void excluirFornecedor(String cnpj) {
        if (cnpj != null && !cnpj.trim().isEmpty()) {
            fornecedorDAO.excluir(cnpj);
        } else {
            System.out.println("❌ CNPJ inválido para exclusão.");
        }
    }

    public List<Fornecedor> listarFornecedores() {
        return fornecedorDAO.listarTodos();
    }
}
