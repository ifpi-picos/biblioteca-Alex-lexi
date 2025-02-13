package dao;

import entidades.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDao {

    

    // Método para adicionar um novo livro ao banco de dados
    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, editora, ano, emprestado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConectDao.getConect(); // Abre a conexão com o banco de dados
                PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a consulta SQL para inserção

            // Define os parâmetros da consulta SQL com os dados do livro

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setBoolean(5, livro.getEmprestado());

            // Executa a consulta e insere o livro no banco de dados
            stmt.executeUpdate();
            System.out.println("Livro adicionado com sucesso.");

        } catch (SQLException e) { // Se ocorrer algum erro, captura a exceção e exibe a mensagem
            System.err.println("Erro ao adicionar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        String sql = "SELECT * FROM livros"; // Consulta SQL para buscar todos os livros
        List<Livro> livros = new ArrayList<>(); // Lista para armazenar os livros recuperados

        try (Connection conn = ConectDao.getConect(); // Abre a conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) { // Executa a consulta SQL

            while (rs.next()) { // Percorre todos os registros retornados
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        rs.getInt("ano"));
                livro.setEmprestado(rs.getBoolean("emprestado")); // Define o status de empréstimo
                livros.add(livro); // Adiciona o livro à lista
            }

        } catch (SQLException e) { // Captura e exibe erros SQL
            System.err.println("Erro ao buscar livros: " + e.getMessage());
            e.printStackTrace();
        }

        return livros; // Retorna a lista de livros encontrados
    }

    public Livro buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM livros WHERE titulo = ?";
        Livro livro = null; // Inicializa como null para evitar erro

        try (
                Connection conn = ConectDao.getConect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        rs.getInt("ano"));
                livro.setEmprestado(rs.getBoolean("emprestado")); // Define o status de empréstimo

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livro; // Retorna o usuário encontrado ou null se não existir
    }

    public List<Livro> ListarLivrosEmprestados() {
        String sql = "SELECT * FROM livros WHERE emprestado = true"; // seleciona tudo da tabela livro que emprestado eh
                                                                     // igual a false
        List<Livro> livros = new ArrayList<>(); // Lista para armazenar os livros recuperados

        try (Connection conn = ConectDao.getConect(); // Abre a conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) { // Executa a consulta SQL

            while (rs.next()) { // Percorre todos os registros retornados
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        rs.getInt("ano"));
                livro.setEmprestado(rs.getBoolean("emprestado")); // Define o status de empréstimo
                livros.add(livro); // Adiciona o livro à lista
            }

        } catch (SQLException e) { // Captura e exibe erros SQL
            System.err.println("Erro ao buscar livros: " + e.getMessage());
            e.printStackTrace();
        }

        return livros; // Retorna a lista de livros encontrados
    }

    public List<Livro> ListarLivrosDisponiveis() {
        String sql = "SELECT * FROM livros WHERE emprestado = false";
        List<Livro> livros = new ArrayList<>(); // Lista para armazenar os livros recuperados

        try (Connection conn = ConectDao.getConect(); // Abre a conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) { // Executa a consulta SQL

            while (rs.next()) { // Percorre todos os registros retornados
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        rs.getInt("ano"));
                livro.setEmprestado(rs.getBoolean("emprestado")); // Define o status de empréstimo
                livros.add(livro); // Adiciona o livro à lista
            }

        } catch (SQLException e) { // Captura e exibe erros SQL
            System.err.println("Erro ao buscar livros: " + e.getMessage());
            e.printStackTrace();
        }

        return livros; // Retorna a lista de livros encontrados
    }

    // Método para atualizar o status de empréstimo de um livro
    public void atualizarStatusEmprestimo(Livro livro) {
        String sql = "UPDATE livros SET emprestado = ? WHERE id = ?"; // Usando ID para garantir precisão
    
        try (Connection conn = ConectDao.getConect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setBoolean(1, livro.getEmprestado());
            stmt.setInt(2, livro.getId()); // Usando o ID do livro
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Status de empréstimo atualizado com sucesso.");
            } else {
                System.out.println("Nenhum livro encontrado com o ID fornecido.");
            }
    
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status de empréstimo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

  
}
