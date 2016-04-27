package it.polito.tdp.dizionario.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DizionarioDAO {
	
	public List<String> cercaParola(int lenght){
		
		List<String> parole = new LinkedList<String>();
		
		Connection conn = ConnectDB.getConnection();
		
		String sql = "select nome from parola where length(nome) = ?";
		
		PreparedStatement st;
		try{
			st = conn.prepareStatement(sql);
			st.setInt(1, lenght);
			
			ResultSet res = st.executeQuery();
			
			while(res.next()){
				String nome = res.getString("nome");
				parole.add(nome);			
			}
			
			res.close();
			conn.close();
			
			return parole;
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}


}
