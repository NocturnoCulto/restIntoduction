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

	// Create get endpoint /getBooks which will return all books,
	// endpoint should return application json content type
	// Tip: no parameters required
	// Tip: check LibraryProvider component and its methods
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

	// Create get endpoint /getBook which will return one book by id passed as pathVariable,
	// endpoint should return application json content type
	// Tip: check LibraryProvider component and its methods
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

	// Correct the method in the LibraryProvider class so that endpoint /getBook returns an empty list
	// instead of a null list when book id is not exist,
	// Tip: check LibraryProvider component and its methods
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

	//The next three tasks are analogous but refer to the readers, except for task 2
	// create endpint /getReaders

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

	// endpoint getReader should accept query parameters as request params
	// create endpoint /getReader

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

	// create put endpoint /addBook which in the body of the query expects the json structure described in the BookToAddDTO class
	// After adding the book, the api should return http created
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

	// create delete endpoint /removeBook which in the body of the query expects the json structure described in the BookToRemoveDTO class
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

	// create post endpoint /borrowBook which in the body of the query expects the json structure described in the BookToBorrowDTO class
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
