package com.jwt.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "custmer")
public  class Custmer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotNull(message =  "name is cannot be null")
	@Column(name = "name")
	private String name;
	@NotNull(message = "email cannot be null")
	@Column(nullable = false, unique = true)
	private String email;
	@NotNull(message =  "password cannot be null")
	@Column(name = "password")
	private String password;
	@NotNull(message = "age Cannot be null")
	@Column(name = "age")
	private int age;
	@NotNull(message = "fullName Cannot be null")
	@Column(name = "fullName")
	private String fullName;

	

}
