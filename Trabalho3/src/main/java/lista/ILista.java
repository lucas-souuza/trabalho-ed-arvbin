package lista;

import iterador.Iterador;

/**
 * Define as operações básicas da estrutura de dados LISTA
 * 
 * @param <T> Tipo a ser armazenado na lista
 */
public interface ILista<T> {
    /**
     * Insere o item e no início da lista.
     * Se houver itens na lista, esses serão deslocados para a posição seguinte.
     * 
     * @param e Item a ser inserido.
     * @return Verdadeiro, se a operação foi bem sucedida; ou falso, caso contrário.
     */
    public boolean inserirInicio(T e);

    /**
     * Insere o item e no final da lista.
     * 
     * @param e Item a ser inserido.
     * @return Verdadeiro, se a operação foi bem sucedida; ou falso, caso contrário.
     */
    public boolean inserirFim(T e);

    /**
     * Insere o item e na posição p da lista.
     * Se houver itens na posição p ou posterior, esses serão deslocados 
     * para frente em uma posição. O primeiro item da lista está na 
     * posição ZERO.
     * 
     * @param p Posição de inserção. Valores válidos: 0 a quantidade-1.
     * @param e Elemento a ser inserido.
     * @return Verdadeiro, se a operação foi bem sucedida; ou falso, caso contrário.
     */
    public boolean inserirPosicao(int p, T e);

    /**
     * Altera o valor da posição p da lista para e.
     * O primeiro item da lista está na posição ZERO.
     * 
     * @param p Posição de alteração. Valores válidos: 0 a quantidade-1.
     * @param e Item a ser inserido.
     * @return Antigo item na posição p da lista; ou null, caso p seja inválido.
     */
    public T alterar(int p, T e);

    /**
     * Remove o item do início da lista e o retorna.
     * 
     * @return Item do início da lista; ou null, se a lista estiver vazia.
     */
    public T removerInicio();

    /**
     * Remove o item do fim da lista e o retorna.
     * 
     * @return Item do fim da lista; ou null, se a lista estiver vazia.
     */
    public T removerFim();

    /**
     * Remove o item da posição p da lista e o retorna.
     * 
     * @param p Posição de remoção. Valores válidos: 0 a quantidade-1.
     * @return Item removido; ou null, caso p seja inválido. 
     */
    public T removerPosicao(int p);
    
    /**
     * Remove o item e da lista e o retorna.
     * 
     * @param e Item a ser removido.
     * @return Item removido; ou null, caso e não exista. 
     */
    public T remover(T e);
    
    /**
     * Retorna o item da posição p da lista.
     * O primeiro item da lista está na posição ZERO.
     * 
     * @param p Posição do item
     * @return Item da posição p; ou null, caso p seja inválido.
     */
    public T getItem(int p);
    
    /**
     * Verifica se a lista contém o item e.
     * 
     * @param e Item a ser buscado.
     * @return Verdadeiro se item e pertence à lista; ou falso, caso contrário.
     */
    public boolean contem(T e);
    
    /**
     * Retorna a primeira posição do item e na lista.
     * 
     * @param e Item a ser buscado.
     * @return Primeira posição do item e na lista; ou -1, se e não pertence à lista.
     */
    public int posicao(T e);
    
    /**
     * Retorna a quantidade de itens da lista.
     * 
     * @return Quantidade de itens da lista
     */
    public int quantidade();

    /**
     * Verifica se a lista está vazia.
     * @return Verdadeiro, se a lista estiver vazia; ou falso, caso contrário 
     */
    public boolean estaVazia();
    
    /**
     * Remove todos os itens da lista.
     */
    public void removerTodos();

    /**
     * Retorna um iterador para a lista
     * @return Iterador
     */
    Iterador<T> iterador();
}
