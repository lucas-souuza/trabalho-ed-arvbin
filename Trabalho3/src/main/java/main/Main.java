package main;

import arvbin.AVL;
import arvbin.IABB;
import arvbin.MaiorSoma;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        IABB<Integer, Integer> arvore2  = new AVL<>();
        arvore2.inserir(50);
        arvore2.inserir(25);
        arvore2.inserir(75);arvore2.inserir(12);arvore2.inserir(37);
        arvore2.inserir(62);arvore2.inserir(90);arvore2.inserir(8);
        arvore2.inserir(18);arvore2.inserir(30);arvore2.inserir(45);
        arvore2.inserir(58);arvore2.inserir(70);arvore2.inserir(80);
        arvore2.inserir(95);arvore2.inserir(5);arvore2.inserir(10);
        arvore2.inserir(15);arvore2.inserir(28);arvore2.inserir(33);
        arvore2.inserir(47);arvore2.inserir(55);arvore2.inserir(60);
        arvore2.inserir(77);arvore2.inserir(85);arvore2.inserir(93);
        arvore2.inserir(99);



        System.out.println(arvore2);

        arvore2.nivel(1, (T) -> System.out.print(T + " "));
        System.out.println();
        arvore2.nivel(3, (T) -> System.out.print(T + " "));
        System.out.println();
        arvore2.nivel(4, (T) -> System.out.print(T + " "));

        System.out.println(arvore2.menorCaminho(24, 28));

        System.out.println(arvore2.codigo(37));System.out.println(arvore2.codigo(30));
        System.out.println(arvore2.codigo(60));
        System.out.println(arvore2.codigo(85));
        System.out.println(arvore2.codigo(5));System.out.println(arvore2.codigo(99));




        IABB<Integer, Integer> arvore1  = new AVL<>();
        arvore1.inserir(50);arvore1.inserir(46);arvore1.inserir(52);
        arvore1.inserir(40);arvore1.inserir(48);
        arvore1.inserir(51);arvore1.inserir(53);arvore1.inserir(47);
        arvore1.inserir(49);

        System.out.println(arvore1);

        System.out.println(arvore1.maxSoma());

        System.out.println(arvore2.maxSoma());

    }
}