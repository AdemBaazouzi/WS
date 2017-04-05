import java.rmi.*;
import java.util.LinkedList;

public interface IVehicule extends Remote {
	
	public String getId() throws RemoteException;
	public String getType() throws RemoteException;
	public String getDescription() throws RemoteException;
	public double getPrixEuro() throws RemoteException;
	public String getDateAjout() throws RemoteException;
	public String getAjouteur() throws RemoteException;
	public String getEtat() throws RemoteException;
	public LinkedList<Integer> getNotes() throws RemoteException;
	public double getNoteMoyenne() throws RemoteException;
	public LinkedList<String> getCommentaires() throws RemoteException;
	public int getNbrEmprunts() throws RemoteException;
	public String getEmprunteur() throws RemoteException;
	public void changeEtat(String etat) throws RemoteException;
	public String ajoutAttendre(String name, String groupe) throws RemoteException;
	public int nbrAttendreEnseignant() throws RemoteException;
	public int nbrAttendreEtudiant() throws RemoteException;
	public String retourner(int note, String commentaire) throws RemoteException;
	public boolean isAchetable() throws RemoteException;
	public boolean getVendu() throws RemoteException;
	public void vendre() throws RemoteException;
}