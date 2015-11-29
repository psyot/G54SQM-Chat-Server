package g54ubi.chat.client.ui;

import g54ubi.chat.client.core.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by linan on 2015/11/28.
 */
public class MainFrame extends JFrame {
    private JTextArea contentArea;
    private JButton listButton;
    private JButton stateButton;
    private JButton idenButton;
    private JButton hailButton;
    private JButton msgButton;
    private JButton quitButton;

    public MainFrame() {
        super("Chat Room");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // draw graph
        setBounds(300, 200, 700, 550);
        contentArea = new JTextArea("hahah");
        contentArea.setEnabled(false);
        contentArea.setBounds(30, 20, 640, 350);
        contentArea.setName("contentArea");
        add(contentArea);
        listButton = new JButton("list");
        listButton.setBounds(30, 400, 150, 40);
        listButton.setName("listButton");
        add(listButton);
        stateButton = new JButton("state");
        stateButton.setBounds(250, 400, 150, 40);
        stateButton.setName("stateButton");
        add(stateButton);
        idenButton = new JButton("iden");
        idenButton.setBounds(470, 400, 150, 40);
        add(idenButton);
        hailButton = new JButton("hail");
        hailButton.setBounds(30, 450, 150, 40);
        hailButton.setName("hailButton");
        add(hailButton);
        msgButton = new JButton("mesg");
        msgButton.setBounds(250, 450, 150, 40);
        msgButton.setName("msgButton");
        add(msgButton);
        quitButton = new JButton("quit");
        quitButton.setBounds(470, 450, 150, 40);
        quitButton.setName("quitButton");
        add(quitButton);
        // bind listener
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // send "LIST" command to server
                Client.getInstance().list();
            }
        });
        stateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // send "STAT" command to server
                Client.getInstance().state();
            }
        });
        idenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show IdenFrame
                new IdenFrame().setVisible(true);
            }
        });
        hailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show HailFrame
                new HailFrame().setVisible(true);
            }
        });
        msgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show MesgFrame
                new MesgFrame().setVisible(true);
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  send "QUIT" command to server
                Client.getInstance().quit();
                System.exit(0);
            }
        });
        // received message from server
        contentArea.setText("");
        receiveMsg();
    }

    private void receiveMsg() {
        // new a thread to receive message from server
        new Thread(new Runnable() {
            @Override
            public void run() {
                Client client = Client.getInstance();
                // loop to receive message from server
                while (true) {
                    try {
                        MainFrame.this.contentArea.append(client.getReturnedMessage() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                        MainFrame.this.contentArea.append("Some error occurs...\n");
                    }
                }
            }
        }).start();
    }
}
