
/**
 * ChatClient é uma classe que implementa a interface ChatInterface para se conectar a um servidor de chat
 * usando RMI e enviar e receber mensagens.
 */
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatInterface {
    private static final long serialVersionUID = 1L;

    // Cria um novo objeto ChatClient remoto.

    protected ChatClient() throws RemoteException {
        super();
    }

    // Imprime a mensagem recebida no console.

    public void sendMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    // Não faz nada. Este método é usado pelo servidor de chat para registrar o
    // cliente.

    public void login(String username, ChatInterface client) throws RemoteException {
    }

    // Não faz nada. Este método é usado pelo servidor de chat para desconectar o
    // cliente.

    public void logout(String username) throws RemoteException {
    }

    // Cria uma conexão com o servidor de chat e envia e recebe mensagens.

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
