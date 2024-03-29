package ProductRegisterPage;

import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JTextPane;

public class ProductRegisterImageContentTextPane implements ProductRegisterTextpane{
	private JFrame frame;
	private static JTextPane textPane;
	
	private int x;
	private int y;
	private int width;
	private int height;
	private SystemColor color;
	private String text;
	
	public ProductRegisterImageContentTextPane(int x, int y, int width, int height, JFrame frame, SystemColor color, String text) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.frame = frame;
		this.color = color;
		this.text = text;
		
		System.out.println("Complete; ProductRegisterNameTextPane::buildTextPane");
		
		textPane = new JTextPane();
		setBoundTextPane(x, y, width, height);
		SetBackgroundTextPane(color);
		setContentTextPane(text);
		frame.getContentPane().add(textPane);
		
	}

	//textPane 의 위치와 크기를 x, y, width, height 값으로 set
	@Override
	public void setBoundTextPane(int x, int y, int width, int height) {
		
		textPane.setBounds(x, y, width, height);
		
	}
	
	//textPane의 배경색을 color 값으로 set
	@Override
	public void SetBackgroundTextPane(SystemColor color) {
	
		textPane.setBackground(color);
		
	}
	
	//textPane의 내용을 text 값으로 set

	public static void setContentTextPane(String text) {
	
		textPane.setText(text);
		
	}


}
