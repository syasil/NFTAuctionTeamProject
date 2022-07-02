package Auction;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Socket.Client;


public class AuctionStartFrame extends JFrame implements ActionListener, Runnable{

	JTextField minuteTextField;
	JTextField secondTextField;
	JTextField priceTextField;
	JTextPane minuteTextPane;
	JTextPane secondTextPane;
	JTextPane priceTextPane;
	JButton startButton;
	
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	String str;
	String data;
	
	
	public AuctionStartFrame(String data) {	
		
		this.data = data;
		
		init();
		start();
		initServer();
	
	}
	
	private void initServer() {
		try {
			
			socket = Client.getSocket();
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
		} catch (UnknownHostException e) {
			
			System.out.println("IP 주소가 다릅니다.");
			
		} catch (IOException e) {
			
			System.out.println("접속 실패");
			
		}
		
		
		
	}

	@Override
	public void run() {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		str = "auctionStart" + "," + data + "," + ((Integer.parseInt(minuteTextField.getText())*60)+(Integer.parseInt(secondTextField.getText()))) + 
				"," + priceTextField.getText();
		out.println(str);
		
		dispose();
	}
	
	
	private void start() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startButton.addActionListener(this);
			
	}

	private void init() {
		
		
		
		setSize(300, 300);
		setLocation(400,400);
		getContentPane().setLayout(null);
		setVisible(true);
		
		
		minuteTextField = new JTextField();
		minuteTextField.setBounds(15, 15, 60, 20);
		getContentPane().add(minuteTextField);
	
		secondTextField = new JTextField();
		secondTextField.setBounds(100, 15, 60, 20);
		getContentPane().add(secondTextField);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(80, 45, 60, 20);
		getContentPane().add(priceTextField);
		
		
		
		
		
		minuteTextPane = new JTextPane();
		minuteTextPane.setBounds(80, 15, 20, 20);		
		minuteTextPane.setText("분");
		minuteTextPane.setBackground(SystemColor.menu);
		getContentPane().add(minuteTextPane);
		
		
		secondTextPane = new JTextPane();
		secondTextPane.setBounds(165, 15, 20, 20);
		secondTextPane.setText("초");
		secondTextPane.setBackground(SystemColor.menu);
		getContentPane().add(secondTextPane);
		
		priceTextPane = new JTextPane();
		priceTextPane.setBounds(15, 45, 50, 20);
		priceTextPane.setText("가격");
		priceTextPane.setBackground(SystemColor.menu);
		getContentPane().add(priceTextPane);
		
		
		startButton = new JButton();
		startButton.setBounds(150, 150, 40, 20);
		startButton.setText("완료");
		startButton.setBackground(Color.lightGray);
		getContentPane().add(startButton);
		
	}

	
	
	

}
