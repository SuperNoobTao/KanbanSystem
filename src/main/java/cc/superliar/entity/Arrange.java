package cc.superliar.entity;

/**
 * Created by shentao on 2016/11/7.
 */
public class Arrange {
    private int id;
    private String device_udid;
    private int url_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevice_udid() {
        return device_udid;
    }

    public void setDevice_udid(String device_udid) {
        this.device_udid = device_udid;
    }

    public int getUrl_id() {
        return url_id;
    }

    public void setUrl_id(int url_id) {
        this.url_id = url_id;
    }

    @Override
    public String toString() {
        return "Arrange{" +
                "id=" + id +
                ", device_udid='" + device_udid + '\'' +
                ", url_id=" + url_id +
                '}';
    }

    public Arrange() {
    }
}
