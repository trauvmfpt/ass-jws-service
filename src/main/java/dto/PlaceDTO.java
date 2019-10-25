package dto;

import entity.Place;
import util.ObjectUtil;

public class PlaceDTO {
    private int id;
    private String name;
    private String address;
    private int status;

    public PlaceDTO(Place place) {
        ObjectUtil.cloneObject(this,place);
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
