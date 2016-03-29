package ru.tritec.jdbcproject.pojo;

public class User {
	
	private int id, id_user;
	private String nik,sex, user_class;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUser_class() {
		return user_class;
	}
	public void setUser_class(String user_class) {
		this.user_class = user_class;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", id_user=" + id_user + ", nik=" + nik + ", sex=" + sex + ", user_class="
				+ user_class + "]";
	}
}
