package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.DB.DizionarioDAO;

public class DizionarioModel {

	private DizionarioDAO dao;
	private SimpleGraph<String, DefaultEdge> grafo;
	
	List<String> dizionario;
	List<String> connessi;
	
	public DizionarioModel() {
		super();
		dao = new DizionarioDAO();
		grafo = new SimpleGraph<>(DefaultEdge.class);
		dizionario = new LinkedList<String>();
		connessi = new ArrayList<String>();
	}

	public List<String> cercaParola(int lenght){
		dizionario.addAll(dao.cercaParola(lenght));
		return dizionario;
	}
	
	public void cerca(int lenght){
		dizionario.addAll(dao.cercaParola(lenght));
	}
	
	public List<String> getDizionario() {
		return dizionario;
	}

	public void generaGrafo(int lenght){
		
		this.cercaParola(lenght);
		
		for(String s: dizionario){
			grafo.addVertex(s);
		}
		
		for(String t1: dizionario){
			for(String t2: this.paroleSimili(t1)){
				grafo.addEdge(t1, t2);
				//System.out.println(t1 + " - " + t2);
			}
		}
	}
	
	public SimpleGraph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public List<String> paroleSimili(String s){
		
		List<String> lista = new LinkedList<String>();
		List<String> listaDAO = new LinkedList<String>(dizionario);
		
		int flag = 0;
		
		//for(String tt: dizionario){
			//flag = 0;
			char[] array1 = s.toCharArray();
			for(String st: listaDAO){
				flag = 0;
				char[] array2 = st.toCharArray();
				for(int i=0; i<array1.length; i++){
					if(array1[i] != array2[i])
						flag ++;	
				}
				
				if(flag == 1)
					lista.add(st);
			}
		//}
		
		return lista;
	}
	
	public void connessi(String p){
		if(connessi.contains(p))
			return;
		
		connessi.add(p);
		for(String t: Graphs.neighborListOf(grafo, p))
			connessi(t);
		
	}

	public List<String> getConnessi() {
		return connessi;
	}
	
	
}
