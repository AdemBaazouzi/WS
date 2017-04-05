import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Stock extends UnicastRemoteObject implements IStock {
	int nbrVehicules;
	LinkedList<IVehicule> listVehicules;

	public Stock() throws RemoteException {
		this.nbrVehicules = 0;
		listVehicules = new LinkedList<IVehicule>();
	}

	public int getNbrVehicules() throws RemoteException {
		return this.nbrVehicules;
	}

	//La liste des vehicules, les parametres sont les criteres de recherche
	public LinkedList<IVehicule> getVehicules(String id, String type, String etat) throws RemoteException {
		LinkedList<IVehicule> list = new LinkedList<IVehicule>();
		int i;
		boolean recherche = true;
		IVehicule vehicule;
		for (i = 0; i < listVehicules.size(); i++) {

			vehicule = listVehicules.get(i);
			//Pas encore vendu
			if (vehicule.getVendu()) {
				recherche = false;
			}
			//Si recherche par id
			if (id != null && id.length() > 0) {
				if (vehicule.getId().compareTo(id) != 0)
					recherche = false;
			}
			//Si recherche par type
			if (type != null && type.length() > 0) {
				if (vehicule.getType().compareTo(type) != 0)
					recherche = false;
			}
			//Si recherche par etat
			if (etat != null && etat.length() > 0) {
				if (vehicule.getEtat().compareTo(etat) != 0)
					recherche = false;
			}

			if (recherche == true)
				list.add(vehicule);

			recherche = true;
		}
		return list;
	}

	//La liste des vehicules achetables, les parametres sont les criteres de recherche
	public LinkedList<IVehicule> getVehiculesAchetables(String id, String type, String etat) throws RemoteException {
		LinkedList<IVehicule> list = new LinkedList<IVehicule>();
		int i;
		boolean recherche = true;
		IVehicule vehicule;
		for (i = 0; i < listVehicules.size(); i++) {

			vehicule = listVehicules.get(i);

			if (!vehicule.isAchetable()) {
				recherche = false;
			}

			if (id != null && id.length() > 0) {
				if (vehicule.getId().compareTo(id) != 0)
					recherche = false;
			}
			if (type != null && type.length() > 0) {
				if (vehicule.getType().compareTo(type) != 0)
					recherche = false;
			}
			if (etat != null && etat.length() > 0) {
				if (vehicule.getEtat().compareTo(etat) != 0)
					recherche = false;
			}

			if (recherche == true)
				list.add(vehicule);

			recherche = true;
		}
		return list;
	}

	//Ajouter un vehicule dans la base
	public void ajoutVehicule(String type, String description, double prixEuro, String ajouteur, Date date)
			throws RemoteException {
		nbrVehicules++;
		String id = "Veh" + nbrVehicules;
		Vehicule vehicule = new Vehicule(id, type, description, prixEuro, date, ajouteur);
		this.listVehicules.add(vehicule);
	}

	//Les vehicules en train d'etre occupes par 'userName'
	public LinkedList<IVehicule> getVehiculesEmpruntesByUser(String userName) throws RemoteException {
		LinkedList<IVehicule> listVehiculesEmpruntesByUser = new LinkedList<IVehicule>();

		int i;
		IVehicule vehicule;

		for (i = 0; i < this.listVehicules.size(); i++) {
			vehicule = this.listVehicules.get(i);
			if (vehicule.getEmprunteur().compareTo(userName) == 0)
				listVehiculesEmpruntesByUser.add(vehicule);
		}

		return listVehiculesEmpruntesByUser;
	}

	//vendre les vehicules d'un panier d'un client
	public String vendreVehicules(LinkedList<IVehicule> panier, double montant, String devise) throws RemoteException {
		int i;
		//Verifier si tous les vehicules sont encore achetables
		for (i = 0; i < panier.size(); i++) {
			IVehicule vehicule = panier.get(i);
			if (vehicule.getVendu() || vehicule.getEtat().compareTo("Disponible") != 0
					|| vehicule.nbrAttendreEnseignant() > 0 || vehicule.nbrAttendreEtudiant() > 0) {
				return "Achat echoue, un ou plusieurs vehicules choisis ne sont plus achetable";
			}
		}
		for (i = 0; i < panier.size(); i++) {
			IVehicule vehicule = panier.get(i);
			vehicule.vendre();
		}
		return "Achat reussi, le montant total est "+montant+devise;
	}
}
