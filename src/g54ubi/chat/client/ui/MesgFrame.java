package g54ubi.chat.client.ui;

import g54ubi.chat.client.core.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by linan on 2015/11/28.
 */
public class MesgFrame extends JPanel {
    private JLabel userLabel;
    private JTextField userText;
    private JLabel contentLabel;
    private JTextField contentText;
    private JButton mesgButton;

    public MesgFrame() {
        setLayout(new FlowLayout());
        // draw graph
        setBounds(400, 300, 500, 150);
        userLabel = new JLabel("user:");
        userLabel.setBounds(10, 30, 50, 30);
        userLabel.setName("userLabel");
        add(userLabel);
        userText = new JTextField("receiver");
        userText.setBounds(70, 30, 70, 30);
        userText.setName("userText");
        add(userText);
        contentLabel = new JLabel("Content:");
        contentLabel.setBounds(170, 30, 50, 30);
        contentLabel.setName("contentLabel");
        add(contentLabel);
        contentText = new JTextField("Input your message to send here");
        contentText.setBounds(230, 30, 130, 30);
        contentText.setName("contentText");
        add(contentText);
        mesgButton = new JButton("MESG");
        mesgButton.setBounds(380, 30, 80, 30);
        mesgButton.setName("mesgButton");
        add(mesgButton);
        // bind listener
        mesgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //validate user's input
                String user = MesgFrame.this.userText.getText();
                if ("".equals(user.trim())) {
                    JOptionPane.showMessageDialog(MesgFrame.this, "The user should not be empty!");
                    return;
                }
                String content = MesgFrame.this.contentText.getText();
                if ("".equals(content.trim())) {
                    JOptionPane.showMessageDialog(MesgFrame.this, "The content should not be empty!");
                    return;
                }
                // send "MESG" command to server
                Client.getInstance().message(user, content);
                MesgFrame.this.setVisible(false);
            }
        });
    }

    public static JFrame showAsJFrame() {
        JFrame mesgJFrame = new JFrame("Mesg");
        mesgJFrame.add(new MesgFrame());
        mesgJFrame.setBounds(400, 300, 500, 150);
        return mesgJFrame;
    }
}
