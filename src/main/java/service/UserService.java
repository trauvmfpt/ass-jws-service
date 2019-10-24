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
        user.setSalt(HashPWUtil.generateSalt());
        user.setPassword(HashPWUtil.hashPW(user.getPassword(),user.getSalt()));
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
        for (User user:userList
             ) {
            user.setCommentSet(null);
            user.setPostSet(null);
            user.setRatingSet(null);
            user.setRoleSet(null);

        }
        return userList;
    }
    @WebMethod
    public User detail(int userId){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user =  session.get(User.class,userId);
        session.getTransaction().commit();
        session.close();
        user.setCommentSet(null);
        user.setPostSet(null);
        user.setRatingSet(null);
        user.setRoleSet(null);
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
                        existedUser.setCommentSet(null);
                        existedUser.setPostSet(null);
                        existedUser.setRatingSet(null);
                        existedUser.setRoleSet(null);
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
