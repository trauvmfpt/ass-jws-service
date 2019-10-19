package service;

import entity.Place;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class PlaceService {

    @WebMethod
    public boolean createPlace(Place place){
        place.setAddress("Ha Noi");
        place.setName("Ha Noi");
        place.setStatus(1);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(place);
        session.getTransaction().commit();
        return true;
    }
}
