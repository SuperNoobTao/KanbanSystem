package cc.superliar.po;


import cc.superliar.enums.ValidFlag;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Authorized roles, provide for spring security.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 6/23/15
 * @since JDK1.8
 */
@Entity
@EntityListeners({AuditingEntityListener.class})
@Table(name = "roles")
@NamedEntityGraph(name = "Role.groups", attributeNodes = @NamedAttributeNode("groups"))
public class Role implements GrantedAuthority, Serializable {

  private static final long serialVersionUID = -5193344128221526323L;

  @Id
  @Column(updatable = false)
  private Long id;

  @NotEmpty
  @Column(nullable = false, length = 20)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private ValidFlag validFlag = ValidFlag.VALID;

  @CreatedDate
  @Column(nullable = false)
  private Date createdDate = new Date();

  @CreatedBy
  @Column(nullable = false)
  private Long createdBy;

  @LastModifiedDate
  @Column(nullable = false)
  private Date lastModifiedDate = new Date();

  @LastModifiedBy
  @Column(nullable = false)
  private Long lastModifiedBy;

  @Version
  @Column(nullable = false)
  private int version;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles", cascade = {CascadeType.REFRESH})
  private Set<User> users = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
  @JoinTable(name = "roles_has_resources",
          joinColumns = {@JoinColumn(name = "role_id")},
          inverseJoinColumns = {@JoinColumn(name = "resource_id")})
  private Set<Resource> resources = new HashSet<>();

  @Override
  public String getAuthority() {
    return name;
  }

  public Role() {
  }

  public Role(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ValidFlag getValidFlag() {
    return validFlag;
  }

  public void setValidFlag(ValidFlag validFlag) {
    this.validFlag = validFlag;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public Long getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(Long lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  public Set<Resource> getResources() {
    return resources;
  }

  public void setResources(Set<Resource> resources) {
    this.resources = resources;
  }
}
