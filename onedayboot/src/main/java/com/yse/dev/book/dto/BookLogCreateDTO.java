package com.yse.dev.book.dto;

import org.springframework.lang.NonNull;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookLogCreateDTO {
	@NonNull
	 @Positive
	 private Integer bookId;
	 @NonNull
	 private String comment;
	 private Integer page;

}
