package entidades;

public class Emprestimo {
    private String dataEmprestimo;  // Data do empréstimo
    private String dataDevolucao;   // Data de devolução do livro
    private String tituloLivro;     // Título do livro
    private String nomeUsuario;    // Nome do usuário
    private String cpfUsuario;     // CPF do usuário

    // Construtor da classe
    public Emprestimo(String dataDevolucao, String tituloLivro, String nomeUsuario, String cpfUsuario) {
        this.dataEmprestimo = new java.util.Date().toString(); // Data atual como data de empréstimo
        this.dataDevolucao = dataDevolucao;
        this.tituloLivro = tituloLivro;
        this.nomeUsuario = nomeUsuario;
        this.cpfUsuario = cpfUsuario;
    }

    // Métodos getters e setters
    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }
}
