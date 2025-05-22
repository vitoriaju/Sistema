package controller;

import DAO.FuncionarioDAO;
import model.Funcionario;

import java.sql.Connection;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController(Connection conn) {
        this.funcionarioDAO = new FuncionarioDAO(conn);
    }

    public Funcionario buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("❌ ID inválido para busca.");
            return null;
        }
        return funcionarioDAO.buscarPorId(id);
    }

    public void salvarFuncionario(Funcionario funcionario) {
        if (funcionario != null && funcionario.getNome() != null && !funcionario.getNome().isEmpty()) {
            funcionarioDAO.salvar(funcionario);
        } else {
            System.out.println("❌ Funcionário inválido para salvar.");
        }
    }
}
