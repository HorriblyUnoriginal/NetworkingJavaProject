package network;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.*;

public class client extends JFrame implements ActionListener {

	private JTextArea ta;
	private JRadioButton udpBtn;
	private JRadioButton tcpBtn;
	private ButtonGroup radioGrp; // groups udpBtn and tcpBtn
	private JTextField ipTF;
	private JLabel ipLabel;
	private JTextField inputTF;
	private JButton sendBtn;
	private JPanel radioPanel; // panel for radioGrp
	private JPanel ipPanel; // panel for ip label and text field
	private JPanel midPanel;
	private JPanel botPanel; // panel for inputTF and sendBtn

	private Socket s = null;
	private BufferedReader br;
	private PrintWriter pw;
	private String message = "";

	public client() {
		super("Client");
		setLayout(new BorderLayout(5, 5)); // frame layout, 5 pixel gap between
											// borders

		// text area
		ta = new JTextArea(30, 30); // set textarea size to 100 columns and 50
									// rows
		ta.setEditable(false); // not editable by user
		add(new JScrollPane(ta), BorderLayout.NORTH); // adds scrolling textarea
														// to north border

		// radio buttons
		udpBtn = new JRadioButton("UDP");
		tcpBtn = new JRadioButton("TCP/IP");
		radioGrp = new ButtonGroup();
		radioGrp.add(udpBtn);
		radioGrp.add(tcpBtn);

		// radio panel
		radioPanel = new JPanel();
		radioPanel.add(udpBtn);
		radioPanel.add(tcpBtn);

		// IP label
		ipLabel = new JLabel("IP: ");
		ipTF = new JTextField(30);

		// ip panel
		ipPanel = new JPanel();
		ipPanel.add(ipLabel);
		ipPanel.add(ipTF);

		midPanel = new JPanel(new BorderLayout());
		midPanel.add(radioPanel, BorderLayout.WEST);
		midPanel.add(ipPanel, BorderLayout.EAST);
		add(midPanel, BorderLayout.CENTER);

		// input text field
		inputTF = new JTextField(60);

		// send button
		sendBtn = new JButton("Send");

		// bottom panel
		botPanel = new JPanel();
		botPanel.add(inputTF);
		botPanel.add(sendBtn);
		add(botPanel, BorderLayout.SOUTH); // add botpanel to south border

		// action listeners
		tcpBtn.addItemListener(new RadioHandler());
		udpBtn.addItemListener(new RadioHandler());
		sendBtn.addActionListener(this);
	}

	private class RadioHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if (tcpBtn.isSelected()) {
				tcpConnect();
			} else if (udpBtn.isSelected()) {
				udpConnect();
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == sendBtn) {
			// ta.append(inputTF.getText() + "\n");
			message = inputTF.getText();
			sendMessage(message);
			System.out.println("?");
			inputTF.setText("");
			inputTF.requestFocus();
		}
	}

	private void tcpConnect() {
		try {
			String msgFromServer = "";
			s = new Socket("129.21.75.6", 15555);
			// s = new Socket("127.0.0.1", 15555);
			ta.append("Successfully connected to server with TCP/IP.\n");

			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

			while (true) {
				try {
					msgFromServer = br.readLine();
					msgFromServer = "huejuejueju";
					while(msgFromServer != null)
					ta.append(msgFromServer + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendMessage(String msg) {
		String msgToServer = msg;
		while(msgToServer != null){
		pw.println(msgToServer);
		pw.flush();
		}
	}

	private void udpConnect() {

	}

	public static void main(String[] args) {
		client myClient = new client();
		myClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myClient.setResizable(false);
		myClient.pack();
		myClient.setVisible(true);
	}
}
