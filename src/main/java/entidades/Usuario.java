package entidades;

import java.util.ArrayList;
import java.util.List;

// Classe que representa um usuário
public class Usuario {
    // Atributos da classe Usuario
    private int id;                    // Identificador único do usuário
    private String nome;               // Nome do usuário
    private String cpf;                // CPF do usuário
    private String email;              // Email do usuário
    
    private List<Emprestimo> historicoEmprestimos = new ArrayList<>(); // Lista para armazenar o histórico de empréstimos do usuário

    // Construtor da classe Usuario (com id)
    // Inicializa o usuário com id, nome, cpf e email
    public Usuario(int id, String nome, String cpf, String email) {
        this.id = id;        
        this.nome = nome;     
        this.cpf = cpf;      
        this.email = email;  
    }
    
    // Construtor da classe Usuario (sem id)
    // Inicializa o usuário apenas com nome, cpf e email
    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;    
        this.cpf = cpf;       
        this.email = email;   
    }

    // Método getter para obter o id do usuário
    public int getId() {
        return id;
    }

    // Método setter para definir o id do usuário
    public void setId(int id) {
        this.id = id;
    }

    // Método getter para obter o nome do usuário
    public String getNome() {
        return nome;
    }

    // Método setter para definir o nome do usuário
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método getter para obter o CPF do usuário
    public String getCpf() {
        return cpf;
    }

    // Método setter para definir o CPF do usuário
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Método getter para obter o email do usuário
    public String getEmail() {
        return email;
    }

    // Método setter para definir o email do usuário
    public void setEmail(String email) {
        this.email = email;
    }

    // Método getter para obter o histórico de empréstimos do usuário
    public List<Emprestimo> getHistoricoEmprestimos() {
        return historicoEmprestimos;
    }

    // Método para adicionar um empréstimo ao histórico de empréstimos do usuário
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.historicoEmprestimos.add(emprestimo); // Adiciona o empréstimo na lista
    }
}
