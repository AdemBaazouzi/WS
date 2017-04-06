import java.rmi.Naming;
import java.text.SimpleDateFormat;

public class Server {
	public static void main(String args[]) {
		try {
			IStock stockProducts = new Stock();

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
			stockProducts.addProduct("Video Projector", "Video Projector EPSON", 200, "Anis",format.parse("10/1/2008"));
			stockProducts.addProduct("Video Projector", "Video Projector CANON", 200, "Anis",format.parse("10/1/2010"));
			stockProducts.addProduct("Book", "Java Web Services", 200, "Adam",format.parse("10/1/2000"));
			Naming.rebind("rmi://localhost/SellService", stockProducts);
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}
}
