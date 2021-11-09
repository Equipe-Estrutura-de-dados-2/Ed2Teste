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
        calcularBalanceamento(raiz);
        n = verificaBalanceamento(raiz);
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
    
    
    //=============== Metodos para verificar rotação ===============//
    
    public NoAVL verificaBalanceamento(NoAVL noAtual) {
    	
    	if(noAtual.getBalanceamento() > 1 || noAtual.getBalanceamento() < -1) {
    		
    		// Desbalanceamento a Direita
    		if(noAtual.getBalanceamento() > 1) {
    			NoAVL direita = noAtual.getDireita();
    			if(noAtual.getBalanceamento() * direita.getBalanceamento() > 0) {
    				System.out.println("Rotação simples a Direita!");
    				return rotacaoSimplesDireita(noAtual);
    			}
    			else {
    				System.out.println("Rotação dupla a Direita!");
    				return rotacaoDuplaDireita(noAtual);
    			}
    		}
    		// Desbalanceamento a Esquerda
    		else {
    			NoAVL esquerda = noAtual.getEsquerda();
    			if(noAtual.getBalanceamento() * esquerda.getBalanceamento() > 0) {
    				System.out.println("Rotação simples a Esquerda!");
    				return rotacaoSimplesEsquerda(noAtual);
    			}
    			else {
    				System.out.println("Rotação dupla a Esquerda!");
    				return rotacaoDuplaEsquerda(noAtual);
    			}
    		}
    	}
    	calcularBalanceamento(noAtual);
    	if(noAtual.getEsquerda() != null) {
    		noAtual.setEsquerda(verificaBalanceamento(noAtual.getEsquerda()));
    	}
    	if(noAtual.getDireita() != null) {
    		noAtual.setDireita(verificaBalanceamento(noAtual.getDireita()));
    	}
    	return noAtual;
    }
    
    public NoAVL rotacaoSimplesEsquerda(NoAVL noAtual) {
    	return noAtual;
    }
    
    public NoAVL rotacaoDuplaEsquerda(NoAVL noAtual) {
    	return noAtual;
    }
    
    public NoAVL rotacaoSimplesDireita(NoAVL noAtual) {
    	return noAtual;
    }
    
    public NoAVL rotacaoDuplaDireita(NoAVL noAtual) {
    	return noAtual;
    }
    
    
    //============ Metodos para calculo de balanceamento ===========//
    
    public int calcularAltura(NoAVL noAtual) {
    	NoAVL esquerda = noAtual.getEsquerda();
    	NoAVL direita = noAtual.getDireita();
    	
    	// Altura da raiz
    	if(esquerda == null && direita == null) {
    		return 1;
    	}
    	
    	// Altura no a esquerda
    	else if(esquerda != null && direita == null) {
    		return 1 + calcularAltura(esquerda);
    	}
    	// Altura no a direita
    	else if(esquerda == null && direita != null) {
    		return 1 + calcularAltura(direita);
    	}
    	// Altura no em ambos os lados
    	else {
    		return 1 + Math.max(calcularAltura(esquerda), calcularAltura(direita));
    	}
    }
    
    public void calcularBalanceamento(NoAVL noAtual) {
    	
    	// Balanceamento da raiz
		if (noAtual.getEsquerda() == null && noAtual.getDireita() == null) {
			noAtual.setBalanceamento(0);
		} else if (noAtual.getEsquerda() == null && noAtual.getDireita() != null) {
			noAtual.setBalanceamento(calcularAltura(noAtual.getDireita()) - 0);
		} else if (noAtual.getEsquerda() != null && noAtual.getDireita() == null) {
			noAtual.setBalanceamento(0 - calcularAltura(noAtual.getEsquerda()));
		} else {
			noAtual.setBalanceamento(calcularAltura(noAtual.getDireita()) - calcularAltura(noAtual.getEsquerda()));
		}
    	
		// Balanceamento no a direita 
		if (noAtual.getDireita() != null) {
			calcularBalanceamento(noAtual.getDireita());
		}
		
		// Balanceamento no a esquerda 
		if (noAtual.getEsquerda() != null) {
			calcularBalanceamento(noAtual.getEsquerda());
		}
    	
    }
    
    
    
    
    
    
    // =======================   Main para testes  ============================== //
    
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