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
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("serial")
public class PageEmprunt extends JLabel {
	Terminal terminal;
	IStock stockVehicules;
	LinkedList<IVehicule> listEmprunt;

	JTextField titleID;
	JTextField titleType;
	JTextField titleCommentaire;
	JTextField titleNote;

	JTextField[] ids;
	JTextField[] types;
	JTextField[] commentaires;
	JComboBox<Integer>[] notes;
	JButton[] views;
	JButton[] retourners;
	JPanel vide1;

	JTextArea infoVehicule;

	JPanel vide2;
	JPanel vide3;

	JButton inference;
	JButton recharger;

	JScrollPane scroll;

	@SuppressWarnings("unchecked")
	public PageEmprunt(Terminal terminal, IStock stockVehicules) throws RemoteException {
		this.terminal = terminal;
		this.stockVehicules = stockVehicules;

		@SuppressWarnings("unused")
		int i, k;
		this.setSize(900, 900);

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
		titleCommentaire = new JTextField();
		titleCommentaire.setEditable(false);
		titleCommentaire.setText("Commentaire");
		titleCommentaire.setHorizontalAlignment(JTextField.CENTER);
		titleCommentaire.setPreferredSize(new Dimension(350, 20));
		titleNote = new JTextField();
		titleNote.setEditable(false);
		titleNote.setText("Note");
		titleNote.setHorizontalAlignment(JTextField.CENTER);
		titleNote.setPreferredSize(new Dimension(100, 20));

		ids = new JTextField[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			ids[i] = new JTextField();
			ids[i].setEditable(false);
			ids[i].setPreferredSize(new Dimension(50, 20));
		}
		types = new JTextField[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			types[i] = new JTextField();
			types[i].setEditable(false);
			types[i].setPreferredSize(new Dimension(100, 20));
		}
		commentaires = new JTextField[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			commentaires[i] = new JTextField();
			commentaires[i].setPreferredSize(new Dimension(350, 20));
		}
		notes = new JComboBox[10];
		Integer[] listeNotes = { 1, 2, 3, 4, 5 };
		for (i = 0; i < 10; i++) {
			k = i + 1;
			notes[i] = new JComboBox<Integer>(listeNotes);
			notes[i].setPreferredSize(new Dimension(100, 20));
		}
		views = new JButton[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			views[i] = new JButton();
			views[i].setText("Voir");
			views[i].setPreferredSize(new Dimension(100, 20));
		}
		retourners = new JButton[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			retourners[i] = new JButton();
			retourners[i].setText("Retourner");
			retourners[i].setPreferredSize(new Dimension(100, 20));
		}

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
		recharger.addActionListener(new ButtonListener_RechargerEmprunts());

		// Le conteneur principal
		this.setPreferredSize(new Dimension(800, 600));
		// On definit le layout manager
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
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
		this.add(titleCommentaire, gbc);
		// ---------------------------------------------
		gbc.gridx = 10;
		gbc.gridwidth = 2;
		this.add(titleNote, gbc);
		// ---------------------------------------------
		for (i = 0; i < 10; i++) {
			gbc.gridx = 0;
			gbc.gridwidth = 1;
			gbc.gridy = i + 1;
			gbc.gridheight = 1;
			this.add(ids[i], gbc);
		}
		// ---------------------------------------------
		for (i = 0; i < 10; i++) {
			gbc.gridx = 1;
			gbc.gridwidth = 2;
			gbc.gridy = i + 1;
			gbc.gridheight = 1;
			this.add(types[i], gbc);
		}
		// ---------------------------------------------
		for (i = 0; i < 10; i++) {
			gbc.gridx = 3;
			gbc.gridwidth = 7;
			gbc.gridy = i + 1;
			gbc.gridheight = 1;
			this.add(commentaires[i], gbc);
		}
		// ---------------------------------------------
		for (i = 0; i < 10; i++) {
			gbc.gridx = 10;
			gbc.gridwidth = 2;
			gbc.gridy = i + 1;
			gbc.gridheight = 1;
			this.add(notes[i], gbc);
		}
		for (i = 0; i < 10; i++) {
			gbc.gridx = 12;
			gbc.gridwidth = 2;
			gbc.gridy = i + 1;
			gbc.gridheight = 1;
			this.add(views[i], gbc);
		}
		for (i = 0; i < 10; i++) {
			gbc.gridx = 14;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridy = i + 1;
			gbc.gridheight = 1;
			this.add(retourners[i], gbc);
		}
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 11;
		gbc.gridheight = 1;
		this.add(vide1, gbc);
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 12;
		gbc.gridheight = 10;
		this.add(scroll, gbc);
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 23;
		gbc.gridheight = 1;
		this.add(vide2, gbc);
		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridy = 24;
		gbc.gridwidth = 12;
		this.add(vide3, gbc);
		// ---------------------------------------------
		gbc.gridx = 12;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(recharger, gbc);
		// ---------------------------------------------

		// On ajoute le conteneur
		this.setVisible(true);
	}

	//Activer la page
	public void activer(){
		recharger.setEnabled(true);
	}
	
	//Desactiver la page
	public void desactiver(){
		recharger.setEnabled(false);
	}
	
	public void initialiser() {
		int i;
		for (i = 0; i < 10; i++) {
			ids[i].setText("");
			types[i].setText("");
			commentaires[i].setText("");
			commentaires[i].setEnabled(false);
			notes[i].setEnabled(false);
			ActionListener[] actionListViews = views[i].getActionListeners();
			if (actionListViews.length > 0)
				views[i].removeActionListener(actionListViews[0]);
			views[i].setEnabled(false);
			ActionListener[] actionListEmprunters = retourners[i].getActionListeners();
			if (actionListEmprunters.length > 0)
				retourners[i].removeActionListener(actionListEmprunters[0]);
			retourners[i].setEnabled(false);
		}
		infoVehicule.setText("");
	}

	//Charger les donnees
	public void chargerTableEmprunts() throws RemoteException {
		listEmprunt = stockVehicules.getVehiculesEmpruntesByUser(terminal.getUserName());
		IVehicule vehicule;
		int i;
		for (i = 0; i < listEmprunt.size(); i++) {
			vehicule = listEmprunt.get(i);
			ids[i].setText(vehicule.getId());
			types[i].setText(vehicule.getType());
			commentaires[i].setText("");
			commentaires[i].setEnabled(true);
			notes[i].setEnabled(true);
			views[i].addActionListener(new ButtonListener_Voir(vehicule));
			views[i].setEnabled(true);
			retourners[i].addActionListener(new ButtonListener_Retourner(vehicule, i));
			retourners[i].setEnabled(true);
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
				content = content + "ID: \t" + vehicule.getId() + "\n\n";
				content = content + "Informations generale:\n";
				content = content + "--------------------------------------\n";
				content = content + "TYPE: \t" + vehicule.getType() + "\n";
				content = content + "DESCRIPTION: \t" + vehicule.getDescription() + "\n";
				content = content + "DATE AJOUT: \t" + vehicule.getDateAjout() + " par " + vehicule.getAjouteur()
						+ "\n";
				if (vehicule.getEtat().compareTo("Emprunte") == 0)
					content = content + "ETAT: \t" + vehicule.getEtat() + " par " + vehicule.getEmprunteur() + "\n";
				else
					content = content + "ETAT: \t" + vehicule.getEtat() + "\n\n";
				content = content + "Informations emprunts:\n";
				content = content + "--------------------------------------\n";
				content = content + "NOMBRE EMPRUNT: \t" + vehicule.getNbrEmprunts() + "\n";
				content = content + "NOTE MOYENNE: \t" + vehicule.getNoteMoyenne() + "\n";
				content = content + "COMMENTAIRES:\t";
				LinkedList<String> listCommentaires = vehicule.getCommentaires();
				if (listCommentaires.size() == 0)
					content = content + "Pas de commentaire\n";
				else {
					content = content + "\n";
					int i;
					for (i = 0; i < listCommentaires.size(); i++)
						content = content + " - " + listCommentaires.get(i) + "\n";
				}
				content = content + "NOMBRE D'ENSEIGNANTS EN ATTENTE: \t" + vehicule.nbrAttendreEnseignant() + "\n";
				content = content + "NOMBRE D'ETUDIANTS EN ATTENTE: \t" + vehicule.nbrAttendreEtudiant() + "\n";

				infoVehicule.setText(content);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	//Bouton Retourner
	class ButtonListener_Retourner implements ActionListener {

		private IVehicule vehicule;
		private int index;

		public ButtonListener_Retourner(IVehicule vehicule, int index) {
			this.vehicule = vehicule;
			this.index = index;
		}

		public void actionPerformed(ActionEvent arg0) {
			try {
				String commentaire = commentaires[index].getText();
				int note = (int) notes[index].getSelectedItem();
				String message = vehicule.retourner(note, commentaire);
				//Recharger les pages
				initialiser();
				chargerTableEmprunts();
				terminal.getPageAllVehicules().initialiser();
				terminal.getPageAllVehicules().chargerTableVehicules();
				terminal.getPageAchat().initialiser();
				terminal.getPageAchat().chargerTableVehiculesAchetables();
				infoVehicule.setText(message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	//Bouton recharger
	class ButtonListener_RechargerEmprunts implements ActionListener {

		public ButtonListener_RechargerEmprunts() {

		}

		public void actionPerformed(ActionEvent arg0) {
			try {
				initialiser();
				chargerTableEmprunts();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

}
