package service;

import com.google.gson.Gson;
import dto.ImageDTO;
import entity.Image;
import entity.Image;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@WebService
public class ImageService {
    private static final Logger LOGGER = Logger.getLogger(ImageService.class.getName());

    @WebMethod
    public boolean createImage(String imageObj){
        if(imageObj != null){
            Image image = new Gson().fromJson(imageObj,Image.class);
            image.setStatus(1);
            try{
                Session session = HibernateUtil.getSession();
                session.beginTransaction();
                session.save(image);
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
    public String getAllImage(){
        List<Image> imageList = new ArrayList<Image>();
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            imageList =  session.createQuery("from Image ", Image.class).list();
            session.close();
            if(imageList != null){
                List<ImageDTO> imageDTOS = new ArrayList<>();
                for (Image image: imageList
                     ) {
                    imageDTOS.add(new ImageDTO(image));
                }
                return new Gson().toJson(imageDTOS);
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
    public String getByIdImage(int imageId){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Image image =  session.get(Image.class, imageId);
            session.close();
            if(image != null){

                return new Gson().toJson(new ImageDTO(image));
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
    public boolean updateImage(String imageObj){
        Image image = new Gson().fromJson(imageObj,Image.class);
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(image);
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
    public boolean deleteImage(String imageObj){
        Image image = new Gson().fromJson(imageObj,Image.class);
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Image existedImage =  session.get(Image.class, image.getId());
            if(existedImage != null){
                existedImage.setStatus(0);
                session.update(existedImage);
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
