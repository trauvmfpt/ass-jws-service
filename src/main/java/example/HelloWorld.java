package example;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.StudentService;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.Calendar;

@WebService()
public class HelloWorld {

    @WebMethod
    public String sayHelloWorldFrom(String from) {
        String result = "Hello, world, from " + from;
        System.out.println(result);
        return result;
    }

    @WebMethod
    public String createStudent(Student student) {
        student.setId(System.currentTimeMillis());
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        return student.getName();
    }

    public static void main(String[] argv) {
        Object implementor = new HelloWorld();
        String address = "http://localhost:9000/HelloWorld";
        Endpoint.publish(address, implementor);
        Endpoint.publish("http://localhost:9000/student-service", new StudentService());
    }
}
