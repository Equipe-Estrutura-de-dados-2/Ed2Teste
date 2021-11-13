package br.unicap.ed2.pv;

import br.unicap.ed2.avl.ArvoreAVL;
import br.unicap.ed2.avl.NoAVL;
import br.unicap.ed2.base.AbstractArvoreBinariaDePesquisa;
import br.unicap.ed2.base.BinaryTreePrinter;

public class ArvorePV extends AbstractArvoreBinariaDePesquisa<NoPV> {

    @Override
    public NoPV novoNode(int key) {
        //Altera
        return new NoPV(key);
    }

    @Override
    public NoPV inserir(int key) {
        inserirNoHelper(raiz, key);
        verificaCaso(raiz);
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
    
    public void verificaCaso(NoPV noAtual) {
    	//caso 1 - se for raiz
    	if(noAtual.getPai() == null) {
    		noAtual.setCor(0);
    	}
    }
    
    
    public void calculaAlturaNegra(NoPV noAtual) {
    	
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
		a.inserir(5);
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