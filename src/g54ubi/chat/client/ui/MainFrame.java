package g54ubi.chat.client.ui;

import g54ubi.chat.client.core.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by linan on 2015/11/28.
 */
public class MainFrame extends JPanel {
    private JTextArea contentArea;
    private JButton listButton;
    private JButton stateButton;
    private JButton idenButton;
    private JButton hailButton;
    private JButton msgButton;
    private JButton quitButton;

    public MainFrame() {
        setLayout(new BorderLayout());
        // draw graph
        setBounds(300, 200, 700, 550);
        contentArea = new JTextArea("");
        contentArea.setEnabled(false);
        contentArea.setBounds(30, 20, 640, 350);
        contentArea.setName("contentArea");
        add(contentArea, BorderLayout.CENTER);
        JPanel sourthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        add(sourthPanel, BorderLayout.SOUTH);
        listButton = new JButton("list");
        listButton.setBounds(30, 400, 150, 40);
        listButton.setName("listButton");
        sourthPanel.add(listButton, BorderLayout.SOUTH);
        stateButton = new JButton("state");
        stateButton.setBounds(250, 400, 150, 40);
        stateButton.setName("stateButton");
        sourthPanel.add(stateButton, BorderLayout.SOUTH);
        idenButton = new JButton("iden");
        idenButton.setBounds(470, 400, 150, 40);
        sourthPanel.add(idenButton, BorderLayout.SOUTH);
        hailButton = new JButton("hail");
        hailButton.setBounds(30, 450, 150, 40);
        hailButton.setName("hailButton");
        sourthPanel.add(hailButton, BorderLayout.SOUTH);
        msgButton = new JButton("mesg");
        msgButton.setBounds(250, 450, 150, 40);
        msgButton.setName("msgButton");
        sourthPanel.add(msgButton, BorderLayout.SOUTH);
        quitButton = new JButton("quit");
        quitButton.setBounds(470, 450, 150, 40);
        quitButton.setName("quitButton");
        sourthPanel.add(quitButton, BorderLayout.SOUTH);
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
                IdenFrame.showAsJFrame().setVisible(true);
            }
        });
        hailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show HailFrame
                HailFrame.showAsJFrame().setVisible(true);
            }
        });
        msgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show MesgFrame
                MesgFrame.showAsJFrame().setVisible(true);
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  send "QUIT" command to server
                Client.getInstance().quit();
                MainFrame.this.getParent().setVisible(false);
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
                        // append space to the message  so that the length growing to 120
                        StringBuffer sb = new StringBuffer(client.getReturnedMessage());
                        MainFrame.this.contentArea.append(sb.toString() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                        MainFrame.this.contentArea.append("Some error occurs...\n");
                    }
                }
            }
        }).start();
    }

    public static JFrame showAsJFrame() {
        JFrame mainJFrame = new JFrame("Chat");
        mainJFrame.add(new MainFrame());
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJFrame.setBounds(300, 200, 700, 550);
        return mainJFrame;
    }
}
