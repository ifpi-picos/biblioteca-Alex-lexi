package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectDao {
    public static Connection getConect() {
        Connection conect = null;
        try {
            // Tenta estabelecer a conexão com o banco de dados
            conect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "#Alex0706");
            
            if (conect != null) {
                System.out.println("Finalmente conectou.");
            } else {
                System.out.println("Deu erro ai oh.");
            }
        } catch (SQLException e) {
            // Captura exceções relacionadas ao SQL e imprime o stack trace
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return conect; // Retorna a conexão (ou null em caso de falha)
    }
}