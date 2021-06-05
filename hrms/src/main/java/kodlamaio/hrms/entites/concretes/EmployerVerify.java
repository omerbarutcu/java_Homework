package kodlamaio.hrms.entites.concretes;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_confirms")
public class EmployerVerify {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employee employee;

	@Column(name = "is_confirmed")
	private boolean confirmed;

	@CreationTimestamp
	@Column(name = "confirm_date")
	private Date confirmDate;

	@OneToOne
	@JoinColumn(name = "employer_id", referencedColumnName = "id")
	private Employer employer;
}
