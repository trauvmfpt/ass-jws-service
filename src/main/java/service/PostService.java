package service;

import entity.Place;
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
    public boolean create(Place place){
        place.setStatus(1);
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.save(place);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception ex){
            LOGGER.log(Level.INFO, "Error create place");
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return false;
    }

    @WebMethod
    public List<Place> getAll(){
        List<Place> placeList = new ArrayList<Place>();
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            placeList =  session.createQuery("from Place ", Place.class).list();
            if(placeList != null){
                return placeList;
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
    public Place getById(int placeId){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Place place =  session.get(Place.class, placeId);
            if(place != null){
                return place;
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
    public boolean update(Place place){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Place existedPlace =  session.get(Place.class, place.getId());
            if(existedPlace != null){
                existedPlace.setName(place.getName());
                existedPlace.setAddress(place.getAddress());
                existedPlace.setStatus(place.getStatus());
                session.update(existedPlace);
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
    public boolean delete(Place place){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Place existedPlace =  session.get(Place.class, place.getId());
            if(existedPlace != null){
                existedPlace.setStatus(0);
                session.update(existedPlace);
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
