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
public class AllProductsGUI extends JLabel {
	Terminal terminal;
	IStock stockProducts;
	LinkedList<IProduct> listProducts;

	JLabel labelRechercherID;
	JLabel labelRechercherType;
	JLabel labelRechercherState;
	JTextField rechercherID;
	JTextField rechercherType;
	JComboBox<String> rechercherState;
	JPanel vide0;
	
	JTextField titleID;
	JTextField titleType;
	JTextField titleDes;
	JTextField titleState;

	JTextField[] ids;
	JTextField[] types;
	JTextField[] descriptions;
	JTextField[] states;
	JButton[] views;
	JButton[] emprunters;
	JPanel vide1;

	JTextArea infoProducts;

	JPanel vide2;
	JPanel vide3;

	JButton inference;
	JButton recharger;

	JScrollPane scroll;

	@SuppressWarnings("unused")
	public AllProductsGUI(Terminal terminal, IStock stockProducts) throws RemoteException {
		this.terminal = terminal;
		this.stockProducts = stockProducts;
		this.setSize(900, 900);

		// Defining interface elements
		int i, k;
		
		//Search area
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
		
		labelRechercherState = new JLabel("Etat: ");
		labelRechercherState.setPreferredSize(new Dimension(100,20));
		labelRechercherState.setHorizontalAlignment(SwingConstants.RIGHT);
		String[] listEtats = {"","Disponible","Emprunte"};
		rechercherState = new JComboBox<String>(listEtats);
		rechercherState.setPreferredSize(new Dimension(100,20));
		
		vide0 = new JPanel();
		vide0.setOpaque(false);
		vide0.setPreferredSize(new Dimension(800, 20));
		
		//Table area
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
		titleState = new JTextField();
		titleState.setEditable(false);
		titleState.setText("Etat");
		titleState.setHorizontalAlignment(JTextField.CENTER);
		titleState.setPreferredSize(new Dimension(100, 20));

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
		states = new JTextField[15];
		for (i = 0; i < 15; i++) {
			k = i + 1;
			states[i] = new JTextField();
			states[i].setEditable(false);
			states[i].setPreferredSize(new Dimension(100, 20));
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
		
		//text area
		vide1 = new JPanel();
		vide1.setOpaque(false);
		vide1.setPreferredSize(new Dimension(800, 20));

		infoProducts = new JTextArea();
		Border border = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		infoProducts.setBorder(border);
		infoProducts.setEditable(false);
		DefaultCaret caret = (DefaultCaret) infoProducts.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scroll = new JScrollPane(infoProducts);
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

		//Organize interface elements
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
		this.add(labelRechercherState, gbc);
		// ---------------------------------------------
		gbc.gridx = 10;
		gbc.gridwidth = 2;
		this.add(rechercherState, gbc);
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
		this.add(titleState, gbc);
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
			this.add(states[i], gbc);
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

	//Activate page
	public void activer(){
		recharger.setEnabled(true);
		rechercherID.setEnabled(true);;
		rechercherType.setEnabled(true);;
		rechercherState.setEnabled(true);
	}
	
	//Deactivate the page
	public void desactiver(){
		recharger.setEnabled(false);
		rechercherID.setEnabled(false);;
		rechercherType.setEnabled(false);;
		rechercherState.setEnabled(false);
	}
	
	public void initialiser() {
		int i;
		for (i = 0; i < 15; i++) {
			ids[i].setText("");
			types[i].setText("");
			descriptions[i].setText("");
			states[i].setText("");
			states[i].setBackground(Color.white);
			ActionListener[] actionListViews = views[i].getActionListeners();
			if (actionListViews.length > 0)
				views[i].removeActionListener(actionListViews[0]);
			views[i].setEnabled(false);
			ActionListener[] actionListEmprunters = emprunters[i].getActionListeners();
			if (actionListEmprunters.length > 0)
				emprunters[i].removeActionListener(actionListEmprunters[0]);
			emprunters[i].setEnabled(false);
		}
		infoProducts.setText("");
	}
	
	//Load the data
	public void LoadProductsTable() throws RemoteException {
		listProducts = stockProducts.getProducts(rechercherID.getText(),rechercherType.getText(),(String)rechercherState.getSelectedItem());
		IProduct product;
		int i;
		for (i = 0; i < listProducts.size(); i++) {
			product = listProducts.get(i);
			ids[i].setText(product.getId());
			types[i].setText(product.getType());
			descriptions[i].setText(product.getDescription());
			String etat = product.getState();
			states[i].setText(etat);
			if (etat.compareTo("Disponible") == 0)
				states[i].setBackground(Color.cyan);
			else if (etat.compareTo("Emprunte") == 0)
				states[i].setBackground(Color.green);
			else
				states[i].setBackground(Color.red);
			views[i].addActionListener(new ButtonListener_Voir(product));
			views[i].setEnabled(true);
			emprunters[i].addActionListener(new ButtonListener_Emprunter(product));
			emprunters[i].setEnabled(true);
		}
	}

	//View Button
	class ButtonListener_Voir implements ActionListener {

		private IProduct product;

		public ButtonListener_Voir(IProduct product) {
			this.product = product;
		}

		public void actionPerformed(ActionEvent arg0) {
			infoProducts.setText("");
			String content = new String("");
			try {
				content = content + "ID:  " + product.getId() + "\n\n";
				content = content + "Informations generale:\n";
				content = content + "--------------------------------------\n";
				content = content + "TYPE:  " + product.getType() + "\n";
				content = content + "DESCRIPTION:  " + product.getDescription() + "\n";
				content = content + "DATE AJOUT:  " + product.getDateOfAdd() + " par " + product.getAdder()
						+ "\n";
				if (product.getState().compareTo("Emprunte") == 0)
					content = content + "ETAT:  " + product.getState() + " par " + product.getBorrower() + "\n\n";
				else
					content = content + "ETAT:  " + product.getState() + "\n\n";
				content = content + "Informations emprunts:\n";
				content = content + "--------------------------------------\n";
				content = content + "NOMBRE EMPRUNT:  " + product.getNbrEmprunts() + "\n";
				content = content + "NOTE MOYENNE:  " + product.getAvgGrade() + "\n";
				content = content + "COMMENTAIRES:  ";
				LinkedList<String> listCommentaires = product.getReviews();
				if (listCommentaires.size() == 0)
					content = content + "Pas de commentaire\n";
				else {
					content = content + "\n";
					int i;
					for (i = 0; i < listCommentaires.size(); i++)
						content = content + " - " + listCommentaires.get(i) + "\n";
				}
				content = content + "NOMBRE D'ENSEIGNANTS EN ATTENTE:  " + product.nbrWaitingEnseignant() + "\n";
				content = content + "NOMBRE D'ETUDIANTS EN ATTENTE:  " + product.nbrWaitingEtudiant() + "\n";

				infoProducts.setText(content);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	//Button borrow
	class ButtonListener_Emprunter implements ActionListener {

		private IProduct product;

		public ButtonListener_Emprunter(IProduct product) {
			this.product = product;
		}

		public void actionPerformed(ActionEvent arg0) {
			infoProducts.setText("");
			String content = new String("MESSAGE:  ");
			try {
				String message = product.ajoutAttendre(AllProductsGUI.this.terminal.getUserName(),
						AllProductsGUI.this.terminal.getUserGroupe());
				content = content + message;
				//Reload pages
				initialiser();
				LoadProductsTable();
				terminal.getPageBorrower().initialiser();
				terminal.getPageBorrower().loadTableBorrowers();
				terminal.getPageAchat().initialiser();
				terminal.getPageAchat().loadTableProductsBuyable();
				infoProducts.setText(content);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	//Recharge button
	class ButtonListener_Recharger implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				initialiser();
				LoadProductsTable();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

}
