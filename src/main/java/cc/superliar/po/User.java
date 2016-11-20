//package cc.superliar.po;
//
//import cc.superliar.enums.ValidFlag;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Created by shentao on 2016/11/7.
// */
//@Entity
//@EntityListeners({AuditingEntityListener.class})
//@Table(name = "tb_user")
//@NamedEntityGraph(name = "User.roles", attributeNodes = @NamedAttributeNode("roles"))
//public class User implements Serializable {
//    private static final long serialVersionUID = 2680591198337929454L;
//
//    @Id()
//    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
//    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
//    @Column(name = "user_id")
//    private int id;
//
//    @Column(name = "user_name")
//    private String name;
//
//    @Column(name = "user_account")
//    private String account;
//
//    @Column(name = "user_pwd")
//    private String pwd;
//
//    @CreatedDate
//    @Column(name = "user_created_date")
//    private Date createdDate = new Date();
//
//    @Column(name = "user_description")
//    private String description;
//
//    @Column(name = "user_ip")
//    private String ip;
//
//    @Column(name = "user_last_login_time")
//    private Date lastLoginTime;
//
//    @LastModifiedDate
//    @Column(name = "user_last_modified_date")
//    private Date lastModifiedDate = new Date();
//
//    @Column(name = "user_valid_flag")
//    private ValidFlag validFlag = ValidFlag.VALID;
//
//    @Column(name = "user_version")
//    private int version;
//
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles", cascade = {CascadeType.REFRESH})
//    private Set<User> users = new HashSet<>();
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
//    @JoinTable(name = "tb_user_has_role",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    private Set<Role> roles = new HashSet<>();
//
//
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAccount() {
//        return account;
//    }
//
//    public void setAccount(String account) {
//        this.account = account;
//    }
//
//    public String getPwd() {
//        return pwd;
//    }
//
//    public void setPwd(String pwd) {
//        this.pwd = pwd;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public Date getLastLoginTime() {
//        return lastLoginTime;
//    }
//
//    public void setLastLoginTime(Date lastLoginTime) {
//        this.lastLoginTime = lastLoginTime;
//    }
//
//    public Date getLastModifiedDate() {
//        return lastModifiedDate;
//    }
//
//    public void setLastModifiedDate(Date lastModifiedDate) {
//        this.lastModifiedDate = lastModifiedDate;
//    }
//
//    public ValidFlag getValidFlag() {
//        return validFlag;
//    }
//
//    public void setValidFlag(ValidFlag validFlag) {
//        this.validFlag = validFlag;
//    }
//
//    public int getVersion() {
//        return version;
//    }
//
//    public void setVersion(int version) {
//        this.version = version;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", account='" + account + '\'' +
//                ", pwd='" + pwd + '\'' +
//                ", validFlag=" + validFlag +
//                '}';
//    }
//
//    public User(int id,String name, String account, String pwd){
//        this.id = id;
//        this.name = name;
//        this.account = account;
//        this.pwd = pwd;
//    }
//
//
//
//    public User(User user) {
//        super();
//        this.id = user.getId();
//        this.name = user.getName();
//        this.account = user.getAccount();
//        this.pwd = user.getPwd();
//        this.roles = user.getRoles();
//    }
//}
