package cc.superliar.po;

import cc.superliar.enums.ValidFlag;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "admin_validflag")
    private ValidFlag validFlag = ValidFlag.VALID;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "users_has_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

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

    public ValidFlag getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(ValidFlag validFlag) {
        this.validFlag = validFlag;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", type=" + type +
                ", validFlag=" + validFlag +
                '}';
    }

    public User(User user) {
        super();
        this.id = user.getId();
        this.name = user.getName();
        this.account = user.getAccount();
        this.pwd = user.getPwd();
        this.type = user.getType();
        this.validFlag = user.getValidFlag();
    }
}
