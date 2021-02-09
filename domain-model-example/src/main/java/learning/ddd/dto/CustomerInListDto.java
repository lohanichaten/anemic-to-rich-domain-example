package learning.ddd.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerInListDto {

	private long id;
	private String name;
	private String email;
	private String status;
	private Date statusExpirationDate;
	private BigDecimal moneySpent;
	
	
	
	
	public CustomerInListDto(long id, String name, String email, String status, Date statusExpirationDate,
			BigDecimal moneySpent) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.status = status;
		this.statusExpirationDate = statusExpirationDate;
		this.moneySpent = moneySpent;
	}
	public CustomerInListDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStatusExpirationDate() {
		return statusExpirationDate;
	}
	public void setStatusExpirationDate(Date statusExpirationDate) {
		this.statusExpirationDate = statusExpirationDate;
	}
	public BigDecimal getMoneySpent() {
		return moneySpent;
	}
	public void setMoneySpent(BigDecimal moneySpent) {
		this.moneySpent = moneySpent;
	}
	
	
	
	
	
}
