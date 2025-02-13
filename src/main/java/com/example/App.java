package com.example;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import dao.ConectDao;
import dao.EmprestimoDao;
import dao.LivroDao;
import dao.UsuarioDao;
import entidades.Emprestimo;
import entidades.Livro;
import entidades.Usuario;

public class App {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Connection conn = ConectDao.getConect();
        if (conn != null) {
            System.out.println("Ufa deu certo");
        } else {
            System.out.println("Minha nossa, VIXE, VIXE, VIXE");
        }

        new App().executar();
    }

    public void executar() {
        while (true) {
            System.out.println("\n1 - Cadastrar Livro\n2 - Listar todos os livros\n3 - Listar livros emprestados e disponíveis\n4 - Listar histórico de empréstimos do usuário\n5 - Pegar livro emprestado\n6 - Devolver livro\n7 - Cadastrar Usuário\n8 - Sair\n");
            String op = scanner.nextLine();

            switch (op) {
                case "1": // Cadastrar um novo livro
                    System.out.println("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.println("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.println("Digite a editora do livro: ");
                    String editora = scanner.nextLine();
                    System.out.println("Digite o ano do livro: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();

                    Livro livro = new Livro(titulo, autor, editora, ano);
                    new LivroDao().adicionarLivro(livro);

                    System.out.println("Livro cadastrado com sucesso!");
                    break;

                case "2": // Listar todos os livros cadastrados
                    List<Livro> livros = new LivroDao().listarLivros();

                    for (Livro literatura : livros) {
                        System.out.println("Titulo: " + literatura.getTitulo() + "\nAutor: " + literatura.getAutor() + "\nEmprestado: " + literatura.getEmprestado() + "\n");
                    }
                    break;

                case "3": // Listar livros emprestados e disponíveis
                    System.out.println("Livros emprestados: \n");
                    List<Livro> livrosEmprestados = new LivroDao().ListarLivrosEmprestados();
                    for (Livro livroEmprestado : livrosEmprestados) {
                        System.out.println("Titulo: " + livroEmprestado.getTitulo() + "\nAutor: " + livroEmprestado.getAutor() + "\nEmprestado: " + livroEmprestado.getEmprestado() + "\n");
                    }

                    System.out.println("Livros disponíveis: \n");
                    List<Livro> livrosDisponiveis = new LivroDao().ListarLivrosDisponiveis();
                    for (Livro livroDisponivel : livrosDisponiveis) {
                        System.out.println("Titulo: " + livroDisponivel.getTitulo() + "\nAutor: " + livroDisponivel.getAutor() + "\nEmprestado: " + livroDisponivel.getEmprestado() + "\n");
                    }
                    break;

                case "4": // Listar histórico de empréstimos de um usuário
                    System.out.println("Digite o CPF do usuário: ");
                    String cpfUsuario = scanner.nextLine();
                    Usuario usuarioEncontrado = new UsuarioDao().buscarPorCPF(cpfUsuario);

                    if (usuarioEncontrado != null) {
                        List<Emprestimo> historico = new EmprestimoDao().listarHistoricoEmprestimos(cpfUsuario);
                        for (Emprestimo emp : historico) {
                            System.out.println("Título do livro: " + emp.getTituloLivro() + "\nData de empréstimo: " + emp.getDataEmprestimo() + "\nData de Devolução: " + emp.getDataDevolucao() + "\n");
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case "5": // Emprestar um livro
                    System.out.println("Digite o CPF do usuário: ");
                    cpfUsuario = scanner.nextLine();
                    Usuario usuarioBuscado = new UsuarioDao().buscarPorCPF(cpfUsuario);

                    System.out.println("Digite o título do livro: ");
                    String tituloLivro = scanner.nextLine();
                    Livro livroBuscado = new LivroDao().buscarPorTitulo(tituloLivro);

                    if (usuarioBuscado != null && livroBuscado != null && !livroBuscado.getEmprestado()) {
                        System.out.println("Digite a data de devolução (dd/MM/yyyy): ");
                        String dataDevolucao = scanner.nextLine();

                        Emprestimo emprestimo = new Emprestimo(dataDevolucao, livroBuscado.getTitulo(), usuarioBuscado.getNome(), usuarioBuscado.getCpf());
                        new EmprestimoDao().inserirEmprestimo(emprestimo);
                        livroBuscado.setEmprestado(true); // Marca o livro como emprestado
                        new LivroDao().atualizarStatusEmprestimo(livroBuscado);
                        System.out.println("Empréstimo realizado com sucesso!");
                    } else {
                        System.out.println("Impossível realizar esse empréstimo.");
                    }
                    break;

                    case "6": // Devolver um livro
                    System.out.println("Digite o título do livro: ");
                    tituloLivro = scanner.nextLine().trim(); // Remove espaços desnecessários
                    Livro livroDevolvido = new LivroDao().buscarPorTitulo(tituloLivro);
                
                    if (livroDevolvido != null && livroDevolvido.getEmprestado()) {
                        System.out.println("Digite o CPF do usuário: ");
                        cpfUsuario = scanner.nextLine().trim();
                
                        Usuario usuarioBuscado2 = new UsuarioDao().buscarPorCPF(cpfUsuario);
                        if (usuarioBuscado2 != null) {
                            System.out.println("Digite a data de devolução (dd/MM/yyyy): ");
                            String dataDevolucao = scanner.nextLine().trim();
                
                            try {
                                // Atualiza a data de devolução
                                new EmprestimoDao().atualizarDataDevolucao(usuarioBuscado2.getCpf(), dataDevolucao);
                
                                // Marca o livro como disponível
                                livroDevolvido.setEmprestado(false); 
                                new LivroDao().atualizarStatusEmprestimo(livroDevolvido);
                
                                System.out.println("Livro '" + tituloLivro + "' devolvido com sucesso!");
                            } catch (Exception e) {
                                System.err.println("Erro ao processar devolução: " + e.getMessage());
                            }
                
                        } else {
                            System.out.println("Usuário não encontrado. Verifique o CPF.");
                        }
                
                    } else {
                        System.out.println("Livro não encontrado ou não está emprestado.");
                    }
                    break;
                

                case "7": // Cadastrar um novo usuário
                    System.out.println("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite o email: ");
                    String email = scanner.nextLine();

                    Usuario usuario = new Usuario(nome, cpf, email);
                    new UsuarioDao().adicionar(usuario);
                    break;

                case "8": // Sair do programa
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
