package collections1;

public class Book implements Comparable<Book> {

	private final String isbn;
	private final String author;
	private final String title;

	static void checkStringValidity(String str) {
		if (str == null) {
			throw new NullPointerException();
		}
		if (str.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

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

	@Override
	public int compareTo(Book b) {
		return isbn.compareTo(b.getIsbn());
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
}
