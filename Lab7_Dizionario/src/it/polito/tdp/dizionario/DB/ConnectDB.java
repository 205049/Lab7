package it.polito.tdp.dizionario.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
		
	private static final String URL = "jdbc:mysql://localhost/dizionario?user=root";

	public static Connection getConnection(){
		
		try{
			Connection conn = DriverManager.getConnection(URL);
			return conn;
		} catch (SQLException e){
			e.printStackTrace();
			throw new RuntimeException("errore nella connessione", e);
		}
	}

}
