package com.javaex.author03;

import java.util.ArrayList;
import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		List<AuthorVO> authorList = new ArrayList<AuthorVO>();
		AuthorDAO authorDao = new AuthorDAO();
		
		AuthorVO authorVo = new AuthorVO("이문열", "경북 영양");
		authorDao.insert(authorVo);
		
		AuthorVO vo2 = new AuthorVO(2, "박경리", "경상남도 통영");
		authorDao.insert(vo2);
		
		AuthorVO authorVo3 = new AuthorVO(2, "수정 박경리", "수정 경상남도 통영");
		System.out.println("=============update================");
		authorDao.update(authorVo3);
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
	
		System.out.println("=============delete================");
		authorDao.delete(2);
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
		
		System.out.println("=============select================");
		authorList = authorDao.select();
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
		
	}
}
