package com.inventory.frontend;

import com.inventory.logic.BookDAOImplementation;
import com.inventory.model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExportForm  extends JFrame{
	private JButton exportButton;
	private BookDAOImplementation bookDAO;

	public ExportForm() {
		bookDAO = new BookDAOImplementation();
		//Layout of the GUI page 
		setTitle("Export Books to CSV");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 

		// Created a button to Export
		exportButton = new JButton("Export Books to CSV");
		exportButton.addActionListener(new ExportButtonListener());
		buttonPanel.add(exportButton);

		JPanel ButtonPanel = new JPanel();
		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new BackButtonLogic());

		ButtonPanel.add(BackButton);
		add(ButtonPanel, BorderLayout.SOUTH);
		add(buttonPanel, BorderLayout.CENTER);
		ButtonPanel.setBackground(new Color(200, 230, 255)); 
	}

	private class ExportButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save CSV File");
			int userSelection = fileChooser.showSaveDialog(ExportForm.this);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
				if (!filePath.endsWith(".csv")) {
					filePath += ".csv";
				}
				exportBooksToCSV(filePath);
			}
		}
		//Extracting all the information from the database and displaying the books datas
		private void exportBooksToCSV(String filePath) {
			ArrayList<Book> books = bookDAO.getAllBooks();
			try (FileWriter writer = new FileWriter(filePath)) {
				writer.write("Title,Author,Genre,PublicationDate,ISBN\n");

				for (Book book : books) {
					writer.write(String.format(
							"\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
							book.getTitle(),
							book.getAuthor(),
							book.getGenre(),
							book.getPublicationDate(),
							book.getIsbn()
							));
				}

				JOptionPane.showMessageDialog(ExportForm.this, "Books has been successfully downloaded " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(ExportForm.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
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
