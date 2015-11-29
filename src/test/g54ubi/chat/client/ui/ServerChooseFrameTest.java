package test.g54ubi.chat.client.ui;

import abbot.finder.Matcher;
import abbot.tester.ComponentTester;
import abbot.tester.JTextComponentTester;
import g54ubi.chat.client.ui.ServerChooseFrame;
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
        Frame frame = showFrame(serverChooseFrame);
        JButton button = (JButton) getFinder().find(new Matcher() {
            public boolean matches(Component c) {
                // Add as much information as needed to distinguish the component
                return c instanceof JButton && ((JButton) c).getText().equals("ok");
            }
        });
        JTextComponentTester tester = new JTextComponentTester();
        tester.actionClick(button);
    }

    public ServerChooseFrameTest(String name) {
        super(name);
    }

    public static void main(String[] args) {
        TestHelper.runTests(args, ServerChooseFrameTest.class);
    }


}
