package service;

import entity.Place;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class PlaceService {

    @WebMethod
    public boolean createPlace(Place place){
//        place.setAddress("Ha Noi");
//        place.setName("Ha Noi");
        place.setStatus(1);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(place);
        session.getTransaction().commit();
        return true;
    }
    @WebMethod
    public boolean updatePlace(Place place, int placeId){
//        place.setAddress("Ha Noi");
//        place.setName("Ha Noi");
        place.setStatus(1);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        place.setId(placeId);
        session.saveOrUpdate(place);
        session.getTransaction().commit();
        return true;
    }
    @WebMethod
    public List<Place> getList(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Place> placeList =  session.createCriteria(Place.class).list();
        session.getTransaction().commit();
        return placeList;
    }
    @WebMethod
    public Place detail(int placeId){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Place place =  session.get(Place.class,placeId);
        session.getTransaction().commit();
        return place;
    }

}
