package test.g54ubi.chat.client.ui;

import abbot.finder.Matcher;
import abbot.finder.matchers.ClassMatcher;
import abbot.tester.ComponentTester;
import abbot.tester.JTextComponentTester;
import g54ubi.chat.client.core.Client;
import g54ubi.chat.client.ui.ServerChooseFrame;
import g54ubi.chat.server.Server;
import javafx.scene.input.KeyCode;
import junit.extensions.abbot.ComponentTestFixture;
import junit.extensions.abbot.TestHelper;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import javax.swing.*;
import java.awt.*;

/**
 * ServerChooseFrame Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11/29/2015</pre>
 */
public class ServerChooseFrameTest extends ComponentTestFixture {
    private ComponentTester tester;

    public void testButtonClick() throws Throwable {
        final ServerChooseFrame serverChooseFrame = new ServerChooseFrame();
        showFrame(serverChooseFrame);
        JTextComponentTester tester = new JTextComponentTester();
        //set host to "127.0.0.1"
        JTextField hostText = (JTextField) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "hostText".equals(component.getName());
            }
        });
        tester.actionKeyPress(hostText, 35);
        for (int i = 0; i < 10; i++) {
            tester.actionKeyPress(hostText, 8);
        }
        tester.actionEnterText(hostText, "127.0.0.1");
        //set port to "9007"
        JTextField portText = (JTextField) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "portText".equals(component.getName());
            }
        });
        tester.actionKeyPress(portText, 35);
        for (int i = 0; i < 5; i++) {
            tester.actionKeyPress(portText, 8);
        }
        tester.actionEnterText(portText, "9007");
        // clict button
        JButton button = (JButton) getFinder().find(new ClassMatcher(JButton.class));
        tester.actionClick(button);
        assertNotNull(Client.getInstance());
    }

    public ServerChooseFrameTest(String name) {
        super(name);
        // start a server for test
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server(9007);
            }
        }).start();
    }

    public static void main(String[] args) {
        TestHelper.runTests(args, ServerChooseFrameTest.class);
    }


}
