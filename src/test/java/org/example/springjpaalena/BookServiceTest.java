//package org.example.springjpaalena;
//
//import org.example.springjpaalena.ppt_tasks.AuthorService;
//import org.example.springjpaalena.ppt_tasks.BookDao;
//import org.example.springjpaalena.ppt_tasks.BookService;
//import org.example.springjpaalena.ppt_tasks.DataNotAvailableException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import java.util.Arrays;
//
//import static org.mockito.ArgumentMatchers.anyInt;
//
//public class BookServiceTest {
////    1 задача
//
//    @Test
//    void testCreateAuthorListDescription() {
//        BookService bookService = new BookService();
//        AuthorService mockAuthorService = Mockito.mock(AuthorService.class);
//        bookService.setAuthorService(mockAuthorService);
//
//        Mockito.when(mockAuthorService.getAuthorsList(Mockito.anyList()))
//                .thenReturn(Arrays.asList("Шекспир", "Байрон"));
//        String description = bookService.createAuthorListDescription(Arrays.asList(3, 12, 7, 4));
//
//        Assertions.assertEquals("Эти 4 книги были написаны 2 авторами: Шекспир Байрон ", description);
//    }
//
////    2 задача
//    @Test
//    void testCreateBookDescriptionComplexLogic() {
//        BookService bookService = new BookService();
//        AuthorService mockAuthorService = Mockito.mock(AuthorService.class);
//        bookService.setAuthorService(mockAuthorService);
//
//        Mockito
//                .when(mockAuthorService.getAuthorDescription(anyInt()))
//                .thenAnswer(invocationOnMock -> {
//                    int authorId = invocationOnMock.getArgument(0, Integer.class);
//                    if (authorId % 2 == 0) {
//                        return "великий писатель";
//                    } else {
//                        return "великий английский писатель";
//                    }
//                });
//
//        String bookDescriptionEnglish = bookService.createBookDescription("Гамлет", 1599, 11, "Уильям Шекспир");
//        Assertions.assertEquals("Гамлет, 1599, автор великий английский писатель Уильям Шекспир", bookDescriptionEnglish);
//
//        String bookDescriptionRussian = bookService.createBookDescription("Война и мир", 1898, 6, "Л.Н.Толстой");
//        Assertions.assertEquals("Война и мир, 1898, автор великий писатель  Л.Н.Толстой", bookDescriptionRussian);
//    }
////    3 задача
//@Test
//public void testFindPublicationYearWithDaoException() {
//    BookService bookService = new BookService();
//    BookDao mockBookDao = Mockito.mock(BookDao.class);
//    bookService.setBookDao(mockBookDao);
//    Mockito.when(mockBookDao.findPublicationDate(5))
//            .thenThrow(new DataNotAvailableException("Данные не найдены"));
//
//    final DataNotAvailableException exception = Assertions.assertThrows(
//            DataNotAvailableException.class,
//            () -> bookService.findPublicationYear(5));
//
//    Assertions.assertEquals("Данные не найдены", exception.getMessage());
//}
//}