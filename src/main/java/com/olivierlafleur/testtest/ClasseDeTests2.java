package com.olivierlafleur.testtest;

import static com.olivierlafleur.testtest.MonFrameworkDeTest.verifieEgal;
import static com.olivierlafleur.testtest.MonFrameworkDeTest.verifieVrai;

public class ClasseDeTests2 {
    public void unAutreTest2() {
        int var1 = 2;
        int var2 = 1;

        verifieVrai(var1 > var2);
    }

    public void unDernierTest() {
        verifieEgal(2, 2);
    }

    public void tddTest() {
        Pile pile = new Pile();

        verifieEgal(0, pile.height());
    }
}
