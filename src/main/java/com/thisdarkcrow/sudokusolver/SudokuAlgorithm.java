/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.thisdarkcrow.sudokusolver;

import java.util.Stack;

import com.thisdarkcrow.validator.SudokuValidator;

/**
 *
 * @author ThisDarkCrow
 */
public class SudokuAlgorithm {

    private static Stack<byte[][]> soluciones = new Stack<byte[][]>();

    public static Stack<byte[][]> getSolutions(byte[][]sudoku){
        soluciones=new Stack<byte[][]>();

        try{
            solucionBruteForceSudoku(sudoku);
        }catch(Exception e){
            throw new IllegalArgumentException("Argument must be a 2D valid byte array");
        }

        return soluciones;
    }
    
    private static void solucionBruteForceSudoku(byte[][] sudokuPrevio) {
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
                if (SudokuValidator.isValid(sudoku)) {
                    if (SudokuValidator.isSolved(sudoku)) {
                        soluciones.add(getCopyArray(sudoku));
                    } else {
                        solucionBruteForceSudoku(sudoku);
                    }
                }
            }
        }

    }

    private static byte[][] getCopyArray(byte[][] original) {
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
