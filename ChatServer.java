import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatInterface {
    private List<ChatInterface> clients = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    protected ChatServer() throws RemoteException {
        super();
    }

    public void sendMessage(String message) throws RemoteException {
        for (ChatInterface client : clients) {
            client.sendMessage(message);
        }
    }

    public void login(String username, ChatInterface client) throws RemoteException {
        clients.add(client);
        sendMessage(username + " entrou no chat.");
    }

    public void logout(String username) throws RemoteException {
        clients.removeIf(client -> {
            try {
                client.sendMessage(username + " saiu do chat.");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return true;
        });
    }
}
