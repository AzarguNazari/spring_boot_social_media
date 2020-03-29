package com.hazar.socialmedia.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Role
 */
@Validated
@Entity
@Table(name="role")
@Data
@NoArgsConstructor
public class Role  implements Serializable  {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	@Id
	@GeneratedValue
	@JsonIgnore
	private Integer id = null;

	@JsonProperty("name")
	@Column(name="name")
	private String name = null;

	@OneToMany(mappedBy="role", cascade={CascadeType.ALL})
	private List<User> users;
}
