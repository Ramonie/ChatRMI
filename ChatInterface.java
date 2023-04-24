import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
    public void sendMessage(String message) throws RemoteException;
    public void login(String username, ChatInterface client) throws RemoteException;
    public void logout(String username) throws RemoteException;
}