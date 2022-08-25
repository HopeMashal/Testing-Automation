package junittask;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShelfTest {
  
  static Shelf myShelf;

  @BeforeClass
  public static void beforeClassTest(){
    myShelf = new Shelf();
  }

  @Test
  public void testCaseOne(){
    myShelf.addBook("The secret");
    Assert.assertTrue(myShelf.getBooks().contains("The secret"));
  }

  @Test
  public void testCaseTwo(){
    myShelf.addBook("The secret");
    myShelf.addBook("The space between the stars");
    myShelf.removeBook("The secret");
    Assert.assertFalse(myShelf.getBooks().contains("The secret"));
  }

  @Test
  public void testCaseThree(){
    myShelf.addBook("The secret");
    myShelf.addBook("The space between the stars");
    myShelf.removeBook("The secret");
    Assert.assertFalse(myShelf.getBooks().contains("The secret"));
    Assert.assertTrue(myShelf.getBooks().contains("The space between the stars"));
  }

  @Test
  public void testCaseFour(){
    myShelf.addBook("The secret");
    myShelf.addBook("The space between the stars");
    myShelf.removeBook("The secret !!");
    Assert.assertTrue(myShelf.getBooks().contains("The secret"));
    Assert.assertTrue(myShelf.getBooks().contains("The space between the stars"));
  }

  @Test
  public void testCaseFive(){
    myShelf.addBook("The secret");
    myShelf.addBook("The space between the stars");
    myShelf.deleteBooks();
    Assert.assertFalse(myShelf.getBooks().contains("The secret"));
    Assert.assertFalse(myShelf.getBooks().contains("The space between the stars"));
  }

  @Test
  public void testCaseSix(){
    myShelf.addBook("The secret");
    myShelf.addBook("The space between the stars");
    myShelf.getBooks();
    System.out.println(myShelf.getBooks());
    Assert.assertTrue(myShelf.getBooks().contains("The secret"));
    Assert.assertTrue(myShelf.getBooks().contains("The space between the stars"));
  }
  
  @Test
  public void testCaseSeven(){
    myShelf.addBook("The secret");
    Assert.assertEquals("Error!! Invalid Value",true,myShelf.getBooks().contains("The secret"));
  }

  @Test
  public void testEmpty(){
    Assert.assertEquals(0, myShelf.getBooks().size());
  }

  @After 
  public void afterTest(){
   myShelf.deleteBooks();
   System.out.println("All Books were Deleted");
  }

}
