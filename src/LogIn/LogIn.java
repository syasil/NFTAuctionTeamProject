package LogIn;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import swing.CButton;
import swing.CLabel;
import swing.CPanel;

public class LogIn extends JFrame {
//	JLabel lblImg;
	private JFrame frameAfter;
	private int point;

	public static LogIn instance;
	public static LogIn getInstance() {
		
		if(instance == null)instance = new LogIn();
		return instance;
		
	}
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					// window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	private LogIn() {
		initialize();
		// getUserInfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		new JFrame();

		setSize(600, 900);

		CPanel pnlAfter = new CPanel();

		CButton bntlogOut = new CButton("로그아웃");
		CLabel lblTitle = new CLabel("NFT 경매 시스템");
		CLabel lblWelcome = new CLabel("환영합니다");
		CLabel lblUserID = new CLabel(Basic.userNick + " 님");
		CLabel lblPoint = new CLabel("포인트");
		CLabel lblPoint_1 = new CLabel(Basic.userPoint + "");
		JLabel lblImg = new JLabel(Basic.userIcon);

		pnlAfter.setLayout(null);

		// ***********좌표 설정***************

		lblWelcome.setBounds(450, 10, 110, 22);
		lblUserID.setBounds(450, 32, 110, 22);
		lblImg.setBounds(345, 27, 100, 100);
		bntlogOut.setBounds(450, 107, 110, 20);
		lblTitle.setBounds(12, -10, 336, 155);
		lblPoint.setBounds(450, 54, 110, 22);
		lblPoint_1.setBounds(450, 76, 110, 22);
		// *************폰트*********************
		bntlogOut.setBackground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("맑은 고딕", Font.ITALIC, 40));

		// ***********add*******************
		getContentPane().add(pnlAfter);
		pnlAfter.add(lblTitle);
		pnlAfter.add(bntlogOut);
		pnlAfter.add(lblWelcome);
		pnlAfter.add(lblUserID);
		pnlAfter.add(lblImg);
		pnlAfter.add(lblPoint);
		pnlAfter.add(lblPoint_1);

		// ************기능********************
		bntlogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				Basic.userNo = "";
				Basic.userID = "";
				Basic.userNick = "";
				Basic.userPoint = 0;
				Basic.userIcon = null;
				Basic.userBirth = "";
				Basic.userPw = "";
				
				Basic.getInstance().setVisible(true);
				
			}
		});
		
	}

	

}
