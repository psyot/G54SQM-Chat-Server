package g54ubi.chat.client.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by linan on 2015/11/28.
 */
public class Client {
    private Socket socket;
    private static Client client;
    private BufferedReader reader;
    private PrintWriter writer;

    /*
    * private construct
    * */
    private Client(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
    }

    /*
    *  init the single client
    * */
    public static void init(Socket socket) throws IOException {
        Client.client = new Client(socket);
    }

    /*
    * get the single client
    * */
    public static Client getInstance() {
        return Client.client;
    }

    /**
     * Send "LIST" command to server
     */
    public void list() {
        sendAndFlush("LIST");
    }

    /**
     * Send "STAT" command to server
     */
    public void state() {
        sendAndFlush("STAT");
    }

    /**
     * Send "IDEN" command to server
     *
     * @param username
     */
    public void iden(String username) {
        sendAndFlush("IDEN " + username);
    }

    /**
     * Send "HAIL" command to server
     *
     * @param content
     */
    public void hail(String content) {
        sendAndFlush("HAIL " + content);
    }

    /**
     * Send "MESG" command to server
     *
     * @param user
     * @param content
     */
    public void message(String user, String content) {
        sendAndFlush("MESG " + user + " " + content);
    }

    /**
     * Send "QUIT" command to server
     */
    public void quit() {
        sendAndFlush("QUIT");
    }


    /**
     * Write  message to the socket and flush
     *
     * @param content the content sent to server
     */
    private void sendAndFlush(String content) {
        writer.println(content);
        writer.flush();
    }

    /**
     * Read message from socket,  ignore the blank message
     *
     * @return
     * @throws IOException
     */
    public String getReturnedMessage() throws IOException {
        while (true) {
            String rawMessage = reader.readLine();
            if (rawMessage == null) {
                throw new IOException("Disconnected");
            }
            if (!"".equals(rawMessage.trim()))
                return rawMessage;
        }
    }
}
