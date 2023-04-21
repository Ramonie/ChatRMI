import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterface extends Remote {
    void registerChatClient(ChatClientInterface chatClient) throws RemoteException;

    void broadcastMessage(String message) throws RemoteException;

    void send(String name, String message);

    void registerClient(String name, ChatClientInterface stub);

    String receive();

    void unregisterClient(String name);

    /**
     * @param message
     */
    default void receive(final String message) {
        System.out.println(message);
    }
}
