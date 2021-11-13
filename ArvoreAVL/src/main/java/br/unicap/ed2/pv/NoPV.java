package br.unicap.ed2.pv;

import br.unicap.ed2.base.No;

public class NoPV extends No {

    private int cor;

    public NoPV(int chave) {
        super(chave);
        this.cor = 1;
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
    

    public int getCor() {
        return this.cor;
    }
    
    public void setCor(int cor) {
    	this.cor = cor;
    }
    
}
