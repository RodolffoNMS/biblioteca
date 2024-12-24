import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public class BibliotecaApp {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Scanner scanner = new Scanner(System.in);
        try {
            biblioteca.carregarDados();
            System.out.println("📂 Dados carregados com sucesso! ✅");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Nenhum dado salvo encontrado. Iniciando nova biblioteca.");
        }
        while (true) {
            Menu.exibirMenu();
            int opcao = -1;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(Menssagens.Mensagem.ERRO_ISBN);
                scanner.nextLine();
                continue;
            }
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("✏️ Digite o título do livro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("✏️ Digite o autor do livro: ");
                        String autor = scanner.nextLine();
                        System.out.print("✏️ Digite o ISBN do livro (13 caracteres): ");
                        String isbn = scanner.nextLine();

                        if (isbn == null || isbn.length() != 13) {
                            System.out.println(Menssagens.Mensagem.ERRO_ISBN);
                            break;
                        }

                        biblioteca.cadastrarLivro(new Livro(titulo, autor, isbn));
                        System.out.println("✅ Livro cadastrado com sucesso! 📚");
                        break;

                    case 2:
                        System.out.print("👤 Digite o nome do usuário: ");
                        String nome = scanner.nextLine();
                        System.out.print("👤 Digite o ID do usuário: ");
                        int id = scanner.nextInt();

                        biblioteca.cadastrarUsuario(new Usuario(nome, id));
                        System.out.println("✅ Usuário cadastrado com sucesso! 👤");
                        break;

                    case 3:
                        System.out.print("📖 Digite o ISBN do livro para empréstimo: ");
                        String isbnEmprestimo = scanner.nextLine();
                        System.out.print("📖 Digite o ID do usuário: ");
                        int idUsuarioEmprestimo = scanner.nextInt();

                        biblioteca.realizarEmprestimo(isbnEmprestimo, idUsuarioEmprestimo);
                        break;

                    case 4:
                        System.out.print("🔄 Digite o ISBN do livro para devolução: ");
                        String isbnDevolucao = scanner.nextLine();
                        System.out.print("🔄 Digite o ID do usuário: ");
                        int idUsuarioDevolucao = scanner.nextInt();

                        biblioteca.realizarDevolucao(isbnDevolucao, idUsuarioDevolucao);
                        break;

                    case 5:
                        biblioteca.exibirLivrosDisponiveis();
                        break;

                    case 6:
                        System.out.print("Digite o ID do 👤 usuário para exibir os detalhes: ");
                        int idUsuarioDetalhes = scanner.nextInt();
                        Usuario usuario = biblioteca.buscarUsuario(idUsuarioDetalhes);
                        if (usuario != null) {
                            usuario.exibirDetalhes();
                        } else {
                            System.out.println("👤Usuário não encontrado.");
                        }
                        break;
                    case 7:
                        try {
                            biblioteca.salvarDados();
                            System.out.println("💾 Dados salvos com sucesso! ✅");
                        } catch (IOException e) {
                            System.out.println("❌ Erro ao salvar os dados.");
                        }
                        System.out.println("👋 Saindo do sistema... Até logo!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("❌ Opção inválida. Por favor, escolha uma opção válida.");

                }
            }catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida. Por favor, insira os dados corretamente.");
                scanner.nextLine();
            }catch (Exception e) {
                System.out.println("❌ Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }
}