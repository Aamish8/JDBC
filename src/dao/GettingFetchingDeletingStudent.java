package dao;
import java.sql.*;
import java.util.Scanner;
import model.Student;
import util.DBUtil;
class Dao{
	public  Student getStudent(int n) throws Exception {
		Connection con=DBUtil.getConnection();
		String q="select * from students where id=?";
		PreparedStatement ps=con.prepareStatement(q);
		ps.setInt(1, n);
		ResultSet set=ps.executeQuery();
		set.next();
		String name=set.getString(2);
		int marks=set.getInt(3);
		Student s=new Student();
		s.setId(n);
		s.setName(name);
		s.setMarks(marks);
		return s;
		
	}
	public void setStudent(Student s) throws Exception {
		Connection con=DBUtil.getConnection();
		String q="insert into students values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(q);
		ps.setInt(1, s.getId());
	    ps.setString(2,s.getName());
	    ps.setInt(3,s.getMarks());
	    ps.executeUpdate();
	}
	public void removeStudent(int id) throws Exception {
	     Connection con=DBUtil.getConnection();
	     String q="delete from students where id=?";
	     PreparedStatement ps=con.prepareStatement(q);
	     ps.setInt(1, id);
	     ps.executeUpdate();
	}
	public static void updateStudent(int id,int Marks) throws Exception {
		Connection con=DBUtil.getConnection();
		String q="update students set marks=? where id=?";
		PreparedStatement ps=con.prepareStatement(q);
		ps.setInt(1,Marks);
		ps.setInt(2,id);
		ps.executeUpdate();
	}
   public void batchPro()throws Exception {
	   Connection con=DBUtil.getConnection();
       String q="insert into students values(?,?,?)";
	   PreparedStatement ps=con.prepareStatement(q);
	   Scanner sc=new Scanner(System.in);
	   while(true) {
		   System.out.print("Enter id : ");
		   int id=sc.nextInt();
		   System.out.print("Enter name : ");
           String name=sc.next();
		   System.out.print("Enter marks : ");
           int marks=sc.nextInt();
           System.out.println("Do you want to add more data ? ");
           String s=sc.next();
           ps.setInt(1, id);
           ps.setString(2,name);
           ps.setInt(3,marks);
           ps.addBatch();
           if(s.toUpperCase().equals("NO"))break;
	   }
	   ps.executeBatch();
	   System.out.print("Data added successfuly");
   }
}
public class GettingFetchingDeletingStudent {
   public static void main(String[]args) throws Exception {
	   Dao dao=new Dao();
//	   System.out.print(s.id+" "+s.name+" "+s.marks);
//   Student stu=new Student();
  // stu.setId(2);
//	   stu.setName("Aamish");
//	   stu.setMarks(55);
     //  dao.setStudent(stu);
    //   dao.updateStudent(2,90);
	  //  Student s=dao.getStudent(2);
      // System.out.print(s.id+","+s.name+","+s.marks);
	   //dao.removeStudent(1);
	   //Bach Processing
	   dao.batchPro();
   }
}
