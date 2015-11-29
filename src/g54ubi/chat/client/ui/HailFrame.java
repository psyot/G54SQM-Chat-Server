package g54ubi.chat.client.ui;

import g54ubi.chat.client.core.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by linan on 2015/11/28.
 */
public class HailFrame extends JPanel {
    private JLabel contentLabel;
    private JTextField contentText;
    private JButton hailButton;

    public HailFrame() {
        setLayout(new FlowLayout());
        // draw graph
        setBounds(400, 300, 380, 150);
        contentLabel = new JLabel("content:");
        contentLabel.setBounds(10, 30, 80, 30);
        contentLabel.setName("contentLabel");
        add(contentLabel);
        contentText = new JTextField("Input the content will be broadcasted here.");
        contentText.setBounds(100, 30, 120, 30);
        contentText.setName("contentText");
        add(contentText);
        hailButton = new JButton("HAIL");
        hailButton.setBounds(250, 30, 100, 30);
        hailButton.setName("hailButton");
        add(hailButton);
        // bind listener
        hailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // validate user's input
                String content = HailFrame.this.contentText.getText();
                if ("".equals(content.trim())) {
                    JOptionPane.showMessageDialog(HailFrame.this, "The content can not be empyt!");
                    return;
                }
                // send "HAIL" command to server
                Client.getInstance().hail(content);
                HailFrame.this.setVisible(false);
            }
        });
    }

    public static JFrame showAsJFrame() {
        JFrame hailFrame = new JFrame("Hail");
        hailFrame.add(new HailFrame());
        hailFrame.setBounds(400, 300, 380, 150);
        return hailFrame;
    }
}
