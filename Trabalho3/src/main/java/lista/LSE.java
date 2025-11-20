package lista;

import iterador.Iterador;

/**
 * Implementa uma Lista Simplesmente Encadeada (LSE)
 * @param <T> Tipo a ser armazenado na lista
 */
public class LSE<T> implements ILista<T> {

    private No  head;
    private int qtdItens;
    
	/**
	 * Implementa o nó da lista encadeada. Armazena o item propriamente 
	 * dito e uma referência para o próximo nó
	 */
    private class No {
        public T  item;
        public No prox;

        public No(T item) {
            this.item = item;
            this.prox = null;
        }
    }

    private class IteradorLSE implements Iterador<T> {
        private No current = null;
        private No next = head;

        @Override
        public boolean temProximo() {
            return next != null;
        }

        @Override
        public T proximo() {
            current = next;
            next = next.prox;
            return current.item;
        }
    }

    @Override
    public Iterador<T> iterador() {
        return new IteradorLSE();
    }
    
    @Override
    public boolean inserirInicio(T e) {
        No no;
        
        try {
            no = new No(e);
        }
        catch(OutOfMemoryError ex) {
            return false;
        }
        
        no.prox = head;
        head = no;
        
        qtdItens++;
        
        return true;
    }
    
    @Override
    public boolean inserirFim(T e) {
        No no, aux;
        
        try {
            no = new No(e);
        }
        catch(OutOfMemoryError ex) {
            return false;
        }
        
        if (qtdItens == 0)
            head = no;
        else {
            aux = localizarNo(qtdItens-1);
            aux.prox = no;
        }
        
        qtdItens++;
        
        return true;
    }

    @Override
    public boolean inserirPosicao(int p, T e) {
        No aux, no;
               
        // Verifica se P é válido
        if (p < 0 || p > qtdItens)
            return false;
        
        // Se P é a primeira posição, então é uma inserção no início
        // Se P é a ultima posição, então é uma inserção no fim
        if (p == 0)
            return inserirInicio(e);
        else if (p == qtdItens)
            return inserirFim(e);
        
        // Cria o NÓ
        try {
            no = new No(e);
        }
        catch(OutOfMemoryError ex) {
            return false;
        }
        
        // AUX = no da posição p-1
        aux = localizarNo(p-1);
        
        // Insere NÓ na posição P
        no.prox = aux.prox;
        aux.prox = no;
        
        qtdItens++;
        
        return true;
    }

    @Override
    public T alterar(int p, T e) {
        No aux;
        T old;
        
        // Verifica se P é válido
        if (p < 0 || p >= qtdItens)
            return null;
        
        aux = localizarNo(p);
        
        old = aux.item;
        aux.item = e;
        
        return old;
    }

    @Override
    public T removerInicio() {
        No no;
        T item;
        
        if (qtdItens == 0)
            return null;
        
        no = head;
        head = head.prox;
        
        item = no.item;
        no.item = null;
        no.prox = null;
        
        qtdItens--;
        
        return item;
    }

    @Override
    public T removerFim() {
        No ultimo, aux;
        T item;
        
        if (qtdItens == 0)
            return null;
        
        if (qtdItens == 1)
            return removerInicio();
        
        // Localiza o penultimo nó
        aux = localizarNo(qtdItens-2);
        
        // Localiza  último nó
        ultimo = aux.prox;
 
        aux.prox = null;
        
        item = ultimo.item;
        ultimo.item = null;
        
        qtdItens--;
        
        // Retorna o item removido
        return item;
    }

        
    @Override
    public T removerPosicao(int p) {
        No no, aux;
        T item;
        
        // Verifica se P é válido
        if (p < 0 || p >= qtdItens)
            return null;
        
        // Se P é a primeira posição, então é uma remoção do início
        // Se P é a ultima posição, então é uma remoção do fim
        if (p == 0)
            return removerInicio();
        else if (p == qtdItens-1)
            return removerFim();
        
        // AUX = nó da posição p-1
        aux = localizarNo(p-1);
        
        no = aux.prox;
        aux.prox = no.prox;
        
        item = no.item;
        no.item = null;
        no.prox = null;
        
        qtdItens--;
        
        return item;
    }
    
    @Override
    public T remover(T e) {
        if (e != null) {
            int p = posicao(e);
            
            if (p != -1) {
                T old = getItem(p);
                
                removerPosicao(p);
                
                return old;
            }
        }
        
        return null;
    }

    @Override
    public T getItem(int p) {
        if (p < 0 || p >= qtdItens)
            return null;
        
        No aux = localizarNo(p);
        
        return aux.item;
    }

    @Override
    public boolean contem(T e) {
        return posicao(e) != -1;
    }

    @Override
    public int posicao(T e) {
        No aux = head;
        int p = 0;
        
        while (aux != null) {
            if (e.equals(aux.item))
                return p;
            aux = aux.prox;
            p++;
        }
        
        return -1;
    }

    @Override
    public int quantidade() {
        return qtdItens;
    }

    @Override
    public boolean estaVazia() {
        return head == null;
    }
    
    @Override
    public void removerTodos() {
        No aux;
        
        while (head != null) {
            aux = head;
            head = head.prox;
            aux.item = null;
            aux.prox = null;
        }
        
        qtdItens = 0;
    }
    
    /**
     * Retorna uma string com os itens da lista no formato [e1, e2, e3, ..., en].
     */
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        
        buffer.append("[");
        
        if (head != null) {
            No aux = head;
            
            while (aux.prox != null) {
                buffer.append(aux.item);
                buffer.append(", ");
                aux = aux.prox;
            }

            buffer.append(aux.item);
        }
        
        buffer.append("]");
        
        return buffer.toString();
    }
    
    /**
     * Retorna o nó da posição P.
     */
    private No localizarNo(int p) {
        No aux = head;
        
        while (p > 0) {
            aux = aux.prox;
            p--;
        }
        
        return aux;
    }
}
