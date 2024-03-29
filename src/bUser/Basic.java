package bUser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import AdminPage.*;
import DBConnection.JdbcUtil;
import bMyPage.Join;
import bMyPage.Mysell;
import swing.*;

public class Basic {
	
	static JdbcUtil dbConn = new JdbcUtil();
	
	public static String userNo = "";
	public static String userID = "";
	public static String userPw = "";
	public static String userBirth = "";
	public static String userNick = "";
	public static int userPoint = 0;
	public static ImageIcon userIcon = null;

	private JFrame frameBefore;

	/**
	 * Launch the application.11
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Basic window = new Basic();
					// window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Basic() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameBefore = new JFrame();
		frameBefore.setSize(600, 300);

		APanel pnlBefore = new APanel();
		ALabel lblID = new ALabel("ID: ");
		ALabel lblPw = new ALabel("PW:");
		ATextField txtfID = new ATextField(10);
		CPasswordField txtfPw = new CPasswordField(10);
		AButton bntlogIn = new AButton("로그인");
		AButton bntSign = new AButton("회원가입");
		AButton bntMissing = new AButton("ID / PW찾기");
		bntMissing.setText("ID/PW찾기");
		ALabel lblTitle = new ALabel("NFT 경매 시스템");

		// ***********좌표 설정***************
		pnlBefore.setLayout(null);
		lblTitle.setBounds(20, -40, 336, 155);
		
		lblID.setBounds(345, 65, 30, 30);
		txtfID.setBounds(377, 65, 100, 30);
		lblPw.setBounds(345, 100, 30, 30);
		bntlogIn.setBounds(485, 105, 90, 23);
		txtfPw.setBounds(377, 100, 100, 30);
		bntSign.setBounds(348, 140, 105, 23);
		bntMissing.setBounds(463, 140, 115, 23);

		// ************색상********************
		bntlogIn.setBackground(Color.LIGHT_GRAY);
		bntSign.setBackground(Color.LIGHT_GRAY);
		bntMissing.setBackground(Color.LIGHT_GRAY);

		// *************폰트*********************
		// Font font = new Font("맑은 고딕", Font.ITALIC, 40);
//		lblTitle.setFont(new Font("맑은 고딕", Font.ITALIC, 40));

		// ***********add*******************
		frameBefore.getContentPane().add(pnlBefore);
		pnlBefore.add(lblID);
		pnlBefore.add(txtfID);
		pnlBefore.add(lblPw);
		pnlBefore.add(txtfPw);
		pnlBefore.add(bntlogIn);
		pnlBefore.add(bntSign);
		pnlBefore.add(bntMissing);
		pnlBefore.add(lblTitle);

		frameBefore.setVisible(true);

		// ***********기능*********
		bntSign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new NewUser();
			}
		});
		// ---------------------------------------------------
		bntMissing.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MissingID();

			}
		});
		// ---------------------------------------------------
		bntlogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				
				try {

					String que = "select*from t_user natural join t_wallet where user_id='" + txtfID.getText() + "'";

//					conn = connecDb.get();
					conn = dbConn.getConnection();

					psmt = conn.prepareStatement(que);
					rs = psmt.executeQuery();
					if (rs.next()) {

						String no = rs.getString(2);
						String id = rs.getString(3);
						String pw = rs.getString(4);
						String birth = rs.getString(5);
						String nick = rs.getString(6);
						int point = rs.getInt(10);

//						Blob blob = rs.getBlob("user_icon"); // 이미지파일 불러오는중
//						InputStream inputStream = blob.getBinaryStream();
//						int bytesRead = -1;
//						byte[] buffer = new byte[4096];
//						inputStream.close();
//
//						ImageIcon icon = new ImageIcon(blob.getBytes(1, (int) blob.length()), "description"); // 이미지 불러옴
//						Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // 크기조절 후 이미지에 넣기
//						ImageIcon icon2 = new ImageIcon(image); // ImageIcon에 조절된 이지 넣기

						if (txtfPw.getText().equals(rs.getString(4))) {
							frameBefore.setVisible(false);
							userNo = no;
							userID = id;
							userNick = nick;
							userBirth = birth;
//							userIcon = icon2;
							userPoint = point;
							if (userID.equals("vft")) {
								new AdminMainPage().setVisible(true);
							} else {
								new login().setVisible(true);
							}

						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 잘못되었습니다.");
//							
						}
					} else {
						JOptionPane.showMessageDialog(null, "등록된" + txtfID.getText() + "가 없습니다.");

					}
//					

					rs.close();
					psmt.close();
					conn.close();
				} catch (Exception e1) {
					e1.getStackTrace();
				}

			}
		});

	}

	public void setVisible(boolean b) {
		frameBefore.setVisible(b);
	}
	

}