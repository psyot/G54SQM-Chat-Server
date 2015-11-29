package g54ubi.chat.client.ui;

import g54ubi.chat.client.core.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by linan on 2015/11/28.
 */
public class ServerChooseFrame extends JPanel {
    private JLabel hostLabel;
    private JTextField hostText;
    private JLabel portLabel;
    private JTextField portText;
    private JButton okButton;

    public ServerChooseFrame() {
//        super("Choose Server");
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // draw graph
        setBounds(400, 300, 500, 150);
        hostLabel = new JLabel("Host:");
        hostLabel.setBounds(10, 30, 50, 30);
        hostLabel.setName("hostLabel");
        add(hostLabel);
        hostText = new JTextField("127.0.0.1");
        hostText.setBounds(70, 30, 120, 30);
        hostText.setName("hostText");
        add(hostText);
        portLabel = new JLabel("Port:");
        portLabel.setBounds(220, 30, 50, 30);
        portLabel.setName("portLabel");
        add(portLabel);
        portText = new JTextField("9000");
        portText.setBounds(290, 30, 50, 30);
        portText.setName("portText");
        add(portText);
        okButton = new JButton("ok");
        okButton.setBounds(380, 30, 80, 30);
        okButton.setName("okButton");
        add(okButton);
        // bind listener
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked!!!!!!");
                ServerChooseFrame.this.hostLabel.setText("HAHA");
                //validate user's input
                String host = ServerChooseFrame.this.hostText.getText();
                if ("".equals(host.trim())) {
                    JOptionPane.showMessageDialog(ServerChooseFrame.this, "The host should not be empty!");
                    return;
                }
                String portInut = ServerChooseFrame.this.portText.getText();
                if ("".equals(portInut.trim())) {
                    JOptionPane.showMessageDialog(ServerChooseFrame.this, "The port should not be empty!");
                    return;
                }
                int port = 9000;
                try {
                    port = Integer.parseInt(portInut);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(ServerChooseFrame.this, "The port should be an integer!");
                    return;
                }
                // connecting to server
                try {
                    Socket socket = new Socket(host, port);
                    Client.init(socket);
                    ServerChooseFrame.this.getParent().setVisible(false);
                    MainFrame.showAsJFrame().setVisible(true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(ServerChooseFrame.this, "Can not connected to the server!");
                    return;
                }
            }
        });
    }

    public static JFrame showAsJFrame() {
        JFrame serverChooseFrame = new JFrame("Choosen Server");
        serverChooseFrame.add(new ServerChooseFrame());
        serverChooseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //serverChooseFrame.pack();
        serverChooseFrame.setBounds(400, 300, 500, 150);
        return serverChooseFrame;
    }
}
