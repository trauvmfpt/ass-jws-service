package service;

import com.google.gson.Gson;
import dto.CommentDTO;
import entity.Comment;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class CommentService {

    @WebMethod
    public boolean createComment(String commentObj){
        Comment comment = new Gson().fromJson(commentObj,Comment.class);
//        comment.setAddress("Ha Noi");
//        comment.setName("Ha Noi");
        comment.setStatus(1);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    @WebMethod
    public boolean updateComment(String commentObj, int commentId){
        Comment comment = new Gson().fromJson(commentObj,Comment.class);
//        comment.setAddress("Ha Noi");
//        comment.setName("Ha Noi");
        comment.setStatus(1);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        comment.setId(commentId);
        session.saveOrUpdate(comment);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    @WebMethod
    public String getListComment(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Comment> commentList =  session.createCriteria(Comment.class).list();
        session.getTransaction().commit();
        session.close();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment:commentList
             ) {
            commentDTOS.add(new CommentDTO(comment));
        }

        return new Gson().toJson(commentDTOS);
    }
    @WebMethod
    public String detailComment(int commentId){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Comment comment =  session.get(Comment.class,commentId);
        session.getTransaction().commit();
        session.close();
        CommentDTO commentDTO = new CommentDTO(comment);
        return new Gson().toJson(commentDTO);
    }

}
