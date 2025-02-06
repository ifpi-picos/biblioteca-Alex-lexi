package entidades;

public class Emprestimo {
    private String dataEmprestimo;
    private String dataDevolucao;
    private Livro livro;
    private Usuario usuario;

    public Emprestimo(String dataDevolucao, Livro livro, Usuario usuario) {
        this.dataEmprestimo = new java.util.Date().toString();
        this.dataDevolucao = dataDevolucao;
        this.livro = livro;
        this.usuario = usuario;
    }

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

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void realizarEmprestimo() {
        if (livro.getEmprestado()) {
            System.out.println("Livro já emprestado");
        } else {
            livro.setEmprestado(true);
            System.out.println("Livro emprestado com sucesso");
        }
    }
}