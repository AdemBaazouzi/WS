import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("serial")
public class PageAllVehicules extends JLabel {
	Terminal terminal;
	IStock stockVehicules;
	LinkedList<IVehicule> listVehicules;

	JLabel labelRechercherID;
	JLabel labelRechercherType;
	JLabel labelRechercherEtat;
	JTextField rechercherID;
	JTextField rechercherType;
	JComboBox<String> rechercherEtat;
	JPanel vide0;
	
	JTextField titleID;
	JTextField titleType;
	JTextField titleDes;
	JTextField titleEtat;

	JTextField[] ids;
	JTextField[] types;
	JTextField[] descriptions;
	JTextField[] etats;
	JButton[] views;
	JButton[] emprunters;
	JPanel vide1;

	JTextArea infoVehicule;

	JPanel vide2;
	JPanel vide3;

	JButton inference;
	JButton recharger;

	JScrollPane scroll;

	@SuppressWarnings("unused")
	public PageAllVehicules(Terminal terminal, IStock stockVehicules) throws RemoteException {
		this.terminal = terminal;
		this.stockVehicules = stockVehicules;
		this.setSize(900, 900);

		// Definir les elements de l'interface
		// ====================================================
		int i, k;
		
		//Zone recherche
		labelRechercherID = new JLabel("ID: ");
		labelRechercherID.setPreferredSize(new Dimension(50,20));
		labelRechercherID.setHorizontalAlignment(SwingConstants.RIGHT);
		rechercherID = new JTextField();
		rechercherID.setPreferredSize(new Dimension(100, 20));
		
		labelRechercherType = new JLabel("Type: ");
		labelRechercherType.setPreferredSize(new Dimension(100,20));
		labelRechercherType.setHorizontalAlignment(SwingConstants.RIGHT);
		rechercherType = new JTextField();
		rechercherType.setPreferredSize(new Dimension(100,20));
		
		labelRechercherEtat = new JLabel("Etat: ");
		labelRechercherEtat.setPreferredSize(new Dimension(100,20));
		labelRechercherEtat.setHorizontalAlignment(SwingConstants.RIGHT);
		String[] listEtats = {"","Disponible","Emprunte"};
		rechercherEtat = new JComboBox<String>(listEtats);
		rechercherEtat.setPreferredSize(new Dimension(100,20));
		
		vide0 = new JPanel();
		vide0.setOpaque(false);
		vide0.setPreferredSize(new Dimension(800, 20));
		
		//Zone table
		titleID = new JTextField();
		titleID.setEditable(false);
		titleID.setText("ID");
		titleID.setHorizontalAlignment(JTextField.CENTER);
		titleID.setPreferredSize(new Dimension(50, 20));
		titleType = new JTextField();
		titleType.setEditable(false);
		titleType.setText("Type");
		titleType.setHorizontalAlignment(JTextField.CENTER);
		titleType.setPreferredSize(new Dimension(100, 20));
		titleDes = new JTextField();
		titleDes.setEditable(false);
		titleDes.setText("Description");
		titleDes.setHorizontalAlignment(JTextField.CENTER);
		titleDes.setPreferredSize(new Dimension(350, 20));
		titleEtat = new JTextField();
		titleEtat.setEditable(false);
		titleEtat.setText("Etat");
		titleEtat.setHorizontalAlignment(JTextField.CENTER);
		titleEtat.setPreferredSize(new Dimension(100, 20));

		ids = new JTextField[15];
		for (i = 0; i < 15; i++) {
			k = i + 1;
			ids[i] = new JTextField();
			ids[i].setEditable(false);
			ids[i].setPreferredSize(new Dimension(50, 20));
		}
		types = new JTextField[15];
		for (i = 0; i < 15; i++) {
			k = i + 1;
			types[i] = new JTextField();
			types[i].setEditable(false);
			types[i].setPreferredSize(new Dimension(100, 20));
		}
		descriptions = new JTextField[15];
		for (i = 0; i < 15; i++) {
			k = i + 1;
			descriptions[i] = new JTextField();
			descriptions[i].setEditable(false);
			descriptions[i].setPreferredSize(new Dimension(350, 20));
		}
		etats = new JTextField[15];
		for (i = 0; i < 15; i++) {
			k = i + 1;
			etats[i] = new JTextField();
			etats[i].setEditable(false);
			etats[i].setPreferredSize(new Dimension(100, 20));
		}
		views = new JButton[15];
		for (i = 0; i < 15; i++) {
			k = i + 1;
			views[i] = new JButton();
			views[i].setText("Voir");
			views[i].setPreferredSize(new Dimension(100, 20));
		}
		emprunters = new JButton[15];
		for (i = 0; i < 15; i++) {
			k = i + 1;
			emprunters[i] = new JButton();
			emprunters[i].setText("Emprunter");
			emprunters[i].setPreferredSize(new Dimension(100, 20));
		}
		
		//Zone text area
		vide1 = new JPanel();
		vide1.setOpaque(false);
		vide1.setPreferredSize(new Dimension(800, 20));

		infoVehicule = new JTextArea();
		Border border = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		infoVehicule.setBorder(border);
		infoVehicule.setEditable(false);
		DefaultCaret caret = (DefaultCaret) infoVehicule.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scroll = new JScrollPane(infoVehicule);
		scroll.setPreferredSize(new Dimension(800, 200));

		vide2 = new JPanel();
		vide2.setOpaque(false);
		vide2.setPreferredSize(new Dimension(800, 20));
		vide3 = new JPanel();
		vide3.setOpaque(false);
		vide3.setPreferredSize(new Dimension(600, 20));

		recharger = new JButton();
		recharger.setText("Recharger");
		recharger.setHorizontalTextPosition(JButton.CENTER);
		recharger.setPreferredSize(new Dimension(200, 20));
		recharger.addActionListener(new ButtonListener_Recharger());

		// Organiser des elements de l'interface
		// =======================================================================
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.add(labelRechercherID, gbc);
		// ---------------------------------------------
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		this.add(rechercherID, gbc);
		// ---------------------------------------------
		gbc.gridx = 4;
		gbc.gridwidth = 2;
		this.add(labelRechercherType, gbc);
		// ---------------------------------------------
		gbc.gridx = 6;
		gbc.gridwidth = 2;
		this.add(rechercherType, gbc);
		// ---------------------------------------------
		gbc.gridx = 8;
		gbc.gridwidth = 2;
		this.add(labelRechercherEtat, gbc);
		// ---------------------------------------------
		gbc.gridx = 10;
		gbc.gridwidth = 2;
		this.add(rechercherEtat, gbc);
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		this.add(vide0, gbc);
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.add(titleID, gbc);
		// ---------------------------------------------
		gbc.gridx = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		this.add(titleType, gbc);
		// ---------------------------------------------
		gbc.gridx = 3;
		gbc.gridwidth = 7;
		this.add(titleDes, gbc);
		// ---------------------------------------------
		gbc.gridx = 10;
		gbc.gridwidth = 2;
		this.add(titleEtat, gbc);
		// ---------------------------------------------
		for (i = 0; i < 15; i++) {
			gbc.gridx = 0;
			gbc.gridwidth = 1;
			gbc.gridy = i + 3;
			gbc.gridheight = 1;
			this.add(ids[i], gbc);
		}
		// ---------------------------------------------
		for (i = 0; i < 15; i++) {
			gbc.gridx = 1;
			gbc.gridwidth = 2;
			gbc.gridy = i + 3;
			gbc.gridheight = 1;
			this.add(types[i], gbc);
		}
		// ---------------------------------------------
		for (i = 0; i < 15; i++) {
			gbc.gridx = 3;
			gbc.gridwidth = 7;
			gbc.gridy = i + 3;
			gbc.gridheight = 1;
			this.add(descriptions[i], gbc);
		}
		// ---------------------------------------------
		for (i = 0; i < 15; i++) {
			gbc.gridx = 10;
			gbc.gridwidth = 2;
			gbc.gridy = i + 3;
			gbc.gridheight = 1;
			this.add(etats[i], gbc);
		}
		for (i = 0; i < 15; i++) {
			gbc.gridx = 12;
			gbc.gridwidth = 2;
			gbc.gridy = i + 3;
			gbc.gridheight = 1;
			this.add(views[i], gbc);
		}
		for (i = 0; i < 15; i++) {
			gbc.gridx = 14;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridy = i + 3;
			gbc.gridheight = 1;
			this.add(emprunters[i], gbc);
		}
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 19;
		gbc.gridheight = 1;
		this.add(vide1, gbc);
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 20;
		gbc.gridheight = 10;
		this.add(scroll, gbc);
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 31;
		gbc.gridheight = 1;
		this.add(vide2, gbc);
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridy = 32;
		gbc.gridwidth = 12;
		this.add(vide3, gbc);
		// ---------------------------------------------
		gbc.gridx = 12;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(recharger, gbc);
		// ---------------------------------------------

		this.setVisible(true);
	}

	//Activer la page
	public void activer(){
		recharger.setEnabled(true);
		rechercherID.setEnabled(true);;
		rechercherType.setEnabled(true);;
		rechercherEtat.setEnabled(true);
	}
	
	//Desactiver la page
	public void desactiver(){
		recharger.setEnabled(false);
		rechercherID.setEnabled(false);;
		rechercherType.setEnabled(false);;
		rechercherEtat.setEnabled(false);
	}
	
	public void initialiser() {
		int i;
		for (i = 0; i < 15; i++) {
			ids[i].setText("");
			types[i].setText("");
			descriptions[i].setText("");
			etats[i].setText("");
			etats[i].setBackground(Color.white);
			ActionListener[] actionListViews = views[i].getActionListeners();
			if (actionListViews.length > 0)
				views[i].removeActionListener(actionListViews[0]);
			views[i].setEnabled(false);
			ActionListener[] actionListEmprunters = emprunters[i].getActionListeners();
			if (actionListEmprunters.length > 0)
				emprunters[i].removeActionListener(actionListEmprunters[0]);
			emprunters[i].setEnabled(false);
		}
		infoVehicule.setText("");
	}
	
	//Charger les donnees
	public void chargerTableVehicules() throws RemoteException {
		listVehicules = stockVehicules.getVehicules(rechercherID.getText(),rechercherType.getText(),(String)rechercherEtat.getSelectedItem());
		IVehicule vehicule;
		int i;
		for (i = 0; i < listVehicules.size(); i++) {
			vehicule = listVehicules.get(i);
			ids[i].setText(vehicule.getId());
			types[i].setText(vehicule.getType());
			descriptions[i].setText(vehicule.getDescription());
			String etat = vehicule.getEtat();
			etats[i].setText(etat);
			if (etat.compareTo("Disponible") == 0)
				etats[i].setBackground(Color.cyan);
			else if (etat.compareTo("Emprunte") == 0)
				etats[i].setBackground(Color.green);
			else
				etats[i].setBackground(Color.red);
			views[i].addActionListener(new ButtonListener_Voir(vehicule));
			views[i].setEnabled(true);
			emprunters[i].addActionListener(new ButtonListener_Emprunter(vehicule));
			emprunters[i].setEnabled(true);
		}
	}

	//Bouton Voir
	class ButtonListener_Voir implements ActionListener {

		private IVehicule vehicule;

		public ButtonListener_Voir(IVehicule vehicule) {
			this.vehicule = vehicule;
		}

		public void actionPerformed(ActionEvent arg0) {
			infoVehicule.setText("");
			String content = new String("");
			try {
				content = content + "ID:  " + vehicule.getId() + "\n\n";
				content = content + "Informations generale:\n";
				content = content + "--------------------------------------\n";
				content = content + "TYPE:  " + vehicule.getType() + "\n";
				content = content + "DESCRIPTION:  " + vehicule.getDescription() + "\n";
				content = content + "DATE AJOUT:  " + vehicule.getDateAjout() + " par " + vehicule.getAjouteur()
						+ "\n";
				if (vehicule.getEtat().compareTo("Emprunte") == 0)
					content = content + "ETAT:  " + vehicule.getEtat() + " par " + vehicule.getEmprunteur() + "\n\n";
				else
					content = content + "ETAT:  " + vehicule.getEtat() + "\n\n";
				content = content + "Informations emprunts:\n";
				content = content + "--------------------------------------\n";
				content = content + "NOMBRE EMPRUNT:  " + vehicule.getNbrEmprunts() + "\n";
				content = content + "NOTE MOYENNE:  " + vehicule.getNoteMoyenne() + "\n";
				content = content + "COMMENTAIRES:  ";
				LinkedList<String> listCommentaires = vehicule.getCommentaires();
				if (listCommentaires.size() == 0)
					content = content + "Pas de commentaire\n";
				else {
					content = content + "\n";
					int i;
					for (i = 0; i < listCommentaires.size(); i++)
						content = content + " - " + listCommentaires.get(i) + "\n";
				}
				content = content + "NOMBRE D'ENSEIGNANTS EN ATTENTE:  " + vehicule.nbrAttendreEnseignant() + "\n";
				content = content + "NOMBRE D'ETUDIANTS EN ATTENTE:  " + vehicule.nbrAttendreEtudiant() + "\n";

				infoVehicule.setText(content);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	//Bouton emprunter
	class ButtonListener_Emprunter implements ActionListener {

		private IVehicule vehicule;

		public ButtonListener_Emprunter(IVehicule vehicule) {
			this.vehicule = vehicule;
		}

		public void actionPerformed(ActionEvent arg0) {
			infoVehicule.setText("");
			String content = new String("MESSAGE:  ");
			try {
				String message = vehicule.ajoutAttendre(PageAllVehicules.this.terminal.getUserName(),
						PageAllVehicules.this.terminal.getUserGroupe());
				content = content + message;
				//Recharger les pages
				initialiser();
				chargerTableVehicules();
				terminal.getPageEmprunt().initialiser();
				terminal.getPageEmprunt().chargerTableEmprunts();
				terminal.getPageAchat().initialiser();
				terminal.getPageAchat().chargerTableVehiculesAchetables();
				infoVehicule.setText(content);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	//Bouton recharger
	class ButtonListener_Recharger implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				initialiser();
				chargerTableVehicules();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

}
