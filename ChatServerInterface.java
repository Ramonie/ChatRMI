import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterface extends Remote {
    void registerClient(String name, ChatClientInterface client) throws RemoteException;
    void unregisterClient(String name) throws RemoteException;
    void broadcast(String message) throws RemoteException;
    void send(String name, String message) throws RemoteException;
    void registerChatClient(ChatClientInterface chatClient) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    String receive() throws RemoteException;
}
