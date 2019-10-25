package dto;

import entity.Role;
import util.ObjectUtil;

public class RoleDTO {
    private int id;
    private String name;

    public RoleDTO(Role role) {
        ObjectUtil.cloneObject(this,role);
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
}
