import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class logIn {

	private JFrame frameBefore;


	/**
	 * Launch the application.11
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logIn window = new logIn();
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
	public logIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameBefore = new JFrame();
		frameBefore.setSize(600, 900);

		JPanel pnlBefore = new JPanel();
		JLabel lblID = new JLabel("ID: ");
		JLabel lblPw = new JLabel("PW:");
		JTextField txtfID = new JTextField(10);
		JPasswordField txtfPw = new JPasswordField(10);
		JButton bntLogin = new JButton("로그인");
		JButton bntSign = new JButton("회원가입");
		JButton bntMissing = new JButton("ID / PW찾기");
		JLabel lblTitle = new JLabel("NFT 경매 시스템");
		// ***********좌표 설정***************
		pnlBefore.setLayout(null);
		lblID.setBounds(360, 25, 30, 30);
		txtfID.setBounds(390, 25, 100, 30);
		lblPw.setBounds(360, 60, 30, 30);
		bntLogin.setBounds(500, 15, 80, 80);
		txtfPw.setBounds(390, 60, 100, 30);
		bntSign.setBounds(370, 100, 100, 20);
		bntMissing.setBounds(480, 100, 100, 20);
		lblTitle.setBounds(12, -10, 336, 155);

		// ************색상********************
		bntLogin.setBackground(Color.LIGHT_GRAY);
		bntSign.setBackground(Color.LIGHT_GRAY);
		bntMissing.setBackground(Color.LIGHT_GRAY);

		// *************폰트*********************
		Font font = new Font("궁서", Font.ITALIC, 40);
		lblTitle.setFont(new Font("궁서", Font.ITALIC, 40));

		
		// ***********add*******************
		frameBefore.getContentPane().add(pnlBefore);
		pnlBefore.add(lblID);
		pnlBefore.add(txtfID);
		pnlBefore.add(lblPw);
		pnlBefore.add(txtfPw);
		pnlBefore.add(bntLogin);
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
		bntLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;

				try {
					String que = "select * from test1";

					conn = connecDb.get();

					psmt = conn.prepareStatement(que);
					rs = psmt.executeQuery();

					while (rs.next()) {

						String id = rs.getString(1);
						String pw = rs.getString(2);
						String birth = rs.getString(3);
						if (txtfID.getText().equals(id)) {
							if (txtfPw.getText().equals(pw)) {
								frameBefore.setVisible(false);
								new logOut().setVisible(true);
								break;
							} else {
								JOptionPane.showMessageDialog(null, "비밀번호가 잘못되었습니다.");
								break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "등록된" + txtfID.getText() + "가 없습니다.");
							break;
						}
					}

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
