package Controller;

import java.sql.Connection;
import db.DB;
import view.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            conn = DB.getConnection();

            MenuPrincipal menuPrincipal = new MenuPrincipal(conn);
            menuPrincipal.exibirMenu(conn);

        } catch (Exception e) {
            System.err.println("Erro ao iniciar o sistema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }
    }
}
