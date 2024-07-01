package com.thisdarkcrow.main;

//First: You need to import the package wherever you will use.
import com.thisdarkcrow.sudokusolver.SudokuAlgorithm;

import java.util.Stack;

public class SudokuMainExample {

    private static long timeStart;
    private static long timeEnd;

    public static void main(String[] args) {

        //Second: You should declare and initialize a 2D byte array.
        //This 2D byte array represent the initial state of sudoku.
        //Values to calculate are represented by zeros.
        byte[][] sudo0 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 3, 0, 1, 9, 0, 7, 0, 5, 0 },
                { 6, 8, 0, 1, 9, 0, 0, 0, 0 },
                { 0, 1, 0, 3, 7, 0, 0, 0, 5 },
                { 0, 0, 0, 0, 0, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 1, 0, 3 },
                { 4, 5, 0, 7, 3, 0, 0, 2, 0 },
                { 0, 0, 0, 0, 0, 8, 0, 0, 0 } };

        timeStart = System.currentTimeMillis();
        //Third: Call the method getSolutions(byte[][]).
        //This method return a Stack of byte solutions.
        //Some sudokus has many posible solutions, the method was restricted 20 solutions.
        Stack<byte[][]> soluciones = SudokuAlgorithm.getSolutions(sudo0);
        timeEnd = System.currentTimeMillis();

        //Fourth: Now you can use the solution stack however you want.
        soluciones.forEach((sudo) -> imprime(sudo));
        System.out.println("Soluciones calculadas: " + (soluciones.size() - 1));
        System.out.println("Tiempo de proceso: " + (timeEnd - timeStart) + " milisegundos");

        //Sixth: Thanks. Let me know if you have any trouble.
    }

    private static void imprime(byte[][] sudoku) {
        int sudoLength = sudoku.length;
        for (byte x = 0; x < sudoLength; ++x) {
            for (byte y = 0; y < sudoLength; ++y) {
                System.out.print(sudoku[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}