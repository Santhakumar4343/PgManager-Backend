package com.pgmanager.entity;



import jakarta.persistence.*;


@Entity

public class SuperAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String mobileNumber;

	public SuperAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuperAdmin(Long id, String username, String email, String password, String mobileNumber) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "SuperAdmin [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", mobileNumber=" + mobileNumber + "]";
	}
    
    
}
