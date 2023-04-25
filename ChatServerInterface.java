import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterface extends Remote {
    // Método para registrar um cliente no servidor
    void register(ChatClientInterface client, String name) throws RemoteException;

    // Método para remover um cliente do servidor
    void unregister(String name) throws RemoteException;

    // Método para enviar uma mensagem para todos os clientes conectados ao servidor
    void broadcast(String message) throws RemoteException;
}
