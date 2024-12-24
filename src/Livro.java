import java.io.Serializable;

public class Livro implements Serializable, Emprestavel{
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn) {
        if (isbn == null || isbn.length() != 13) {
            throw new IllegalArgumentException(Menssagens.Mensagem.ERRO_ISBN);
        }
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }

    public void exibirDetalhes() {
        System.out.printf("📖 Título: %s, Autor: %s, ISBN: %s%n", titulo, autor, isbn);
        System.out.println("📚 Disponibilidade: " + (disponivel ? "✅ Disponível" : "❌ Indisponível"));
    }

    @Override
    public boolean emprestar() {
        if (disponivel) {
            disponivel = false;
            System.out.println("✅ Livro \"" + titulo + "\" emprestado com sucesso!");
            return true;
        } else {
            System.out.println("❌ O título \"" + titulo + "\" não está disponível para empréstimos.");
            return false;
        }
    }

    @Override
    public void devolver() {
        if (!disponivel) {
            disponivel = true;
            System.out.println("✅ Livro \"" + titulo + "\" devolvido com sucesso!");
        } else {
            System.out.println("⚠️ O livro \"" + titulo + "\" já está disponível.");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean livroDisponivel() {
        return disponivel;
    }
}