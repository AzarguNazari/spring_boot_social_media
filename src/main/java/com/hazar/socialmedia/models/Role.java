package com.hazar.socialmedia.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToMany(mappedBy = "roles")
	private List<User> users;
}
