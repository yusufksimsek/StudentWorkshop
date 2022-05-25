package _27AralikPazartesi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.cj.x.protobuf.MysqlxExpr.Object;

public class DbConnection {
	
	public Connection getConnection() throws SQLException	{
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/uclutable","root","Yusuf2001");
		
		return con;
	}
	
	public String[] getDepts() throws SQLException{
		Statement st = getConnection().createStatement();
		ResultSet res = st.executeQuery("select department from department");
		String[] depts = new String[4];
		int index = 0;
		//res.last()
		//res.getRow()
		while(res.next()){
			depts[index] = res.getString(1);
			index++;
		}
		return depts;
	}
	
	public void saveStu(Student stu) throws SQLException	{
		String query ="insert into student5 (name,surname,department) values (?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, stu.name);
		ps.setString(2, stu.surname);
		ps.setString(3, stu.department);
		ps.executeUpdate();
	}
	
	public ResultSet getList() throws SQLException	{
		Statement st = getConnection().createStatement();
		ResultSet res = st.executeQuery("select * from student5");
		return res;
	}
	
	public void deleteStu(Object idstudent)	throws SQLException{
		
		int id = Integer.parseInt(idstudent.toString());
		Statement st = getConnection().createStatement();
		st.executeUpdate("delete from student5 where idstudent = '"+id+"'");
		
	}
	
	public void updateStu(Student stu)	throws SQLException{
		String query = "update student5 set name = ?, surname = ?, department = ? where idstudent = ?";
		
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, stu.name);
		ps.setString(2, stu.surname);
		ps.setString(3,stu.department);
		ps.setInt(4, stu.idstudent);
		ps.executeUpdate();
	}
	
}
