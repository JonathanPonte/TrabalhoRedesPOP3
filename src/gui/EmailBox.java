package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Email;
import service.EmailService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmailBox extends JFrame {

	private JPanel contentPane;
	private JTable table_emails;
	DefaultTableModel emailsModel = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailBox frame = new EmailBox();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public EmailBox() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		emailsModel.addColumn("Remetente");
		emailsModel.addColumn("Data");
		emailsModel.addColumn("Hora");
		emailsModel.addColumn("Abrir Email");

		JScrollPane scrollPane = new JScrollPane(table_emails, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 527, 268);
		contentPane.add(scrollPane);

		table_emails = new JTable();
		table_emails.setModel(emailsModel);
		// table_emails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table_emails);

		try {
			listEmails();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
	}

	public void listEmails() throws IOException {
		ArrayList<Email> emails = (ArrayList<Email>) Controller.ListEmail();

		int size = emails.size();

		emailsModel.setNumRows(size);
		
		int i = 0;
		for (Email email : emails) {
			
			emailsModel.setValueAt(email.getFrom(), i, 0);
			emailsModel.setValueAt(email.getData(), i, 1);
			emailsModel.setValueAt(email.getData(), i, 2);
			i++;
		}
		
//		for (int i = 0; i < size; i++) {
//			emailsModel.setValueAt(emails.get(i).getFrom(), i, 0);
//			emailsModel.setValueAt(emails.get(i).getData(), i, 1);
//			emailsModel.setValueAt(emails.get(i).getData(), i, 2);
//		}

	}

}
