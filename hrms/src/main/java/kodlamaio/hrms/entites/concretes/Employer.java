package kodlamaio.hrms.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kodlamaio.hrms.core.entites.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@NoArgsConstructor
@Table(name = "employers")
public class Employer extends User {

	@NotBlank
	@NotNull
	@Column(name = "company_name")
	private String companyName;

	@NotBlank
	@NotNull
	@Column(name = "web_address")
	private String webAddress;

	@NotBlank
	@NotNull
	@Column(name = "phone_number")
	private String phoneNumber;

}
