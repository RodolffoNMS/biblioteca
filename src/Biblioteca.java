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
        try {
            Livro livro = buscarLivro(isbn);
            Usuario usuario = buscarUsuario(idUsuario);

            if (livro != null && usuario != null) {
                if (livro.livroDisponivel() && usuario.podeEmprestar()) {
                    livro.emprestar();
                    usuario.adicionarLivro(livro);
                    System.out.println("✅ Empréstimo realizado com sucesso! 📚 Livro: " + livro.getTitulo() + " 👤 Usuário: " + usuario.getNome());
                } else {
                    System.out.println(Menssagens.Mensagem.ERRO_LIMITE_EMPRESTIMOS);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    public void realizarDevolucao(String isbn, int idUsuario) {
        try {
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
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao realizar devolução: " + e.getMessage());
        }
    }

    public void exibirLivrosDisponiveis() {
        System.out.println("📚 Livros disponíveis:");
        boolean algumDisponivel = false;
        for (Livro livro : livros) {
            if (livro.livroDisponivel()) {
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
        }
    }
}