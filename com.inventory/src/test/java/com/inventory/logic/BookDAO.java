package com.inventory.logic;

import java.util.List;
import com.inventory.model.Book;

public class BookDAO {

	public interface BookDao {
		// defines the methods 
		void addBook(Book book);
		List<Book> getAllBooks();
		void updateBook(Book book);
		void deleteBook(int entryID);
		Book filterBookById(int entryID);
		List<Book> filterBooksByAuthor(String author);
		List<Book> filterBooksByTitle(String title);
		List<Book> filterBooksByGenre(String genre);
	}

}