import java.util.ArrayList;
import java.io.Serializable;

public class Usuario implements Serializable {
    private static final int LIMITE_EMPRESTIMOS = 3;
    private static final String ERRO_LIMITE_EMPRESTIMOS = "❌ O usuário já atingiu o limite de empréstimos (%d livros).";
    private static final String ERRO_LIVRO_NAO_ENCONTRADO = "❌ O livro \"%s\" não está na lista de empréstimos.";
    private String nome;
    private int id;
    private ArrayList<Livro> livrosEmprestados;

    public Usuario(String nome, int id) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ O nome do usuário não pode ser nulo ou vazio.");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("❌ O ID do usuário deve ser um número positivo.");
        }
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
        if (livro == null) {
            throw new IllegalArgumentException("❌ O livro não pode ser nulo.");
        }
        if (livrosEmprestados.size() < LIMITE_EMPRESTIMOS) {
            livrosEmprestados.add(livro);
            System.out.println("✅ Livro \"" + livro.getTitulo() + "\" adicionado à lista de empréstimos.");
        } else {
            System.out.printf((ERRO_LIMITE_EMPRESTIMOS) + "%n", LIMITE_EMPRESTIMOS);
        }
    }

    public void removerLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("❌ O livro não pode ser nulo.");
        }
        if (livrosEmprestados.remove(livro)) {
            System.out.println("✅ Livro \"" + livro.getTitulo() + "\" removido da lista de empréstimos.");
        } else {
            System.out.printf((ERRO_LIVRO_NAO_ENCONTRADO) + "%n", livro.getTitulo());
        }
    }

    public int getId() {
        return id;
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        return new ArrayList<>(livrosEmprestados); // Retorna uma cópia para evitar modificações externas
    }

    public boolean podeEmprestar() {
        return livrosEmprestados.size() < LIMITE_EMPRESTIMOS;
    }

    public String getNome() {
        return nome;
    }
}
