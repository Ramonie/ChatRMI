
/**
 * ChatInterface é uma interface que define os métodos que um cliente deve
 * implementar para se comunicar com um servidor de chat usando RMI.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {

    // Envia uma mensagem para o servidor de chat para ser enviada a outros
    // usuários.

    public void sendMessage(String message) throws RemoteException;

    // Registra um usuário no servidor de chat.

    public void login(String username, ChatInterface client) throws RemoteException;

    public void logout(String username) throws RemoteException;
}
