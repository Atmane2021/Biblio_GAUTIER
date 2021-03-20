package biblio.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import biblio.control.ConsulterCtl;
import biblio.control.EmprunterCtl;

import javax.swing.JTextArea;
import java.awt.Font;

public class Ecran_Acceuil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 816343808390032402L;
	private JFrame frmBiblio;
	private JTextArea textArea;
	private JTextField textField;
	private JTextField textField_3;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Apply Look and Feel
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Ecran_Acceuil window = new Ecran_Acceuil();
					window.frmBiblio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Ecran_Acceuil(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
			frmBiblio = new JFrame();
			frmBiblio.setTitle("CONNECTION A LA BIBLIOTHEQUE");
			frmBiblio.setBounds(100, 100, 1031, 713);
			frmBiblio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmBiblio.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(416, 114, 170, 30);
		frmBiblio.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Connection � la base de donn�es");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String user = ConsulterCtl.connectbase();
					JOptionPane.showMessageDialog(null, "Vous �tes connect�(e) � la base de donn�es de la biblioth�que avec le user : "+user, "Etat de la connection � la base de donn�es" , JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(14, 14, 191, 77);
		frmBiblio.getContentPane().add(btnNewButton);
		
		JButton btnEffacer = new JButton("Faire un emprunt");
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.setText(EmprunterCtl.creaemprunt(textField.getText(), textField_3.getText()));
					
				} catch (NumberFormatException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//textField_1.setText("");
			}
		});
		btnEffacer.setBounds(613, 14, 170, 77);
		frmBiblio.getContentPane().add(btnEffacer);
		
		JButton btnOk = new JButton("Restituer un emprunt");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//textField_1.setText(textField.getText());
			}
		});
		btnOk.setBounds(806, 15, 170, 77);
		frmBiblio.getContentPane().add(btnOk);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);		
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textArea.setBounds(22, 193, 967, 458);
		frmBiblio.getContentPane().add(textArea);
		
		JButton btnNewButton_1 = new JButton("Consulter un exemplaire");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					textArea.setText(ConsulterCtl.consultexemplaire(textField.getText()).toString());
				} catch (NumberFormatException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(224, 14, 170, 77);
		frmBiblio.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Consulter un utilisateur");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					textArea.setText(ConsulterCtl.consultutilisateur(textField_3.getText()).toString());
				} catch (NumberFormatException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setBounds(416, 14, 170, 77);
		frmBiblio.getContentPane().add(btnNewButton_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(809, 114, 167, 30);
		frmBiblio.getContentPane().add(textField_3);
		
		JLabel lblNewLabel = new JLabel("  Saisir l'ID de l'exemplaire");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(228, 113, 166, 20);
		frmBiblio.getContentPane().add(lblNewLabel);
		
		JLabel lblSaisirLidDe = new JLabel("  Saisir l'ID de l'utilisateur");
		lblSaisirLidDe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSaisirLidDe.setBounds(613, 113, 170, 20);
		frmBiblio.getContentPane().add(lblSaisirLidDe);
	}
}

