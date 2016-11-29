package cc.superliar.po;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shentao on 2016/11/7.
 */
@Entity
@EntityListeners({AuditingEntityListener.class})
@Table(name = "tb_url")
public class Url {



    @Id()
    @SequenceGenerator(name = "urls_seq", sequenceName = "urls_seq", allocationSize = 1)
    @GeneratedValue(generator = "urls_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "url_id")
    private Long id;
    @Column(name = "url_content")
    private String content;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "urls", cascade = {CascadeType.REFRESH})
    private Set<Device> devices = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
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

