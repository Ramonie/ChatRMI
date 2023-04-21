import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient implements ChatClientInterface {
    private String name;
    private static ChatServerInterface server;
    private Scanner scanner;

    public ChatClient(String name, ChatServerInterface server) {
        super();
        this.name = name;
        ChatClient.server = server;
    }

    public void receiveMessage(String message) {
        System.out.println(message);
    }

    public void start() {
        try {
            ChatClientInterface stub = (ChatClientInterface) UnicastRemoteObject.exportObject(this, 0);
            server.registerClient(name, stub);

            scanner = new Scanner(System.in);

            String inputLine;
            while ((inputLine = scanner.nextLine()) != null) {
                if (inputLine.startsWith("/exit")) {
                    server.unregisterClient(name);
                    scanner.close();
                    System.exit(0);
                } else {
                    server.broadcastMessage(name + ": " + inputLine);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java ChatClient <host> <porta>");
            System.exit(1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            ChatClient client = new ChatClient(host, server);
            ChatClientInterface stub = (ChatClientInterface) UnicastRemoteObject.exportObject(client, 0);

            // Obtém o registro RMI do servidor na máquina especificada pelo host
            Registry registry = LocateRegistry.getRegistry(host, port);

            // Obtém o objeto remoto do servidor com o nome "ChatServer"
            ChatServerInterface server = (ChatServerInterface) registry.lookup("ChatServer");

            // Lê o nome do cliente do teclado
            System.out.print("Digite seu nome: ");
            try (Scanner scanner = new Scanner(System.in)) {
                String name = scanner.nextLine();

                // Chama o método register do servidor para registrar o cliente com o nome
                // escolhido
                server.registerClient(name, stub);

                // Inicia uma nova thread para receber mensagens do servidor e exibi-las na tela
                new Thread(() -> {
                    try {
                        while (true) {
                            String message = server.receive();
                            System.out.println(message);
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao receber mensagem do servidor: " + e.getMessage());
                    }
                }).start();

                // Loop infinito que lê mensagens do teclado e envia para o servidor
                while (true) {
                    String message = scanner.nextLine();
                    server.send(name, message);
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
