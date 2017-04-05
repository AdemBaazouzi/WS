import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Product extends UnicastRemoteObject implements IProduct {
	String id;
	String type;
	String description;
	double prixEuro;
	Date dateOfAdd;
	String adder; //The person who added the product to the base
	String state;
	LinkedList<Integer> notes;
	LinkedList<String> reviews;
	int nbrEmprunts;
	String emprunteur; //The person occupying the product
	LinkedList<String> listeAttEnseignant; //List of teachers in waiting
	LinkedList<String> listeAttEtudiant; //List of students waiting
	boolean vendu;
	
	public Product(String id, String type, String description, double prixEuro, Date dateOfAdd, String adder)
			throws RemoteException {
		this.id = id;
		this.type = type;
		this.description = description;
		this.prixEuro = prixEuro;
		this.dateOfAdd = dateOfAdd;
		this.adder = adder;
		this.state = "Disponible";
		notes = new LinkedList<Integer>();
		reviews = new LinkedList<String>();
		nbrEmprunts = 0;
		emprunteur = new String("");
		listeAttEnseignant = new LinkedList<String>();
		listeAttEtudiant = new LinkedList<String>();
		vendu = false;
	}

	public String getId() throws RemoteException {
		return this.id;
	}

	public String getType() throws RemoteException {
		return this.type;
	}

	public String getDescription() throws RemoteException {
		return this.description;
	}

	public double getPrixEuro() throws RemoteException {
		return this.prixEuro;
	}
	
	public String getDateOfAdd() throws RemoteException {
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/YYYY");
		return ft.format(this.dateOfAdd).toString();
	}

	public String getAdder() throws RemoteException {
		return this.adder;
	}

	public String getState() throws RemoteException {
		return this.state;
	}

	public LinkedList<Integer> getNotes() throws RemoteException {
		return this.notes;
	}

	public double getAvgGrade() throws RemoteException {
		if(notes.size()==0) return 0;
		int somme = 0;
		int i;
		for (i = 0; i < notes.size(); i++)
			somme = somme + notes.get(i);
		return (double)somme / (double)(notes.size());
	}

	public LinkedList<String> getReviews() throws RemoteException {
		return this.reviews;
	}

	public int getNbrEmprunts() throws RemoteException {
		return this.nbrEmprunts;
	}

	public String getBorrower() throws RemoteException {
		return this.emprunteur;
	}

	public void changeState(String state) throws RemoteException {
		this.state = state;
	}

	//Borrower
	public String ajoutAttendre(String name, String groupe) throws RemoteException {
		//If the product was purchased just before by another person
		if(this.vendu){
			return "Emprunt echouee,le vehicule a ete vendu";
		}
		
		int i;
		//If the user is a teacher
		if (groupe.compareTo("Enseignant") == 0) {

			if (this.listeAttEnseignant.size() == 0 && this.state.compareTo("Disponible") == 0) {
				this.emprunteur = name;
				this.nbrEmprunts++;
				this.changeState("Emprunte");
				return "Emprunt reussi par " + name;
			} else if (this.emprunteur.compareTo(name) == 0) {
				return this.id + "Reservation echouee, " + name + " est en train d'utiliser le vehicule";
			} else {
				for (i = 0; i < this.listeAttEnseignant.size(); i++) {
					if (this.listeAttEnseignant.get(i).compareTo(name) == 0) {
						return "Reservation echouee, " + name + " est deja dans la liste d'attente";
					}
				}

				this.listeAttEnseignant.add(name);
				return "Reservation reussie, " + name + " est bien ajoute dans la liste d'attente";
			}

		} else { //If the user is a student

			if (this.listeAttEnseignant.size() == 0 && this.listeAttEtudiant.size() == 0
					&& this.state.compareTo("Disponible") == 0) {
				this.emprunteur = name;
				this.nbrEmprunts++;
				this.changeState("Emprunte");
				return "Emprunt reussi par " + name;
			} else if (this.emprunteur.compareTo(name) == 0) {
				return this.id + "Reservation echouee, " + name + " est en train d'utiliser le vehicule";
			} else {
				for (i = 0; i < this.listeAttEtudiant.size(); i++) {
					if (this.listeAttEtudiant.get(i).compareTo(name) == 0) {
						return "Reservation echouee, " + name + " est deja dans la liste d'attente";
					}
				}

				this.listeAttEtudiant.add(name);
				return "Reservation reussie, " + name + " est bien ajoute dans la liste d'attente";
			}

		}
	}
	
	//Number of teachers waiting
	public int nbrWaitingEnseignant() throws RemoteException {
		return this.listeAttEnseignant.size();
	}

	//Number of Student Pending
	public int nbrWaitingEtudiant() throws RemoteException {
		return this.listeAttEtudiant.size();
	}

	//Return of the product
	public String returnProduct(int note, String review) throws RemoteException {
		this.notes.add(note);
		if (review != null && review.length() > 0)
			this.reviews.add(review);
		//If there are one or more reservations
		if (listeAttEnseignant.size() > 0) {
			emprunteur = listeAttEnseignant.removeFirst();
			nbrEmprunts++;
			return "Vehicule " + this.getId() + " a ete retourne et ensuite a ete donne a " + this.getBorrower();
		} else if (listeAttEtudiant.size() > 0) {
			emprunteur = listeAttEtudiant.removeFirst();
			nbrEmprunts++;
			return "Vehicule " + this.getId() + " a ete retourne et ensuite a ete donne ensuite a "
					+ this.getBorrower();
		} else {
			this.changeState("Disponible");
			this.emprunteur = new String("");
			return "Vehicule " + this.getId() + " a ete retourne, il est maintenant disponible";
		}
	}
	
	//Check whether the product can be sold
	public boolean isAchetable()throws RemoteException{
		//Not yet sold
		if(this.vendu){
			return false;
		}
		//At least one borrowing
		if(nbrEmprunts==0){
			return false;
		}
		//Available
		if(state.compareTo("Disponible")!=0){
			return false;
		}
		//No reservation
		if(listeAttEnseignant.size()>0 || listeAttEtudiant.size()>0){
			return false;
		}
		//Added 2 years ago
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -2);
		Date before2Years = cal.getTime();
		if(before2Years.compareTo(dateOfAdd)<0){
			return false;
		}
		return true;
	}
	
	public boolean getSoldProd() throws RemoteException{
		return this.vendu;
	}
	
	//Sell the product
	public void sell() throws RemoteException{
		this.state = "Vendu";
		this.vendu = true;
	}
	
}
