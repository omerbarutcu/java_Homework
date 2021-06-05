package kodlamaio.hrms.core.entites;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(updatable = false, unique = true)
	private UUID uuid = UUID.randomUUID();

	@Email
	@NotBlank
	@NotNull
	@Column(name = "email", unique = true)
	private String email;

	@NotBlank
	@NotNull
	@Column(name = "password")
	private String password;

	@NotBlank
	@NotNull
	@Transient
	private String passwordCheck;

}
