package br.unicap.ed2.avl;

import br.unicap.ed2.base.AbstractArvoreBinariaDePesquisa;
import br.unicap.ed2.base.BinaryTreePrinter;

public class ArvoreAVL extends AbstractArvoreBinariaDePesquisa<NoAVL> {

    @Override
    public NoAVL novoNode(int key) {
        //Altera
        return new NoAVL(key);
    }

    @Override
    public NoAVL inserir(int key) {
        NoAVL n = inserirNoHelper(raiz, key);
        //Altera
        return n;
    }

    @Override
    public NoAVL procurar(int key) {
        return procurarNoHelper(raiz, key);
    }

    @Override
    public void deletar(int key) {
        raiz = deletarNoHelper(raiz, key);
        //alteracao
    }
    
    public int calcularAltura(NoAVL noAtual) {
    	NoAVL esquerda = noAtual.getEsquerda();
    	NoAVL direita = noAtual.getDireita();
    	
    	if(esquerda == null && direita == null) {
    		return 1;
    	}
    	else if(esquerda != null && direita == null) {
    		return 1 + calcularAltura(esquerda);
    	}
    	else if(esquerda == null && direita != null) {
    		return 1 + calcularAltura(direita);
    	}
    	else {
    		return 1 + Math.max(calcularAltura(esquerda), calcularAltura(direita));
    	}
    }
    
    public void calcularBalanceamento(NoAVL noAtual) {
    	
    	NoAVL esquerda = noAtual.getEsquerda();
    	NoAVL direita = noAtual.getDireita();
    	int alturaEsquerda = calcularAltura(esquerda);
    	int alturaDireita = calcularAltura(direita);
    	
    	// Balanceamento da raiz
		if (esquerda == null && direita == null) {
			noAtual.setBalanceamento(0);
		} else if (esquerda == null && direita != null) {
			noAtual.setBalanceamento(alturaDireita - 0);
		} else if (esquerda != null && direita == null) {
			noAtual.setBalanceamento(0 - alturaEsquerda);
		} else {
			noAtual.setBalanceamento(alturaDireita - alturaEsquerda);
		}
    	
		// Balanceamento no a direita 
		if (direita != null) {
			calcularBalanceamento(direita);
		}
		
		// Balanceamento no a esquerda 
		if (esquerda != null) {
			calcularBalanceamento(esquerda);
		}
    	
    }
    
    
    
    
    
    
    // =============================================================== //
    
    public static void main(String[] args) {
        ArvoreAVL a = new ArvoreAVL();
        a.inserir(10);
        a.inserir(20);
        a.inserir(5);
        a.inserir(4);
        a.inserir(7);
        a.inserir(30);
        a.inserir(15);
        a.inserir(2);
        a.inserir(1);
        
        BinaryTreePrinter<NoAVL> p = new BinaryTreePrinter<NoAVL>(a);
        p.imprimir(System.out);
    }
    
    
    
    
}