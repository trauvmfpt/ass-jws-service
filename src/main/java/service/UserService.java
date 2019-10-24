package service;

import com.google.gson.Gson;
import entity.Post;
import entity.Rating;
import entity.Role;
import entity.User;
import org.hibernate.Session;
import util.HashPWUtil;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(PostService.class.getName());

    @WebMethod
    public boolean createUser(User user, int[] roleIds) throws InvalidKeySpecException, NoSuchAlgorithmException {
        user.setStatus(1);
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            for (int roleId : roleIds
                 ) {
                Role role = session.get(Role.class,roleId);
                user.addRole(role);
                session.save(user);
            }
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
    @WebMethod
    public boolean updateUser(User user, int[] roleIds){
        user.setStatus(1);
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            for (int roleId : roleIds
            ) {
                Role role = session.get(Role.class,roleId);
                user.addRole(role);
                session.saveOrUpdate(user);
            }
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
    @WebMethod
    public List<User> getList(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<User> userList =  session.createQuery("from User ", User.class).list();
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
    public User getByUserName(String username ){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String sqlQuery = "select u from User u where u.username = :username";
        User user =  session.createQuery(sqlQuery, User.class).setParameter("username", username).getSingleResult();
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

    @WebMethod
    public User login(User user){
        if(user != null){
            try{
                Session session = HibernateUtil.getSession();
                session.beginTransaction();
                String sqlQuery = "select r from User r where r.username = :userName";
                User existedUser =  session.createQuery(sqlQuery, User.class).setParameter("userName", user.getUsername()).getSingleResult();
                if(existedUser != null){
                    user.setPassword(HashPWUtil.hashPW(user.getPassword(),existedUser.getSalt()));
                    if(existedUser.getPassword().equals(user.getPassword()) ){
                        existedUser.setToken(UUID.randomUUID().toString());
                        session.saveOrUpdate(existedUser);
                        session.close();
                        return existedUser;
                    }
                }
                return null;
            }
            catch (Exception ex){
                LOGGER.log(Level.INFO, "Error create rating");
                LOGGER.log(Level.WARNING, ex.getMessage());
            }
        }
        return null;
    }

}
