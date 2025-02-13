package dao;

import entidades.Emprestimo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao {

    // Método para inserir um empréstimo no banco de dados
    public void inserirEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (titulo_livro, nome_usuario, cpf_usuario, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conexao = ConectDao.getConect(); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
    
            String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    
            // Passando apenas o título do livro para o banco de dados
            stmt.setString(1, emprestimo.getTituloLivro());  // Apenas o título é passado
            stmt.setString(2, emprestimo.getNomeUsuario());
            stmt.setString(3, emprestimo.getCpfUsuario());
            stmt.setString(4, dataAtual);
            stmt.setString(5, emprestimo.getDataDevolucao());
    
            stmt.executeUpdate();
            System.out.println("Empréstimo realizado com sucesso!");
    
        } catch (SQLException e) {
            System.err.println("Erro ao realizar o empréstimo: " + e.getMessage());
        }
    }
    

    // Método para listar o histórico de empréstimos de um usuário
    public List<Emprestimo> listarHistoricoEmprestimos(String cpfUsuario) {
        List<Emprestimo> historicoEmprestimos = new ArrayList<>();
        String sql = "SELECT titulo_livro, data_emprestimo, data_devolucao FROM emprestimos WHERE cpf_usuario = ?";

        try (Connection conexao = ConectDao.getConect();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpfUsuario);

            // Executa a consulta e pega os resultados
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tituloLivro = rs.getString("titulo_livro");
                String dataEmprestimo = rs.getString("data_emprestimo");
                String dataDevolucao = rs.getString("data_devolucao");

                // Cria um objeto Emprestimo com os dados do banco e adiciona à lista
                historicoEmprestimos.add(new Emprestimo(dataEmprestimo, dataDevolucao, tituloLivro, "", cpfUsuario));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar histórico de empréstimos: " + e.getMessage());
        }

        return historicoEmprestimos;
    }

    // Método para atualizar a data de devolução de um empréstimo
    public void atualizarDataDevolucao(String cpfEmprestimo, String novaDataDevolucao) {
        String sql = "UPDATE emprestimos SET data_devolucao = ? WHERE cpf = ?";

        try (Connection conexao = ConectDao.getConect(); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novaDataDevolucao);
            stmt.setString(2, cpfEmprestimo);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Data de devolução atualizada com sucesso!");
            } else {
                System.out.println("Nenhum empréstimo encontrado com o CPF fornecido.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar data de devolução: " + e.getMessage());
        }
    }

    
}
