import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;

@XmlRootElement
public class JaXmUtil {

    /**
     * JavaBean转换成xml
     *
     * @return
     */

    public static String convertToXml(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(baos, (String) marshaller.getProperty(Marshaller.JAXB_ENCODING));
            xmlStreamWriter.writeStartDocument((String) marshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");
            marshaller.marshal(obj, xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();
            return new String(baos.toString("GBK"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * xml转换成JavaBean
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static void objectToXmlToFile(Object object, String filePath) {
        try {
            if (object == null || filePath == null || "".equals(filePath)) return;
            File file = new File(filePath);
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = context.createMarshaller();
            //是否格式化输出xml文件
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //设置编码
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
            //写入到文件中
            jaxbMarshaller.marshal(object, file);
            System.out.println("xml已写入到文件" + filePath);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void XmlfileToObject( String filePath) {
        try {
            File xmlFile = new File(filePath);
            ScoketBean scoketBean = new ScoketBean();
            JAXBContext context = JAXBContext.newInstance(scoketBean.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ScoketBean bean = (ScoketBean) unmarshaller.unmarshal(xmlFile);
            System.out.println(bean.getAge());
            System.out.println(bean.toString());
            System.out.println("1111");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
