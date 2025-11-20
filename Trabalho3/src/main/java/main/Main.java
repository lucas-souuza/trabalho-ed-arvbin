package main;

import arvbin.AVL;
import arvbin.IABB;

public class Main {
    public static void main(String[] args) {

        IABB<Integer, Integer> arvore  = new AVL<>();
        arvore.inserir(3);
        arvore.inserir(10);
        arvore.inserir(4);arvore.inserir(27);arvore.inserir(2);
        arvore.inserir(12);

        System.out.println(arvore);

        arvore.nivel(2, System.out::println);


    }
}