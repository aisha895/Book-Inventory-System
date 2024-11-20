package com.inventory.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.inventory.logic.BookDAOImplementation;
import com.inventory.model.Book;

public class AllBooks extends JFrame{
	private BookDAOImplementation bookDAO;

	public AllBooks() {
		bookDAO = new BookDAOImplementation(); 

		setTitle("Book Details");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		String[] columnNames = {"Title", "Author", "Genre", "Publication Date", "ISBN"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		JPanel ButtonPanel = new JPanel();
		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new BackButtonLogic());
		ButtonPanel.add(BackButton);
		add(ButtonPanel, BorderLayout.SOUTH);
		ButtonPanel.setBackground(new Color(200, 230, 255)); 


		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 14));
		header.setBackground(new Color(200, 230, 255)); 
		header.setForeground(Color.BLACK);
		setSize(800, 500);
		table.setShowGrid(true);
		table.setGridColor(new Color(200, 200, 200)); 

		add(new JScrollPane(table), BorderLayout.CENTER);
		table.setRowHeight(30);


		// Get the information from the database 
		List<Book> books = bookDAO.getAllBooks();
		for (Book book : books) {
			Object[] rowData = {
					book.getTitle(),
					book.getAuthor(),
					book.getGenre(),
					book.getPublicationDate(),
					book.getIsbn()
			};
			tableModel.addRow(rowData);
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