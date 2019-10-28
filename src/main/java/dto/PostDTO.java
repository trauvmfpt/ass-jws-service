package dto;

import entity.Comment;
import entity.Image;
import entity.Post;
import entity.Rating;
import util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class PostDTO {
    private int id;
    private String info;
    private String userName;
    private int placeId;
    private int status;
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private List<ImageDTO> images;
    private List<CommentDTO> comments;
    private List<RatingDTO> ratings;

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDTO> ratings) {
        this.ratings = ratings;
    }

    public PostDTO(Post post) {
        ObjectUtil.cloneObject(this,post);
        if (post.getUser() != null){
            this.userName = post.getUser().getUsername();
        }
        this.placeId = post.getPlace().getId();
        for (Image i : post.getImageSet()) {
            if (this.images == null){
                this.images = new ArrayList<>();
            }
            this.images.add(new ImageDTO(i));
        }

        for (Comment c : post.getCommentSet()) {
            if (this.comments == null){
                this.comments = new ArrayList<>();
            }
            this.comments.add(new CommentDTO(c));
        }

        for (Rating r : post.getRatingSet()) {
            if (this.ratings == null){
                this.ratings = new ArrayList<>();
            }
            this.ratings.add(new RatingDTO(r));
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
