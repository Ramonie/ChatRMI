import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServerMain {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        ChatServer server = new ChatServer();
        registry.rebind("Chat", server);
        System.out.println("Servidor pronto para aceitar conexões...");
    }
}