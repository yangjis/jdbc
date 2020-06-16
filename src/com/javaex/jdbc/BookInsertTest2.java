package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsertTest2 {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"webdb","1234");
			System.out.println("접속성공");
			
			conn.setAutoCommit(false);//이 설정을 안할 경우 쿼리를 날리면 자동 커밋이 되지만 지금 설정은 바로 커밋이 안되게 설정하였다.
			
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "insert into book values(seq_book_id.nextval, ?, ?, ?,?)";
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1, "우리들의 일그러진 영웅");
		    pstmt.setString(2, "다림");
		    pstmt.setString(3, "1998-02-2");
		    pstmt.setInt(4, 1);
		    
		    int count = pstmt.executeUpdate();
		    System.out.println(count);
		    
		    pstmt.setString(1, "삼국지");
		    pstmt.setString(2, "민음사");
		    pstmt.setString(3, "2002-03-1");
		    pstmt.setInt(4, 1000000);
		    
		    		    
		    int count2 = pstmt.executeUpdate();
		    System.out.println(count2);
		    //int count = pstmt.executeUpdate();
		    //System.out.println(count + "건이 처리되었습니다.");
		    conn.commit();

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		    
		    try {
				conn.rollback();
				System.out.println("롤백처리 되었습니다.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}

		
	}

}
