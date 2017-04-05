import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PageAuthentification extends JLabel {
	Terminal terminal;

	JLabel userLabel;
	JTextField userNameField;
	JLabel passWordLabel;
	JTextField passWordField;
	JLabel userNameErrorLabel;
	JLabel groupLabel;
	JLabel userGroupeField;
	JButton valider;

	LinkedList<Utilisateur> utilisateurs;
	
	public PageAuthentification(Terminal terminal) {
		this.terminal = terminal;

		this.setSize(900, 900);
		this.setLayout(null);

		userLabel = new JLabel("User Name");
		userLabel.setBounds(370, 20, 80, 25);
		this.add(userLabel);

		userNameField = new JTextField(20);
		userNameField.setBounds(470, 20, 160, 25);
		this.add(userNameField);

		userNameErrorLabel = new JLabel();
		userNameErrorLabel.setBounds(650, 20, 160, 25);
		userNameErrorLabel.setVisible(false);
		this.add(userNameErrorLabel);

		passWordLabel = new JLabel("Mot de passe");
		passWordLabel.setBounds(370, 60, 80, 25);
		this.add(passWordLabel);

		passWordField = new JTextField(20);
		passWordField.setBounds(470, 60, 160, 25);
		this.add(passWordField);
		
		groupLabel = new JLabel("Groupe Name");
		groupLabel.setBounds(370, 100, 80, 25);
		this.add(groupLabel);

		utilisateurs = new LinkedList<Utilisateur>();
		utilisateurs.add(new Utilisateur("duykhanh", "duykhanh", "Etudiant"));
		utilisateurs.add(new Utilisateur("omar", "omar", "Enseignant"));
		utilisateurs.add(new Utilisateur("zaid", "zaid", "Etudiant"));
		utilisateurs.add(new Utilisateur("sabrina", "sabrina", "Client externe"));
		
		userGroupeField = new JLabel("Inconnu");
		userGroupeField.setBounds(470, 100, 160, 25);
		this.add(userGroupeField);

		JButton loginButton = new JButton("Valider");
		loginButton.setBounds(470, 140, 80, 25);
		this.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String userName = userNameField.getText();
				String passWord = passWordField.getText();
				
				//Authentification reussi
				if (authentification(userName, passWord)==true){
					String userGroupe = getUserGroupe(userName, passWord);
					userNameErrorLabel.setVisible(true);
					userNameErrorLabel.setText("Authentification reussie");
					userGroupeField.setText(userGroupe);
					terminal.setUserName(userName);
					terminal.setUserGroupe(userGroupe);
					terminal.setOnline(true);
					try {
						//Activer les fonctionnalites d'ajout, d'emprunter si l'utilisateur est un enseignant ou un etudiant
						if(userGroupe.compareTo("Enseignant")==0 || userGroupe.compareTo("Etudiant")==0){
							terminal.getPageAllVehicules().initialiser();
							terminal.getPageAllVehicules().activer();
							terminal.getPageAllVehicules().chargerTableVehicules();
	
							terminal.getPageEmprunt().initialiser();
							terminal.getPageEmprunt().activer();
							terminal.getPageEmprunt().chargerTableEmprunts();
	
							terminal.getPageAjoutVehicule().initialiser();
							terminal.getPageAjoutVehicule().activer();
						}
						//Activer la fonctionnalite d'achat pour tous les groupe d'utilisateur
						terminal.getPageAchat().initialiser();
						terminal.getPageAchat().activer();
						terminal.getPageAchat().chargerTableVehiculesAchetables();	
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}else{//Authentification echouee => desactiver les pages
					userGroupeField.setText("Inconnu");
					userNameErrorLabel.setVisible(true);
					userNameErrorLabel.setText("Authentification echouee");
					terminal.setUserName("");
					terminal.setUserGroupe("");
					terminal.setOnline(false);

					terminal.getPageAllVehicules().initialiser();
					terminal.getPageAllVehicules().desactiver();

					terminal.getPageEmprunt().initialiser();
					terminal.getPageEmprunt().desactiver();

					terminal.getPageAjoutVehicule().initialiser();
					terminal.getPageAjoutVehicule().desactiver();
					
					terminal.getPageAchat().initialiser();
					terminal.getPageAchat().desactiver();
				}

			}
		});

		this.setVisible(true);
	}
	
	public boolean authentification(String userName, String passWord){
		int i;
		for(i=0;i<utilisateurs.size();i++){
			if(utilisateurs.get(i).identification(userName, passWord)==true)
				return true;
		}
		return false;
	}
	
	public String getUserGroupe(String userName, String passWord){
		int i;
		for(i=0;i<utilisateurs.size();i++){
			if(utilisateurs.get(i).identification(userName, passWord)==true)
				return utilisateurs.get(i).getGroupe();
		}
		return null;
	}
	
	//Classe interne Utilisateur
	class Utilisateur{
		String userName;
		String passWord;
		String userGroupe; //Enseignant, Etudiant, Client externe
		
		Utilisateur(String userName, String passWord, String userGroupe){
			this.userName = userName;
			this.passWord = passWord;
			this.userGroupe = userGroupe;
		}
		
		public String getGroupe(){
			return this.userGroupe;
		}
		
		boolean identification(String userName, String passWord){
			if(userName == null || userName.length()==0 || this.userName.compareTo(userName)!=0){
				return false;
			}
			if(passWord == null || passWord.length()==0 || this.passWord.compareTo(passWord)!=0){
				return false;
			}
			return true;
		}
	}
}
