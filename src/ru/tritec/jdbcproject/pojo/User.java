package ru.tritec.jdbcproject.pojo;

public class User {
	
	private int id, rate;
	private String name, nik,sex;
	
	public User(int id, int rate, String name, String nik, String sex) {
		super();
		this.id = id;
		this.rate = rate;
		this.name = name;
		this.nik = nik;
		this.sex = sex;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", rate=" + rate + ", name=" + name + ", nik=" + nik + ", sex=" + sex + "]";
	}

}
