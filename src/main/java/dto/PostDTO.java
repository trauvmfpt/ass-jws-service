package dto;

import entity.Post;
import util.ObjectUtil;

public class PostDTO {
    private int id;
    private String info;
    private int userId;
    private int placeId;
    private int status;

    public PostDTO(Post post) {
        ObjectUtil.cloneObject(this,post);
        if (post.getUser() != null){
            this.userId = post.getUser().getId();
        }
        this.placeId = post.getPlace().getId();
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
