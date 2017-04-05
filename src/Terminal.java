import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Terminal extends JFrame{
	private String userName = "NGHIEM Duy Khanh";
	private String userGroupe = "Etudiant";
	private boolean online;
	private IStock stockProducts;
	private JTabbedPane onglet;
	private AuthentificationGUI pageAuthentification;
	private AddProductGUI pageAddProduct;
	private AllProductsGUI pageAllProducts;
	private LoanGUI pageEmprunt;
	private PurchaseGUI pageAchat;
	
	public Terminal(IStock stockProducts) throws RemoteException{
		this.stockProducts = stockProducts;
		this.setTitle("Dauphine products");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		
		//Define tab and pages
		onglet = new JTabbedPane();
		//Creating pages. At the beginning the pages are disactivated
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
        onglet.add("Ajout products", pageAddProduct);
		onglet.add("Liste products", pageAllProducts);
		onglet.add("Liste emprunts", pageEmprunt);
		onglet.add("Cars Service", pageAchat);
		 
		// We then pass the tabs to the content pane
		this.getContentPane().add(onglet);
		this.setVisible(true);
	}
	
        public String getUserName(){
            return userName;
        }
        
        public String getUserGroupe(){
            return userGroupe;
        }
        
        public boolean getOnline(){
            return online;
        }
        
        public void setUserName(String userName){
            this.userName = userName;
        }
        
        public void setUserGroupe(String userGroupe){
            this.userGroupe = userGroupe;
        }
        
        public void setOnline(boolean online){
            this.online = online;
        }
        
        public AuthentificationGUI getPageAuthentification(){
        	return this.pageAuthentification;
        }
        
        public AddProductGUI getPageAddProduct(){
        	return this.pageAddProduct;
        }
        
        public AllProductsGUI getPageAllProducts(){
        	return this.pageAllProducts;
        }
        
        public LoanGUI getPageBorrower(){
        	return this.pageEmprunt;
        }
        
        public PurchaseGUI getPageAchat(){
        	return this.pageAchat;
        }
}
