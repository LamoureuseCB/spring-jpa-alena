//package org.example.springjpaalena.ppt_tasks;
//
//import lombok.Setter;
//
//import java.util.List;
//
//public class BookService {
//    @Setter
//    private AuthorService authorService;
//    @Setter
//    private BookDao bookDao;
//    private DateService dateService = new DateService();
//    private int leastExpectedYear = 1700;
//
//
//    public String createAuthorListDescription(List<Integer> bookIdList) {
//        List<String> authorList = authorService.getAuthorsList(bookIdList);
//
//        StringBuilder result = new StringBuilder("Эти ")
//                .append(bookIdList.size())
//                .append(" книги были написаны ")
//                .append(authorList.size())
//                .append(" авторами: ");
//
//        for (String author: authorList) {
//            result.append(author).append(" ");
//        }
//
//        return result.toString();
//    }
//    public String createBookDescription(String bookName, int creationYear, int authorId, String authorName) {
//        StringBuffer description = new StringBuffer();
//        description.append(bookName).append(", ");
//        description.append(creationYear);
//
//        String authorDescription = authorService.getAuthorDescription(authorId);
//        if (authorDescription != null) {
//            description.append(" автор ");
//            description.append(authorName).append(", ");
//            description.append(authorDescription);
//        }
//
//        return description.toString();
//    }
//
////    3 задача
//public int findPublicationYear(int bookId) {
//    String creationDate = bookDao.findPublicationDate(bookId);
//    int publicationYear = dateService.getYear(creationDate);
//    if (publicationYear < leastExpectedYear) {
//        throw new IllegalArgumentException("Год слишком маленький: где-то произошла ошибка");
//    }
//    return publicationYear;
//}
//}