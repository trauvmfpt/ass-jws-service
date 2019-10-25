package service;

import dto.PlaceDTO;
import entity.Place;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
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
        session.close();
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
        session.close();
        return true;
    }
    @WebMethod
    public List<PlaceDTO> getListPlace(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Place> placeList =  session.createCriteria(Place.class).list();
        session.getTransaction().commit();
        session.close();
        List<PlaceDTO> placeDTOS = new ArrayList<>();
        for (Place place:placeList
             ) {
            placeDTOS.add(new PlaceDTO(place));
        }
        return placeDTOS;
    }
    @WebMethod
    public Object detailPlace(int placeId){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Place place =  session.get(Place.class,placeId);
        session.getTransaction().commit();
        session.close();

        return new PlaceDTO(place);
    }

}
