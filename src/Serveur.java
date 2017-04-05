import java.rmi.Naming;
import java.text.SimpleDateFormat;

public class Serveur {
	public static void main(String args[]) {
		try {
			IStock stockVehicules = new Stock();

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
			stockVehicules.ajoutVehicule("Voiture", "Vehicule de type voiture", 200, "duykhanh",
					format.parse("10/1/2012"));
			stockVehicules.ajoutVehicule("Moto", "Vehicule de type moto", 200, "duykhanh", format.parse("10/1/2012"));
			stockVehicules.ajoutVehicule("Autobus", "Vehicule de type Autobus", 150, "zaid", format.parse("10/1/2015"));
			stockVehicules.ajoutVehicule("Autobus", "Vehicule de type Autobus", 150, "zaid", format.parse("10/1/2011"));
			stockVehicules.ajoutVehicule("Autobus", "Vehicule de type Autobus", 150, "omar", format.parse("10/1/2015"));

			Naming.rebind("rmi://localhost/VehiculesService", stockVehicules);
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}
}
