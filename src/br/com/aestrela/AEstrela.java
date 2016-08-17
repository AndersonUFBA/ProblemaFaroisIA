package br.com.aestrela;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class AEstrela {
	
	static Table<Node,Node,Integer> distancia = HashBasedTable.create();
	static Map<Node, Node> caminho = new HashMap<Node, Node>();
	static Node[] ruas = new Node[30];
	
	public static int Search_name(String nomeRua) throws Exception{
		for (int i=0; i<20; i++){
			if (nomeRua.equals(ruas[i].getNome())){
				return i;
			}
		}
		throw new Exception("Rua inexistente.");
	}

	public static void addAresta(String cid1, String cid2, int dist) throws Exception{
		int cod1 = Search_name(cid1);
		int cod2 = Search_name(cid2);
		
		ruas[cod1].AddVizinhos(ruas[cod2]);
		ruas[cod2].AddVizinhos(ruas[cod1]);
		distancia.put(ruas[cod1], ruas[cod2], dist);
		distancia.put(ruas[cod2], ruas[cod1], dist);
	}
	
	
	//Este método cria as arestas entre os nós atribuindo o custo entre eles
	public static void criarArestas() throws Exception{
		
		addAresta("R1", "R2", 75);
		addAresta("R1", "R3", 118);
		addAresta("R1", "Farol1", 300);
		addAresta("R2", "R4", 71);
		addAresta("R4", "Farol1", 300);	
		addAresta("Farol1", "R5", 99);		
		addAresta("Farol1", "R6", 80);	
		addAresta("R3", "Farol2", 300);		
		addAresta("Farol2", "R7", 300);	
		addAresta("R7", "R8", 75);
		addAresta("R9", "R8", 120);		
		addAresta("R9", "R6", 146);		
		addAresta("R9", "R10", 138);
		addAresta("R10", "R6", 97);
		addAresta("R11", "R10", 101);
		addAresta("R11", "R5", 211);	
		addAresta("R11", "Farol3", 300);		
		addAresta("R11", "R12", 85);		
		addAresta("Farol4", "R12", 300);	
		addAresta("Farol4", "R13", 300);		
		addAresta("R12", "R14", 142);
		addAresta("R15", "R14", 92);
		addAresta("R15", "R16", 87);
		addAresta("Farol3", "R13", 87);
	}
	
	// Este método estabelece a função de avaliacao de cada nó 
	public static void setarHeuristica() throws Exception{
		
		for (int i=0; i<30; i++){
			ruas[i] = new Node();
		}
		
		ruas[0].setNome("R1");
		ruas[0].setDist(366);	
		
		ruas[1].setNome("R11");
		ruas[1].setDist(1);
		
		ruas[2].setNome("R9");
		ruas[2].setDist(160);
		
		ruas[3].setNome("R8");
		ruas[3].setDist(242);
		
		ruas[4].setNome("R13");
		ruas[4].setDist(161);
		
		ruas[5].setNome("R5");
		ruas[5].setDist(176);
		
		ruas[6].setNome("Farol3");
		ruas[6].setDist(77);
		
		ruas[7].setNome("Farol4");
		ruas[7].setDist(151);
		
		ruas[8].setNome("R15");
		ruas[8].setDist(226);
		
		ruas[9].setNome("Farol2");
		ruas[9].setDist(244);
		
		ruas[10].setNome("R7");
		ruas[10].setDist(241);
		
		ruas[11].setNome("R16");
		ruas[11].setDist(234);
		
		ruas[12].setNome("R4");
		ruas[12].setDist(380);
		
		ruas[13].setNome("R10");
		ruas[13].setDist(10);
		
		ruas[14].setNome("R6");
		ruas[14].setDist(193);
		
		ruas[15].setNome("Farol1");
		ruas[15].setDist(253);
		
		ruas[16].setNome("R3");
		ruas[16].setDist(329);
		
		ruas[17].setNome("R12");
		ruas[17].setDist(80);
		
		ruas[18].setNome("R14");
		ruas[18].setDist(199);
		
		ruas[19].setNome("R2");
		ruas[19].setDist(374);
		
		
	}

	public static void disp_borda(SortedSet<Celula> borda){
		System.out.println("------------ Borda ------------"); 
		for (Celula e : borda) {
			e.print();
		}
		System.out.println("-------------------------------\n\n\n");
	}
	
	public static boolean Reconstruct_path(Node filho){
		while (filho != null){
			System.out.print(filho.getNome() + " <-- ");
			filho = caminho.get(filho);
		}
		System.out.println();
		return true;
	}
	
	public static boolean busca_Estrela(String cid_origem, String cid_destino) throws Exception{
		
		SortedSet<Celula> borda = new TreeSet<Celula>();
		Node destino = ruas[Search_name(cid_destino)];
		
		Celula estado_atual = new Celula();
		estado_atual.setEstado(ruas[Search_name(cid_origem)]);
		
		int funcao_de_borda=0;
		
		borda.add(estado_atual);
		
		while(!borda.isEmpty()){
			
			disp_borda(borda);
			
			estado_atual=borda.first();
			borda.remove(estado_atual);
			
			System.out.println("\nRua com menor função de função de avalaliação e custo: "+ estado_atual.getEstado().getNome());
			
			if (estado_atual.getEstado() == destino) return(Reconstruct_path(estado_atual.getEstado()));
			
			estado_atual.getEstado().setExplored(true);
			
			for (Node no : estado_atual.getEstado().vizinhos){
				System.out.println(no.getNome());
				
				
				funcao_de_borda = estado_atual.getCusto()+distancia.get(estado_atual.getEstado(), no);
				
				if (!no.isExplored() && !no.esta_na_borda(borda)){
					Celula tmp = new Celula();
					tmp.setEstado(no);
					
					tmp.setPai(estado_atual.getEstado());
					
					tmp.setCusto(estado_atual.getCusto()+distancia.get(estado_atual.getEstado(), no));
					
					borda.add(tmp);
					caminho.put(no, estado_atual.getEstado());
				}
				else{
					for (Celula e : borda) {
				        if ( e.getEstado() == no && e.getCusto() > funcao_de_borda ) {
				        	e.setPai(estado_atual.getEstado());
				        	e.setCusto(funcao_de_borda);
				        	break;
				        }
					}
				}
				
				
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		setarHeuristica();
		criarArestas();
		busca_Estrela("R1","R13");
	}
}
