package dto;

import entity.Rating;
import util.ObjectUtil;

public class RatingDTO {
    private int id;
    private double star;
    private int postId;
    private int imageId;
    private int userId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RatingDTO(Rating rating) {
        ObjectUtil.cloneObject(this,rating);
        if(rating.getPost() != null){
            this.postId = rating.getPost().getId();
        }
        if(rating.getImage() != null){
            this.imageId = rating.getImage().getId();
        }
        this.userId = rating.getUser().getId();
        this.userName = rating.getUser().getUsername();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
