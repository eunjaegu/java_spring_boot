package com.yse.dev.book.dto;

import lombok.Getter;

@Getter
public class BookListResponseDTO {
	private Integer bookId;
	private String title;

	//응답해서 내보내는 Response //외부에서 오는 건 request
	//생성자를 통한 주입방식
	public BookListResponseDTO(Integer bookId, String title) {
		this.bookId = bookId;
		this.title = title;
	}

}
