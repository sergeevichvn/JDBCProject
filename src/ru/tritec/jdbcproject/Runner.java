package ru.tritec.jdbcproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ru.tritec.jdbcproject.pojo.User;

public class Runner {
	private static final String NAME_DB = "db.db";
	private static final String URL = "http://tst.ru/account/info?id=";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {

		/*for (int i = 603; i < 43415; i++){
			User user = getUser(URL + i, i);
			if(user.getUser_class() != null)
				addUserInBD(user);
			//Thread.sleep(3000);
		}*/
		
		List<User> users = getAllUsers();
		
		for(User u: users)
			if(u.getUser_class().contains("Ìîäåðàòîð"))
				System.out.println("Login: " + u.getNik() + "; User Class: " + u.getUser_class());

	}

	private static User getUser(String URL, int id) throws IOException {
		System.out.println("Connect to " + URL);
		Document doc = Jsoup.connect(URL).get();
		Elements elements = doc.getAllElements();
		User user = new User();
		
		if (!doc.hasClass("httperror-code")) {
			user.setId_user(id);
			
			elements = doc.getElementsByClass("UserClassLine");
			
			for(Element e: elements)
				user.setUser_class(e.text());
			
			elements = doc.getElementsByTag("span");
			
			for(Element e: elements)
				if(e.hasAttr("title"))
					user.setNik(e.text());
			
			elements = doc.getElementsByClass("surrounded");
			
			for(Element e: elements){
				String text = e.text();
				if(text.contains("Ìóæñêîé")){
					user.setSex("Ìóæñêîé");
					break;
				}else if (text.contains("Æåíñêèé")){
					user.setSex("Æåíñêèé");
					break;
				}else 
					user.setSex("unknown");
			}
		}

		return user;
	}

	private static void addUserInBD(User user) throws SQLException, ClassNotFoundException{
		System.out.println("Äîáàâëÿåì â ÁÄ");
		Class.forName("org.sqlite.JDBC"); 
		Connection con = null;
		con = DriverManager.getConnection("jdbc:sqlite:"+NAME_DB);
		Statement stat = con.createStatement();
		String Query = "INSERT INTO user (id_user, nik, sex, user_class) VALUES("+user.getId_user()+",\""+user.getNik()+"\",\""+user.getSex()+"\",\""+user.getUser_class().replace("\"", "")+"\");";
		System.out.println(Query);
		stat.execute(Query);
	}

	private static List<User> getAllUsers() throws ClassNotFoundException, SQLException{
		List<User> allUsers = new ArrayList<>();
		String Query = "SELECT * FROM user";
		
		Class.forName("org.sqlite.JDBC"); 
		Connection con = null;
		con = DriverManager.getConnection("jdbc:sqlite:"+NAME_DB);
		Statement stat = con.createStatement();
		ResultSet rs = stat.executeQuery(Query);
		
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setId_user(rs.getInt("id_user"));
			user.setNik(rs.getString("nik"));
			user.setSex(rs.getString("sex"));
			user.setUser_class(rs.getString("user_class"));
			allUsers.add(user);
		}
		
		return allUsers;
	}
}
