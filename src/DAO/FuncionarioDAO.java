package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Funcionario;

public class FuncionarioDAO {

    private Connection conn;

    public FuncionarioDAO(Connection conn) {
        this.conn = conn;
    }
    
//buscar
    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionarios WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                String cargo = rs.getString("cargo");
                double salario = rs.getDouble("salario");
                String dataContratacao = rs.getString("data_contratacao"); 

                return new Funcionario(id, nome, cpf, telefone, cargo, salario, dataContratacao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário por ID: " + e.getMessage());
        }

        return null;
    }
//salvar
    public void salvar(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (nome, cpf, telefone, cargo, salario, data_contratacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getCargo());
            stmt.setDouble(5, funcionario.getSalario());

            //conversao Date:
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date utilDate = sdf.parse(funcionario.getDataContratacao());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            stmt.setDate(6, sqlDate);

            stmt.executeUpdate();
            System.out.println("Funcionário salvo no banco com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar funcionário: " + e.getMessage());
        }
    }
}
