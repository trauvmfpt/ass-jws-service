package service;

import com.google.gson.Gson;
import entity.Post;
import entity.User;
import org.hibernate.Session;
import util.HashPWUtil;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@WebService
public class UserService {

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = new User();
        user.setUsername("nguyenhoang24695");
        user.setEmail("nguyenhoang@gmail.com");
        user.setPassword("123456");
        user.setAge(18);
        user.setGender(1);
        user.setRole(1);

        UserService userService = new UserService();

        System.out.println(new Gson().toJson(userService.createUser(user)));
        return;
    }
    @WebMethod
    public boolean createUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
//        user.setAddress("Ha Noi");
//        user.setName("Ha Noi");
        user.setStatus(1);
        user.setSalt(HashPWUtil.generateSalt());
        user.setPassword(HashPWUtil.hashPW(user.getPassword(),user.getSalt()));
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return true;
    }
    @WebMethod
    public boolean updateUser(User user, int userId){
//        user.setAddress("Ha Noi");
//        user.setName("Ha Noi");
        user.setStatus(1);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        user.setId(userId);
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        return true;
    }
    @WebMethod
    public List<User> getList(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<User> userList =  session.createCriteria(User.class).list();
        session.getTransaction().commit();
        return userList;
    }
    @WebMethod
    public User detail(int userId){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user =  session.get(User.class,userId);
        session.getTransaction().commit();
        return user;
    }

}
