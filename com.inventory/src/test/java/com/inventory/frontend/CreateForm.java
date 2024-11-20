package com.inventory.frontend;
import javax.swing.*;

import com.inventory.logic.BookDAOImplementation;
import com.inventory.model.Book;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateForm extends JFrame{
	private JTextField TitleField;
	private JTextField AuthorField;
	private JTextField GenreField;
	private JTextField PublicationDateField;
	private JTextField ISBNField;
	private JButton SubmitButton;
	private JButton BackButton;
	private BookDAOImplementation bookDao;


	public CreateForm () {

		setTitle("Add New Book");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// fields created to add input in each of the categories 
		JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

		panel.add(new JLabel("Title:"));
		TitleField = new JTextField();
		panel.add(TitleField);

		panel.add(new JLabel("Author:"));
		AuthorField = new JTextField();
		panel.add(AuthorField);

		panel.add(new JLabel("Genre:"));
		GenreField = new JTextField();
		panel.add(GenreField);

		panel.add(new JLabel("Publication Date (YYYY-MM-DD):"));
		PublicationDateField = new JTextField();
		panel.add(PublicationDateField);

		panel.add(new JLabel("ISBN:"));
		ISBNField = new JTextField();
		panel.add(ISBNField);

		SubmitButton = new JButton("Add Book");
		SubmitButton.addActionListener(new SubmitButtonLogic());

		JPanel ButtonPanel = new JPanel();

		BackButton = new JButton("Back");
		BackButton.addActionListener(new BackButtonLogic());

		ButtonPanel.add(BackButton);
		ButtonPanel.add(SubmitButton);

		add(panel, BorderLayout.CENTER);
		add(ButtonPanel, BorderLayout.SOUTH);

	}

	private class SubmitButtonLogic implements ActionListener {

		private BookDAOImplementation BookDAO = new BookDAOImplementation();
		// This method ensures that the correct information is added into the fields & adds the data to the database through the DAO implementation
		@Override
		public void actionPerformed(ActionEvent e) {
			String title = TitleField.getText();
			String author = AuthorField.getText();
			String genre = GenreField.getText();
			String publicationDate = PublicationDateField.getText();
			String isbn = ISBNField.getText();

			if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || publicationDate.isEmpty() || isbn.isEmpty()) {
				JOptionPane.showMessageDialog(CreateForm.this, "All fields required", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Book book = new Book(title, author, genre, publicationDate, isbn);
			book.setTitle(title);
			book.setAuthor(author);
			book.setGenre(genre);
			book.setPublicationDate(publicationDate);
			book.setIsbn(isbn);  
			BookDAO.addBook(book);
			JOptionPane.showMessageDialog(CreateForm.this, "Books has been successfully added " , "Success", JOptionPane.INFORMATION_MESSAGE);
			TitleField.setText("");
			AuthorField.setText("");
			GenreField.setText("");
			PublicationDateField.setText("");
			ISBNField.setText("");
		}
	}
	private class BackButtonLogic implements ActionListener {
		@Override

		public void actionPerformed(ActionEvent e) {
			setVisible(false);  // Hide CreateForm
			MainForm mainForm = new MainForm();
			mainForm.setVisible(true);
		}
	}
}
