import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddProductGUI extends JLabel {

	Terminal terminal;
	IStock stockProducts;
	JLabel userLabel;
	JLabel userInfoLabel;
	JLabel typeLabel;
	JLabel descLabel;
	JLabel prixLabel;
	JTextArea descField;
	JComboBox<String> typeField;
	JTextField prixField;
	JButton addButton;

	public AddProductGUI(Terminal terminal, IStock stockProducts) {
		this.terminal = terminal;
		this.stockProducts = stockProducts;
		this.setSize(800, 500);
		this.setLayout(null);

		userLabel = new JLabel("User");
		userLabel.setBounds(370, 25, 80, 25);
		this.add(userLabel);

		userInfoLabel = new JLabel();
		userInfoLabel.setBounds(470, 25, 160, 25);
		this.add(userInfoLabel);

		typeLabel = new JLabel("Type");
		typeLabel.setBounds(370, 60, 80, 25);
		this.add(typeLabel);

		String[] st = { "Book", "Cloths", "Video Projector", "White Board", "Interactive DVD" };
		typeField = new JComboBox<String>(st);
		typeField.setBounds(470, 60, 160, 25);
		this.add(typeField);

		descLabel = new JLabel("Description");
		descLabel.setBounds(370, 100, 80, 25);
		this.add(descLabel);

		descField = new JTextArea();
		descField.setBounds(470, 100, 200, 100);
		this.add(descField);

		prixLabel = new JLabel("Price in Eur");
		prixLabel.setBounds(370, 220, 80, 25);
		this.add(prixLabel);

		prixField = new JTextField(25);
		prixField.setBounds(470, 220, 160, 25);
		this.add(prixField);

		addButton = new JButton("Add");
		addButton.setBounds(470, 260, 80, 25);
		this.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					stockProducts.addProduct((String) typeField.getSelectedItem(), descField.getText(),
							Double.parseDouble(prixField.getText()), terminal.getUserName(), new Date());
					terminal.getPageAllProducts().initialiser();
					terminal.getPageAllProducts().LoadProductsTable();
					JOptionPane.showMessageDialog(terminal, "Product Added", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		this.setVisible(true);
	}

	// Activate page
	public void activer() {
		String userInfo = terminal.getUserName() + " - " + terminal.getUserGroupe();
		userInfoLabel.setText(userInfo);
		addButton.setEnabled(true);
		descField.setEnabled(true);
		prixField.setEnabled(true);
		typeField.setEnabled(true);
	}

	// Deactivate the page
	public void desactiver() {
		userInfoLabel.setText("Non identifiable");
		addButton.setEnabled(false);
		descField.setEnabled(false);
		prixField.setEnabled(false);
		typeField.setEnabled(false);
	}

	public void initialiser() {
		descField.setText("");
		prixField.setText("");
	}

}
