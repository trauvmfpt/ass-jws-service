package service;

import entity.Comment;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class CommentService {

    @WebMethod
    public boolean createComment(Comment comment){
//        comment.setAddress("Ha Noi");
//        comment.setName("Ha Noi");
        comment.setStatus(1);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();
        return true;
    }
    @WebMethod
    public boolean updateComment(Comment comment, int commentId){
//        comment.setAddress("Ha Noi");
//        comment.setName("Ha Noi");
        comment.setStatus(1);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        comment.setId(commentId);
        session.saveOrUpdate(comment);
        session.getTransaction().commit();
        return true;
    }
    @WebMethod
    public List<Comment> getList(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Comment> commentList =  session.createCriteria(Comment.class).list();
        session.getTransaction().commit();
        return commentList;
    }
    @WebMethod
    public Comment detail(int commentId){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Comment comment =  session.get(Comment.class,commentId);
        session.getTransaction().commit();
        return comment;
    }

}
