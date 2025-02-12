package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectDao {
    // Método estático que retorna a conexão com o banco de dados
    public static Connection getConect() {
        Connection conect = null;
        try {
            // Tenta estabelecer a conexão com o banco de dados PostgreSQL
            conect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "#Alex0706");
            if (conect != null) {
                System.out.println("Conexão estabelecida com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return conect;
    }
}
