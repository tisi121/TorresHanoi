/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torreshanoi;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author thejo
 */
public class Model {

    //Constants
    private final int MAX_PILES = 3;
    private final int TOTAL_DISCS = 5;
    //Atributs

    private Deque<Integer> piles[];

    public Model() {
        this.piles = new ArrayDeque[MAX_PILES];

        // Crear 3 torres (con ArrayDeque)
        piles = new ArrayDeque[MAX_PILES];
        for (int i = 0; i < MAX_PILES; i++) {
            piles[i] = new ArrayDeque<>();
        }

        //Omplim la 1era pila per poder començar el joc
        for (int disc = TOTAL_DISCS; disc >= 1; disc--) {
            piles[0].push(disc);
        }
    }

    public void setPiles(Deque<Integer>[] piles) {
        this.piles = piles;
    }

    public int getMAX_PILES() {
        return MAX_PILES;
    }

    public Deque<Integer>[] getPiles() {
        return piles;
    }

    public int getTOTAL_DISCS() {
        return TOTAL_DISCS;
    }

    //Mètodes
    // Fem el moviment de disc
    public boolean moureDisc(int origen, int desti) {
        if (origen < 0 || origen > 2 || desti < 0 || desti > 2) {
            throw new IllegalArgumentException("Les piles han d'estar entre 0 i 2.");
        }

        // Validar si la pila d'origen está buida
        if (piles[origen].isEmpty()) {
            System.out.println("❌ Movimiento inválid: Torre de origen buida.");
            return false;
        }

        int disco = piles[origen].peek(); // Disc a moure

        // Validar si el movimient es válid (disc petit sobre disc gran)
        if (!piles[desti].isEmpty() && piles[desti].peek() < disco) {
            System.out.println("❌ Movimient inválid: Disc mes gran sobre un mes petit.");
            return false;
        }

        // Realizar el movimiento
        piles[desti].push(piles[origen].pop());
        return true;
    }

}
