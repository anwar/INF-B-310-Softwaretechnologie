package collections1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Library {

	private List<Book> stock;

	public Library() {
		stock = new ArrayList<Book>();
	}


	public boolean insertBook(Book newBook) {
		stock.add(newBook);
		Collections.sort(stock);
		return true;
	}


	public Book searchForIsbn(String isbn) {
		int i = Collections.binarySearch(stock, new Book(isbn));
		if (i < 0) {
			return null;
		}
		return stock.get(i);
	}


	public Collection<Book> searchForAuthor(String author) {
		Collection<Book> result = new ArrayList<Book>();
		for (int i = 0; i < stock.size(); i++) {
			Book b = stock.get(i);
			if (author.equals(b.getAuthor())) {
				result.add(b);
			}
		}
		return result;
	}
}
