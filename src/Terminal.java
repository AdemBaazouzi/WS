import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Terminal extends JFrame {
	private String userName;
	private String userGroupe = "Etudiant";
	private boolean online;
	private IStock stockProducts;
	private JTabbedPane onglet;
	private AuthentificationGUI pageAuthentification;
	private AddProductGUI pageAddProduct;
	private AllProductsGUI pageAllProducts;
	private LoanGUI pageEmprunt;
	private PurchaseGUI pageAchat;

	public Terminal(IStock stockProducts) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, IOException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		this.stockProducts = stockProducts;
		this.setTitle("Dauphine products");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		String imagePath = "res/Image.png";
		InputStream imgStream = Terminal.class.getResourceAsStream(imagePath);
		BufferedImage myImg = ImageIO.read(imgStream);
		this.setIconImage(myImg);
		// Define tab and pages
		onglet = new JTabbedPane();
		// Creating pages. At the beginning the pages are disactivated
		pageAuthentification = new AuthentificationGUI(this);
		pageAddProduct = new AddProductGUI(this, this.stockProducts);
		pageAddProduct.desactiver();
		pageAllProducts = new AllProductsGUI(this, this.stockProducts);
		pageAllProducts.desactiver();
		pageAllProducts.initialiser();
		pageEmprunt = new LoanGUI(this, this.stockProducts);
		pageEmprunt.desactiver();
		pageEmprunt.initialiser();
		pageAchat = new PurchaseGUI(this, this.stockProducts);
		pageAchat.desactiver();
		pageAchat.initialiser();
		onglet.add("Authentification", pageAuthentification);
		onglet.add("Add products", pageAddProduct);
		onglet.add("Productlist", pageAllProducts);
		onglet.add("Borrow list", pageEmprunt);
		onglet.add("Product Sell Service", pageAchat);

		// We then pass the tabs to the content pane
		this.getContentPane().add(onglet);
		this.setVisible(true);
	}

	public String getUserName() {
		return userName;
	}

	public String getUserGroupe() {
		return userGroupe;
	}

	public boolean getOnline() {
		return online;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserGroupe(String userGroupe) {
		this.userGroupe = userGroupe;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public AuthentificationGUI getPageAuthentification() {
		return this.pageAuthentification;
	}

	public AddProductGUI getPageAddProduct() {
		return this.pageAddProduct;
	}

	public AllProductsGUI getPageAllProducts() {
		return this.pageAllProducts;
	}

	public LoanGUI getPageBorrower() {
		return this.pageEmprunt;
	}

	public PurchaseGUI getPageAchat() {
		return this.pageAchat;
	}
}
