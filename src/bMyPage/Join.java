package bMyPage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DBConnection.JdbcUtil;
//import dbBasic.Db;
import bUser.Basic;

public class Join {
	
	static JdbcUtil dbConn = new JdbcUtil();
	static String userNo = Basic.userNo;
	
	public static String[][] getData() {
		Connection conn = null; // DB와 연결하는 인터페이스
		PreparedStatement psmt = null; // sql문 객체
		ResultSet rs = null; // sql에 대한 반환(쿼리 실행에 대한 결과값 저장)
		try {
//			conn = Db.get();
			
//		   String que="SELECT PRO_NO, AUC_END, AUC_LPRICE from T_auction where user_no=1";
		   String que="SELECT PRO_NO, AUC_END, AUC_LPRICE from T_auction "
		   		+ "where user_no= ' "+userNo+" '";
		   
		   System.out.println("Join 쿼리: "+que);
//		   conn=Db.get(); //DB연결
		   conn = dbConn.getConnection();
		   psmt=conn.prepareStatement(que);
		   rs=psmt.executeQuery();
//			CallableStatement cs = conn.prepareCall("begin T_PROC_AUCTION_JOIN(?); end;");
//			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
//			cs.execute();
//			rs = (ResultSet) cs.getObject(1);

			ArrayList<String[]> ar = new ArrayList<>();
			while (rs.next()) {
				
				ar.add(new String[] { rs.getString(1), rs.getString(2), rs.getString(3) });
			}
			System.out.println("성공");
			return ar.toArray(new String[ar.size()][3]);
//		   String[][] arr = new String[ar.size()][3];
//		   return ar.toArray(arr);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패");
			return null;
		}
//		rs.close();
//		psmt.close();
//		conn.close();
	}
}
