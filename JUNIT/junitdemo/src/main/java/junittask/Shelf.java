package junittask;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
  List<String> Books ;

  public Shelf() {
    Books = new ArrayList<String>();
  }

  public void addBook(String book){
    Books.add(book);
  }

  public void removeBook(String book){
    try {
      Books.remove(book);
    } catch (Exception e) {
      //TODO: handle exception
      System.out.println("Error!! Invalid Book");
    }
  }

  public void deleteBooks(){
    Books.removeAll(Books);
  }

  public List<String> getBooks(){
    return Books;
  }
}