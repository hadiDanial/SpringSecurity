package hadi.springSecurity.beans.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hadi.springSecurity.beans.embeddables.Name;

@Entity
@Table(name = "Users")
@JsonIgnoreProperties({"password"})
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	private String username;
	private String email;
	@Embedded
	private Name name;
	
	private String password;
	
}
