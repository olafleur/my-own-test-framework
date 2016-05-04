package com.olivierlafleur.testtest;

import static com.olivierlafleur.testtest.MonFrameworkDeTest.verifieEgal;
import static com.olivierlafleur.testtest.MonFrameworkDeTest.verifieVrai;

public class ClasseDeTests {
    public void divisionParZeroTest() {
        int var1 = 1;
        int var2 = 2;

        //int res = 2/0;

        verifieVrai(var2 > var1);
    }

    public void unAutreTest() {
        int var1 = 1;
        int var2 = 2;

        verifieVrai(var2 > var1);
    }

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
