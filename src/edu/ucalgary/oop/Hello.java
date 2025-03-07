package edu.ucalgary.oop;
/**
@author Rajdeep Das <a href="mailto:firstname.lastname@ucalgary.ca">
firstname.lastname@ucalgary.ca</a>
@version 1.7
@since 1.0
*/
/*
Hello is a simple example program which prints the classic message:
Hello, world
*/
public class Hello {
     /**
         This prints "Hello, world" and the contents of a variable to the terminal window.
        @param args Optional command-line argument
        */
    public static void main(String[] args) {
        short exampleShort = 2;
        long exampleLong = 100100;
        float exampleFloat = 1;
        double exampleDouble = 2.03;
        int intNum = 25;
        double doubNum = intNum; //automatic casting

        System.out.println("Integer value: " + intNum);
        System.out.println("Double Value: " + doubNum);
        System.out.println("Hello, " + exampleShort + ", " + exampleLong);
        System.out.println(exampleFloat + ", " + exampleDouble);
    }
} // End of Class Declaration
