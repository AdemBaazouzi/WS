import java.rmi.Naming;
import java.text.SimpleDateFormat;

public class Server {
	public static void main(String args[]) {
		try {
			IStock stockProducts = new Stock();

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
			stockProducts.addProduct("Voiture", "Vehicule de type voiture", 200, "duykhanh",format.parse("10/1/2012"));
			stockProducts.addProduct("Moto", "Vehicule de type moto", 200, "duykhanh", format.parse("10/1/2012"));
			stockProducts.addProduct("Autobus", "Vehicule de type Autobus", 150, "zaid", format.parse("10/1/2015"));
			stockProducts.addProduct("Autobus", "Vehicule de type Autobus", 150, "zaid", format.parse("10/1/2011"));
			stockProducts.addProduct("Autobus", "Vehicule de type Autobus", 150, "omar", format.parse("10/1/2015"));

			Naming.rebind("rmi://localhost/SellService", stockProducts);
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}
}
