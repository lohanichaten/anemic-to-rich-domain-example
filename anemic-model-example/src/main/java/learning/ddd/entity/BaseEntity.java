package learning.ddd.entity;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	 @Version
	 private Long version;
	
	 
	 
	 
	 public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Long getVersion() {
	        return version;
	    }

	    public void setVersion(Long version) {
	        this.version = version;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        BaseEntity that = (BaseEntity) o;
	        return Objects.equals(id, that.id) &&
	                Objects.equals(version, that.version);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, version);
	    }

	    @Override
	    public String toString() {
	        return "BaseEntity{" +
	                "id=" + id +
	                ", version=" + version +
	                '}';
	    }
}
