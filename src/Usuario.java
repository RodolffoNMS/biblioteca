/*
 * Feedback visual:
 * 👤 para representar o usuário.
 * 📚 para indicar livros emprestados.
 * 📖 para listar os títulos dos livros.
 * ✅ para ações bem-sucedidas.
 * ❌ para erros.
 * ⚠️ para avisos.
 */

import java.util.ArrayList;
import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 122024;
    private static final int LIMITE_EMPRESTIMOS = 3;
    private String nome;
    private int id;
    private ArrayList<Livro> livrosEmprestados;

    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.livrosEmprestados = new ArrayList<>();
    }

    public void exibirDetalhes() {
        System.out.println("👤 Detalhes do Usuário:");
        System.out.println("   🆔 ID: " + id);
        System.out.println("   📛 Nome: " + nome);
        if (livrosEmprestados.isEmpty()) {
            System.out.println("   ⚠️ Não possui livros emprestados.");
        } else {
            System.out.println("   📚 Livros emprestados:");
            for (Livro livro : livrosEmprestados) {
                System.out.println("      📖 - " + livro.getTitulo());
            }
        }
    }

    public void adicionarLivro(Livro livro) {
        if (livrosEmprestados.size() < 3) {
            livrosEmprestados.add(livro);
            System.out.println("✅ Livro \"" + livro.getTitulo() + "\" adicionado à lista de empréstimos.");
        } else {
            System.out.println("❌ O usuário já possui 3 livros emprestados. Não é possível emprestar mais.");
        }
    }

    public void removerLivro(Livro livro) {
        if (livrosEmprestados.remove(livro)) {
            System.out.println("✅ Livro \"" + livro.getTitulo() + "\" removido da lista de empréstimos.");
        } else {
            System.out.println("❌ O livro \"" + livro.getTitulo() + "\" não está na lista de empréstimos.");
        }
    }

    public int getId() {
        return id;
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public boolean podeEmprestar() {
        return livrosEmprestados.size() < LIMITE_EMPRESTIMOS;
    }

    public String getNome() {
        return nome;
    }
}
