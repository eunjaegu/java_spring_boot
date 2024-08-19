package com.yse.dev.book.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.yse.dev.book.dto.BookCreateDTO;
import com.yse.dev.book.dto.BookEditDTO;
import com.yse.dev.book.dto.BookEditResponseDTO;
import com.yse.dev.book.dto.BookListResponseDTO;
import com.yse.dev.book.dto.BookReadResponseDTO;
import com.yse.dev.book.entity.Book;
import com.yse.dev.book.entity.BookRepository;

@Service
public class BookService {
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) { 
		this.bookRepository = bookRepository; 
		} 
		
	public Integer insert(BookCreateDTO bookCreateDTO) { //insert=> dao
		Book book = Book.builder() 
		.title(bookCreateDTO.getTitle()) 
		.price(bookCreateDTO.getPrice()) 
		.build();
		this.bookRepository.save(book); //save=> jpaRepository //bookRepository는 jpa한테 상속받음
		return book.getBookId(); // pk를 반환해서 보기 화면(html)으로 이동할 수 있음. 
		}

	public BookReadResponseDTO read(Integer bookId) throws NoSuchElementException {
		 Book book = this.bookRepository.findById(bookId).orElseThrow();
			/*
			 * Book book = new Book(); 
			 * book =this.bookRepository.findById(bookId).orElseThrow();
			 */
		 BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();
		 bookReadResponseDTO.fromBook(book); 
		 //fromBook 메소드는 Book 엔티티를 매개변수로 받아서 내부의 값을 채우는 역할
		 return bookReadResponseDTO;
		}	//NoSuchElementException 와 orElseThrow 다른 예외처리임.
	
	 public BookEditResponseDTO edit(Integer bookId) throws NoSuchElementException {
		 Book book = this.bookRepository.findById(bookId).orElseThrow();
		return BookEditResponseDTO.BookFactory(book);
	 	}
	 
	public void update(BookEditDTO bookEditDTO) throws NoSuchElementException {
		Book book = this.bookRepository.findById(bookEditDTO.getBookId()).orElseThrow();
		//findByID로 ID조회 ID없으면 NoSuchElementException 예외발생
		//Book book 수정전 값을 contorller 에 있는 아래 Fill로 전달
		book = bookEditDTO.fill(book);
		this.bookRepository.save(book);
		}
		
	 public void delete(@RequestParam("bookId") Integer bookId) throws NoSuchElementException {
		 Book book = this.bookRepository.findById(bookId).orElseThrow();
		this.bookRepository.delete(book);
		 }

	 //integer 클래스타입임으로 page==null 가능함.
	 public List<BookListResponseDTO> bookList(String title, Integer page, Integer pageSize) {
//		final int pageSize = 5;
		
		 if(pageSize==null) {
			 pageSize=5;
		 }
		 
		 List<Book> books;
		
		if (page == null) {
			page = 0;
		} else {
			page -= 1;
		}
	
		//전체조회가 안넘어옴. 페이지번호 페이지사이즈 정렬방식 필드
		if (title == null) { 
			Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "insertDateTime");
			books = this.bookRepository.findAll(pageable).toList();
		} else {
			Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Order.desc("insertDateTime")));
//			Sort sort = Sort.by(Order.desc("insertDateTime")); //insertDateTime 를 기준으로 역순정렬(sort)
//			pageable.getSort().and(sort);
			books = this.bookRepository.findByTitleContains(title, pageable);
		}
			//entity를 dto로 내보내기 위해 stream , 생성자 후 전달 
			return books.stream().map(book -> new BookListResponseDTO(book.getBookId(), book.getTitle()))
				.collect(Collectors.toList());
			// mapping을 list로 내보내기 위해 tolist() 붙임.
		}
}


