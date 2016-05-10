package com.olivierlafleur.testtest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyTestFramework {
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
            System.err.println(errors.size() + " test(s) in error on " + m.length + " tests run\n");

            for(String failingTest : errors) {
                System.err.println(failingTest);
            }
        } else {
            System.out.println("All the tests pass.");
        }

    }

    public static void checkTrue(boolean condition) {
        if(condition) {
            success();
        } else {
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            fail(methodName + " : Condition not verified");
        }
    }

    public static void checkEqual(int expected, int result) {
        if(expected == result) {
            success();
        } else {
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            fail(methodName + " : Expected " + expected + " / Obtained " + result);
        }
    }

    private static void success() {
        System.out.print(".");
    }

    private static void fail(String message) {
        System.out.print("F");
        errors.add(message);
    }

    private static String printArray(StackTraceElement[] stackTraceElement) {
        String result = "";

        for(StackTraceElement line : stackTraceElement) {
            result += line + "\n";
        }

        return result;
    }
}
