package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Email;
import service.EmailService;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class EmailBox extends JFrame {

	private JPanel contentPane;
	private JTable table_emails;
	int aux = 1;
	ArrayList<Email> emails;
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
		setLocationRelativeTo(null);
		setResizable(false);

		JScrollPane scrollPane = new JScrollPane(table_emails, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 527, 268);
		contentPane.add(scrollPane);

		table_emails = new JTable();
		table_emails.setModel(emailsModel);
		table_emails.setBackground(Color.cyan);
		table_emails.getColumnModel().getColumn(0).setPreferredWidth(200);
		// table_emails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table_emails);

		try {
			listEmails();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

		ListSelectionModel model = table_emails.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (aux == 1) {
					int selectedRow = model.getMinSelectionIndex();
					// System.out.println(selectedRow);
					OpenedEmail openedEmail = new OpenedEmail(emails.get(selectedRow));
					openedEmail.setVisible(true);
					
					String[] type = emails.get(selectedRow).getFileName().split(".");
					System.out.println("aquiiiii" + type[0]);

					if (emails.get(selectedRow).getBase64() != "" && type[1] != "pdf") {

						// byte[] btDataFile = Base64.decodeBase64(email.getBase64());
						// Base64 base64 = Base64.decodeBase64(email.getBase64());

						BufferedImage image;

						byte[] btDataFile = Base64.getDecoder().decode(emails.get(selectedRow).getBase64());
						try {
							image = ImageIO.read(new ByteArrayInputStream(btDataFile));
							JOptionPane.showMessageDialog(null, "", "Image", JOptionPane.INFORMATION_MESSAGE,
									new ImageIcon(image));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						

					}

					aux = 2;
				} else {
					aux = 1;
				}

			}
		});

	}

	public void listEmails() throws IOException {
		emails = (ArrayList<Email>) Controller.ListEmail();

		setTitle("Logado: " + emails.get(0).getTo());

		int size = emails.size();

		emailsModel.setNumRows(size);

		int i = 0;
		for (Email email : emails) {

			emailsModel.setValueAt(email.getFrom(), i, 0);
			emailsModel.setValueAt(email.getData(), i, 1);
			emailsModel.setValueAt(email.getTime(), i, 2);
			i++;
		}

		// ButtonColumn buttonColumn = new ButtonColumn(table_emails, 3, emails,
		// table_emails.getSelectedRowCount());

//		for (int i = 0; i < size; i++) {
//			emailsModel.setValueAt(emails.get(i).getFrom(), i, 0);
//			emailsModel.setValueAt(emails.get(i).getData(), i, 1);
//			emailsModel.setValueAt(emails.get(i).getData(), i, 2);
//		}

	}

}
