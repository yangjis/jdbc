package com.javaex.book01;

import java.util.ArrayList;
import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		List<BookVO> bookList = new ArrayList<BookVO>();
		BookDAO bookDao = new BookDAO();
		
		bookList = bookDao.select();
		System.out.println("===================select===================");
		for(BookVO vo: bookList) {
			System.out.println(vo.getBook_id() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPub_date() + ", " + vo.getAuthor_name());
		}
		
		BookVO vo1 = new BookVO("우리들의 일그러진 영웅", "다림", "1998-02-22",1);
		bookDao.insert(vo1);
		System.out.println("===================insert===================");
		for(BookVO vo: bookList) {
			System.out.println(vo.getBook_id() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPub_date() + ", " + vo.getAuthor_name());
		}
		
		BookVO vo2 = new BookVO("수정 우리들의 일그러진 영웅", "수정 다림", 1);
		bookDao.update(vo2);
		System.out.println("===================update===================");
		for(BookVO vo: bookList) {
			System.out.println(vo.getBook_id() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPub_date() + ", " + vo.getAuthor_name());
		}
		
		BookVO vo3 = new BookVO(1);
		bookDao.delete(vo3);
		System.out.println("===================delete===================");
		for(BookVO vo: bookList) {
			System.out.println(vo.getBook_id() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPub_date() + ", " + vo.getAuthor_name());
		}
		
		
	}

}
