package pack;



import java.sql.*;
public class Connexion{
private static Connection conn;
static {
try { 
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	conn = DriverManager.getConnection("jdbc:sqlserver:LMKD\\SQLEXPRESS ; database=Gestion_des_rendez-vous ;");
    
    }
catch(Exception e){ 
	System.out.println("Erreur lors du chargement de Driver:"+e);
	}
 }
 public static Connection getConnection(){
	 return conn;
	 }
 }
