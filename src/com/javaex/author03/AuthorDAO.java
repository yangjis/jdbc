package com.javaex.author03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
	public static List<AuthorVO> authorList = new ArrayList<AuthorVO>();
	
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "1234";

	public AuthorDAO() {}

	public void insert(AuthorVO vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
			String query = "insert into author values(seq_author_id.nextval, ?, ?)";// 쿼리문 문자열만들기. ?주의
			pstmt = conn.prepareStatement(query);// 쿼리로 만들기

			pstmt.setString(1, vo.getAuthor_name());
			pstmt.setString(2, vo.getAuthor_desc()); 

			int count = pstmt.executeUpdate(); // insert, update, delete를 사용할때 사용

			// 4.결과처리
			select();
			System.out.println(count + "건 입력되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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

	public void update(AuthorVO vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
			String query = "update author set author_desc = ?, author_name = ? where author_id= ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getAuthor_desc());
			pstmt.setString(2, vo.getAuthor_name());
			pstmt.setInt(3, vo.getAuthor_id());
			int count = pstmt.executeUpdate();
			
			select();
			System.out.println(count + "건이 수정되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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

	public void delete(int author_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
			String query = "delete from author where author_id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, author_id);
			int count = pstmt.executeUpdate();
			
			select();
			System.out.println(count + "건이 삭제되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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

	public List<AuthorVO> select() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			String query = "";
			query += "select author_id,";
			query += "		 author_name,";
			query += "		 author_desc ";
			query += " from author";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery(query);
			authorList.removeAll(authorList);
			
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				authorList.add(new AuthorVO(authorId, authorName, authorDesc));

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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

		return authorList;

	}

}
