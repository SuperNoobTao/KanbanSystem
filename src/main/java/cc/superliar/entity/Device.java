package cc.superliar.entity;

import javax.persistence.Id;

/**
 * Created by shentao on 2016/10/31.
 */

public class Device {
    @Id
    private String id;
    private String name;
    private String location;
    private int screensize;
    private int screennum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getScreensize() {
        return screensize;
    }

    public void setScreensize(int screensize) {
        this.screensize = screensize;
    }

    public int getScreennum() {
        return screennum;
    }

    public void setScreennum(int screennum) {
        this.screennum = screennum;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", screensize=" + screensize +
                ", screennum=" + screennum +
                '}';
    }

    public Device() {
    }
}
