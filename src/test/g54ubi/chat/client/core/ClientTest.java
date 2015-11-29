package test.g54ubi.chat.client.core;

import g54ubi.chat.client.core.Client;
import g54ubi.chat.server.Server;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Client Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 28, 2015</pre>
 */
public class ClientTest {

    @Before
    public void before() throws Exception {

    }


    @After
    public void after() throws Exception {

    }

    /**
     * Method: list()
     */
    @Test
    public void testList() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server(9001);
            }
        }).start();
        Socket socket = new Socket("127.0.0.1", 9001);
        Client.init(socket);
        Client client = Client.getInstance();
        assertEquals("OK Welcome to the chat server, there are currelty 1 user(s) online", client.getReturnedMessage());
        client.list();
        assertEquals("BAD You have not logged in yet", client.getReturnedMessage());
    }

    /**
     * Method: state()
     */
    @Test
    public void testState() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server(9002);
            }
        }).start();
        Socket socket = new Socket("127.0.0.1", 9002);
        Client.init(socket);
        Client client = Client.getInstance();
        assertEquals("OK Welcome to the chat server, there are currelty 1 user(s) online", client.getReturnedMessage());
        client.state();
        assertEquals("OK There are currently 1 user(s) on the server You have not logged in yet", client.getReturnedMessage());
    }

    /**
     * Method: iden(String username)
     */
    @Test
    public void testIden() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server(9003);
            }
        }).start();
        Socket socket = new Socket("127.0.0.1", 9003);
        Client.init(socket);
        Client client = Client.getInstance();
        assertEquals("OK Welcome to the chat server, there are currelty 1 user(s) online", client.getReturnedMessage());
        client.iden("test");
        assertEquals("OK Welcome to the chat server test", client.getReturnedMessage());
    }

    /**
     * Method: hail(String content)
     */
    @Test
    public void testHail() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server(9004);
            }
        }).start();
        Socket socket = new Socket("127.0.0.1", 9004);
        Client.init(socket);
        Client client = Client.getInstance();
        assertEquals("OK Welcome to the chat server, there are currelty 1 user(s) online", client.getReturnedMessage());
        client.hail("Test");
        assertEquals("BAD You have not logged in yet", client.getReturnedMessage());
    }

    /**
     * Method: message(String user, String content)
     */
    @Test
    public void testMessage() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server(9005);
            }
        }).start();
        Socket socket = new Socket("127.0.0.1", 9005);
        Client.init(socket);
        Client client = Client.getInstance();
        assertEquals("OK Welcome to the chat server, there are currelty 1 user(s) online", client.getReturnedMessage());
        client.message("test1", "Test");
        assertEquals("BAD You have not logged in yet", client.getReturnedMessage());
    }

    /**
     * Method: quit()
     */
    @Test
    public void testQuit() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server(9006);
            }
        }).start();
        Socket socket = new Socket("127.0.0.1", 9006);
        Client.init(socket);
        Client client = Client.getInstance();
        assertEquals("OK Welcome to the chat server, there are currelty 1 user(s) online", client.getReturnedMessage());
        client.quit();
        assertEquals("OK goodbye", client.getReturnedMessage());
    }

}
