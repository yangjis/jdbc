package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAllTest {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
				// 1. JDBC 드라이버 (Oracle) 로딩
				// 2. Connection 얻어오기
				// 3. SQL문 준비 / 바인딩 / 실행
				// 4. 결과처리
				// 5. 자원정리

				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");

					// 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "1234");
					System.out.println("접속성공");

					// 3. SQL문 준비 / 바인딩 / 실행
					String query = ""; // 쿼리문 문자열만들기, ? 주의
					query += " select bo.book_id, ";
					query += "        bo.title, ";
					query += "        bo.pubs, ";
					query += "        to_char(bo.pub_date, 'YYYY-MM-DD') pub_date, ";
					query += "        au.author_id, ";
					query += "        au.author_name, ";
					query += "        au.author_desc ";
					query += " from book bo, author au ";
					query += " where bo.author_id = au.author_id ";
					System.out.println(query);

					pstmt = conn.prepareStatement(query); // 쿼리로 만들기

					rs = pstmt.executeQuery();

					// 4.결과처리
					while (rs.next()) {
						int bookId = rs.getInt("book_id");
						String bookTitle = rs.getString("title");
						String bookPubs = rs.getString("pubs");
						String bookPubDate = rs.getString("pub_date");
						int authorId = rs.getInt("author_id");
						String authorName = rs.getString("author_name");
						String authorDesc = rs.getString("author_desc");
						System.out.println(bookId + "\t" + bookTitle + "\t" + bookPubs + 
											"\t" + bookPubDate + "\t" + authorId +
											"\t" + authorName + "\t" + authorDesc);
					}

				} catch (ClassNotFoundException e) {
					System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
					System.out.println("error:" + e);
				} finally {

					// 5. 자원정리
					try {
						if (rs != null) {
							rs.close();
						}
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
