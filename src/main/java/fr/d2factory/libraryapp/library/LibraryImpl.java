package fr.d2factory.libraryapp.library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.Member;

@Service
public class LibraryImpl implements Library {

	private static final Logger LOG = LoggerFactory.getLogger(LibraryImpl.class);

	BookRepository bookRepository;

	public LibraryImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book borrowBook(final long isbnCode, final Member member, final LocalDate borrowedAt)
			throws HasLateBooksException {
		LOG.debug("member {}: " + member + "borrow Book  with isbnCode{}: " + isbnCode);

		if (member.getBorrowBook() != null) {
			bookRepository.findBorrowedBookDate(member.getBorrowBook()).ifPresent(borrowedDate -> {
				if (ChronoUnit.DAYS.between(borrowedDate, LocalDate.now()) > member.getNumberDaysKeeping()) {
					throw new HasLateBooksException();
				}
			});
		}

		Book borrowedBook = bookRepository.findBook(isbnCode).orElseThrow(() -> new BookNotFoundException());
		bookRepository.saveBookBorrow(borrowedBook, borrowedAt);
		member.setBorrowBook(borrowedBook);
		return borrowedBook;
	}

	@Override
	public void returnBook(final Book book, final Member member) {
		LOG.debug("member {} " + member + ", return Book {}" + book);

		bookRepository.findBorrowedBookDate(book).ifPresent(borrowedDate -> {
			int keepingDays = (int) ChronoUnit.DAYS.between(borrowedDate, LocalDate.now());
			member.payBook(keepingDays);
		});
	}

}
