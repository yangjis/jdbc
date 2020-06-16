package com.javaex.author02;

import java.util.ArrayList;
import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDAO authorDao = new AuthorDAO();
		
		AuthorVO authorVo = new AuthorVO("이문열", "경북 영양");
		List<AuthorVO> authorList = new ArrayList<AuthorVO>(); 
				
		authorDao.insert(authorVo);
		
		
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
		for(AuthorVO vo: authorList) {
			System.out.println(vo.getAuthor_id() + ", " + vo.getAuthor_name() + ", " + vo.getAuthor_desc());
		}
		
	}
}
