import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterface extends Remote {
    void register(ChatClientInterface client, String name) throws RemoteException;
    void unregister(String name) throws RemoteException;
    void broadcast(String message) throws RemoteException;
}
