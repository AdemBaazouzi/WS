import java.rmi.*;
import java.util.Date;
import java.util.LinkedList;

public interface IStock extends Remote{
	public int getNbrProducts() throws RemoteException;
	public LinkedList<IProduct> getProducts(String id, String type, String state) throws RemoteException;
	public LinkedList<IProduct> getProductsBuyable(String id, String type, String state) throws RemoteException; 
	public void addProduct(String type, String description, double prixEuro, String adder, Date date) throws RemoteException;
	public LinkedList<IProduct> getProductsBorrowedByUser(String userName) throws RemoteException;
	public String sellProducts(LinkedList<IProduct> panier, double amount, String devise) throws RemoteException;
}
