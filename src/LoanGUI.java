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
public class LoanGUI extends JLabel {
	Terminal terminal;
	IStock stockProducts;
	LinkedList<IProduct> listEmprunt;

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

	JTextArea infoProduct;

	JPanel vide2;
	JPanel vide3;

	JButton inference;
	JButton recharger;

	JScrollPane scroll;

	@SuppressWarnings("unchecked")
	public LoanGUI(Terminal terminal, IStock stockProducts) throws RemoteException {
		this.terminal = terminal;
		this.stockProducts = stockProducts;

		@SuppressWarnings("unused")
		int i, k;
		this.setSize(800, 500);

		titleID = new JTextField();
		titleID.setEditable(false);
		titleID.setText("ID");
		titleID.setHorizontalAlignment(JTextField.CENTER);
		titleID.setPreferredSize(new Dimension(50, 25));
		titleType = new JTextField();
		titleType.setEditable(false);
		titleType.setText("Type");
		titleType.setHorizontalAlignment(JTextField.CENTER);
		titleType.setPreferredSize(new Dimension(100, 25));
		titleCommentaire = new JTextField();
		titleCommentaire.setEditable(false);
		titleCommentaire.setText("Comments");
		titleCommentaire.setHorizontalAlignment(JTextField.CENTER);
		titleCommentaire.setPreferredSize(new Dimension(350, 25));
		titleNote = new JTextField();
		titleNote.setEditable(false);
		titleNote.setText("Note");
		titleNote.setHorizontalAlignment(JTextField.CENTER);
		titleNote.setPreferredSize(new Dimension(100, 25));

		ids = new JTextField[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			ids[i] = new JTextField();
			ids[i].setEditable(false);
			ids[i].setPreferredSize(new Dimension(50, 25));
		}
		types = new JTextField[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			types[i] = new JTextField();
			types[i].setEditable(false);
			types[i].setPreferredSize(new Dimension(100, 25));
		}
		commentaires = new JTextField[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			commentaires[i] = new JTextField();
			commentaires[i].setPreferredSize(new Dimension(350, 25));
		}
		notes = new JComboBox[10];
		Integer[] listeNotes = { 1, 2, 3, 4, 5 };
		for (i = 0; i < 10; i++) {
			k = i + 1;
			notes[i] = new JComboBox<Integer>(listeNotes);
			notes[i].setPreferredSize(new Dimension(100, 25));
		}
		views = new JButton[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			views[i] = new JButton();
			views[i].setText("See details");
			views[i].setPreferredSize(new Dimension(100, 25));
		}
		retourners = new JButton[10];
		for (i = 0; i < 10; i++) {
			k = i + 1;
			retourners[i] = new JButton();
			retourners[i].setText("Return");
			retourners[i].setPreferredSize(new Dimension(100, 25));
		}

		vide1 = new JPanel();
		vide1.setOpaque(false);
		vide1.setPreferredSize(new Dimension(800, 25));

		infoProduct = new JTextArea();
		Border border = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		infoProduct.setBorder(border);
		infoProduct.setEditable(false);
		DefaultCaret caret = (DefaultCaret) infoProduct.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scroll = new JScrollPane(infoProduct);
		scroll.setPreferredSize(new Dimension(800, 200));

		vide2 = new JPanel();
		vide2.setOpaque(false);
		vide2.setPreferredSize(new Dimension(800, 25));

		vide3 = new JPanel();
		vide3.setOpaque(false);
		vide3.setPreferredSize(new Dimension(600, 25));

		recharger = new JButton();
		recharger.setText("Reset");
		recharger.setHorizontalTextPosition(JButton.CENTER);
		recharger.setPreferredSize(new Dimension(200, 25));
		recharger.addActionListener(new ButtonListener_RechargerEmprunts());

		// The main container
		this.setPreferredSize(new Dimension(800, 600));
		// The layout manager
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

		// The container
		this.setVisible(true);
	}

	//Activate page
	public void activer(){
		recharger.setEnabled(true);
	}
	
	//Deactivate the page
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
		infoProduct.setText("");
	}

	//Load the data
	public void loadTableBorrowers() throws RemoteException {
		listEmprunt = stockProducts.getProductsBorrowedByUser(terminal.getUserName());
		IProduct product;
		int i;
		for (i = 0; i < listEmprunt.size(); i++) {
			product = listEmprunt.get(i);
			ids[i].setText(product.getId());
			types[i].setText(product.getType());
			commentaires[i].setText("");
			commentaires[i].setEnabled(true);
			notes[i].setEnabled(true);
			views[i].addActionListener(new ButtonListener_Voir(product));
			views[i].setEnabled(true);
			retourners[i].addActionListener(new ButtonListener_Retourner(product, i));
			retourners[i].setEnabled(true);
		}
	}

	//View Button
	class ButtonListener_Voir implements ActionListener {

		private IProduct product;

		public ButtonListener_Voir(IProduct product) {
			this.product = product;
		}

		public void actionPerformed(ActionEvent arg0) {
			infoProduct.setText("");
			String content = new String("");
			try {
				content = content + "ID: \t" + product.getId() + "\n\n";
				content = content + "General Information:\n";
				content = content + "--------------------------------------\n";
				content = content + "TYPE: \t" + product.getType() + "\n";
				content = content + "DESCRIPTION: \t" + product.getDescription() + "\n";
				content = content + "Add date: \t" + product.getDateOfAdd() + " from " + product.getAdder()
						+ "\n";
				if (product.getState().compareTo("Borrowed") == 0)
					content = content + "STATUS: \t" + product.getState() + " from " + product.getBorrower() + "\n";
				else
					content = content + "STATUS: \t" + product.getState() + "\n\n";
				content = content + "Borrow Information:\n";
				content = content + "--------------------------------------\n";
				content = content + "Borrow number: \t" + product.getNbrEmprunts() + "\n";
				content = content + "Average Note: \t" + product.getAvgGrade() + "\n";
				content = content + "COMMENTS:\t";
				LinkedList<String> listCommentaires = product.getReviews();
				if (listCommentaires.size() == 0)
					content = content + "No comments\n";
				else {
					content = content + "\n";
					int i;
					for (i = 0; i < listCommentaires.size(); i++)
						content = content + " - " + listCommentaires.get(i) + "\n";
				}
				content = content + "Number of Professor waiting: \t" + product.nbrWaitingEnseignant() + "\n";
				content = content + "Number of Student Professor: \t" + product.nbrWaitingEtudiant() + "\n";

				infoProduct.setText(content);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	//Back Button
	class ButtonListener_Retourner implements ActionListener {

		private IProduct product;
		private int index;

		public ButtonListener_Retourner(IProduct product, int index) {
			this.product = product;
			this.index = index;
		}

		public void actionPerformed(ActionEvent arg0) {
			try {
				String commentaire = commentaires[index].getText();
				int note = (int) notes[index].getSelectedItem();
				String message = product.returnProduct(note, commentaire);
				//Reload pages
				initialiser();
				loadTableBorrowers();
				terminal.getPageAllProducts().initialiser();
				terminal.getPageAllProducts().LoadProductsTable();
				terminal.getPageAchat().initialiser();
				terminal.getPageAchat().loadTableProductsBuyable();
				infoProduct.setText(message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	//Recharge button
	class ButtonListener_RechargerEmprunts implements ActionListener {

		public ButtonListener_RechargerEmprunts() {

		}

		public void actionPerformed(ActionEvent arg0) {
			try {
				initialiser();
				loadTableBorrowers();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
