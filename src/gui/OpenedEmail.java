package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Email;
import model.FilesDownload;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpenedEmail extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OpenedEmail frame = new OpenedEmail();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public OpenedEmail(Email email) {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 322);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 418, 20);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblFrom = new JLabel("Remetente:");
		lblFrom.setBounds(10, 0, 76, 20);
		panel.add(lblFrom);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lbl_from = new JLabel("New label");
		lbl_from.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_from.setBounds(83, 3, 316, 14);
		panel.add(lbl_from);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 35, 418, 20);
		contentPane.add(panel_1);

		JLabel lblDestinatrio_1 = new JLabel("Destinat\u00E1rio:");
		lblDestinatrio_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDestinatrio_1.setBounds(10, 0, 76, 20);
		panel_1.add(lblDestinatrio_1);

		JLabel label_to = new JLabel("New label");
		label_to.setHorizontalAlignment(SwingConstants.CENTER);
		label_to.setBounds(83, 3, 316, 14);
		panel_1.add(label_to);

		JLabel label_data = new JLabel("New label");
		label_data.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_data.setForeground(Color.BLACK);
		label_data.setHorizontalAlignment(SwingConstants.CENTER);
		label_data.setBounds(241, 60, 187, 14);
		contentPane.add(label_data);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 85, 418, 166);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(10, 11, 398, 144);
		panel_2.add(textPane);
		setLocationRelativeTo(null);
		setResizable(false);

		setTitle("Assunto: " + email.getSubject());
		lbl_from.setText(email.getFrom());
		label_to.setText(email.getTo());
		label_data.setText(email.getData() + " " + email.getTime());
		textPane.setText(email.getTextBody());

		JButton btnNewButton = new JButton("Download");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<FilesDownload> fileDownload = email.getFileDownload();

				if (fileDownload != null) {

					File file = new File("C:/Users/Public/Downloads");

					for (FilesDownload filesDownload : fileDownload) {
						
						byte[] imageBytes = Base64.getDecoder().decode(filesDownload.getBases64());
						FileOutputStream fileone;

						try {
							fileone = new FileOutputStream("C:/Users/Public/Downloads/" + filesDownload.getFileNames());
							// filetwo = new FileOutputStream("C:/Users/Public/Downloads/fileTwo.png");

							// filetwo.write(imageBytes);
							fileone.write(imageBytes);
							fileone.close();

						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} // Or PDF file
						catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					try {
						Desktop.getDesktop().open(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "esse email n�o possui anexos.");
				}

			}
		});
		btnNewButton.setBounds(323, 255, 105, 23);
		contentPane.add(btnNewButton);

//		if(email.getBase64() != "") {
//			
//			//byte[] btDataFile = Base64.decodeBase64(email.getBase64());
//			//Base64 base64 = Base64.decodeBase64(email.getBase64());
//			
//			BufferedImage image;
//			try {
//				byte[] btDataFile = Base64.getDecoder().decode(email.getBase64());
//				image = ImageIO.read(new ByteArrayInputStream(btDataFile));
//				
//				JOptionPane.showMessageDialog(null, "", "Image", 
//				        JOptionPane.INFORMATION_MESSAGE, 
//				        new ImageIcon(image));
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}

	}
}
