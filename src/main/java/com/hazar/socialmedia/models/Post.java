package com.hazar.socialmedia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Validated
@Entity
@Builder
@NoArgsConstructor
@Table(name="Announcement")
public class Post implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id = null;

    @JsonProperty("user")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="admin_id", insertable=true, updatable=false , nullable=true)
    private User user = null;

    @JsonProperty("description")
    @Column(name="description")
    private String content = null;

    @JsonProperty("creation_date")
    @Column(name="creation_date")
    private Date creationDate = null;

    @OneToMany(mappedBy="comment", cascade=CascadeType.ALL)
    private List<Comment> comments;
}
