package com.olivierlafleur.testtest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MonFrameworkDeTest {
    private static List<String> errors = new ArrayList<String>();

    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = Class.forName(args[0]);
        Constructor constructor = c.getConstructor();
        Object testsClass = constructor.newInstance();
        Method[] m = c.getDeclaredMethods();

        for (Method test : m) {
            try {
                test.invoke(testsClass);
            } catch (Exception e) {
                fail(test.getName() + " : \n" + e.getCause().getMessage() + "\n" + printArray(e.getCause().getStackTrace()));
            }
        }

        System.out.println("");
        System.out.println("-----");

        if(errors.size() > 0) {
            System.err.println(errors.size() + " test(s) en erreur sur " + m.length + " tests exécutés\n");

            for(String failingTest : errors) {
                System.err.println(failingTest);
            }
        } else {
            System.out.println("Tous les tests passent.");
        }

    }

    public static void verifieVrai(boolean condition) {
        if(condition) {
            reussite();
        } else {
            String nomMethode = Thread.currentThread().getStackTrace()[2].getMethodName();

            fail(nomMethode + " : Condition non vérifiée");
        }
    }

    public static void verifieEgal(int attendu, int resultat) {
        if(attendu == resultat) {
            reussite();
        } else {
            String nomMethode = Thread.currentThread().getStackTrace()[2].getMethodName();

            fail(nomMethode + " : Attendu " + attendu + " / Obtenu " + resultat);
        }
    }

    private static void reussite() {
        System.out.print(".");
    }

    private static void fail(String message) {
        System.out.print("F");
        errors.add(message);
    }

    private static String printArray(StackTraceElement[] stackTraceElement) {
        String resultat = "";

        for(StackTraceElement line : stackTraceElement) {
            resultat += line + "\n";
        }

        return resultat;
    }
}
