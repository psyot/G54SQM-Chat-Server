package test.g54ubi.chat.server;

import g54ubi.chat.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

/**
 * Server Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 28, 2015</pre>
 */
public class ServerTest {
    private static final int SERVER_PORT = 9000;

    @Before
    public void before() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server(SERVER_PORT);
            }
        }).start();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Do the functional test, include:
     * 1）客户端A发起连接请求。
     * 2）客户端A获取自身状态。
     * 3）客户端A获取服务器上的用户列表。
     * 4）客户端A向用户“test2”发送消息“Test”。
     * 5）客户端A进行广播。
     * 6）客户端A注册为用户名“test1”。
     * 7）客户端A获取自身状态。
     * 8）客户端A获取服务器上的用户列表。
     * 9）客户端A向用户“test2”发送消息“Test”。
     * 10）客户端A进行广播。
     * 11）客户端B发起连接请求。
     * 12）客户端B注册为用户名“test1”。
     * 13）客户端B注册为用户名“test2”。
     * 14）客户端B向用户“test1”发送消息“Test”。
     * 15）客户端B下线。
     * 16）客户端A获取服务器上的用户列表。
     */
    @Test
    public void functionalTest() throws Exception {
        // 1）客户端A发起连接请求。
        Socket clientA = new Socket("localhost", SERVER_PORT);
        BufferedReader readerA = new BufferedReader(new InputStreamReader(clientA.getInputStream()));
        String returnMessageA = readValieMessage(readerA);
        assertEquals("OK Welcome to the chat server, there are currelty 1 user(s) online", returnMessageA);
        //2）客户端A获取自身状态。
        PrintWriter writerA = new PrintWriter(clientA.getOutputStream());
        sendAndFlush(writerA, "STAT");
        returnMessageA = readValieMessage(readerA);
        assertEquals("OK There are currently 1 user(s) on the server You have not logged in yet", returnMessageA);
        // 3）客户端A获取服务器上的用户列表。
        sendAndFlush(writerA, "LIST");
        returnMessageA = readValieMessage(readerA);
        assertEquals("BAD You have not logged in yet", returnMessageA);
        // 4）客户端A向用户“test2”发送消息“Test”。
        sendAndFlush(writerA, "MESG test2 Test");
        returnMessageA = readValieMessage(readerA);
        assertEquals("BAD You have not logged in yet", returnMessageA);
        // 5）客户端A进行广播。
        sendAndFlush(writerA, "HAIL Test");
        returnMessageA = readValieMessage(readerA);
        assertEquals("BAD You have not logged in yet", returnMessageA);
        // 6）客户端A注册为用户名“test1”。
        sendAndFlush(writerA, "IDEN test1");
        returnMessageA = readValieMessage(readerA);
        assertEquals("OK Welcome to the chat server test1", returnMessageA);
        // 7）客户端A获取自身状态。
        sendAndFlush(writerA, "STAT");
        returnMessageA = readValieMessage(readerA);
        assertEquals("OK There are currently 1 user(s) on the server You are logged im and have sent 0 message(s)", returnMessageA);
        // 8）客户端A获取服务器上的用户列表。
        sendAndFlush(writerA, "LIST");
        returnMessageA = readValieMessage(readerA);
        assertEquals("OK test1, ", returnMessageA);
        // 9）客户端A向用户“test2”发送消息“Test”。
        sendAndFlush(writerA, "MESG test2 Test");
        returnMessageA = readValieMessage(readerA);
        assertEquals("BAD the user does not exist", returnMessageA);
        // 10）客户端A进行广播。
        sendAndFlush(writerA, "HAIL Test");
        returnMessageA = readValieMessage(readerA);
        assertEquals("Broadcast from test1: Test", returnMessageA);
        // 11）客户端B发起连接请求。
        Socket clientB = new Socket("localhost", SERVER_PORT);
        BufferedReader readerB = new BufferedReader(new InputStreamReader(clientB.getInputStream()));
        String returnMessageB = readValieMessage(readerB);
        assertEquals("OK Welcome to the chat server, there are currelty 2 user(s) online", returnMessageB);
        // 12）客户端B注册为用户名“test1”。
        PrintWriter writerB = new PrintWriter(clientB.getOutputStream());
        sendAndFlush(writerB, "IDEN test1");
        returnMessageB = readValieMessage(readerB);
        assertEquals("BAD username is already taken", returnMessageB);
        // 13）客户端B注册为用户名“test2”。
        sendAndFlush(writerB, "IDEN test2");
        returnMessageB = readValieMessage(readerB);
        assertEquals("OK Welcome to the chat server test2", returnMessageB);
        // 14）客户端B向用户“test1”发送消息“Test”。
        sendAndFlush(writerB, "MESG test1 Test");
        returnMessageB = readValieMessage(readerB);
        assertEquals("OK your message has been sent", returnMessageB);
        returnMessageA = readValieMessage(readerA);
        assertEquals("PM from test2:Test", returnMessageA);
        // 15）客户端B下线。
        sendAndFlush(writerB, "QUIT");
        returnMessageB = readValieMessage(readerB);
        assertEquals("OK thank you for sending 0 message(s) with the chat service, goodbye. ", returnMessageB);
        // 16）客户端A获取服务器上的用户列表。
        Thread.sleep(1000);// waiting for client being  removed from server
        sendAndFlush(writerA, "LIST");
        returnMessageA = readValieMessage(readerA);
        assertEquals("OK test1, ", returnMessageA);
    }

    private void sendAndFlush(PrintWriter writer, String content) {
        writer.println(content);
        writer.flush();
    }

    private String readValieMessage(BufferedReader reader) throws IOException {
        while (true) {
            String rawMessage = reader.readLine();
            if (!"".equals(rawMessage.trim()))
                return rawMessage;
        }
    }
} 
