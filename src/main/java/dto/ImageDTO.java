package dto;

import entity.Comment;
import entity.Image;
import entity.Rating;
import util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class ImageDTO {
    private int id;
    private String source;
    private int postId;
    private int status;

    private List<CommentDTO> comments;
    private List<RatingDTO> ratings;

    public ImageDTO(Image image) {
        ObjectUtil.cloneObject(this,image);
        this.postId = image.getPost().getId();

        for (Comment c : image.getCommentSet()) {
            if (this.comments == null){
                this.comments = new ArrayList<>();
            }
            this.comments.add(new CommentDTO(c));
        }

        for (Rating r : image.getRatingSet()) {
            if (this.ratings == null){
                this.ratings = new ArrayList<>();
            }
            this.ratings.add(new RatingDTO(r));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
