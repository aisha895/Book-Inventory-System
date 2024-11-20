package com.inventory.main;


import javax.swing.*;

import com.inventory.frontend.MainForm;
import com.inventory.logic.BookDAOImplementation;
import java.sql.Connection;

public class BookInventorySystem {
// This is the main class which is used to run the project as it contains the main method.
	private static Connection connection;
    private static BookDAOImplementation bookDao;
    
    public static void main(String[] args) {
     
        SwingUtilities.invokeLater(() -> {
           MainForm inventoryUI = new MainForm();
           inventoryUI.setVisible(true);
        });
    }

}
