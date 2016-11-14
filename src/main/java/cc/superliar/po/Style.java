package cc.superliar.po;

/**
 * Created by shentao on 2016/11/7.
 */
public class Style {
    private int id;
    private int time;
    private int mode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Style{" +
                "id=" + id +
                ", time=" + time +
                ", mode=" + mode +
                '}';
    }

    public Style() {
    }
}
