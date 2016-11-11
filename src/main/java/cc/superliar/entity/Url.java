package cc.superliar.entity;

/**
 * Created by shentao on 2016/11/7.
 */
public class Url {
    private int id;
    private String content;

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

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

    public Url() {
    }
}

