package entidades;

// Classe que representa um livro
public class Livro {
    // Atributos da classe Livro
    private int id;            // Identificador único do livro
    private String autor;      // Autor do livro
    private String titulo;     // Título do livro
    private String editora;    // Editora do livro
    private int ano;           // Ano de publicação do livro
    private boolean emprestado; // Indica se o livro está emprestado (verdadeiro ou falso)

    // Construtor da classe Livro (sem o id, o id será gerado posteriormente)
    // Inicializa o livro com título, autor, editora, ano e define que o livro não está emprestado
    public Livro(String titulo, String autor, String editora, int ano) {
        this.titulo = titulo;    // Inicializa o título com o valor fornecido
        this.autor = autor;      // Inicializa o autor com o valor fornecido
        this.editora = editora;  // Inicializa a editora com o valor fornecido
        this.ano = ano;          // Inicializa o ano de publicação com o valor fornecido
        this.emprestado = false; // Inicializa o estado do livro como não emprestado
    }

    // Construtor da classe Livro (com id)
    // Inicializa o livro com id, título, autor, editora, ano e define que o livro não está emprestado
    public Livro(int id, String titulo, String autor, String editora, int ano) {
        this.id = id;            // Inicializa o id do livro com o valor fornecido
        this.titulo = titulo;    // Inicializa o título com o valor fornecido
        this.autor = autor;      // Inicializa o autor com o valor fornecido
        this.editora = editora;  // Inicializa a editora com o valor fornecido
        this.ano = ano;          // Inicializa o ano de publicação com o valor fornecido
        this.emprestado = false; // Inicializa o estado do livro como não emprestado
    }

    // Método getter para obter o autor do livro
    public String getAutor() {
        return autor;
    }

    // Método setter para definir o autor do livro
    public void setAutor(String autor) {
        this.autor = autor;
    }

    // Método getter para obter o título do livro
    public String getTitulo() {
        return titulo;
    }

    // Método setter para definir o título do livro
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Método getter para obter a editora do livro
    public String getEditora() {
        return editora;
    }

    // Método setter para definir a editora do livro
    public void setEditora(String editora) {
        this.editora = editora;
    }

    // Método getter para obter o ano de publicação do livro
    public int getAno() {
        return ano;
    }

    // Método setter para definir o ano de publicação do livro
    public void setAno(int ano) {
        this.ano = ano;
    }

    // Método getter para verificar se o livro está emprestado
    public boolean getEmprestado() {
        return emprestado;
    }

    // Método setter para definir se o livro está emprestado
    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    // Método getter para obter o id do livro
    public int getId() {
        return id;
    }
}
