package collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Library {

	private Set<Book> stock;

	public Library() {
		stock = new TreeSet<Book>();
	}


	public boolean insertBook(Book newBook) {
		return stock.add(newBook);
	}


	public Book searchForIsbn(String isbn) {
		for (Book b : stock) {
			Book b2 = new Book(isbn);
			if (b2.equals(b)) {
				return b;
			}
		}
		return null;
	}


	public Collection<Book> searchForAuthor(String author) {
		Collection<Book> result = new ArrayList<Book>();
		for (Book b : stock) {
			if (author.equals(b.getAuthor())) {
				result.add(b);
			}
		}
		return result;
	}

	public Collection<Book> getStock() {
		return stock;
	}
}
