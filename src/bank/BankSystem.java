package bank;
import java.sql.*;
import java.util.Scanner;

public class BankSystem {
static boolean check(int amount,int accontNo,Connection con) throws Exception {
	String q="select amount from accounts where account_num=?";
	PreparedStatement st=con.prepareStatement(q);
	st.setInt(1, accontNo);
	ResultSet set=st.executeQuery();
	set.next();
	int previousAmnt=set.getInt("amount");
	st.close();
	set.close();
	if(previousAmnt<amount)return false;
	return true;
}
	public static void main(String[] args) throws Exception{
		 String url="jdbc:mysql://localhost:3306/BankingSystem";
		  String username="root or somethig else";
		  String password="your_password";
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Scanner sc=new Scanner(System.in);
		  Connection con=DriverManager.getConnection(url,username,password);
		  con.setAutoCommit(false);
		  System.out.print("Enter account number for debit : ");
		  int debitAc=sc.nextInt();
		  System.out.print("Enter account number for credit : ");
		  int creditAc=sc.nextInt();
		  System.out.print("Enter amount : ");
		  int amnt=sc.nextInt();
		  if(!check(amnt,debitAc,con)) {
			  System.out.print("Insufficient Balance");
			  con.rollback();
			  return;
		  }
		  String q1="update accounts set amount=amount-? where account_num=?";
		  String q2="update accounts set amount=amount+? where account_num=?";
		  PreparedStatement deb=con.prepareStatement(q1);
		  PreparedStatement cre=con.prepareStatement(q2);
		  deb.setInt(1,amnt);
		  deb.setInt(2,debitAc);
		  cre.setInt(1,amnt);
		  cre.setInt(2,creditAc);
		  deb.executeUpdate();
		  cre.executeUpdate();
	      System.out.print("Transaction Succesful");
	      con.close();
	      deb.close();
	      cre.close();
	      con.commit();
	}

}
