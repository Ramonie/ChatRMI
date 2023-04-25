
/**
 * ChatClientInterface é uma interface que define um método que um servidor de chat deve
 * chamar para enviar mensagens a um cliente usando RMI.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterface extends Remote {

    // Recebe uma mensagem enviada pelo servidor de chat.
    void receiveMessage(String message) throws RemoteException;
}
