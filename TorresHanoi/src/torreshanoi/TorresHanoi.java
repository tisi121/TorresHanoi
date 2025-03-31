/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package torreshanoi;

/**
 *
 * @author thejo
 */
public class TorresHanoi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Model model = new Model();

        // Crear la vista y conectar con el modelo
        Vista view = new Vista(model);

        // Pruebas automáticas: simular movimientos
        System.out.println("🏗️ Estado inicial:");
        printState(model);

        /*
        // Simular un movimiento válido
        if (model.moureDisc(0, 1)) {
            System.out.println("✅ Movimiento: 0 -> 1");
        }
*/

        // Actualizar la vista después del movimiento
        view.actualitzarVista();
        printState(model);

        // Movimiento inválido: de una torre vacía
        if (!model.moureDisc(2, 1)) {
            System.out.println("❌ Movimiento inválido (torre vacía).");
        }

        // Actualizar la vista
        view.actualitzarVista();
    }

    // Método auxiliar para mostrar el estado de las torres
    public static void printState(Model model) {
        for (int i = 0; i < model.getMAX_PILES(); i++) {
            System.out.println("Torre " + i + ": " + model.getPiles()[i]);
        }
    }
    
}
