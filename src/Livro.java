/*
 * Feedback visual:
 * 📖 para representar o título do livro.
 * 📚 para indicar a disponibilidade.
 * ✅ para ações bem-sucedidas.
 * ❌ para erros.
 * ⚠️ para avisos.
 */
import java.io.Serializable;
public class Livro implements Serializable{
    private static final long serialVersionUID = 122024;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn) {
        if (isbn == null || isbn.length() != 13) {
            throw new IllegalArgumentException("❌ O ISBN é um código de barras EAN-13. Ele deve conter 13 caracteres.");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true; // Por padrão, ao cadastrar, o Livro encontra-se disponível
    }

    public void exibirDetalhes() {
        System.out.printf("📖 Título: %s, Autor: %s, ISBN: %s%n", titulo, autor, isbn);
        System.out.println("📚 Disponibilidade: " + (disponivel ? "✅ Disponível" : "❌ Indisponível"));
    }

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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.length() != 13) {
            throw new IllegalArgumentException("❌ O ISBN é um código de barras EAN-13. Ele deve conter 13 caracteres.");
        }
        this.isbn = isbn;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}