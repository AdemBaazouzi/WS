import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Vehicule extends UnicastRemoteObject implements IVehicule {
	String id;
	String type;
	String description;
	double prixEuro;
	Date dateAjout;
	String ajouteur; //La personne qui a ajoute le vehicule dans la base
	String etat;
	LinkedList<Integer> notes;
	LinkedList<String> commentaires;
	int nbrEmprunts;
	String emprunteur; //La personne en train d'occuper le vehicul
	LinkedList<String> listeAttEnseignant; //Liste d'enseignants en attente
	LinkedList<String> listeAttEtudiant; //Liste d'etudiants en attente
	boolean vendu;
	
	public Vehicule(String id, String type, String description, double prixEuro, Date dateAjout, String ajouteur)
			throws RemoteException {
		this.id = id;
		this.type = type;
		this.description = description;
		this.prixEuro = prixEuro;
		this.dateAjout = dateAjout;
		this.ajouteur = ajouteur;
		this.etat = "Disponible";
		notes = new LinkedList<Integer>();
		commentaires = new LinkedList<String>();
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
	
	public String getDateAjout() throws RemoteException {
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/YYYY");
		return ft.format(this.dateAjout).toString();
	}

	public String getAjouteur() throws RemoteException {
		return this.ajouteur;
	}

	public String getEtat() throws RemoteException {
		return this.etat;
	}

	public LinkedList<Integer> getNotes() throws RemoteException {
		return this.notes;
	}

	public double getNoteMoyenne() throws RemoteException {
		if(notes.size()==0) return 0;
		int somme = 0;
		int i;
		for (i = 0; i < notes.size(); i++)
			somme = somme + notes.get(i);
		return (double)somme / (double)(notes.size());
	}

	public LinkedList<String> getCommentaires() throws RemoteException {
		return this.commentaires;
	}

	public int getNbrEmprunts() throws RemoteException {
		return this.nbrEmprunts;
	}

	public String getEmprunteur() throws RemoteException {
		return this.emprunteur;
	}

	public void changeEtat(String etat) throws RemoteException {
		this.etat = etat;
	}

	//Emprunter
	public String ajoutAttendre(String name, String groupe) throws RemoteException {
		//Si le vehicule a ete achete juste avant par une autre personne 
		if(this.vendu){
			return "Emprunt echouee,le vehicule a ete vendu";
		}
		
		int i;
		//Si l'utilisateur est un enseignant
		if (groupe.compareTo("Enseignant") == 0) {

			if (this.listeAttEnseignant.size() == 0 && this.etat.compareTo("Disponible") == 0) {
				this.emprunteur = name;
				this.nbrEmprunts++;
				this.changeEtat("Emprunte");
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

		} else { //Si l'utilisateur est un etudiant

			if (this.listeAttEnseignant.size() == 0 && this.listeAttEtudiant.size() == 0
					&& this.etat.compareTo("Disponible") == 0) {
				this.emprunteur = name;
				this.nbrEmprunts++;
				this.changeEtat("Emprunte");
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
	
	//Nombre d'enseignant en attente
	public int nbrAttendreEnseignant() throws RemoteException {
		return this.listeAttEnseignant.size();
	}

	//Nombre d'etudiant en attente
	public int nbrAttendreEtudiant() throws RemoteException {
		return this.listeAttEtudiant.size();
	}

	//Retour du vehicule
	public String retourner(int note, String commentaire) throws RemoteException {
		this.notes.add(note);
		if (commentaire != null && commentaire.length() > 0)
			this.commentaires.add(commentaire);
		//S'il y a une ou plusieurs reservations
		if (listeAttEnseignant.size() > 0) {
			emprunteur = listeAttEnseignant.removeFirst();
			nbrEmprunts++;
			return "Vehicule " + this.getId() + " a ete retourne et ensuite a ete donne a " + this.getEmprunteur();
		} else if (listeAttEtudiant.size() > 0) {
			emprunteur = listeAttEtudiant.removeFirst();
			nbrEmprunts++;
			return "Vehicule " + this.getId() + " a ete retourne et ensuite a ete donne ensuite a "
					+ this.getEmprunteur();
		} else { //Sinon
			this.changeEtat("Disponible");
			this.emprunteur = new String("");
			return "Vehicule " + this.getId() + " a ete retourne, il est maintenant disponible";
		}
	}
	
	//Verifier si le vehicule peut etre vendu
	public boolean isAchetable()throws RemoteException{
		//Pas encore vendu
		if(this.vendu){
			return false;
		}
		//Au moins un emprunt effectue
		if(nbrEmprunts==0){
			return false;
		}
		//Disponible
		if(etat.compareTo("Disponible")!=0){
			return false;
		}
		//Pas de reservation
		if(listeAttEnseignant.size()>0 || listeAttEtudiant.size()>0){
			return false;
		}
		//Ajoute il y a au moins 2 ans
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -2);
		Date before2Years = cal.getTime();
		if(before2Years.compareTo(dateAjout)<0){
			return false;
		}
		return true;
	}
	
	public boolean getVendu() throws RemoteException{
		return this.vendu;
	}
	
	//Vendre le vehicule
	public void vendre() throws RemoteException{
		this.etat = "Vendu";
		this.vendu = true;
	}
	
}
