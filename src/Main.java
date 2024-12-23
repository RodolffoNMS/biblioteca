/*
 * Feedback visual:
 * ✅ para ações bem-sucedidas.
 * ❌ para erros.
 * ⚠️ para avisos.
 * 📚, 📖, 🔄, 👤, etc., para representar diferentes ações.
 */

import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        // Teastando de salvamento e carregamento
        try {
            biblioteca.carregarDados();
            System.out.println("📂 Dados carregados com sucesso! ✅");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Nenhum dado salvo encontrado. Iniciando nova biblioteca.");
        }
/*
////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Dados para teste:
        biblioteca.cadastrarLivro(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "1234567890123"));
        biblioteca.cadastrarUsuario(new Usuario("João Silva", 1));

        try {
            biblioteca.salvarDados();
            System.out.println("💾 Dados salvos com sucesso! ✅");
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar os dados.");
        }
        // Tente carregar novamente
        try {
            biblioteca.carregarDados();
            System.out.println("📂 Dados carregados novamente com sucesso! ✅");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Erro ao carregar os dados.");
        }
///////////////////////////////////////////////////////////////////////////////////////////////////*/

        Scanner scanner = new Scanner(System.in);
        try {
            biblioteca.carregarDados();
            System.out.println("📂 Dados carregados com sucesso! ✅");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Nenhum dado salvo encontrado. Iniciando nova biblioteca.");
        }

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("✏️ Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("✏️ Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("✏️ Digite o ISBN do livro (13 caracteres): ");
                    String isbn = scanner.nextLine();

                    if (isbn == null || isbn.length() != 13) {
                        System.out.println("❌ Erro: O ISBN deve ter exatamente 13 caracteres. Tente novamente.");
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
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n📚 Bem-vindo ao Sistema de Gerenciamento de Biblioteca!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. ✏️ Cadastrar livro");
        System.out.println("2. 👤 Cadastrar usuário");
        System.out.println("3. 📖 Realizar empréstimo");
        System.out.println("4. 🔄 Realizar devolução");
        System.out.println("5. 📋 Exibir livros disponíveis");
        System.out.println("6. 👤 Exibir detalhes do usuário");
        System.out.println("7. 💾 Salvar");
        System.out.println("0. ❌ Sair");
        System.out.print("Opção: ");
    }
}