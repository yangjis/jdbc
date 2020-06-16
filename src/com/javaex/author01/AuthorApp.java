package com.javaex.author01;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDAO authorDao = new AuthorDAO();
		List<AuthorVO> authorList = authorDao.select();
		
		authorDao.insert("유시민", "17대 국회의원");
		
		authorDao.select();
		System.out.println("=============select================");
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
		
		authorDao.insert("박경리", "경상남도 통영");
		System.out.println("=============insert================");
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
		
		authorDao.update(2, "수정 박경리", "수정 경상남도 통영");
		System.out.println("=============update================");
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
	
		authorDao.delete(2);
		System.out.println("=============delete================");
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
		
	}
}
