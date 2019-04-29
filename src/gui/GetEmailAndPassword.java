package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GetEmailAndPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JLabel lblSenha;
	private JTextField textFieldPassword;
	private PrintWriter output;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GetEmailAndPassword frame = new GetEmailAndPassword();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public PrintWriter getOutput() {
		return output;
	}

	public void setOutput(PrintWriter output) {
		this.output = output;
	}

	/**
	 * Create the frame.
	 */
	public GetEmailAndPassword() {
		setTitle("Trabalho POP3");

		setBounds(100, 100, 338, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(57, 90, 48, 14);
		contentPane.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setText("trabalhoderedespop3@gmail.com");
		textFieldEmail.setBounds(100, 87, 130, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(57, 132, 48, 14);
		contentPane.add(lblSenha);

		textFieldPassword = new JTextField();
		textFieldPassword.setText("redes123");
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(100, 129, 130, 20);
		contentPane.add(textFieldPassword);

		JLabel LabelErro = new JLabel("Email ou Senha invalidos.");
		LabelErro.setForeground(Color.RED);
		LabelErro.setBounds(84, 229, 183, 14);
		contentPane.add(LabelErro);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LabelErro.setVisible(false);

		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email = textFieldEmail.getText();
				String password = textFieldPassword.getText();

				try {
					boolean wasAccepted = Controller.login(email, password);

					if (wasAccepted) {
						
						
						EmailBox emailBox = new EmailBox();
						emailBox.setVisible(true);
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						dispose();

					} else {
						LabelErro.setVisible(true);
					}

				} catch (IOException e1) {

				}

			}
		});
		btnNewButton.setBounds(100, 178, 89, 23);
		contentPane.add(btnNewButton);

	}
}
