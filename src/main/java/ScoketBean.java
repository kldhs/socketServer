import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)//表示使用这个类中的 private 非静态字段作为 XML 的序列化的属性或者元素,对应属性要使用get、set方法。
@XmlRootElement(name = "ROOT")
@XmlType(propOrder = {"code", "name", "age"})//xml格式数据的显示的顺序名字要和定义变量的一样，而不是@XmlElement中的name
public class ScoketBean {
    @XmlElement(name = "Code", required = true)//定义xml中显示的数据
    private String code;
    @XmlElement(name = "Name", required = true)
    private String name;
    @XmlElement(name = "Age", required = true)
    private String age;
    @XmlElementWrapper(name="ROWS")
    @XmlElement(name="LIST",required=true)
    private List<Book> books;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        String result = "";
        result=this.age+","+this.name+this.code+",集合的数据：";
        for (Book book:books) {
            result+=book.getAuthor()+book.getTime()+book.getBookName();
        }
        return result;
    }
}
