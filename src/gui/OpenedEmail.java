package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Email;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 307);
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
		
		setTitle("Logado: " + email.getTo());
		lbl_from.setText(email.getFrom());
		label_to.setText(email.getTo());
		label_data.setText(email.getData() + " " + email.getTime());
		textPane.setText(email.getTextBody());
		
		
		
		
	}
}
