/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.thisdarkcrow.sudokusolver;

import java.util.Stack;

/**
 *
 * @author ThisDarkCrow
 */
public class SudokuSolver {

    private static Stack<byte[][]> soluciones = new Stack<byte[][]>();
    private static long timeStart;
    private static long timeEnd;

    public static void main(String[] args) {

        byte[][] sudo0 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 3, 0, 1, 9, 0, 7, 0, 5, 0 },
                { 6, 8, 0, 1, 9, 0, 0, 0, 0 },
                { 0, 1, 0, 3, 7, 0, 0, 0, 5 },
                { 0, 0, 0, 0, 0, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 1, 0, 3 },
                { 4, 5, 0, 7, 3, 0, 0, 2, 0 },
                { 0, 0, 0, 0, 0, 8, 0, 0, 0 } };

        soluciones.add(sudo0); // Agrego sudoku original para posteriormente imprimirlo

        timeStart = System.currentTimeMillis();
        solucionBruteForceSudoku(sudo0); // Función que agrega las posibles soluciones en Stack respectivo
        timeEnd = System.currentTimeMillis();

        soluciones.forEach((sudo) -> imprime(sudo));
        System.out.println("Soluciones calculadas: " + (soluciones.size() - 1));
        System.out.println("Tiempo de proceso: " + (timeEnd - timeStart) + " milisegundos");
    }

    public static void solucionBruteForceSudoku(byte[][] sudokuPrevio) {
        // Función topada a 20 soluciones

        boolean existenCeros = false;
        byte coordenadaCeroX = -1, coordenadaCeroY = -1;
        byte[][] sudoku = getCopyArray(sudokuPrevio);
        int sudoLength = sudoku.length;

        // Verifico si existen ceros
        for (byte x = 0; x < sudoLength && !existenCeros; ++x) {
            for (byte y = 0; y < sudoLength && !existenCeros; ++y) {
                if (sudoku[x][y] == 0) {
                    existenCeros = true;
                    coordenadaCeroX = x;
                    coordenadaCeroY = y;
                }
            }
        }

        // Para cada casilla vacía, itero posibles valores
        if (existenCeros) {
            for (byte x = 1; x <= sudoLength && soluciones.size() < 21; ++x) {
                sudoku[coordenadaCeroX][coordenadaCeroY] = x;
                if (AlgoritmosSudoku.isValid(sudoku)) {
                    if (AlgoritmosSudoku.isSolved(sudoku)) {
                        soluciones.add(getCopyArray(sudoku));
                    } else {
                        solucionBruteForceSudoku(sudoku);
                    }
                }
            }
        }

    }

    public static void imprime(byte[][] sudoku) {
        int sudoLength = sudoku.length;
        for (byte x = 0; x < sudoLength; ++x) {
            for (byte y = 0; y < sudoLength; ++y) {
                System.out.print(sudoku[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static byte[][] getCopyArray(byte[][] original) {
        int originalLenght = original.length;
        byte[][] copy = new byte[originalLenght][originalLenght];
        for (byte i = 0; i < originalLenght; i++) {
            for (byte j = 0; j < originalLenght; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

}
