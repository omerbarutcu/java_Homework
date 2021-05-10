package gun_5_odev.entities.concretes;

import java.util.Date;

import gun_5_odev.entities.abstracts.Entity;

import java.util.Calendar;

public class Verify implements Entity {

	private String eMail;
	private String comfirmCode;
	private Date expriation;

	public Verify(String eMail, String comfirmCode) {
		super();
		this.eMail = eMail;
		this.comfirmCode = comfirmCode;

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 30);

		expriation = calendar.getTime();
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getComfirmCode() {
		return comfirmCode;
	}

	public void setComfirmCode(String comfirmCode) {
		this.comfirmCode = comfirmCode;
	}

	public Date getExpriation() {
		return expriation;
	}

	public void setExpriation(Date expriation) {
		this.expriation = expriation;
	}

}
