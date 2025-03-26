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
    private final int MAX_PILES=3;
    private final int TOTAL_DISCS=5;
    //Atributs
    
    private Deque<Integer> piles[];

    public Model(Deque<Integer>[] piles) {
        this.piles = piles;
        
        // Crear 3 torres (con ArrayDeque)
        piles = new ArrayDeque[MAX_PILES];
        for (int i = 0; i < MAX_PILES; i++) {
            piles[i] = new ArrayDeque<>();
        }
        
        //Omplim la 1era pila per poder començar el joc
        for(int disc=TOTAL_DISCS;disc>1;disc--){
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
    public boolean moureDisc(int origen, int destino) {
        if (origen < 0 || origen > 2 || destino < 0 || destino > 2) {
            throw new IllegalArgumentException("Torres deben estar entre 0 y 2.");
        }

        // Validar si la torre de origen está vacía
        if (piles[origen].isEmpty()) {
            System.out.println("❌ Movimiento inválido: Torre de origen vacía.");
            return false;
        }

        int disco = piles[origen].peek(); // Disco a mover

        // Validar si el movimiento es válido (disco pequeño sobre disco grande)
        if (!piles[destino].isEmpty() && piles[destino].peek() < disco) {
            System.out.println("❌ Movimiento inválido: Disco más grande sobre uno más pequeño.");
            return false;
        }

        // Realizar el movimiento
        piles[destino].push(piles[origen].pop());
        return true;
    }

    
    
    
    

    
    
    
    
}
