/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thisdarkcrow.sudokusolver;

/**
 *
 * @author ThisDarkCrow
 */
public class AlgoritmosSudoku {

    public static boolean isSolved(byte[][] sudo) {
        boolean isSolved = true;
        int sudoLength = sudo.length;

        for (byte x = 0; x < sudoLength && isSolved; ++x) {
            for (byte y = 0; y < sudoLength && isSolved; ++y) {
                if (sudo[x][y] == 0) {
                    isSolved = false;
                }
            }
        }

        return isSolved;
    }

    public static boolean isValid(byte[][] sudo) {
        boolean isValid = true;
        int sudoLength = sudo.length;
        byte[] vertical = new byte[sudoLength];
        byte[] horizontal = new byte[sudoLength];

        for (int x = 0; x < sudoLength; ++x) {
            for (int w = 0; w < sudoLength; ++w) {
                horizontal[w] = sudo[x][w];
            }
            for (int y = 0; y < sudoLength; ++y) {
                for (int w = 0; w < sudoLength; ++w) {
                    vertical[w] = sudo[w][y];
                }
                if (sudo[x][y] != 0) {
                    if (topaH(x, y, sudo, horizontal) || topaV(x, y, sudo, vertical) || topaC(x, y, sudo)) {
                        isValid = false;
                    }
                }
            }
        }

        return isValid;
    }

    private static boolean topaH(int x, int y, byte[][] sudo, byte[] horizontal) {
        boolean flag = false;
        for (int w = 0; w < y; ++w) {
            if (w == y) {
                ++w;
            }
            if (sudo[x][y] == horizontal[w]) {
                flag = true;
            }
        }
        return flag;
    }

    private static boolean topaV(int x, int y, byte[][] sudo, byte[] vertical) {
        boolean flag = false;
        for (int w = 0; w < x; ++w) {
            if (sudo[x][y] == vertical[w]) {
                flag = true;
            }
        }
        return flag;
    }

    private static boolean topaC(int x, int y, byte[][] sudo) {
        int fil, col, veces = 0;
        boolean flag = false;
        if (x <= 2) {
            fil = 0;
            if (y <= 2)
                col = 0;
            else if (y >= 3 && y <= 5)
                col = 3;
            else
                col = 6;
        } else if (x >= 3 && x <= 5) {
            fil = 3;
            if (y <= 2)
                col = 0;
            else if (y >= 3 && y <= 5)
                col = 3;
            else
                col = 6;
        } else {
            fil = 6;
            if (y <= 2)
                col = 0;
            else if (y >= 3 && y <= 5)
                col = 3;
            else
                col = 6;
        }

        for (int i = fil; i < fil + 3; ++i)
            for (int j = col; j < col + 3; ++j) {
                if (sudo[x][y] == sudo[i][j])
                    ++veces;
            }
        if (veces >= 2)
            flag = true;
        return flag;
    }

}