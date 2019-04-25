package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GetAdressAndDoor extends JFrame {

	private JPanel contentPane;
	private JTextField adress_home;
	private JTextField door_home;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetAdressAndDoor frame = new GetAdressAndDoor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GetAdressAndDoor() {
		setTitle("Trabalho POP3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 286, 313);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Informe o endere\u00E7o de conex\u00E3o");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(69, 61, 181, 20);
		contentPane.add(lblNewLabel);

		adress_home = new JTextField();
		adress_home.setBounds(79, 92, 126, 20);
		contentPane.add(adress_home);
		adress_home.setColumns(10);

		JLabel lblSenha = new JLabel("Informe a porta de conex\u00E3o");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setBounds(69, 123, 181, 20);
		contentPane.add(lblSenha);

		door_home = new JTextField();
		door_home.setColumns(10);
		door_home.setBounds(79, 154, 126, 20);
		contentPane.add(door_home);

		JLabel lblEndereoDeConexo = new JLabel("Endere\u00E7o de conex\u00E3o ou porta est\u00E3o incorretos");
		lblEndereoDeConexo.setForeground(Color.RED);
		lblEndereoDeConexo.setBounds(30, 230, 240, 14);
		lblEndereoDeConexo.setVisible(false);
		contentPane.add(lblEndereoDeConexo);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String adress = adress_home.getText();
				int door = Integer.parseInt(door_home.getText());

				try {

					Controller.setAdressAndDoor(adress, door);
					System.out.println(Controller.response());

					GetEmailAndPassword getEmailPassword = new GetEmailAndPassword();

					getEmailPassword.setVisible(true);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();

				} catch (IOException e1) {
					lblEndereoDeConexo.setVisible(true);
					System.err.println("Erro no endereço ou porta");

				}

			}
		});
		btnEnviar.setBounds(97, 196, 89, 23);
		contentPane.add(btnEnviar);

		setLocationRelativeTo(null);
		setResizable(false);

	}
}
