package com.hazar.socialmedia.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
@Data
@Builder
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToMany(mappedBy = "roles")
	private List<User> users;
}
