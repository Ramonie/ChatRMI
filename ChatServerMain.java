import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServerMain {
    public static void main(String[] args) throws Exception {
        // Cria um registro na porta 1099 para o servidor de chat
        Registry registry = LocateRegistry.createRegistry(1099);

        // Instancia um objeto ChatServer
        ChatServer server = new ChatServer();

        // Registra o objeto ChatServer no registro com o nome "Chat"
        registry.rebind("Chat", server);

        // Imprime uma mensagem informando que o servidor está pronto para aceitar
        // conexões
        System.out.println("Servidor pronto para aceitar conexões...");
    }
}
