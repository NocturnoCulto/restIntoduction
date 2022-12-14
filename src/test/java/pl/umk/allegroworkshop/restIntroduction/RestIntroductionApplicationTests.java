package pl.umk.allegroworkshop.restIntroduction;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToBorrowDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToReturnDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.response.*;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToAddDTO;
import pl.umk.allegroworkshop.restIntroduction.api.v1.model.request.BookToRemoveDTO;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RestIntroductionApplicationTests extends BaseTest {

	@Test
	void getAllBooksEndpoint() throws Exception {
		String uri = "/getBooks";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		BooksResponse books = super.mapFromJson(content, BooksResponse.class);
		assertTrue(books.getBooks().size() > 0);
	}

	@Test
	void getOneBookEndpoint() throws Exception {
		String uri = "/getBook/125";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		BooksResponse books = super.mapFromJson(content, BooksResponse.class);
		assertEquals(1, books.getBooks().size());
	}
	@Test
	void getEmptyBooksList() throws Exception {
		String uri = "/getBook/1928";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		BooksResponse books = super.mapFromJson(content, BooksResponse.class);
		assertEquals(0, books.getBooks().size());
	}

	@Test
	void getAllReadersEndpoint() throws Exception {
		String uri = "/getReaders";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ReadersResponse readers = super.mapFromJson(content, ReadersResponse.class);
		assertTrue(readers.getReaders().size() > 0);
	}

	@Test
	void getOneReaderEndpoint() throws Exception {
		String uri = "/getReader?id=3";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ReadersResponse readers = super.mapFromJson(content, ReadersResponse.class);
		assertEquals(1, readers.getReaders().size());
	}
	@Test
	void getEmptyReaderList() throws Exception {
		String uri = "/getReader?id=8238";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ReadersResponse readers = super.mapFromJson(content, ReadersResponse.class);
		assertEquals(0, readers.getReaders().size());
	}

	@Test
	void addBook() throws Exception {
		String uri = "/addBook";
		BookToAddDTO bookToAdd = new BookToAddDTO("Lalka", "Boleslaw", "Prus");
		String inputJson = super.mapToJson(bookToAdd);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		AddedBookDTO addedBook = super.mapFromJson(content, AddedBookDTO.class);
		assertEquals(161, addedBook.getId());
		assertEquals("Boleslaw", addedBook.getAuthorFirstName());
		assertEquals("Prus", addedBook.getAuthorLastName());
	}

	@Test
	void removeBook() throws Exception {
		String uri = "/removeBook";
		BookToRemoveDTO bookToRemove = new BookToRemoveDTO(150);
		String inputJson = super.mapToJson(bookToRemove);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		RemovedBookDTO removedBook = super.mapFromJson(content, RemovedBookDTO.class);
		assertEquals(150, removedBook.getRemovedBookId());

		String uriForTest = "/getBook/150";
		MvcResult mvcResultForTest = mvc.perform(MockMvcRequestBuilders.get(uriForTest)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int statusForTest = mvcResultForTest.getResponse().getStatus();
		assertEquals(200, statusForTest);
		String contentForTest = mvcResultForTest.getResponse().getContentAsString();
		BooksResponse books = super.mapFromJson(contentForTest, BooksResponse.class);
		assertEquals(0, books.getBooks().size());
	}

	@Test
	void borrowBook() throws Exception {
		String uri = "/borrowBook";
		BookToBorrowDTO bookToBorrow = new BookToBorrowDTO(160, 3);
		String inputJson = super.mapToJson(bookToBorrow);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		BooksResponse borrowedBook = super.mapFromJson(content, BooksResponse.class);
		assertEquals(160, borrowedBook.getBooks().get(0).getId());
		assertEquals(false, borrowedBook.getBooks().get(0).getInStock());
		assertEquals(3, borrowedBook.getBooks().get(0).getReaderId());
	}

	@Test
	void returnBook() throws Exception {
		String uri = "/returnBook";
		BookToReturnDTO bookToReturn = new BookToReturnDTO(135);
		String inputJson = super.mapToJson(bookToReturn);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		BooksResponse returnedBook = super.mapFromJson(content, BooksResponse.class);
		assertEquals(135, returnedBook.getBooks().get(0).getId());
		assertEquals(true, returnedBook.getBooks().get(0).getInStock());
		assertNull(returnedBook.getBooks().get(0).getReaderId());

		String uriForReader = "/getReader?id=2";
		MvcResult mvcResultForTest = mvc.perform(MockMvcRequestBuilders.get(uriForReader)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int statusForTest = mvcResultForTest.getResponse().getStatus();
		assertEquals(200, statusForTest);
		String reader = mvcResultForTest.getResponse().getContentAsString();
		ReadersResponse readers = super.mapFromJson(reader, ReadersResponse.class);
		assertEquals(0, readers.getReaders().get(0).getBookIdList().size());
	}
}
