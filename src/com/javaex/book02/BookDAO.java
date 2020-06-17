package com.javaex.book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

	private static List<BookVO> bookList = new ArrayList<BookVO>();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "1234";
	
	private void getConnect() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void close() {
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

	public List<BookVO> select() {
		getConnect();

		try {
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
			query += " where bo.author_id(+) = au.author_id ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("title");
				String bookPubs = rs.getString("pubs");
				String bookPubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");
				bookList.add(new BookVO(bookId, bookTitle, bookPubs, bookPubDate, authorName));
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return bookList;
	}

	public void update(BookVO vo) {
		getConnect();

		try {
			String query = "update book set title = ? ,pubs = ? where book_id = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setInt(3, vo.getBook_id());
			// 4.결과처리
			int count = pstmt.executeUpdate();
			bookList.removeAll(bookList);
			bookList = select();
			System.out.println(count + "건이 처리되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
	}

	public void delete(BookVO vo) {
		getConnect();
		
		try {
		    String query = "delete from book where book_id = ?";
		    pstmt = conn.prepareStatement(query);
		    pstmt.setInt(1, vo.getBook_id());

		    int count = pstmt.executeUpdate();
		    bookList.removeAll(bookList);
		    bookList = select();
		    System.out.println(count + "건이 처리 되었습니다.");

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		close();
	}

	public void insert(BookVO vo) {
		getConnect();

		try {
			String query = "insert into book values(seq_book_id.nextval, ?, ?, ?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPub_date());
			pstmt.setInt(4, vo.getAuthor_id());

			// 4.결과처리
			int count = pstmt.executeUpdate();
			bookList.removeAll(bookList);
			bookList = select();

			System.out.println(count + "건이 처리되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
	}
}
