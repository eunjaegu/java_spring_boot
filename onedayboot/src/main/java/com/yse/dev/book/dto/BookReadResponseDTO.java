package com.yse.dev.book.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.yse.dev.book.entity.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 멤버변수없는기본생성자
@Getter
public class BookReadResponseDTO {
	private Integer bookId;
	private String title;
	private Integer price;
	private LocalDateTime insertDateTime;
	private List<BookLogReadResponseDTO> bookLogs;

	// @NoArgsConstructor //멤버변수없는기본생성자
	public BookReadResponseDTO fromBook(Book book) {
		this.bookId = book.getBookId();
		this.title = book.getTitle();
		this.price = book.getPrice();
		this.insertDateTime = book.getInsertDateTime();
		this.bookLogs = book.getBookLogList().stream().map(bookLog-> BookLogReadResponseDTO.BookLogFactory(bookLog)).collect(Collectors.toList());
		return this;
	}

	public static BookReadResponseDTO BookFactory(Book book) {
		BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();
		bookReadResponseDTO.fromBook(book);
		return bookReadResponseDTO;
	}

}

//상세보기 역할로 entity에서 가져와서 멤버변수에 넣어준 후 상세보기로 내보내줌.
