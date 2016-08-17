package br.com.aestrela;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class Node{
	//estrutura básica de cada nó
	List <Node> vizinhos = new ArrayList<Node>();
	
	boolean explored;
	String nome;
	int dist;

	
	
	public int getDist() {
		return dist;
	}
	public void setDist(int dist) {
		this.dist = dist;
	}
	public List<Node> getVizinhos() {
		return vizinhos;
	}
	public void setVizinhos(List<Node> vizinhos) {
		this.vizinhos = vizinhos;
	}
	
	public void AddVizinhos(Node vizinho) {
		this.vizinhos.add(vizinho);
	}
	
	public boolean isExplored() {
		return explored;
	}
	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean esta_na_borda(SortedSet<Celula> borda){
		
		for (Celula e : borda) {
			if (e.getEstado().getNome().equals(this.getNome())) {
				return true;
			}
		}
		return false;
	}
	
}
