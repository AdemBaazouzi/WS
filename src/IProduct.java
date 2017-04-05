import java.rmi.*;
import java.util.LinkedList;

public interface IProduct extends Remote {
	
	public String getId() throws RemoteException;
	public String getType() throws RemoteException;
	public String getDescription() throws RemoteException;
	public double getPrixEuro() throws RemoteException;
	public String getDateOfAdd() throws RemoteException;
	public String getAdder() throws RemoteException;
	public String getState() throws RemoteException;
	public LinkedList<Integer> getNotes() throws RemoteException;
	public double getAvgGrade() throws RemoteException;
	public LinkedList<String> getReviews() throws RemoteException;
	public int getNbrEmprunts() throws RemoteException;
	public String getBorrower() throws RemoteException;
	public void changeState(String etat) throws RemoteException;
	public String ajoutAttendre(String name, String groupe) throws RemoteException;
	public int nbrWaitingEnseignant() throws RemoteException;
	public int nbrWaitingEtudiant() throws RemoteException;
	public String returnProduct(int note, String commentaire) throws RemoteException;
	public boolean isAchetable() throws RemoteException;
	public boolean getSoldProd() throws RemoteException;
	public void sell() throws RemoteException;
}