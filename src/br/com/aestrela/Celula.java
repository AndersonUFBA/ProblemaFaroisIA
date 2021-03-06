package br.com.aestrela;

public class Celula implements Comparable<Celula> {

	//classe que define o modelo de cada celula
	private int custo;
	private Node pai = new Node();
	private Node estado;
	
	
	
	
	
	
	public Celula() {
		custo=0;
	}

	public void print(){
		System.out.println("Pai: " + pai.getNome() + " -> " + estado.getNome() + "      Custo: " + custo + "  G + H: " + (custo + estado.getDist()) );
	}

	@Override
	public int compareTo(Celula outroNode) {
		if (this.custo + estado.getDist() < outroNode.custo + outroNode.getEstado().getDist()) {
            return -1;
        }
        if (this.custo + estado.getDist() > outroNode.custo + outroNode.getEstado().getDist()) {
            return 1;
        }
        return 0;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	public Node getEstado() {
		return estado;
	}

	public void setEstado(Node estado) {
		this.estado = estado;
	}

	public Node getPai() {
		return pai;
	}

	public void setPai(Node pai) {
		this.pai = pai;
	}
}
