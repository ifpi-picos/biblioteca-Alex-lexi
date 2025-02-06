
    package entidades;

    import java.util.ArrayList;
    import java.util.List;
    
    public class Usuario {
        private int id;
        private String nome;
        private String cpf;
        private String email;
        
        private List<Emprestimo> historicoEmprestimos = new ArrayList<>();
    
        public Usuario(int id,String nome, String cpf, String email) {
            this.id = id;
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
        }
    
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
