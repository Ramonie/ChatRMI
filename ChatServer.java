import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ChatServer implements ChatServerInterface {
    private Map<String, ChatClientInterface> clients = new HashMap<>();

    public ChatServer() {
        super();
    }

    public void registerClient(String name, ChatClientInterface client) {
        System.out.println(name + " entrou no chat");
        clients.put(name, client);

        try {
            client.receiveMessage("Bem-vindo(a) ao chat, " + name + "!");
            broadcast(name + " entrou no chat");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) {
        System.out.println(message);

        for (ChatClientInterface client : clients.values()) {
            try {
                client.receiveMessage(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unregisterClient(String name) {
        System.out.println(name + " saiu do chat");
        clients.remove(name);

        broadcast(name + " saiu do chat");
    }

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            ChatServerInterface stub = (ChatServerInterface) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatServer", stub);

            System.out.println("Servidor iniciado");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void registerChatClient(ChatClientInterface chatClient) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerChatClient'");
    }

    @Override
    public void broadcastMessage(String message) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'broadcastMessage'");
    }

    @Override
    public void send(String name, String message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }

    @Override
    public String receive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'receive'");
    }
}