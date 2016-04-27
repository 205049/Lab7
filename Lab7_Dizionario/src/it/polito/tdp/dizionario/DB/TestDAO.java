package it.polito.tdp.dizionario.DB;

import java.util.LinkedList;
import java.util.List;

public class TestDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DizionarioDAO dao =  new DizionarioDAO();
		List<String> res = new LinkedList<String>(dao.cercaParola(3));
		
		for(String p: res){
			System.out.println(p);
		}
	}

}
