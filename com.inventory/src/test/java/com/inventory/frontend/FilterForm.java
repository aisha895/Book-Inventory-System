package com.inventory.frontend;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.inventory.logic.BookDAOImplementation;
import com.inventory.model.Book;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class FilterForm extends JFrame{
	private JComboBox<String> filterAttributeDropdown;
	private JTextField filterValueField;
	private JButton searchButton;
	private JTable resultsTable;
	private BookDAOImplementation BookDAO = new BookDAOImplementation(); 


	public FilterForm() {

		setTitle("Filter Books");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Filter drop down options created 
		JPanel filterPanel = new JPanel(new FlowLayout());
		filterAttributeDropdown = new JComboBox<>(new String[] {"Title", "Author", "Genre","ID","ISBN"});
		filterPanel.add(new JLabel("Filter By:"));
		filterPanel.add(filterAttributeDropdown);

		filterValueField = new JTextField(15);
		filterPanel.add(new JLabel("Search Term:"));
		filterPanel.add(filterValueField);

		// Button created for searching through the stored books
		searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener());
		filterPanel.add(searchButton);

		// Table created with column headings to display the searched data
		String[] columnNames = {"Entry ID", "Title", "Author", "Genre", "Publication Date", "ISBN"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		resultsTable = new JTable(tableModel);


		// Layout of the table
		resultsTable.setShowGrid(true);
		resultsTable.setGridColor(new Color(200, 230, 255));


		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new BackButtonLogic());
		JPanel BackButtonPanel = new JPanel();
		BackButtonPanel.add(BackButton);

		// Layout 
		add(filterPanel, BorderLayout.NORTH);
		add(BackButtonPanel,BorderLayout.SOUTH);
		add(new JScrollPane(resultsTable), BorderLayout.CENTER);
		filterPanel.setBackground(new Color(200, 230, 255)); 

	}

	private class SearchButtonListener implements ActionListener {
		// Logic behind the filtering of the data
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedAttribute = filterAttributeDropdown.getSelectedItem().toString();
			String filterValue = filterValueField.getText();

			if (filterValue.isEmpty()) {
				JOptionPane.showMessageDialog(FilterForm.this, "Please enter a search term", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			List <Book> Books = new ArrayList<>();

			if(selectedAttribute.equals("Author")){
				Books = BookDAO.filterBooksByAuthor(filterValue);
			}
			if(selectedAttribute.equals("Title")) { 
				Books = BookDAO.filterBooksByTitle(filterValue);
			}
			if(selectedAttribute.equals("Genre")){
				Books = BookDAO.filterBooksByGenre(filterValue);
			}
			if(selectedAttribute.equals("ID")){
				Book book = new Book();
				book = BookDAO.filterBookById(Integer.parseInt(filterValue));
			}
			else if (selectedAttribute.equals("ISBN")) {
				Books = BookDAO.filterBooksByISBN(filterValue);
			}
			if(Books != null) {
				DefaultTableModel tableModel = (DefaultTableModel) resultsTable.getModel();
				tableModel.setRowCount(0); 

				for (Book book : Books) {
					Object[] rowData = {
							book.getEntryID(),
							book.getTitle(),
							book.getAuthor(),
							book.getGenre(),
							book.getPublicationDate(),
							book.getIsbn()
					};
					tableModel.addRow(rowData);
				}
				resultsTable.setModel(tableModel);
			}
			// Displaying a pop up message if there are no given values after filtering
			if (Books.isEmpty()){
				JOptionPane.showMessageDialog(FilterForm.this, "No given information", "No Matches", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	}

	private class BackButtonLogic implements ActionListener {
		@Override

		public void actionPerformed(ActionEvent e) {
			setVisible(false); 
			MainForm mainForm = new MainForm();
			mainForm.setVisible(true);
		}
	}
}