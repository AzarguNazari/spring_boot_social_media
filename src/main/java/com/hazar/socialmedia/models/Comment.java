package com.hazar.socialmedia.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Validated
@Entity
@Table(name="comment")
@Data
@NoArgsConstructor
public class Comment implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @Id
  @GeneratedValue
  private Integer id = null;

  @JsonProperty("user")
  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="user_id", nullable=false)
  private User user = null;

  @JsonProperty("post")
  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="announcement_id", nullable=false)
  private Post post = null;

  @JsonProperty("creation_date")
  @Column(name="creation_date")
  private Date creationDate = null;

  @JsonProperty("comment_text")
  @Column(name="comment_text")
  private String commentText = null;
}
