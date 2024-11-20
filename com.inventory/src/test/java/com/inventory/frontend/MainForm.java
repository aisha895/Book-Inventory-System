package com.inventory.frontend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainForm extends JFrame {

	public MainForm() {
		//Designing the layout of the GUI 
		setTitle("Book Inventory System");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

		JLabel headerLabel = new JLabel("Book Inventory System");
		headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(headerLabel, BorderLayout.NORTH);

		// Button created to add a book into the system
		JButton createButton = new JButton("Create Book");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateForm createForm = new CreateForm();
				createForm.setVisible(true);
			}
		});

		//Button created for Filtering through the data
		JButton filterButton = new JButton("Filter Books");
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FilterForm filterForm = new FilterForm();
				filterForm.setVisible(true);
			}
		});

		// Button created for Exporting the data 
		JButton exportButton = new JButton("Export Books");
		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExportForm exportForm = new ExportForm();
				exportForm.setVisible(true);
			}
		});
		//Button created to display all books
		JButton displayBooksButton = new JButton("Display All Books");
		displayBooksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AllBooks displayBooksForm = new AllBooks();
				displayBooksForm.setVisible(true);
			}
		});
		//Buttons are added to panels
		panel.add(createButton);
		panel.add(filterButton);
		panel.add(exportButton);
		panel.add(displayBooksButton); 
		add(panel);
		panel.setBackground(new Color(200, 230, 255)); 

	}
}
