
    package dominios;

    import java.util.ArrayList;
    import java.util.List;
    
    public class Usuario {
        private String nome;
        private String cpf;
        private String email;
        
        private List<Emprestimo> historicoEmprestimos = new ArrayList<>();
    
        public Usuario(String nome, String cpf, String email) {
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
        }
    
        public String getNome() {
            return nome;
        }
    
        public void setNome(String nome) {
            this.nome = nome;
        }
    
        public String getCpf() {
            return cpf;
        }
    
        public void setCpf(String cpf) {
            this.cpf = cpf;
        }
    
        public String getEmail() {
            return email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
        public List<Emprestimo> getHistoricoEmprestimos() {
            return historicoEmprestimos;
        }
    
        public void adicionarEmprestimo(Emprestimo emprestimo) {
            this.historicoEmprestimos.add(emprestimo);
        }
    }
