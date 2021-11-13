package br.unicap.ed2.avl;

import br.unicap.ed2.base.AbstractArvoreBinariaDePesquisa;
import br.unicap.ed2.base.BinaryTreePrinter;

public class ArvoreAVL extends AbstractArvoreBinariaDePesquisa<NoAVL> {

	@Override
	public NoAVL novoNode(int key) {
		return new NoAVL(key);
	}

	@Override
	public NoAVL inserir(int key) {
		inserirNoHelper(raiz, key);
		calcularBalanceamento(raiz);
		raiz = verificaBalanceamento(raiz);
		return raiz;
	}

	@Override
	public NoAVL procurar(int key) {
		return procurarNoHelper(raiz, key);
	}

	@Override
	public void deletar(int key) {
		raiz = deletarNoHelper(raiz, key);
		calcularBalanceamento(raiz);
		raiz = verificaBalanceamento(raiz);
	}

	// =============== Metodos para verificar rotação ===============//

	public NoAVL verificaBalanceamento(NoAVL noAtual) {

		if (noAtual.getBalanceamento() >= 2 || noAtual.getBalanceamento() <= -2) {

			// Desbalanceamento a Direita
			if (noAtual.getBalanceamento() >= 2) {

				if (noAtual.getNoDireita().getBalanceamento() >= noAtual.getBalanceamento()) {
					noAtual.setNoDireita(verificaBalanceamento(noAtual.getNoDireita()));
					// recalcula balanceamento
					calcularBalanceamento(raiz);
				}

				if (noAtual.getBalanceamento() * noAtual.getNoDireita().getBalanceamento() > 0
						&& !estaBalanceada(noAtual)) {
					System.out.println("Rotação simples a Direita no Nó " + noAtual.getChave());
					return rotacaoSimplesDireita(noAtual);
				} else if (!estaBalanceada(noAtual)) {
					System.out.println("Rotação dupla a Direita no Nó " + noAtual.getChave());
					return rotacaoDuplaDireita(noAtual);
				} else {
					return noAtual;
				}
			}
			// Desbalanceamento a Esquerda
			else {

				if (noAtual.getNoEsquerda().getBalanceamento() <= noAtual.getBalanceamento()) {
					noAtual.setNoEsquerda(verificaBalanceamento(noAtual.getNoEsquerda()));
					// recalcula balanceamento
					calcularBalanceamento(raiz);
				}

				if (noAtual.getBalanceamento() * noAtual.getNoEsquerda().getBalanceamento() > 0
						&& !estaBalanceada(noAtual)) {
					System.out.println("Rotação simples a Esquerda no Nó " + noAtual.getChave());
					return rotacaoSimplesEsquerda(noAtual);
				} else if (!estaBalanceada(noAtual)) {
					System.out.println("Rotação dupla a Esquerda no Nó " + noAtual.getChave());
					return rotacaoDuplaEsquerda(noAtual);
				} else {
					return noAtual;
				}
			}
		}
		calcularBalanceamento(noAtual);
		if (noAtual.getNoEsquerda() != null) {
			noAtual.setNoEsquerda(verificaBalanceamento(noAtual.getNoEsquerda()));
		}
		if (noAtual.getNoDireita() != null) {
			noAtual.setNoDireita(verificaBalanceamento(noAtual.getNoDireita()));
		}
		return noAtual;
	}

	public NoAVL rotacaoSimplesEsquerda(NoAVL noAtual) {
		NoAVL filhoEsquerda;
		NoAVL filhoDoFilho = null;

		filhoEsquerda = noAtual.getNoEsquerda();
		if (noAtual.getNoEsquerda() != null) {
			
			if (noAtual.getNoEsquerda().getNoDireita() != null) {
				filhoDoFilho = filhoEsquerda.getNoDireita();
			}
		}
		filhoEsquerda.setNoDireita(noAtual);
		noAtual.setNoEsquerda(filhoDoFilho);

		return filhoEsquerda;
	}

	public NoAVL rotacaoSimplesDireita(NoAVL noAtual) {
		NoAVL filhoDireita;
		NoAVL filhoDoFilho = null;

		filhoDireita = noAtual.getDireita();
		if (noAtual.getNoDireita() != null) {

			if (noAtual.getNoDireita().getNoEsquerda() != null) {
				filhoDoFilho = filhoDireita.getNoEsquerda();
			}
		}
		filhoDireita.setNoEsquerda(noAtual);
		noAtual.setNoDireita(filhoDoFilho);

		return filhoDireita;
	}

	public NoAVL rotacaoDuplaEsquerda(NoAVL noAtual) {
		NoAVL arvore = noAtual;
		NoAVL filhoEsquerda = noAtual.getNoEsquerda();
		NoAVL filhoDoFilho = filhoEsquerda.getNoDireita();
		NoAVL noInserido = filhoDoFilho.getNoEsquerda();

		filhoEsquerda.setNoDireita(noInserido);
		filhoDoFilho.setNoEsquerda(filhoEsquerda);
		noAtual.setNoEsquerda(filhoDoFilho);

		NoAVL novoFilhoEsquerda = noAtual.getNoEsquerda();
		arvore.setNoEsquerda(null);
		novoFilhoEsquerda.setNoDireita(arvore);
		return novoFilhoEsquerda;
	}

	public NoAVL rotacaoDuplaDireita(NoAVL noAtual) {
		NoAVL arvore = noAtual;
		NoAVL filhoDireita = noAtual.getNoDireita();
		NoAVL filhoDoFilho = filhoDireita.getNoEsquerda();
		NoAVL noInserido = filhoDoFilho.getNoDireita();

		filhoDireita.setNoEsquerda(noInserido);
		filhoDoFilho.setNoDireita(filhoDireita);
		noAtual.setNoDireita(filhoDoFilho);

		NoAVL novoFilhoDireita = noAtual.getNoDireita();
		arvore.setNoDireita(null);
		novoFilhoDireita.setNoEsquerda(arvore);
		return novoFilhoDireita;
	}

	// ============ Metodos para calculo de balanceamento =========== //

	public int calcularAltura(NoAVL noAtual) {
		NoAVL esquerda = noAtual.getEsquerda();
		NoAVL direita = noAtual.getDireita();

		// Altura da raiz
		if (esquerda == null && direita == null) {
			return 1;
		}

		// Altura no a esquerda
		else if (esquerda != null && direita == null) {
			return 1 + calcularAltura(esquerda);
		}
		// Altura no a direita
		else if (esquerda == null && direita != null) {
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

	public boolean estaBalanceada(NoAVL no) {
		return !(no.getBalanceamento() >= 2 || no.getBalanceamento() <= -2);
	}

	// ======================= Main para testes ============================== //

	public static void main(String[] args) {
		ArvoreAVL a = new ArvoreAVL();
		BinaryTreePrinter<NoAVL> p = new BinaryTreePrinter<NoAVL>(a);
		a.inserir(50);
		p = new BinaryTreePrinter<NoAVL>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(10);
		p = new BinaryTreePrinter<NoAVL>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(60);
		p = new BinaryTreePrinter<NoAVL>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(20);
		p = new BinaryTreePrinter<NoAVL>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(5);
		p = new BinaryTreePrinter<NoAVL>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(70);
		p = new BinaryTreePrinter<NoAVL>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(40);
		p = new BinaryTreePrinter<NoAVL>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(80);
		p = new BinaryTreePrinter<NoAVL>(a);
		p.imprimir(System.out);
		System.out.println();
		a.inserir(90);
		p = new BinaryTreePrinter<NoAVL>(a);
		p.imprimir(System.out);
		System.out.println();

	}

}