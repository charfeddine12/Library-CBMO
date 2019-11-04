package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

/**
 * The book repository emulates a database via 2 HashMaps
 */
@Repository
public class BookRepository {
	private Map<ISBN, Book> availableBooks = new HashMap<>();
	private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

	public void addBooks(List<Book> books) {
		if (books != null) {
			books.forEach(book -> availableBooks.computeIfAbsent(book.getIsbn(), key -> book));
		}
	}

	public Optional<Book> findBook(long isbnCode) {
		return Optional.of(new ISBN(isbnCode)).map(availableBooks::get);
	}

	public void saveBookBorrow(Book book, LocalDate borrowedAt) {

		if (book != null && borrowedAt != null) {
			borrowedBooks.put(book, borrowedAt);
			availableBooks.remove(book.getIsbn()).getIsbn();
		}

	}

	public Optional<LocalDate> findBorrowedBookDate(Book book) {
		return Optional.ofNullable(book).map(borrowedBooks::get);
	}
}
