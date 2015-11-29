package test.g54ubi.chat.client.ui;

import abbot.finder.Matcher;
import abbot.finder.matchers.ClassMatcher;
import abbot.tester.JTextComponentTester;
import g54ubi.chat.client.core.Client;
import g54ubi.chat.client.ui.*;
import g54ubi.chat.server.Server;
import junit.extensions.abbot.ComponentTestFixture;
import junit.extensions.abbot.TestHelper;

import javax.swing.*;
import java.awt.*;

/**
 * ServerChooseFrame Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11/29/2015</pre>
 */
public class ServerUITest extends ComponentTestFixture {

    public void testServerChoose() throws Throwable {
        // start a server for test
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Server(9007);
            }
        }).start();
        //打开ServerChooseFrame界面，输入“127.0.0.1”和“9007”，点击按钮，Client.getInstance()返回值不为null。
        ServerChooseFrame serverChooseFrame = new ServerChooseFrame();
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

    public void testMainFrame() throws Throwable {
//2）	打开MainFrame界面，进行如下测试：
        MainFrame mainFrame = new MainFrame();
        showFrame(mainFrame);
        //获取name为“listButton”的按钮，其上文字为“list”；
        JButton listButton = (JButton) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "listButton".equals(component.getName());
            }
        });
        assertEquals("list", listButton.getText());
        //获取name为“stateButton”的按钮，其上文字为“state”；
        JButton stateButton = (JButton) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "stateButton".equals(component.getName());
            }
        });
        assertEquals("state", stateButton.getText());
        //获取name为“idenButton”的按钮，其上文字为“iden”；
        JButton idenButton = (JButton) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "idenButton".equals(component.getName());
            }
        });
        assertEquals("iden", idenButton.getText());
        //获取name为“hailButton”的按钮，其上文字为“hail”；
        JButton hailButton = (JButton) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "hailButton".equals(component.getName());
            }
        });
        assertEquals("hail", hailButton.getText());
        //获取name为“msgButton”的按钮，其上文字为“mesg”；
        JButton msgButton = (JButton) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "msgButton".equals(component.getName());
            }
        });
        assertEquals("mesg", msgButton.getText());
        //获取name为“quitButton”的按钮，其上文字为“quit”。
        JButton quitButton = (JButton) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "quitButton".equals(component.getName());
            }
        });
        assertEquals("quit", quitButton.getText());
    }

    public void testHailFrame() throws Throwable {
        //3）	打开HailFrrame界面，进行如下测试：
        showFrame(new HailFrame());
        // 获取name为“contentLabel”的Label，其上文字为“content:”；
        JLabel contentLabel = (JLabel) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "contentLabel".equals(component.getName());
            }
        });
        assertEquals("content:", contentLabel.getText());
        //获取name为“contentText”的输入框，其上文字为“Input the content will be broadcasted here.”；
        JTextField contentText = (JTextField) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "contentText".equals(component.getName());
            }
        });
        assertEquals("Input the content will be broadcasted here.", contentText.getText());
        //获取name为“hailButton”的按钮，其上文字为“HAIL”。
        JButton hailButton1 = (JButton) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "hailButton".equals(component.getName());
            }
        });
        assertEquals("HAIL", hailButton1.getText());
    }

    public void testIdenFrame() throws Throwable {
        //4）	打开IdenFrame界面，进行如下测试：
        showFrame(new IdenFrame());
        // 获取name为“usernameLabel”的Label，其上文字为“username:”；
        JLabel usernameLabel = (JLabel) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "usernameLabel".equals(component.getName());
            }
        });
        assertEquals("username:", usernameLabel.getText());
        // 获取name为“usernameText”的输入框，其上文字为“Your username”；
        JTextField usernameText = (JTextField) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "usernameText".equals(component.getName());
            }
        });
        assertEquals("Your username", usernameText.getText());
        // 获取name为“idenButton”的按钮，其上文字为“IDEN”。
        JButton idenButton1 = (JButton) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "idenButton".equals(component.getName());
            }
        });
        assertEquals("IDEN", idenButton1.getText());
    }

    public void testMesgFrame() throws Throwable {
//5）	打开MesgFram界面，进行如下测试：
        showFrame(new MesgFrame());
        // 获取name为“userLabel”的Label，其上文字为“user:”；
        JLabel userLabel = (JLabel) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "userLabel".equals(component.getName());
            }
        });
        assertEquals("user:", userLabel.getText());
        // 获取name为“userText”的输入框，其上文字为“receiver”；
        JTextField userText = (JTextField) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "userText".equals(component.getName());
            }
        });
        assertEquals("receiver", userText.getText());
        // 获取name为“contentLabel”的Label，其上文字为“Content:”；
        JLabel contentLabel1 = (JLabel) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "contentLabel".equals(component.getName());
            }
        });
        assertEquals("Content:", contentLabel1.getText());
        // 获取name为“contentText”的输入框，其上文字为“Input your message to send here”；
        JTextField contentText1 = (JTextField) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "contentText".equals(component.getName());
            }
        });
        assertEquals("Input your message to send here", contentText1.getText());
        // 获取name为“mesgButton”的按钮，其上文字为“MESG”。
        JButton mesgButton = (JButton) getFinder().find(new Matcher() {
            @Override
            public boolean matches(Component component) {
                return "mesgButton".equals(component.getName());
            }
        });
        assertEquals("MESG", mesgButton.getText());
    }

    public ServerUITest(String name) {
        super(name);
    }

    public static void main(String[] args) {
        TestHelper.runTests(args, ServerUITest.class);
    }


}
