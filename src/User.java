import java.rmi.*;

public class User{
	
	@SuppressWarnings({ "unused", "deprecation" })
	public static void main(String[] args) {
		try {
			String codebase = "file:///D:/WorkspaceJava/DauphineSellServer/src/";
			System.setProperty("java.rmi.server.codebase", codebase);
			System.setProperty("java.security.policy", "security.policy");
			System.setSecurityManager(new RMISecurityManager());
			
			IStock stockProducts = (IStock) Naming.lookup("rmi://localhost/SellService");
			Terminal f = new Terminal(stockProducts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}