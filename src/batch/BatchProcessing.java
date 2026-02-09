package batch;
import java.sql.*;
import java.util.Scanner;
public class BatchProcessing {
  public static void main(String[]agrs) throws Exception{
	  String url="jdbc:mysql://localhost:3306/MyDB";
	  String username="root";
	  String password="your_Password";
	  Class.forName("com.mysql.cj.jdbc.Driver");
	  Connection con=DriverManager.getConnection(url,username,password);
	  String q="insert into students(id,name,marks) values(?,?,?)";
	  PreparedStatement ps=con.prepareStatement(q);
	  Scanner sc=new Scanner(System.in);
	  while(true) {
		  System.out.print("Enter id : ");
		  int id=sc.nextInt();
		  System.out.print("Enter Name : ");
		  String name=sc.next();
		  System.out.print("Enter marks : ");
		  int marks=sc.nextInt();
		  ps.setInt(1,id);
		  ps.setString(2,name);
		  ps.setInt(3, marks);
		  ps.addBatch();
		  System.out.print("Do you want to insert more data ? yes/no ?");
		  String choice=sc.next();
		  if(choice.toUpperCase().equals("NO"))break;
	  }
	  ps.executeBatch();
	  System.out.print("Data added successfully");
	  q="select *from students";
	  Statement st=con.prepareStatement(q);
	  ResultSet rs=st.executeQuery(q);
	  ResultSetMetaData meta=rs.getMetaData();
	  int n=meta.getColumnCount();
	  while(rs.next()) {
		  for(int i=1;i<=n;i++) {
			  System.out.print(rs.getString(i)+" ");
		  }
		  System.out.println();
	  }
	  con.close();
	  ps.close();
	  rs.close();
  }
}
