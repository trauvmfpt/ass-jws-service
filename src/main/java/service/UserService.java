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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(PostService.class.getName());

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
        session.close();
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
        session.close();
        return true;
    }
    @WebMethod
    public List<User> getList(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<User> userList =  session.createCriteria(User.class).list();
        session.getTransaction().commit();
        session.close();
        return userList;
    }
    @WebMethod
    public User detail(int userId){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user =  session.get(User.class,userId);
        session.getTransaction().commit();
        session.close();
        return user;
    }
    @WebMethod
    public boolean delete(User user){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Post existedPost =  session.get(Post.class, user.getId());
            if(existedPost != null){
                existedPost.setStatus(-1);
                session.update(existedPost);
                session.getTransaction().commit();
                session.close();
                return true;
            }
            return false;
        }
        catch (Exception ex){
            LOGGER.log(Level.INFO, "Error create place");
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return false;
    }

}
