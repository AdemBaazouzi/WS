import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AuthentificationGUI extends JLabel {
	Terminal terminal;

	JLabel userLabel;
	JTextField userNameField;
	JLabel passWordLabel;
	JPasswordField passWordField;
	JLabel userNameErrorLabel;
	JLabel groupLabel;
	JLabel userGroupeField;
	JButton valider;

	LinkedList<Utilisateur> utilisateurs;

	public AuthentificationGUI(Terminal terminal) {
		this.terminal = terminal;

		this.setSize(800, 500);
		this.setLayout(null);

		userLabel = new JLabel("Username");
		userLabel.setBounds(370, 25, 400, 500);
		this.add(userLabel);

		userNameField = new JTextField(25);
		userNameField.setBounds(470, 260, 160, 25);
		this.add(userNameField);

		userNameErrorLabel = new JLabel();
		userNameErrorLabel.setBounds(650, 25, 160, 25);
		userNameErrorLabel.setVisible(false);
		this.add(userNameErrorLabel);

		passWordLabel = new JLabel("Password");
		passWordLabel.setBounds(370, 60, 400, 510);
		this.add(passWordLabel);

		passWordField = new JPasswordField(25);
		passWordField.setBounds(470, 310, 160, 25);
		this.add(passWordField);

		groupLabel = new JLabel("Status: ");
		groupLabel.setBounds(700, 100, 80, 25);
		this.add(groupLabel);

		utilisateurs = new LinkedList<Utilisateur>();

		utilisateurs.add(new Utilisateur("Mahdi", "Mahdi", "Professor"));
		utilisateurs.add(new Utilisateur("anis", "anis", "Student"));
		utilisateurs.add(new Utilisateur("adam", "adam", "Student"));
		utilisateurs.add(new Utilisateur("viet", "viet", "Extern Client"));


		userGroupeField = new JLabel("");
		userGroupeField.setBounds(750, 100, 80, 25);
		this.add(userGroupeField);

		JButton loginButton = new JButton("Validate");
		loginButton.setBounds(470, 400, 80, 25);
		this.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String userName = userNameField.getText();
				String passWord = passWordField.getText();

				// Successful Authentication
				if (authentification(userName, passWord) == true) {
					String userGroupe = getUserGroupe(userName, passWord);
					userNameErrorLabel.setVisible(true);
					String Hello = "Hello " + userName + " !";
					JOptionPane.showMessageDialog(terminal, Hello, "Access Granted", JOptionPane.INFORMATION_MESSAGE);
					userGroupeField.setText(userGroupe);
					terminal.setUserName(userName);
					terminal.setUserGroupe(userGroupe);
					terminal.setOnline(true);
					Terminal.onglet.add("Add products", Terminal.pageAddProduct);
					Terminal.onglet.add("Productlist", Terminal.pageAllProducts);
					Terminal.onglet.add("Borrow list", Terminal.pageEmprunt);
					Terminal.onglet.add("Product Sell Service", Terminal.pageAchat);
					Terminal.onglet.remove(Terminal.pageAuthentification);
					try {
						// Enable add functionality, borrow if user is a
						// Professor
						// or student
						if (userGroupe.compareTo("Professor") == 0 || userGroupe.compareTo("Student") == 0) {
							terminal.getPageAllProducts().initialiser();
							terminal.getPageAllProducts().activer();
							terminal.getPageAllProducts().LoadProductsTable();

							terminal.getPageBorrower().initialiser();
							terminal.getPageBorrower().activer();
							terminal.getPageBorrower().loadTableBorrowers();

							terminal.getPageAddProduct().initialiser();
							terminal.getPageAddProduct().activer();
						}
						// Enable purchase functionality for all user groups
						terminal.getPageAchat().initialiser();
						terminal.getPageAchat().activer();
						terminal.getPageAchat().loadTableProductsBuyable();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else {// Failed Authentication => disable pages
					userGroupeField.setText("<Not loggin>");
					userNameErrorLabel.setVisible(true);
					userNameErrorLabel.setVisible(true);
					JOptionPane.showMessageDialog(terminal, "Wrong credentials. Please try again");
					terminal.setUserName("");
					terminal.setUserGroupe("");
					terminal.setOnline(false);

					terminal.getPageAllProducts().initialiser();
					terminal.getPageAllProducts().desactiver();

					terminal.getPageBorrower().initialiser();
					terminal.getPageBorrower().desactiver();

					terminal.getPageAddProduct().initialiser();
					terminal.getPageAddProduct().desactiver();

					terminal.getPageAchat().initialiser();
					terminal.getPageAchat().desactiver();
				}

			}
		});

		this.setVisible(true);
	}

	public boolean authentification(String userName, String passWord) {
		int i;
		for (i = 0; i < utilisateurs.size(); i++) {
			if (utilisateurs.get(i).identification(userName, passWord) == true)
				return true;
		}
		return false;
	}

	public String getUserGroupe(String userName, String passWord) {
		int i;
		for (i = 0; i < utilisateurs.size(); i++) {
			if (utilisateurs.get(i).identification(userName, passWord) == true)
				return utilisateurs.get(i).getGroupe();
		}
		return null;
	}

	// Internal class User
	class Utilisateur {
		String userName;
		String passWord;
		String userGroupe; // Professor, Student, External client

		Utilisateur(String userName, String passWord, String userGroupe) {
			this.userName = userName;
			this.passWord = passWord;
			this.userGroupe = userGroupe;
		}

		public String getGroupe() {
			return this.userGroupe;
		}

		boolean identification(String userName, String passWord) {
			if (userName == null || userName.length() == 0 || this.userName.compareTo(userName) != 0) {
				return false;
			}
			if (passWord == null || passWord.length() == 0 || this.passWord.compareTo(passWord) != 0) {
				return false;
			}
			return true;
		}
	}
}
