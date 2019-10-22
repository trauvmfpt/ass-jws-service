package util;

import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.PlaceService;
import service.PostService;
import service.RatingService;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
//        Place place = new Place();
//        place.setName("test place");
//        place.setAddress("test place");
//        place.setStatus(1);
//
//        User user = new User();
//        user.setName("test user");
//        user.setAddress("test user");
//        user.setAge(19);
//
//        Image image = new Image();
//        image.setSource("gfhjghj");
//
//        Image image2 = new Image();
//        image2.setSource("bvnmbnm");
//
//        Image image3 = new Image();
//        image3.setSource("etryeryt");
//
//        PostService ps = new PostService();
//        Post post = new Post();
//        post.setInfo("test2");
//        post.setPlace(place);
//        post.setUser(user);
//        post.setId(3);

//        Post post = ps.getById(6);
//        post.setInfo("test3");
//        post.getImageSet().clear();
//        post.addImage(image);
//        post.addImage(image2);
//        post.addImage(image3);
//        ps.create(post);
//        ps.update(post);

        RatingService rs = new RatingService();
        Rating rating = rs.getByUserIdAndPostId(4,6);
        System.out.println(rating.getStar());
    }
}