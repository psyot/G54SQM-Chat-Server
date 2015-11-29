package g54ubi.chat.client.ui;

import g54ubi.chat.client.core.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by linan on 2015/11/28.
 */
public class IdenFrame extends JFrame {
    private JLabel usernameLabel;
    private JTextField usernameText;
    private JButton idenButton;

    public IdenFrame() {
        super("Iden");
        setLayout(null);
        // draw graph
        setBounds(400, 300, 380, 150);
        usernameLabel = new JLabel("username:");
        usernameLabel.setBounds(10, 30, 80, 30);
        usernameLabel.setName("usernameLabel");
        add(usernameLabel);
        usernameText = new JTextField();
        usernameText.setBounds(100, 30, 120, 30);
        usernameText.setName("usernameText");
        add(usernameText);
        idenButton = new JButton("IDEN");
        idenButton.setBounds(250, 30, 100, 30);
        idenButton.setName("idenButton");
        add(idenButton);
        // bind listener
        idenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // validate user's input
                String username = IdenFrame.this.usernameText.getText();
                if ("".equals(username.trim())) {
                    JOptionPane.showMessageDialog(IdenFrame.this, "The username can not be empyt!");
                    return;
                }
                // send  "IDEN" command
                Client.getInstance().iden(username);
                IdenFrame.this.setVisible(false);
            }
        });
    }
}
