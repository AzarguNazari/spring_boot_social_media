package com.facebook.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User
 */
@Validated
@Entity
@Table(name="user_details")
@Data
@NoArgsConstructor
public class User  implements Serializable  {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id = null;

    @JsonProperty("first_name")
    @Column(name="first_name")
    private String firstName = null;

    @JsonProperty("last_name")
    @Column(name="last_name")
    private String lastName = null;

    @JsonProperty("username")
    @Column(name="username")
    @NotNull
    private String email = null;

    @JsonIgnore
    @Column(name="password")
    @NotNull
    private String password=null;

    @JsonIgnore
    @Column(name="about")
    private String about=null;

    @JsonIgnore
    @Column(name="bdate")
    private Date bdate=null;

    @JsonProperty("Roles")
    @OneToMany(mappedBy="user", cascade={CascadeType.ALL})
    private List<Role> roles = null;
}