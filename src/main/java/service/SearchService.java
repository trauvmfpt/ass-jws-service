package service;

import com.google.gson.Gson;

import entity.Place;
import entity.Post;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Query;
import java.util.List;


@WebService
public class SearchService {
    String hql = "";

    public static void main(String[] args) {
        new SearchService().searchByPlace("a");
    }

    @WebMethod
    public List<Place> searchByPlace(String key) {
        hql = "FROM Place P WHERE P.name LIKE '%" + key + "%' OR P.address like '%" + key + "%'";
        List<Place> placeList = (List<Place>)QuerySearch(hql);
        System.out.println(new Gson().toJson(placeList));
        return placeList;
    }
    @WebMethod
    public List<Post> searchByPost(String key) {
        hql = "FROM Post P WHERE P.info LIKE '%" + key + "%'";
        List<Post> postList = (List<Post>)QuerySearch(hql);
        System.out.println(new Gson().toJson(postList));
        return postList;
    }

    private Object QuerySearch(String hql) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        List object = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return object;
    }
}
