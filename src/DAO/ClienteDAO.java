package DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.Cliente;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE cliente_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getString("dataCadastro")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID", e);
        }
        return null;
    }

    public boolean clienteExiste(String cpf) {
        String sql = "SELECT 1 FROM clientes WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência de cliente", e);
        }
    }

    public boolean salvar(Cliente cliente) {
        if (clienteExiste(cliente.getCpf())) {
            return false; 
        }

        String sql = "INSERT INTO clientes (cliente_id, nome, cpf, telefone, endereco, dataCadastro) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getClienteId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());

            java.sql.Date dataCadastro = convertToDate(cliente.getDataCadastro());
            stmt.setDate(6, dataCadastro);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                java.sql.Date dataCadastro = rs.getDate("dataCadastro");
                String dataCadastroStr = null;
                if (dataCadastro != null) {
                    dataCadastroStr = dataCadastro.toString(); 
                } else {
                    dataCadastroStr = ""; 
                }

                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        dataCadastroStr
                );
                lista.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes", e);
        }

        return lista;
    }


    private java.sql.Date convertToDate(String dataStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = sdf.parse(dataStr);
            return new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter data", e);
        }
    }
}
