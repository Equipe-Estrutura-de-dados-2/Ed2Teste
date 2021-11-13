package br.unicap.ed2.pv;

import br.unicap.ed2.base.No;

public class NoPV extends No {

    public enum Color {
    	PRETO, VERMELHO;
    }
    
    private Color cor;

    public NoPV(int chave) {
        super(chave);
        this.cor = Color.VERMELHO;
    }
    
    public NoPV getNoEsquerda() {
    	return this.getEsquerda();
    }
    
    public NoPV getNoDireita() {
    	return this.getDireita();
    }
    
    public NoPV getNoPai() {
    	return this.getPai();
    }
    

    public Color getCor() {
        return this.cor;
    }
    
    public void setCor(Color cor) {
    	this.cor = cor;
    }
    
}
