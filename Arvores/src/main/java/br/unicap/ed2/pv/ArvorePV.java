package br.unicap.ed2.pv;

import br.unicap.ed2.avl.ArvoreAVL;
import br.unicap.ed2.avl.NoAVL;
import br.unicap.ed2.base.AbstractArvoreBinariaDePesquisa;
import br.unicap.ed2.base.BinaryTreePrinter;
import br.unicap.ed2.pv.NoPV.Color;

public class ArvorePV extends AbstractArvoreBinariaDePesquisa<NoPV> {

    @Override
    public NoPV novoNode(int key) {
        //Altera
        return new NoPV(key);
    }

    @Override
    public NoPV inserir(int key) {
        inserirNoHelper(raiz, key);
        raiz = verificaCasoDeRotacao(raiz);
        recoloreArvore(raiz);
        return raiz;
    }

    @Override
    public NoPV procurar(int key) {
        return procurarNoHelper(raiz, key);
    }

    @Override
    public void deletar(int key) {
        raiz = deletarNoHelper(raiz, key);
        //alteracao
    }
    
    
    
    
    
    //=====================================================//
    
    public NoPV verificaCasoDeRotacao(NoPV noAtual) {
    	
    	//verifica se é raiz
    	if (noAtual.getNoPai() == null) {
    		//verifica se nao tem filhos
    		if(!temFilhos(noAtual)) {
    			return noAtual;
    		}
    		else {
    			if (noAtual.getDireita() != null) {
    				noAtual.setDireita(verificaCasoDeRotacao(noAtual.getDireita()));
    			}

    			if (noAtual.getEsquerda() != null) {
    				noAtual.setEsquerda(verificaCasoDeRotacao(noAtual.getEsquerda()));
    			}
    			return noAtual;
    			
    		}
    		
    	} else {
    		// verifico se no atual tem avo e filhos
    		if (noAtual.getNoPai().getNoPai() != null && temFilhos(noAtual)) {
    			NoPV pai = noAtual.getNoPai();
    			NoPV avo = pai.getNoPai();
    			
    			// caso 3a - rotacao simples a direita
    			if (noAtual.getChave() < pai.getChave() && noAtual.getChave() < avo.getChave()) {
    				if(avo.getNoDireita() == null || avo.getNoDireita().getCor() == Color.PRETO) {
    					return rotacaoSimplesDireita(noAtual);
    				}
    			}
    			
    			// caso 3d - rotacao dupla a direita
    			else if (noAtual.getChave() > pai.getChave() && noAtual.getChave() < avo.getChave()) {
    				if(avo.getNoDireita() == null || avo.getNoDireita().getCor() == Color.PRETO) {
    					return rotacaoDuplaDireita(noAtual);
    				}
    			}
    		}
    		return noAtual;
    	}
    	
    }
    
    public boolean temFilhos(NoPV noAtual) {
    	return !(noAtual.getNoEsquerda() == null && noAtual.getNoDireita() == null);
    }
    
    public NoPV rotacaoSimplesDireita(NoPV noAtual) {
		NoPV filhoDireita;
		NoPV filhoDoFilho = null;

		filhoDireita = noAtual.getDireita();
		if (noAtual.getNoDireita() != null) {

			if (noAtual.getNoDireita().getNoEsquerda() != null) {
				filhoDoFilho = filhoDireita.getNoEsquerda();
			}
		}
		filhoDireita.setEsquerda(noAtual);
		noAtual.setDireita(filhoDoFilho);

		return filhoDireita;
	}
    
    public NoPV rotacaoDuplaDireita(NoPV noAtual) {
		NoPV arvore = noAtual;
		NoPV filhoDireita = noAtual.getNoDireita();
		NoPV filhoDoFilho = filhoDireita.getNoEsquerda();
		NoPV noInserido = filhoDoFilho.getNoDireita();

		filhoDireita.setEsquerda(noInserido);
		filhoDoFilho.setDireita(filhoDireita);
		noAtual.setDireita(filhoDoFilho);

		NoPV novoFilhoDireita = noAtual.getNoDireita();
		arvore.setDireita(null);
		novoFilhoDireita.setEsquerda(arvore);
		return novoFilhoDireita;
	}
    
//    public boolean temFilhosEsquerda(NoPV noAtual) {
//    	return noAtual.getNoEsquerda() != null && noAtual.getNoDireita() == null;
//    }
//    
//    public boolean temFilhosDireita(NoPV noAtual) {
//    	return noAtual.getNoEsquerda() == null && noAtual.getNoDireita() != null;
//    }
    
    
	public void recoloreArvore(NoPV noAtual) {

		// caso 1 - se for raiz
		if (noAtual.getPai() == null) {
			noAtual.setCor(Color.PRETO);
		}
		
		// Caso 3 - altera cor apos rotacoes
		if (temFilhos(noAtual)) {
			if(!temFilhos(noAtual.getNoDireita())){
				noAtual.getNoDireita().setCor(Color.VERMELHO);
			}
			if(!temFilhos(noAtual.getEsquerda())){
				noAtual.getNoEsquerda().setCor(Color.VERMELHO);
			}
		}
	}
    
    
    //================== main ==============================//
    
    public static void main(String[] args) {
		ArvorePV a = new ArvorePV();
		BinaryTreePrinter<NoPV> p = new BinaryTreePrinter<NoPV>(a);
		a.inserir(8);
		p = new BinaryTreePrinter<NoPV>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(18);
		p = new BinaryTreePrinter<NoPV>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(20);
		p = new BinaryTreePrinter<NoPV>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(15);
		p = new BinaryTreePrinter<NoPV>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(17);
		p = new BinaryTreePrinter<NoPV>(a);
		p.imprimir(System.out);
		System.out.println();
	}
    
    
    
    
}