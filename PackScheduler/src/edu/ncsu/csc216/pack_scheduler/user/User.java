package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Extracted Abstract User from Student
 * @author Julie Tran
 *
 */
public abstract class User {

	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's ID */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;

	/**
	 * User Constructor 
	 * @param firstName User's first name
	 * @param lastName User's last name 
	 * @param id User's ID
	 * @param email User's email
	 * @param password User's password
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		
	}

	/**
	 * Gets the student's email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets a students email. If email is empty, null, has no '@' sign, has no '.', or if the first index of '@'
	 * is greater than the last index of '.', an IllegalArguemtnException is thrown.
	 * @param email the email to set
	 * @throws IllegalArgumentException if the passed in email is invalid
	 */
	public void setEmail(String email) {
		if (email == null || email.length() == 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Gets the student's password.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student's password. If the password is empty or null, and Illegal Argument Exception is thrown.
	 * @param password the password to set
	 * @throws IllegalArgumentException if the password passed in is empty or null
	 */
	public void setPassword(String password) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Sets the student's first name.
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if the passed in first name is null or an empty string
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name.
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if the passed in last name is null or an empty string
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Sets the sthudent's id
	 * @param id the id to set
	 * @throws IllegalArgumentException if the id is null or an empty string
	 */
	public void setId(String id) {
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Gets the student's first name
	 * @return firstName the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * gets the student's last name
	 * @return lastName the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the student's id
	 * @return id the student's id
	 */
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}