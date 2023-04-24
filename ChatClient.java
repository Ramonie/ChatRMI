import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatInterface {
    private static final long serialVersionUID = 1L;

    protected ChatClient() throws RemoteException {
        super();
    }

    public void sendMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    public void login(String username, ChatInterface client) throws RemoteException {}

    public void logout(String username) throws RemoteException {}

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        ChatInterface server = (ChatInterface) registry.lookup("Chat");
        ChatClient client = new ChatClient();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String username = scanner.nextLine();
        server.login(username, client);
        System.out.println("Digite 'sair' para sair do chat.");
        while (true) {
            String message = scanner.nextLine();
            if (message.equalsIgnoreCase("sair")) {
                server.logout(username);
                System.exit(0);
            } else {
                server.sendMessage(username + ": " + message);
            }
        }
    }
}
