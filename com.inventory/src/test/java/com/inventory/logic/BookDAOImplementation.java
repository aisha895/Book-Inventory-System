package com.inventory.logic;

import com.inventory.connection.DatabaseConnection;
import com.inventory.logic.BookDAO.BookDao;
import com.inventory.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BookDAOImplementation implements BookDao {
	private DatabaseConnection databaseConnection = new DatabaseConnection();
	private Connection connection = databaseConnection.connect();


	@Override
	public void addBook(Book book) {
		String sql = "INSERT INTO Books (Title, Author, Genre, PublicationDate, ISBN) VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getGenre());
			pstmt.setString(4, book.getPublicationDate());
			pstmt.setString(5, book.getIsbn());
			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void updateBook(Book updatedBook) {
		String sql = "UPDATE Books SET Title = ?, Author = ?, Genre = ?, PublicationDate = ?, ISBN = ? WHERE EntryID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, updatedBook.getTitle());
			pstmt.setString(2, updatedBook.getAuthor());
			pstmt.setString(3, updatedBook.getGenre());
			pstmt.setString(4, updatedBook.getPublicationDate());
			pstmt.setString(5, updatedBook.getIsbn());
			pstmt.setInt(6, updatedBook.getEntryID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deleteBook(int entryID) {
		String sql = "DELETE FROM Books WHERE EntryID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, entryID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Book> getAllBooks() {
		ArrayList<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM Books";
		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				books.add(new Book(
						rs.getString("Title"),
						rs.getString("Author"),
						rs.getString("Genre"),
						rs.getString("PublicationDate"),
						rs.getString("ISBN")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public Book filterBookById(int entryID) {
		String sql = "SELECT * FROM Books WHERE EntryID = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, entryID);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new Book(
							rs.getInt("EntryID"),
							rs.getString("Title"),
							rs.getString("Author"),
							rs.getString("Genre"),
							rs.getString("PublicationDate"),
							rs.getString("ISBN")
							);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Book> filterBooksByAuthor(String author) {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM Books WHERE Author LIKE ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, "%" + author + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					books.add(extractBookFromResultSet(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	public List<Book> filterBooksByTitle(String title) {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM Books WHERE Title LIKE ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, "%" + title + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					books.add(extractBookFromResultSet(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public List<Book> filterBooksByGenre(String genre) {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM Books WHERE Genre LIKE ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, "%" + genre + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					books.add(extractBookFromResultSet(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	public List<Book> filterBooksByISBN(String isbn) {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM Books WHERE ISBN LIKE ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, "%" + isbn + "%"); 
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					books.add(extractBookFromResultSet(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	private Book extractBookFromResultSet(ResultSet rs) throws SQLException {
		return new Book(
				rs.getInt("EntryID"),
				rs.getString("Title"),
				rs.getString("Author"),
				rs.getString("Genre"),
				rs.getString("PublicationDate"),
				rs.getString("ISBN")
				);
	}
}