package service;

import com.google.gson.Gson;
import dto.UserDTO;
import entity.Post;
import entity.Rating;
import entity.Role;
import entity.User;
import org.hibernate.Session;
import util.HashPWUtil;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Query;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(PostService.class.getName());

    @WebMethod
    public boolean createUser(String userObj, int[] roleIds) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = new Gson().fromJson(userObj,User.class);
        user.setStatus(1);
//        user.setSalt(HashPWUtil.generateSalt());
//        user.setPassword(HashPWUtil.hashPW(user.getPassword(),user.getSalt()));
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
    public boolean updateUser(String userObj, int[] roleIds){
        User user = new Gson().fromJson(userObj,User.class);
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
    public String  getList(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<User> userList =  session.createQuery("from User ", User.class).list();
        session.getTransaction().commit();
        session.close();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user:userList
             ) {
            userDTOS.add(new UserDTO(user));
        }
        return new Gson().toJson(userDTOS);
    }
    @WebMethod
    public String  detail(int userId){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user =  session.get(User.class,userId);
        session.getTransaction().commit();
        session.close();

        return new Gson().toJson(new UserDTO(user));
    }
    @WebMethod
    public String  getByUserName(String username){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "FROM User u WHERE u.username ='" + username +"'";
        Query query = session.createQuery(hql);
        List object = query.getResultList();
        User user =  (User)object.get(0);
        session.getTransaction().commit();
        session.close();
        UserDTO userDTO = new UserDTO(user);
        return new Gson().toJson(userDTO);
    }
    @WebMethod
    public boolean delete(String userObj){
        User user = new Gson().fromJson(userObj,User.class);
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
    public String login(String userObj){
        User user = new Gson().fromJson(userObj,User.class);
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
                        return new Gson().toJson(existedUser);
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
