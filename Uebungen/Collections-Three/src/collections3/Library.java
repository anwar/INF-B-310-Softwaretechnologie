package collections3;

import java.util.*;

public class Library {

	private Map<String, Set<Book>> stock;

	public Library() {
		stock = new TreeMap<>();
	}


	public boolean insertBook(Book newBook) {
		Set<Book> books = stock.get(newBook.getAuthor());
		if (books == null) {
			books = new TreeSet<Book>();
			stock.put(newBook.getAuthor(), books);
		}
		return books.add(newBook);
	}


	public Book searchForIsbn(String isbn) {
		for (Map.Entry<String, Set<Book>> m : stock.entrySet()) {
			for (Book b : m.getValue()) {
				Book b2 = new Book(isbn);
				if (b2.equals(b)) {
					return b;
				}
			}
		}
		return null;
	}


	public Collection<Book> searchForAuthor(String author) {
		Collection<Book> result = new ArrayList<Book>();
		for (Map.Entry<String, Set<Book>> m : stock.entrySet()) {
			if (author.equals(m.getKey())) {
				result.addAll(m.getValue());
			}
		}
		return result;
	}

	public Map<String, Set<Book>> listStockByAuthor() {
		return stock;
	}

	public Collection<Book> getStock() {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Map.Entry<String, Set<Book>> m : stock.entrySet()) {
			result.addAll(m.getValue());
		}
		Collections.sort(result);
		return result;
	}
}
