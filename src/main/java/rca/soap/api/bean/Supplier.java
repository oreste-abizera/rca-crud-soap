package rca.soap.api.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Supplier {

	@Id
	@GeneratedValue
	private int id;

	private String names;

	private String email;

	private String mobile;

	public Supplier(int id, String names, String email, String mobile) {
		this.id = id;
		this.names = names;
		this.email = email;
		this.mobile = mobile;
	}

	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Supplier{" +
				"id=" + id +
				", names='" + names + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				'}';
	}
}
