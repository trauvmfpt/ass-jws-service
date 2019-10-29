package dto;

import entity.Comment;
import util.ObjectUtil;

public class CommentDTO {
    private int id;
    private String content;
    private int userId;
    private String userName;
    private int postId;
    private int imageId;
    private int status;

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public CommentDTO(Comment comment) {
        ObjectUtil.cloneObject(this,comment);
        this.userId = comment.getUser().getId();
        this.userName = comment.getUser().getUsername();
        if(comment.getPost() != null){
            this.postId = comment.getPost().getId();
        }
        if(comment.getImage() != null){
            this.imageId = comment.getImage().getId();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
