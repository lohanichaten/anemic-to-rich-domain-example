package learning.ddd.entity.common;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import learning.ddd.utils.Result;

@Embeddable
public class ExpirationDate {

	@Column(name="expiration_date")
	private Date date;
	
	
	
	public ExpirationDate() {
		
	}

	private ExpirationDate(Date expirationDate) {
		this.date=expirationDate;
	}
	
	public static Result<ExpirationDate> create(Date date){
		return Result.ok(new ExpirationDate(date));
	}
	
	public static ExpirationDate expirationDate(Date date) {
		if(Objects.isNull(date)) {
			return null;
		}
		
		return new ExpirationDate(date);
	}
	
	
	public boolean equals(ExpirationDate expirationDate) {
		return this.date.equals(expirationDate.getDate());
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.date);
	}

	
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public boolean isExpired() {
		return this.date.before(Date.from(Instant.now()));
	}
	
}
