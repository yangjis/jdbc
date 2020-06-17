package com.javaex.author02;

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
		authorDao.update(authorVo3);
		System.out.println("=============update================");
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
	
		authorDao.delete(2);
		System.out.println("=============delete================");
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
		
		authorList = authorDao.select();
		System.out.println("=============select================");
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
		
	}
}
