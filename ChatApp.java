import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Main Class
public class ChatApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ChatRoom room = new ChatRoom("Live Chat", 100, 10);

        // Create users
        User u1 = new User("Alice");
        User u2 = new User("Bob");
        User u3 = new User("Charlie");

        room.addUser(u1);
        room.addUser(u2);
        room.addUser(u3);

        System.out.println("\n--- Start Chat (type 'exit' to stop) ---");

        while (true) {
            System.out.println("\nChoose user:");
            System.out.println("1. Alice  2. Bob  3. Charlie");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            User currentUser;

            if (choice == 1) currentUser = u1;
            else if (choice == 2) currentUser = u2;
            else if (choice == 3) currentUser = u3;
            else {
                System.out.println("Invalid choice!");
                continue;
            }

            System.out.print(currentUser.getUsername() + ": ");
            String msg = sc.nextLine();

            if (msg.equalsIgnoreCase("exit")) break;

            room.sendMessage(currentUser, msg);
        }

        // Show chat history
        room.displayChatHistory();

        sc.close();
    }
}


// User Class
class User {
    private String username;
    private boolean isOnline;

    public User(String username) {
        this.username = username;
        this.isOnline = true;
    }

    public String getUsername() {
        return username;
    }
}


// Message Class
class Message {
    private User sender;
    private String content;
    private String timestamp;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = getTime();
    }

    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(new Date());
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }
}


// ChatRoom Class
class ChatRoom {
    private String roomName;
    private Message[] messages;
    private int messageCount;

    public ChatRoom(String roomName, int maxMessages, int maxUsers) {
        this.roomName = roomName;
        this.messages = new Message[maxMessages];
        this.messageCount = 0;
    }

    public void addUser(User user) {
        System.out.println(user.getUsername() + " joined the chat.");
    }

    public void sendMessage(User user, String text) {
        if (messageCount < messages.length) {
            messages[messageCount++] = new Message(user, text);
        }
    }

    public void displayChatHistory() {
        System.out.println("\n--- Chat History ---");

        for (int i = 0; i < messageCount; i++) {
            Message m = messages[i];
            System.out.println("[" + m.getTimestamp() + "] "
                    + m.getSender().getUsername() + ": "
                    + m.getContent());
        }
    }
}