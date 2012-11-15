package database;

import Data.Assignment;
import Data.TimeLog;
import Data.User;
import java.io.*;
import java.sql.*;

public class SQLConnection {

  Connection c;   SQLConnection MyCon;
  Statement stmt; String SQL;
  DatabaseMetaData md; ResultSet rs;
  int found=0; String msg="";
    //private String status;
    String passSQL, username,urlLocal,port;
  public SQLConnection(){
      try {
            BufferedReader br = new BufferedReader(new FileReader("C:/windows/journal.txt"));
            String buffer, result3 = null , result2 = null, result1 = null , result4 = null;
            int i=0;
                while ((buffer = br.readLine()) != null) {
                    i++;
                    if(i == 1){result1 = buffer;}
                    else if(i == 2){result2 = buffer;}
                    else if(i == 3){result3 = buffer;}
                    else if(i == 4){result4 = buffer;}
                }
                urlLocal = result1;
                username = result2;
                passSQL = result3;
                port = result4;
        }
        catch(IOException e){
            System.out.println("I could not read the file");
        }
  }
  public Connection startProgram(){
            String name="journal";
            try {
                    MyCon = new SQLConnection();
                    c = MyCon.getConnection("");
                    stmt = c.createStatement();
                    SQL = "show databases;";
                    rs = stmt.executeQuery(SQL);
                    while(rs.next()){
                        if(rs.getString(1).toUpperCase().equals(name)){
                            found = 1;
                            break;
                        }
                    }
                    if(found == 0){
                        SQL = "CREATE SCHEMA `journal` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;";
                        stmt.executeUpdate(SQL);
                        SQL = "use journal;";
                        stmt.executeUpdate(SQL);
                        SQL = "create table profile(username varchar(100) ,password varchar(100) , firstname varchar(100),"
                                + "lastname varchar(100), studentID varchar[100] , birdthday varchar[30] , telephone varchar(20),"
                                + "email varchar(100),facebook varchar(100),status varchar(7));";
                        stmt.executeUpdate(SQL);
                        
                        Class.forName("com.mysql.jdbc.Driver");
                        c = DriverManager.getConnection("jdbc:mysql://localhost/"+name,"username", passSQL);
                        
                    }
                    else{
                        Class.forName("com.mysql.jdbc.Driver");
                        c = DriverManager.getConnection("jdbc:mysql://localhost/"+name,"username", passSQL);
                    }
                    
		} catch (SQLException | ClassNotFoundException e) {
			//System.out.println(e);
		}
            return c;
        }
  
   public void SQLCreateTableForFirst() {
    String tname= "profile";
    boolean founds=false;
    
    try {
      MyCon = new SQLConnection();
      c = MyCon.getConnection("journal");
      md = c.getMetaData();
      rs = md.getTables(null, null, "%", null);
      while (rs.next()) {
        if (rs.getString(3).equals(tname)) {
          founds = true;
          break;
        }
      }
      stmt = c.createStatement();
      if (!founds) {
        if (tname.equals("profile"))
           SQL = "create table profile(username varchar(100) ,password varchar(100) , firstname varchar(100),"
                                + "lastname varchar(100), studentID varchar(100) , birdthday varchar(30) , telephone varchar(20),"
                                + "email varchar(100),facebook varchar(100),status varchar(7));";
        stmt.executeUpdate(SQL);
        
      }
      else {

      }
      SQL = "create table usertemp(id int, useUsername varchar(100));";
                        stmt.executeUpdate(SQL);
                        SQL = "insert into usertemp(id,useUsername) values(1,'temp');";
                        stmt.executeUpdate(SQL);
                        SQL = "insert into usertemp(id,useUsername) values(2,'temp2');";
                        stmt.executeUpdate(SQL);
    }
    catch (SQLException ex) {
      System.out.println(ex);
    }
  }
   
  public void SQLcreateTableForUser(String name){
      Connection theConn = null; String txt;

    try {
      MyCon = new SQLConnection();
      theConn = MyCon.getConnection("journal");
      stmt = theConn.createStatement();
      txt = "create table "+name+"_TimeLog(Dates Date,start Time,End Time,interrupt double,RealTime double,Activities varchar(100),note varchar(100),Finish varchar(10),Number int); ";
      stmt.executeUpdate(txt);
      txt = "create table "+name+"_Overview(Date Date,HomeWork varchar(100),Study varchar (100),Activities varchar(100),Reading varchar(100),Other varchar(100)); ";
      stmt.executeUpdate(txt); 
      txt = "create table "+name+"_Assignment(DueDate Date,Course varchar(100), Comment varchar(1000), DeadLine Date); ";
      stmt.executeUpdate(txt); 
      //SQLSelectAllTable all = new SQLSelectAllTable();
    }

    catch (SQLException ex) {
      System.out.println(ex);
    }

    finally {
      try {
        if (theConn != null) theConn.close();
      }
      catch (Exception e) {
      }
    }
  }
  
  public void SQLInsertProfile (User user) {
    Connection theConn = null;
    try {
      MyCon = new SQLConnection();
      theConn = MyCon.getConnection("journal");
      stmt = theConn.createStatement();
      SQL = "insert into profile(username,password,firstname,lastname,studentID,birdthday,telephone,email,facebook,status) values('"
              +user.getUsername()+"','"
              +user.getPassword()+"','"
              +user.getFirstname()+"','"
              +user.getLastname()+"','"
              +user.getStudentID()+"','"
              +user.getBirthDay()+"','"
              +user.getTelephone()+"','"
              +user.getEmail()+"','"
              +user.getFacebook()+"','"
              +user.getStatus()+"');";  
      stmt.executeUpdate(SQL);      
      //SQLSelectAllTable all = new SQLSelectAllTable();
    }
    catch (SQLException ex) {
      System.out.println(ex);
    }
    finally {
      try {
        if (theConn != null) theConn.close();
      }
      catch (Exception e) {
      }
    }
  }
  
  public void SQLInsertTableAddignment(Assignment assignment,String name){
    Connection theConn = null; String txt;

    try {
      MyCon = new SQLConnection();
      theConn = MyCon.getConnection("journal");
      stmt = theConn.createStatement();
      txt = "insert into "+name+"_Assignment(DueDate,Course , Comment, DeadLine) "
              + "value('"+assignment.getDueDate().getYear()+"-"+assignment.getDueDate().getMonth()+"-"+assignment.getDueDate().getDate()
              +"','"+assignment.getCourse()
              +"','"+assignment.getComment()
              +"','"+assignment.getDeadLine().getYear()+"-"+assignment.getDeadLine().getMonth()+"-"+assignment.getDeadLine().getDate()
              +"'); ";
      stmt.executeUpdate(txt);      
    }

    catch (SQLException ex) {
      System.out.println(ex);
    }

    finally {
      try {
        if (theConn != null) theConn.close();
      }
      catch (Exception e) {
      }
    }  
  }
  
  public void SQLInsertTimeLog(TimeLog timelog,String name){
      Connection theConn = null; String txt;

    try {
      MyCon = new SQLConnection();
      theConn = MyCon.getConnection("journal");
      stmt = theConn.createStatement();
      txt = "insert into "+name+"_TimeLog(Dates,Start, End,"
              + " Interrupt,RealTime,Activities,"
              + "Note, Finish, Number) value('"
              + timelog.getDate().getYear()+"-" + timelog.getDate().getMonth()+ "-" + timelog.getDate().getDate()+"','"
              + timelog.getStartTime().getHours()+":" + timelog.getStartTime().getMinutes()+ ":00','"
              + timelog.getEndTime().getHours()+":" + timelog.getEndTime().getMinutes()+":00',"
              + timelog.getInterrupt()+","
              + timelog.getRealTime()+",'"
              + timelog.getActivities()+"','"
              + timelog.getNote()+"','"
              + timelog.getFinish()+"',"
              + timelog.getNumber()+"); ";
      System.out.println(txt);
      stmt.executeUpdate(txt);      
    }

    catch (SQLException ex) {
      System.out.println(ex);
    }

    finally {
      try {
        if (theConn != null) theConn.close();
      }
      catch (Exception e) {
      }
    }
  }
  
  public void SQLUpdateProfile(User user,String name){
      Connection theConn = null;
    try {
      MyCon = new SQLConnection();
      theConn = MyCon.getConnection("journal");
      stmt = theConn.createStatement();
      SQL = "update profile set "
              + "firstname='"+user.getFirstname()+"',"
              + "lastname='"+user.getLastname()+"',"
              + "birdthday='"+user.getBirthDay()+"',"
              + "telephone='"+ user.getTelephone()+"',"
              + "email='"+ user.getEmail()+"',"
              + "facebook='"+ user.getFacebook()+"' "
              + "where username = '"+name+"';";
      System.out.println(SQL);
      stmt.executeUpdate(SQL);      
      //SQLSelectAllTable all = new SQLSelectAllTable();
    }
    catch (SQLException ex) {
      System.out.println(ex);
    }
    finally {
      try {
        if (theConn != null) theConn.close();
      }
      catch (Exception e) {
      }
    }
  }
 
  public void useUsername(String name) throws SQLException{
                MyCon = new SQLConnection();
                c = MyCon.getConnection("journal");
                stmt = c.createStatement();
                SQL = "update usertemp set useUsername='"+ name + "' where id=1";
                stmt.executeUpdate(SQL);
  }
  
  public String getUsername() throws SQLException{
        String sql="";
        MyCon = new SQLConnection();
                c = MyCon.getConnection("journal");
                stmt = c.createStatement();
                SQL = "select useUsername from usertemp where id=1;";
                    rs = stmt.executeQuery(SQL);
                    while(rs.next()){
                        sql = rs.getString(1);
                    }
       return sql;
  }
  
    public String getUsernameForSeach() throws SQLException{
        String sql="";
        MyCon = new SQLConnection();
                c = MyCon.getConnection("journal");
                stmt = c.createStatement();
                SQL = "select useUsername from usertemp where id=2;";
                    rs = stmt.executeQuery(SQL);
                    while(rs.next()){
                        sql = rs.getString(1);
                    }
       return sql;
  }
    
      public void useUsernameForSeach(String name) throws SQLException{
                MyCon = new SQLConnection();
                c = MyCon.getConnection("journal");
                stmt = c.createStatement();
                SQL = "update usertemp set useUsername='"+ name + "' where id=2";
                stmt.executeUpdate(SQL);
  }
    
    public Connection getConnection(String fn) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url ="jdbc:mysql://"+urlLocal+":"+port+"/"+fn+"?characterEncoding=utf8";
      c = DriverManager.getConnection(url,username, passSQL);
    }
    catch (ClassNotFoundException | SQLException e) {
      System.out.println(e);
    }
    return c;
  }
    
    public boolean testConnection(String urlLo,String username,String pass,String port) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url ="jdbc:mysql://"+urlLo+":"+port+"/"+"?characterEncoding=utf8";
      c = DriverManager.getConnection(url,username, pass);
      return true;
    }
    catch (ClassNotFoundException | SQLException e) {
      System.out.println(e);
      return false;
    }
  }

}