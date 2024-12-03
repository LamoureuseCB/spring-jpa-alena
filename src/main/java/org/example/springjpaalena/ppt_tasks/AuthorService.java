//package org.example.springjpaalena.ppt_tasks;
//
//import lombok.Getter;
//
//import java.awt.print.Book;
//import java.util.*;
//
//public class AuthorService {
//    public List<String> getAuthorsList(List<Integer> bookIdList) {
//        Set<String> authorsNames = new HashSet<>();
//        for (Integer bookId : bookIdList) {
//            Optional<Book> book = bookRepository.findBookById(bookId).orElseThrow;
//            authorSet.add(book.getAuthor.getName);
//        }
//
//        return new ArrayList<>(authorsNames);
//    }
//    public String getAuthorDescription(int authorId) {
//        //сложная логика по формированию описания автора из базы данных
//        return "описание";
//    }
//}
//
//class Author {
//    private int id;
//    @Getter
//    private String name;
//
//    public Author(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//}