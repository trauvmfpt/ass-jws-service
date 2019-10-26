package dto;

import entity.Image;
import util.ObjectUtil;

public class ImageDTO {
    private int id;
    private String source;
    private int postId;
    private int status;

    public ImageDTO(Image image) {
        ObjectUtil.cloneObject(this,image);
        this.postId = image.getPost().getId();
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
