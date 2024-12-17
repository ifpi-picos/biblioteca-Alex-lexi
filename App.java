import dominios.Emprestimo;
import dominios.Livro;
import dominios.Usuario;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Livro listaDeLivros[] = new Livro[100];
        Usuario listaDeUsuarios[] = new Usuario[100];

        int contadorDeLivros = 0;
        int contadorDeUsuarios = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "\n1 - Cadastrar Livro\n2 - Listar todos os livros\n3 - Listar livros emprestados e disponiveis\n4 - Listar histórico de emprestimos do usuario\n5 - Pegar livro emprestado\n6 - Devolver livro\n7 - Cadastrar Usuario\n8 - Sair\n");
            String op = scanner.nextLine();

            switch (op) {
                case "1":
                    System.out.println("Digite o titulo do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.println("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.println("Digite a editora do livro: ");
                    String editora = scanner.nextLine();
                    System.out.println("Digite o ano do livro: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    Livro livro = new Livro(titulo, autor, editora, ano);

                    listaDeLivros[contadorDeLivros] = livro;
                    contadorDeLivros++;

                    System.out.println("Livro cadastrado com sucesso!");
                    break;

                case "2":
                    for (int i = 0; i < contadorDeLivros; i++) {
                        System.out.println((i + 1) + " - Titulo: " + listaDeLivros[i].getTitulo() + " Autor: "
                                + listaDeLivros[i].getAutor() + " Editora: " + listaDeLivros[i].getEditora() + " Ano: "
                                + listaDeLivros[i].getAno());
                    }
                    break;

                case "3":
                    System.out.println("Livros emprestados: \n");
                    for (int i = 0; i < contadorDeLivros; i++) {
                        if (listaDeLivros[i].getEmprestado()) {
                            System.out.println("Titulo: " + listaDeLivros[i].getTitulo() + " Autor: "
                                    + listaDeLivros[i].getAutor() + " Editora: " + listaDeLivros[i].getEditora()
                                    + " Ano: " + listaDeLivros[i].getAno());
                        }
                    }

                    System.out.println("Livros disponíveis: \n");
                    for (int i = 0; i < contadorDeLivros; i++) {
                        if (!listaDeLivros[i].getEmprestado()) {
                            System.out.println("Titulo: " + listaDeLivros[i].getTitulo() + " Autor: "
                                    + listaDeLivros[i].getAutor() + " Editora: " + listaDeLivros[i].getEditora()
                                    + " Ano: " + listaDeLivros[i].getAno());
                        }
                    }
                    break;

                case "4":
                    System.out.println("Digite o CPF do usuário para ver o histórico de empréstimos: ");
                    String cpfUsuario = scanner.nextLine();
                    Usuario usuarioEncontrado = null;
                    for (int i = 0; i < contadorDeUsuarios; i++) {
                        if (listaDeUsuarios[i].getCpf().equals(cpfUsuario)) {
                            usuarioEncontrado = listaDeUsuarios[i];
                            break;
                        }
                    }

                    if (usuarioEncontrado != null) {
                        System.out.println("Mostrando emprestimos: ");
                        for (Emprestimo emp : usuarioEncontrado.getHistoricoEmprestimos()) {
                            System.out.println("Titulo do livro: " + emp.getLivro().getTitulo() + " Nome do usuario: "
                                    + emp.getUsuario().getNome() + " Data emprestimo: " + emp.getDataEmprestimo()
                                    + " Data devolução: " + emp.getDataDevolucao());
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case "5":
                    System.out.println("Digite o CPF do usuário que deseja pegar um livro emprestado: ");
                    cpfUsuario = scanner.nextLine();
                    usuarioEncontrado = null;
                    for (int i = 0; i < contadorDeUsuarios; i++) {
                        if (listaDeUsuarios[i].getCpf().equals(cpfUsuario)) {
                            usuarioEncontrado = listaDeUsuarios[i];
                            break;
                        }
                    }

                    if (usuarioEncontrado != null) {
                        System.out.println("Digite o titulo do livro que deseja pegar emprestado");
                        String tituloLivro = scanner.nextLine();
                        System.out.println("Digite a data de devolução (dd/mm/aaaa)");
                        String dataDevolucao = scanner.nextLine();

                        for (int i = 0; i < contadorDeLivros; i++) {
                            if (listaDeLivros[i].getTitulo().equals(tituloLivro)
                                    && !listaDeLivros[i].getEmprestado()) {
                                Livro livroAchado = listaDeLivros[i];
                                livroAchado.setEmprestado(true);
                                Emprestimo emprestimo = new Emprestimo(dataDevolucao, livroAchado, usuarioEncontrado);
                                emprestimo.realizarEmprestimo();
                                usuarioEncontrado.adicionarEmprestimo(emprestimo);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case "6":
                    System.out.println("Digite o CPF do usuário que deseja devolver um livro: ");
                    cpfUsuario = scanner.nextLine();
                    usuarioEncontrado = null;
                    for (int i = 0; i < contadorDeUsuarios; i++) {
                        if (listaDeUsuarios[i].getCpf().equals(cpfUsuario)) {
                            usuarioEncontrado = listaDeUsuarios[i];
                            break;
                        }
                    }

                    if (usuarioEncontrado != null) {
                        System.out.println("Digite o titulo do livro que deseja devolver: ");
                        String tituloLivroParaDevolver = scanner.nextLine();

                        for (int i = 0; i < contadorDeLivros; i++) {
                            if (listaDeLivros[i].getTitulo().equals(tituloLivroParaDevolver)
                                    && listaDeLivros[i].getEmprestado()) {
                                Livro livroAchado = listaDeLivros[i];
                                livroAchado.setEmprestado(false);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case "7":
                    System.out.println("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o CPF do usuário: ");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite o email do usuário: ");
                    String email = scanner.nextLine();
                    Usuario usuario = new Usuario(nome, cpf, email);

                    listaDeUsuarios[contadorDeUsuarios] = usuario;
                    contadorDeUsuarios++;

                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case "8":
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