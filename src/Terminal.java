import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Terminal extends JFrame{
	private String userName = "NGHIEM Duy Khanh";
	private String userGroupe = "Etudiant";
	private boolean online;
	private IStock stockVehicules;
	private JTabbedPane onglet;
	private PageAuthentification pageAuthentification;
	private PageAjoutVehicule pageAjoutVehicule;
	private PageAllVehicules pageAllVehicules;
	private PageEmprunt pageEmprunt;
	private PageAchat pageAchat;
	
	public Terminal(IStock stockVehicules) throws RemoteException{
		this.stockVehicules = stockVehicules;
		this.setTitle("Dauphine vehicules");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		
		//Definir l'onglet et les pages
		onglet = new JTabbedPane();
		//Creation des pages. Au debut les pages sont desactivees
        pageAuthentification = new PageAuthentification(this);
        pageAjoutVehicule = new PageAjoutVehicule(this, this.stockVehicules);
        pageAjoutVehicule.desactiver();
		pageAllVehicules = new PageAllVehicules(this, this.stockVehicules);
		pageAllVehicules.desactiver();
		pageAllVehicules.initialiser();
		pageEmprunt = new PageEmprunt(this, this.stockVehicules);
		pageEmprunt.desactiver();
		pageEmprunt.initialiser();
		pageAchat = new PageAchat(this, this.stockVehicules);
		pageAchat.desactiver();
		pageAchat.initialiser();
		onglet.add("Authentification", pageAuthentification);
        onglet.add("Ajout vehicules", pageAjoutVehicule);
		onglet.add("Liste vehicules", pageAllVehicules);
		onglet.add("Liste emprunts", pageEmprunt);
		onglet.add("Cars Service", pageAchat);
		 
		// On passe ensuite les onglets au content pane
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
        
        public PageAuthentification getPageAuthentification(){
        	return this.pageAuthentification;
        }
        
        public PageAjoutVehicule getPageAjoutVehicule(){
        	return this.pageAjoutVehicule;
        }
        
        public PageAllVehicules getPageAllVehicules(){
        	return this.pageAllVehicules;
        }
        
        public PageEmprunt getPageEmprunt(){
        	return this.pageEmprunt;
        }
        
        public PageAchat getPageAchat(){
        	return this.pageAchat;
        }
}
