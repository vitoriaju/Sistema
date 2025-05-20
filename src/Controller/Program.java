package Controller;

import java.sql.Connection;
import DAO.ClienteDAO;
import db.DB;
import view.MenuCadastro;

public class Program {
    public static void main(String[] args) {
        try (Connection conn = DB.getConnection()) {
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            MenuCadastro menuCadastro = new MenuCadastro(clienteDAO);
            menuCadastro.exibirMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}