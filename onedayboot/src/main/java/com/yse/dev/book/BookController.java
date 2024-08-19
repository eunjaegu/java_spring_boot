package com.yse.dev.book;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yse.dev.book.dto.BookCreateDTO;
import com.yse.dev.book.dto.BookEditDTO;
import com.yse.dev.book.dto.BookEditResponseDTO;
import com.yse.dev.book.dto.BookListResponseDTO;
import com.yse.dev.book.dto.BookReadResponseDTO;
import com.yse.dev.book.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	private Integer pageSize;

	@GetMapping("/book/create")
	public String create() {
		return "book/create";
	}

	@PostMapping("/book/create")
	public String insert(BookCreateDTO bookCreateDTO) {
		Integer bookId = this.bookService.insert(bookCreateDTO);
		return String.format("redirect:/book/read/%s", bookId);
		// bookid 값을 "redifect:/book/read/%s" %s에 넣어 리턴 // %s => String
	}

	// read메서드는 서비스 read메서드한테 전달 
	@GetMapping("/book/read/{bookId}")
	public ModelAndView read(@PathVariable("bookId") Integer bookId) {
		ModelAndView mav = new ModelAndView();
		try {
			BookReadResponseDTO bookReadResponseDTO = this.bookService.read(bookId);
			mav.addObject("bookReadResponseDTO", bookReadResponseDTO);
			mav.setViewName("book/read");
		} catch (NoSuchElementException ex) {
			mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
			mav.addObject("message", "책 정보가 없습니다.");
			mav.addObject("location", "/book");
			mav.setViewName("common/error/422");
		}
		return mav;
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {
		 return this.error422("책 정보가 없습니다.", "/book/list");
	}
	
	@GetMapping("/book/edit/{bookId}")
	public ModelAndView edit(@PathVariable("bookId") Integer bookId) throws NoSuchElementException {
		ModelAndView mav = new ModelAndView();
		BookEditResponseDTO bookEditResponseDTO = this.bookService.edit(bookId);
		mav.addObject("bookEditResponseDTO", bookEditResponseDTO);
		mav.setViewName("book/edit");
		return mav;
	}
	 
	private ModelAndView error422(String message, String location) {
		ModelAndView mav = new ModelAndView();
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message", message);
		mav.addObject("location", location);
		mav.setViewName("common/error/422");
		return mav;
	}
	//유효성검사(위반사항 받는애)(title => notblank / price => 1000 이하일경우 위반)
	//required는 빈칸 못비우도록
	//수정할 ID 받아줘야함. {bookId}
	@PostMapping("/book/edit/{bookId}")
	 public ModelAndView update(
	 @Validated BookEditDTO bookEditDTO, Errors errors) {
		if (errors.hasErrors()) {
			String errorMessage = errors
			.getFieldErrors() // 2
			.stream()		  // 2 , 2개 중에 하나들 키와 값으로 꺼내서 한쌍으로
			.map(x-> x.getField() + " : " + x.getDefaultMessage())
			//map(key, value) // 맵에 키가 2개 
			.collect(Collectors.joining("\n"));	// 키가 2개니 줄바꿈 \n
			return this.error422(
			errorMessage,
			String.format("/book/edit/%s", bookEditDTO.getBookId())
			//위반사항 있을 시 수정화면으로 되돌아감.
			);
		}
			this.bookService.update(bookEditDTO);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(String.format("redirect:/book/read/%s", bookEditDTO.getBookId()));
			return mav;
			}
	
			@PostMapping("/book/delete")
			public String delete(@RequestParam("bookId") Integer bookId) throws NoSuchElementException {
				this.bookService.delete(bookId);
				return "redirect:/book/list";
			}
			
			 // 에러발생시 book으로 가기 때문에 사실 의미없음. 두개도 넣을 수 있다는 방법이 있다고 알려줄 뿐
			@GetMapping(value = { "/book/list", "/book" })
			public ModelAndView bookList(String title, Integer page, Integer pageSize, ModelAndView mav) {
				mav.setViewName("/book/list");
				List<BookListResponseDTO> books = this.bookService.bookList(title, page, pageSize);
				mav.addObject("books", books);
				return mav;
			}
	}

/**
 * @GetMapping("/book/create") 
 * public String create() { 
 * return "book/create"; 
 * }
 **/
