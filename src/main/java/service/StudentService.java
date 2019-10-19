package service;

import entity.Student;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class StudentService {

    @WebMethod
    public boolean createStudent(Student student){
        student.setId(System.currentTimeMillis());
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        return true;
    }
}
