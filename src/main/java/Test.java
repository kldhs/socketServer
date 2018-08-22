import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //
        //ScoketBean scoketBean = new ScoketBean();
        //scoketBean.setName("zhangsan");
        //scoketBean.setAge("22");
        //scoketBean.setCode("zxvsfsavc");
        //
        //List<Book> books = new ArrayList<Book>();
        //Book book1 = new Book();
        //book1.setAuthor("作者1");
        //book1.setTime("2014-12-28");
        //book1.setBookName("How to use JAXB");
        //Book book2= new Book();
        //book2.setAuthor("作者2");
        //book2.setTime("2014-06-06");
        //book2.setBookName("How to use SOCKET");
        //books.add(book1);
        //books.add(book2);
        //
        //scoketBean.setBooks(books);
        //JaXmUtil.objectToXmlToFile(scoketBean,"d:\\teacher.xml");
        //System.out.println(JaXmUtil.convertToXml(scoketBean));
        JaXmUtil.XmlfileToObject("d:\\teacher.xml");

    }
}