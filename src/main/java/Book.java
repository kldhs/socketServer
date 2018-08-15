import javax.xml.bind.annotation.XmlElement;

/**
 *
 *
 *
 *
 *
 */
public class Book {
    @XmlElement(name="BookName")
    private String bookName;
    @XmlElement(name="Time")
    private String time;
    @XmlElement(name="Author")
    private String author;
    public String getBookName() {
        return bookName;
    }
    public String getTime() {
        return time;
    }
    public String getAuthor() {
        return author;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}
