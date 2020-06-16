package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsertTest {

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
			
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "insert into book values(seq_book_id.nextval, ?, ?, ?,?)";
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1, "토지");
		    pstmt.setString(2, "마로니에북스");
		    pstmt.setString(3, "2012-08-15");
		    pstmt.setInt(4, 2);
		    
		    
		    
		    
		    // 4.결과처리
		    int count = pstmt.executeUpdate();
		    System.out.println(count + "건이 처리되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
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
