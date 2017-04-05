import java.rmi.*;
import java.util.Date;
import java.util.LinkedList;

public interface IStock extends Remote{
	public int getNbrVehicules() throws RemoteException;
	public LinkedList<IVehicule> getVehicules(String id, String type, String etat) throws RemoteException;
	public LinkedList<IVehicule> getVehiculesAchetables(String id, String type, String etat) throws RemoteException; 
	public void ajoutVehicule(String type, String description, double prixEuro, String ajouteur, Date date) throws RemoteException;
	public LinkedList<IVehicule> getVehiculesEmpruntesByUser(String userName) throws RemoteException;
	public String vendreVehicules(LinkedList<IVehicule> panier, double montant, String devise) throws RemoteException;
}
