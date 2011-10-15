/*
 * Distributed under Beer Ware License.
 */
package eventdb;
import java.sql.*;

/**
 * @author NIKHIL
 */
public class EventDB {
    
    private Statement statement;
    private Connection conn = null;
    
    EventDB()
    {
        try
        {            
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String serverName = "localhost";
            String portNumber = "1521";
            String sid = "xe";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
            String username = "system";
            String password = "revolution";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established temprorily");
        }        
      catch(Exception e)
      {
            System.out.println(e);
      }
   
     try
     {   
      statement = conn.createStatement();  
     }
     catch(SQLException msg)
     {
       System.out.println(msg);    
     }
     
    try
    {        
            String table_candidate = 
                    "CREATE TABLE candidate("
                    + "reg_id varchar(7) PRIMARY KEY,"
                    + "name   varchar(20),"
                    + "sex    varchar(1) ,"
                    + "email  varchar(20),"
                    + "phno   varchar(10),"
                    + "collegeid varchar(7) REFERENCES college ( id ),"
                    + "account number(5),"
                    + "hostel varchar(1))";
            
            String table_college = 
                    " CREATE TABLE college( id varchar(7) PRIMARY KEY , "
                    + " name   varchar(20) , "
                    + " points number(5) ) ";
                    
            
            String table_event = 
                    "CREATE TABLE event("
                    + "id varchar(7) PRIMARY KEY,"
                    + "name   varchar(20),"
                    + "no_participants number(5) )";
             
            String table_relation = 
                    "CREATE TABLE participants("
                    + "partcipant_id varchar(7) REFERENCES candidate ( reg_id),"
                    + "event_id varchar(7) REFERENCES event (id) )" ;
           
            
            String table_groups = 
                    "CREATE TABLE groups_events("
                    + "id varchar(7) PRIMARY KEY,"
                    + "event_id varchar(7) REFERENCES event (id), "
                    + "college_id varchar(7) REFERENCES college (id),"
                    + "leader_id varchar(7) REFERENCES candidate ( reg_id))" ;
                        
            
            String table_results = 
                    "CREATE TABLE results("
                    + "eventid varchar(7) PRIMARY KEY REFERENCES event (id),"
                    + "first varchar(7) REFERENCES candidate ( reg_id),"
                    + "second varchar(7) REFERENCES candidate ( reg_id),"
                    + "third varchar(7) REFERENCES candidate ( reg_id),"
                    + "firstpoint number(5), "
                    + "secondpoint number(5), "
                    + "thirdpoint number(5)) ";
            
           
    statement.executeQuery(table_results);
    statement.executeQuery(table_college);
    statement.executeQuery(table_candidate);
    statement.executeQuery(table_event);
    statement.executeQuery(table_relation);
    statement.executeQuery(table_groups);
        
    System.out.println("All important tables created "); 
 }
 catch(SQLException s)
 {
    System.out.println(s);
 }
    
 try
 {  
      String seq_regseq =
             "CREATE SEQUENCE regseq START WITH 1000 ";
      String seq_eventseq =        
             "CREATE SEQUENCE eventseq START WITH 1000";
      String seq_groupseq =  
             "CREATE SEQUENCE groupseq START WITH 1000";
      String seq_collegeseq =
             "CREATE SEQUENCE collegeseq START WITH 1000";     
     
   statement.executeQuery(seq_regseq);
   statement.executeQuery(seq_eventseq);  
   statement.executeQuery(seq_groupseq);  
   statement.executeQuery(seq_collegeseq);  
 }
 catch(SQLException s)
 {
   System.out.println(s);  
 }
    
 try
 {
    conn.commit();
    conn.close();
    System.out.println("Temprory database connection closed");
 }
 catch(SQLException closeexception)
 {
    System.out.println(closeexception);
 }    
    }
    
 Statement getStatement()
 {
      try
        {            
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String serverName = "localhost";
            String portNumber = "1521";
            String sid = "xe";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
            String username = "system";
            String password = "revolution";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established temprorily");
        }        
      catch(Exception e)
      {
            System.out.println(e);
      }
   
     try
     {   
      statement = conn.createStatement();  
     }
     catch(SQLException msg)
     {
       System.out.println(msg);    
     }     
     
     return statement; 
 }
    
 
 public ResultSet executeQuery(String query)
 {    
    ResultSet resultset = null;
      try
      {            
         Class.forName("oracle.jdbc.driver.OracleDriver");
         String serverName = "localhost";
         String portNumber = "1521";
         String sid = "xe";
         String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
         String username = "system";
         String password = "revolution";
         conn = DriverManager.getConnection(url, username, password);
         System.out.println("Database connection established temprorily for query");
      }        
      catch(Exception e)
      {
         System.out.println(e);
      }   
     try
     {   
         statement = conn.createStatement();
         resultset = statement.executeQuery(query);
     }     
     catch(SQLException msg)
     {
         System.out.println(msg);    
     }
     
       return resultset;   
     
 }
       
 
 
 public void close()
 {     
     try
     {
        conn.commit();  
        conn.close();  
        System.out.println("Temprory database connection closed after query");
     }
     catch(Exception e)
     {
       System.out.println(e);
     }
 }
    
 public static void main(String[] args) 
 {       
     EventDB m = new EventDB();
     try
     {
        ResultSet res = m.executeQuery("select 2+2 ,3+3 from dual");
        res.next();
        System.out.println(res.getString("3+3"));
        m.close();    
     }
    catch(SQLException l)
    {
       System.out.println(l);
    }
    
    }

}