package lab03;

// What imports do you need to include? Put them here.
import java.util.GregorianCalendar;
public class LibraryBook extends Book { 

	// A LibraryBook contains a holder (a String) and due date represented by
	// a GregorianCalendar
	String bookHolder;
	GregorianCalendar mom;
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);	
		bookHolder = null;
	}

	public String getHolder() {
		return bookHolder; // placeholder
	}
	
	public GregorianCalendar getDueDate() {
		return mom; // placeholder
	}
	
	public void checkin() {
		bookHolder = null;
		mom = null;
	}
	
	public void checkout(String holder, GregorianCalendar dueDate){
		this.bookHolder = holder;
		this.mom = dueDate;
	}	

	// Do not override the equals method in Book

}