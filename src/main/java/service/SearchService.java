package service;

import com.google.gson.Gson;

import dto.PlaceDTO;
import dto.PostDTO;
import entity.Place;
import entity.Post;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@WebService
public class SearchService {
    String hql = "";

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new SearchService().searchByPost("a")));

    }

    @WebMethod
    public String searchByPlace(String key) {
        hql = "FROM Place P WHERE P.name LIKE '%" + key + "%' OR P.address like '%" + key + "%'";
        List<Place> placeList = (List<Place>)QuerySearch(hql);
        List<PlaceDTO> placeDTOS = new ArrayList<>();
        for (Place p:placeList
             ) {
            placeDTOS.add(new PlaceDTO(p));
        }
//        System.out.println(new Gson().toJson(placeList));
        return new Gson().toJson(placeDTOS);
    }
    @WebMethod
    public String searchByPost(String key) {
        hql = "FROM Post P WHERE P.info LIKE '%" + key + "%'";
        List<Post> postList = (List<Post>)QuerySearch(hql);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post p:postList
             ) {
            postDTOS.add(new PostDTO(p));

        }
        return new Gson().toJson(postDTOS);
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
