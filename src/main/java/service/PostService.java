package service;

import com.google.gson.Gson;
import dto.PostDTO;
import entity.Image;
import entity.Post;
import javafx.geometry.Pos;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService
public class PostService {
    private static final Logger LOGGER = Logger.getLogger(PostService.class.getName());

    @WebMethod
    public boolean createPost(String postObj){
        if(postObj != null){
            Post post = new Gson().fromJson(postObj, Post.class);
            for (Image image :
                    post.getImageSet()) {
                image.setPost(post);
            }
            post.setStatus(1);
            try{
                Session session = HibernateUtil.getSession();
                session.beginTransaction();
                session.save(post);
                session.getTransaction().commit();
                session.close();
                return true;
            }
            catch (Exception ex){
                LOGGER.log(Level.INFO, "Error create place");
                LOGGER.log(Level.WARNING, ex.getMessage());
            }
            return false;
        }
        return false;
    }

    @WebMethod
    public String getAllPost(){
        List<Post> postList = new ArrayList<Post>();
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            postList =  session.createQuery("from Post ", Post.class).list();
            session.close();
            if(postList != null){
                List<PostDTO> postDTOS = new ArrayList<>();
                for (Post post: postList
                     ) {
                   postDTOS.add(new PostDTO(post));
                }
                return new Gson().toJson(postDTOS);
            }
            return null;
        }
        catch (Exception ex){
            LOGGER.log(Level.INFO, "Error create place");
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return null;
    }

    @WebMethod
    public String getByIdPost(int postId){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Post post =  session.get(Post.class, postId);
            session.close();
            if(post != null){

                return new Gson().toJson(new PostDTO(post));
            }
            return null;
        }
        catch (Exception ex){
            LOGGER.log(Level.INFO, "Error create place");
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return null;
    }

    @WebMethod
    public boolean updatePost(String postObj){
        Post post = new Gson().fromJson(postObj,Post.class);
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(post);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception ex){
            LOGGER.log(Level.INFO, "Error create place");
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return false;
    }

    @WebMethod
    public boolean deletePost(String postObj){
        Post post = new Gson().fromJson(postObj,Post.class);
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Post existedPost =  session.get(Post.class, post.getId());
            if(existedPost != null){
                existedPost.setStatus(0);
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
