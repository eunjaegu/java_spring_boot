package com.yse.dev.book.dto;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateDTO {
	@NonNull
	private String title;

	@NonNull
	private Integer price;

}
