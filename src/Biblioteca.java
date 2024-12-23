/*
 * Feedback visual:
 * ✅ para ações bem-sucedidas.
 * ❌ para erros.
 * ⚠️ para avisos.
 * 📚 para livros.
 * 👤 para usuários.
 * 🔄 para operações de salvar/carregar dados.
 */
import java.io.*;
import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("✅ Livro cadastrado com sucesso: " + livro.getTitulo());
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("✅ Usuário cadastrado com sucesso: " + usuario.getId() + " - " + usuario.getNome());
    }

    public void realizarEmprestimo(String isbn, int idUsuario) {
        Livro livro = buscarLivro(isbn);
        Usuario usuario = buscarUsuario(idUsuario);

        if (livro != null && usuario != null) {
            if (livro.isDisponivel() && usuario.podeEmprestar()) {
                livro.emprestar();
                usuario.adicionarLivro(livro);
                System.out.println("✅ Empréstimo realizado com sucesso! 📚 Livro: " + livro.getTitulo() + " 👤 Usuário: " + usuario.getNome());
            } else {
                System.out.println("❌ Não foi possível realizar o empréstimo. Verifique a disponibilidade do livro ou o limite de empréstimos do usuário.");
            }
        } else {
            System.out.println("❌ Livro ou usuário não encontrado.");
        }
    }

    public void realizarDevolucao(String isbn, int idUsuario) {
        Livro livro = buscarLivro(isbn);
        Usuario usuario = buscarUsuario(idUsuario);

        if (livro != null && usuario != null) {
            if (usuario.getLivrosEmprestados().contains(livro)) {
                livro.devolver();
                usuario.removerLivro(livro);
                System.out.println("✅ Devolução realizada com sucesso! 📚 Livro: " + livro.getTitulo() + " 👤 Usuário: " + usuario.getNome());
            } else {
                System.out.println("⚠️ O usuário não possui este livro emprestado.");
            }
        } else {
            System.out.println("❌ Livro ou usuário não encontrado.");
        }
    }

    public void exibirLivrosDisponiveis() {
        System.out.println("📚 Livros disponíveis:");
        boolean algumDisponivel = false;
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                livro.exibirDetalhes();
                algumDisponivel = true;
            }
        }
        if (!algumDisponivel) {
            System.out.println("⚠️ Nenhum livro disponível no momento.");
        }
    }

    private Livro buscarLivro(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        System.out.println("⚠️ Livro com ISBN " + isbn + " não encontrado.");
        return null;
    }

    Usuario buscarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        System.out.println("⚠️ Usuário com ID " + id + " não encontrado.");
        return null;
    }

    public void salvarDados() throws IOException {
        String caminhoArquivo = "biblioteca.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo)))  {
            oos.writeObject(livros);
            oos.writeObject(usuarios);
            System.out.println("✅ Dados salvos com sucesso no arquivo: " + caminhoArquivo);
        } catch (IOException exep) {
            System.out.println("❌ Erro ao salvar os dados.");
            throw exep;
        }
    }

    public void carregarDados() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("biblioteca.dat"))) {
            livros = (ArrayList<Livro>) ois.readObject();
            usuarios = (ArrayList<Usuario>) ois.readObject();
            System.out.println("✅ Dados carregados com sucesso! 🔄");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Nenhum dado salvo encontrado. Iniciando nova biblioteca.");
            throw e;
        }
    }
}