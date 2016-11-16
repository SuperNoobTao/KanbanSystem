package cc.superliar.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shentao on 2016/11/7.
 */
@Entity
@Table(name = "tb_admin")
public class User implements Serializable {
    @Id()
    @Column(name = "admin_id")
    private int id;
    @Column(name = "admin_name")
    private String name;
    @Column(name = "admin_account")
    private String account;
    @Column(name = "admin_pwd")
    private String pwd;
    @Column(name = "admin_type")
    private int type;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", type=" + type +
                '}';
    }

    public User() {
    }
}
