package cc.superliar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shentao on 2016/11/29.
 */
public class UserVO implements Serializable {

    private static final long serialVersionUID = 6597728015488383528L;

    private int id;
    private String account;
    private String name;
    private String ip;
    private String lastLoginTime;
    private Date createdDate;
    private Date description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDescription() {
        return description;
    }

    public void setDescription(Date description) {
        this.description = description;
    }


}
