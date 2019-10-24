package service;

import entity.Image;
import entity.Rating;
import org.hibernate.Criteria;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@WebService
public class RatingService {
    private static final Logger LOGGER = Logger.getLogger(RatingService.class.getName());

    @WebMethod
    public boolean createRate(Rating rating){
        if(rating != null){
            try{
                Session session = HibernateUtil.getSession();
                session.beginTransaction();
                session.save(rating);
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
    public List<Rating> getAllRate(){
        List<Rating> ratingList = new ArrayList<Rating>();
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            ratingList =  session.createQuery("from Rating ", Rating.class).list();
            session.close();
            if(ratingList != null){
                for (Rating rating:ratingList
                     ) {
                    rating.setImage(null);
                    rating.setPost(null);
                    rating.setUser(null);
                }
                return ratingList;
            }
            return null;
        }
        catch (Exception ex){
            LOGGER.log(Level.INFO, "Error create rating");
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return null;
    }

    @WebMethod
    public Rating getByUserIdAndPostId(int userId, int postId){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            String sqlQuery = "select r from Rating r where r.user.id = :userId and r.post.id = :postId";
            Rating rating =  session.createQuery(sqlQuery, Rating.class).setParameter("userId", userId).setParameter("postId", postId).getSingleResult();
            session.close();
            if(rating != null){
                rating.setImage(null);
                rating.setPost(null);
                rating.setUser(null);
                return rating;
            }
            return null;
        }
        catch (Exception ex){
            LOGGER.log(Level.INFO, "Error create rating");
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return null;
    }

    @WebMethod
    public boolean updateRate(Rating rating){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(rating);
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
    public boolean deleteRate(Rating rating){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Rating existedRating =  session.get(Rating.class, rating.getId());
            if(existedRating != null){
                session.update(existedRating);
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
