package kodlamaio.hrms.entites.concretes;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import kodlamaio.hrms.core.entites.User;
import lombok.Data;

@Data
@Entity
@Table(name = "verification_codes")
public class VerificationCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "code")
	private String verificationCode;

	@Column(name = "is_verified")
	private boolean isVerified;

	@UpdateTimestamp
	@Column(name = "verified_date",insertable = false)
	private Date verifiedDate;

	@OneToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;


}
