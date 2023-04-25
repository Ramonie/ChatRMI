import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatInterface {
    // Lista de clientes conectados ao servidor
    private List<ChatInterface> clients = new ArrayList<>();

    // Número de série da classe (obrigatório para serialização)
    private static final long serialVersionUID = 1L;

    // Construtor padrão da classe, que lança uma exceção RemoteException
    protected ChatServer() throws RemoteException {
        super();
    }

    // Implementação do método sendMessage da interface ChatInterface
    public void sendMessage(String message) throws RemoteException {
        // Envia a mensagem para todos os clientes conectados
        for (ChatInterface client : clients) {
            client.sendMessage(message);
        }
    }

    // Implementação do método login da interface ChatInterface
    public void login(String username, ChatInterface client) throws RemoteException {
        // Adiciona o cliente na lista de clientes conectados
        clients.add(client);

        // Envia uma mensagem informando que o usuário entrou no chat
        sendMessage(username + " entrou no chat.");
    }

    // Implementação do método logout da interface ChatInterface
    public void logout(String username) throws RemoteException {
        // Remove o cliente da lista de clientes conectados
        clients.removeIf(client -> {
            try {
                // Envia uma mensagem informando que o usuário saiu do chat
                client.sendMessage(username + " saiu do chat.");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return true;
        });
    }
}
