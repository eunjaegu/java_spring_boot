package com.yse.dev.book.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

	 @Entity
	 @Data
	 @Builder // 객체생성 시 빌더 패턴으로 생성하게끔 도와줌, 서비스 클래스 만들기에서 확인
	 @NoArgsConstructor
	 @AllArgsConstructor
	 public class Book {
	 
	 @Id //primary key
	 @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	 private Integer bookId;
	 
	 @Column(length = 200)
	 private String title;
	 private Integer price;
	 
	 @CreationTimestamp// 자동 시간 설정	// @ UpdateTimestamp : 시간 수정 원할 경우
	 private LocalDateTime insertDateTime;

	 @OneToMany(mappedBy="book", fetch=FetchType.LAZY)
	 @Builder.Default
	 private List<BookLog> bookLogList = new ArrayList();
	 
}
