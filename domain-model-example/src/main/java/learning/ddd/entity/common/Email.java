package learning.ddd.entity.common;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import learning.ddd.utils.Result;

@Embeddable
public class Email {

	@Column(name="email")
	private String value;
	
	public Email() {
		super();
		
	}

	private Email(String value) {
		this.value=value;
	}
	
	
	public static Result<Email> create(String email){
		email=(email!=null)?email.trim():"";
		
		if(email.length()==0) {
			return Result.error("Email should not be empty");
		}
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		 
		if(!pattern.matches("^(.+)@(.+)$",email)) {
			return Result.error("Email is invalid");
		}
		
		
		return Result.ok(new Email(email));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
}
