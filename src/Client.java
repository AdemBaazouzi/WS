import java.rmi.*;

public class Client{
	
	@SuppressWarnings({ "unused", "deprecation" })
	public static void main(String[] args) {
		try {
			String codebase = "file:///D:/WorkspaceJava/DauphineVehiculesServeur/src/";
			System.setProperty("java.rmi.server.codebase", codebase);
			System.setProperty("java.security.policy", "security.policy");
			System.setSecurityManager(new RMISecurityManager());
			
			IStock stockVehicules = (IStock) Naming.lookup("rmi://localhost/VehiculesService");
			Terminal f = new Terminal(stockVehicules);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}