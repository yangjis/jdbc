package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorInsertTest {
	public static void main(String[] args) {
		//0. import java.sql.*;
		//1. JDBC 드라이버 (Oracle) 로딩
		//2. Connection 얻어오기
		//3. SQL문 준비 / 바인딩 / 실행
		// 4.결과처리
		//5. 자원정리

		
		
		
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기(ip, portNomber, id, pw)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"webdb","1234");
		    // 3. SQL문 준비 / 바인딩 / 실행
			String quary = "insert into author values(seq_author_id.nextval, ?, ?)";//쿼리문 문자열만들기. ?주의
			pstmt = conn.prepareStatement(quary);//쿼리로 만들기
			
			pstmt.setString(1,"강풀");
			pstmt.setString(2, "온라인 만화가 1세대");
			
			//rs = pstmt.executeQuery();	//select를 사용할때 사용.
			int count = pstmt.executeUpdate();	//insert, update, delete를 사용할때 사용
			
		    
		    // 4.결과처리
			System.out.println(count + "건 처리되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        /*if (rs != null) {
		            rs.close();
		        } */               
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
