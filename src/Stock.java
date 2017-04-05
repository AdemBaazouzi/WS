import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Stock extends UnicastRemoteObject implements IStock {
	int nbrProducts;
	LinkedList<IProduct> listProducts;

	public Stock() throws RemoteException {
		this.nbrProducts = 0;
		listProducts = new LinkedList<IProduct>();
	}

	public int getNbrProducts() throws RemoteException {
		return this.nbrProducts;
	}

	//The list of products, the parameters are the search criteria
	public LinkedList<IProduct> getProducts(String id, String type, String etat) throws RemoteException {
		LinkedList<IProduct> list = new LinkedList<IProduct>();
		int i;
		boolean recherche = true;
		IProduct product;
		for (i = 0; i < listProducts.size(); i++) {

			product = listProducts.get(i);
			//not yet sold
			if (product.getSoldProd()) {
				recherche = false;
			}
			//if search is by Id
			if (id != null && id.length() > 0) {
				if (product.getId().compareTo(id) != 0)
					recherche = false;
			}
			//if search is by Type
			if (type != null && type.length() > 0) {
				if (product.getType().compareTo(type) != 0)
					recherche = false;
			}
			//if search is by State
			if (etat != null && etat.length() > 0) {
				if (product.getState().compareTo(etat) != 0)
					recherche = false;
			}

			if (recherche == true)
				list.add(product);

			recherche = true;
		}
		return list;
	}

	//The list of purchasable products, the parameters are the search criteria
	public LinkedList<IProduct> getProductsBuyable(String id, String type, String etat) throws RemoteException {
		LinkedList<IProduct> list = new LinkedList<IProduct>();
		int i;
		boolean recherche = true;
		IProduct product;
		for (i = 0; i < listProducts.size(); i++) {

			product = listProducts.get(i);

			if (!product.isAchetable()) {
				recherche = false;
			}

			if (id != null && id.length() > 0) {
				if (product.getId().compareTo(id) != 0)
					recherche = false;
			}
			if (type != null && type.length() > 0) {
				if (product.getType().compareTo(type) != 0)
					recherche = false;
			}
			if (etat != null && etat.length() > 0) {
				if (product.getState().compareTo(etat) != 0)
					recherche = false;
			}

			if (recherche == true)
				list.add(product);

			recherche = true;
		}
		return list;
	}

	//Add a vehicle to the database
	public void addProduct(String type, String description, double prixEuro, String adder, Date date)
			throws RemoteException {
		nbrProducts++;
		String id = "Veh" + nbrProducts;
		Product product = new Product(id, type, description, prixEuro, date, adder);
		this.listProducts.add(product);
	}

	//Vehicles being occupied by 'userName'
	public LinkedList<IProduct> getProductsBorrowedByUser(String userName) throws RemoteException {
		LinkedList<IProduct> listProductsBorrowedByUser = new LinkedList<IProduct>();

		int i;
		IProduct product;

		for (i = 0; i < this.listProducts.size(); i++) {
			product = this.listProducts.get(i);
			if (product.getBorrower().compareTo(userName) == 0)
				listProductsBorrowedByUser.add(product);
		}

		return listProductsBorrowedByUser;
	}

	//Sell products from a customer's basket
	public String sellProducts(LinkedList<IProduct> panier, double amount, String devise) throws RemoteException {
		int i;
		//Check whether all products are still purchasable
		for (i = 0; i < panier.size(); i++) {
			IProduct product = panier.get(i);
			if (product.getSoldProd() || product.getState().compareTo("Disponible") != 0
					|| product.nbrWaitingEnseignant() > 0 || product.nbrWaitingEtudiant() > 0) {
				return "Achat echoue, un ou plusieurs vehicules choisis ne sont plus achetable";
			}
		}
		for (i = 0; i < panier.size(); i++) {
			IProduct product = panier.get(i);
			product.sell();
		}
		return "Achat reussi, le montant total est "+amount+devise;
	}
}
