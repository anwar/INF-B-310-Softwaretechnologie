package collections2;

import java.util.Objects;

public class Book implements Comparable<Book> {

	private final String isbn;
	private final String author;
	private final String title;

	public Book(String isbn) {
		checkStringValidity(isbn);

		this.isbn = isbn;
		this.author = "";
		this.title = "";
	}

	public Book(String isbn, String author, String title) {
		checkStringValidity(isbn);
		checkStringValidity(author);
		checkStringValidity(title);

		this.isbn = isbn;
		this.author = author;
		this.title = title;
	}

	static void checkStringValidity(String str) {
		if (str == null) {
			throw new NullPointerException();
		}
		if (str.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int compareTo(Book b) {
		return isbn.compareTo(b.getIsbn());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Book)) return false;
		Book b = (Book) o;
		return isbn.equals(b.getIsbn());
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	public String getIsbn() {
		return isbn;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return String.format("%s by %s (ISBN %s)",
				title, author, isbn);
	}
}
