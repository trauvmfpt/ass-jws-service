package dto;

import entity.Place;
import entity.Post;
import entity.Role;
import util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class PlaceDTO {
    private int id;
    private String name;
    private String address;
    private int status;
    private List<PostDTO> posts;

    public PlaceDTO(Place place) {
        ObjectUtil.cloneObject(this,place);
        if(place.getPostSet() != null){
            for (Post p : place.getPostSet()) {
                if (this.posts == null){
                    this.posts = new ArrayList<>();
                }
                this.posts.add(new PostDTO(p));
            }
        }
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
