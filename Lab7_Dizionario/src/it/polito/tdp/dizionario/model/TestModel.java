package it.polito.tdp.dizionario.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DizionarioModel mm = new DizionarioModel();
		
		//List<String> lista= new LinkedList<String>(mm.cercaParola(3));
		
		//for(String s: mm.cercaParola(3))
			//System.out.println(s);
		mm.cerca(3);
		//for(String s: mm.paroleSimili("abc"))
			//System.out.println(s);
		
		mm.generaGrafo(3);
		
		for(String s: mm.paroleSimili("abc"))
			System.out.println(s);
	}

}
