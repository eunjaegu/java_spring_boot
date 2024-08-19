package com.yse.dev.book.dto;

import com.yse.dev.book.entity.Book;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

//getter setter nonnull 롬복 그외(pisitive, notblank, min)는 자바
@Getter
@Setter
public class BookEditDTO {
	@NonNull
	@Positive
	private Integer bookId;
	
	@NonNull
	@NotBlank
	private String title;
	
	@NonNull
	@Min(1000)
	private Integer price;

	public Book fill(Book book) {
		book.setTitle(this.title);
		book.setPrice(this.price);
		return book;
		//entity에 전달받은 값을 채운다.
		// 수정기능에서는 빌더 패턴이 아닌 fill로 사용함.
		// jpa에 의해 이미 값이 채워진 엔티이를 다루기 때문.
	}
}
