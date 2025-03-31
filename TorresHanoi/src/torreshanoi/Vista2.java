/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torreshanoi;

/**
 *
 * @author thejo
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista2 extends JFrame{
     private Model model;
    private JButton[][] towerButtons;  // Botons dels discs en cada pila
    private JButton moveButton;        // Botó per fer movimients
    private JComboBox<Integer> fromBox, toBox; // Selecció de piles origen i desti
    private JLabel statusLabel;        // Estat del joc

    public Vista2(Model model) {
        this.model = model;

        // Configuració de la finestra
        setTitle("Torres de Hanoi 🏗️");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal per les piles
        JPanel towersPanel = new JPanel(new GridLayout(1, model.getMAX_PILES(), 10, 10));
        towerButtons = new JButton[model.getMAX_PILES()][model.getTOTAL_DISCS()];

        // Crear piles amb botons buits
        for (int i = 0; i < model.getMAX_PILES(); i++) {
            JPanel tower = new JPanel(new GridLayout(model.getTOTAL_DISCS(), 1));
            tower.setBorder(BorderFactory.createTitledBorder("Pila " + i));

            for (int j = 0; j < model.getTOTAL_DISCS(); j++) {
                towerButtons[i][j] = new JButton();
                towerButtons[i][j].setEnabled(false);
                tower.add(towerButtons[i][j]);
            }

            towersPanel.add(tower);
            
            
        }

        // Panel de control
        JPanel controlPanel = new JPanel();
        fromBox = new JComboBox<>(new Integer[]{0, 1, 2});
        toBox = new JComboBox<>(new Integer[]{0, 1, 2});
        moveButton = new JButton("Moure");
        statusLabel = new JLabel("Selecciona origen i desti.");

        controlPanel.add(new JLabel("De:"));
        controlPanel.add(fromBox);
        controlPanel.add(new JLabel("A:"));
        controlPanel.add(toBox);
        controlPanel.add(moveButton);
        
        // Evento del botón "Mover"
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int origen = (int) fromBox.getSelectedItem();
                int destino = (int) toBox.getSelectedItem();

                if (origen == destino) {
                    statusLabel.setText("❌ No pots moure a la mateixa pila.");
                    return;
                }

                boolean success = model.moureDisc(origen, destino);
                if (success) {
                    statusLabel.setText("✅ Movimient realitzat.");
                    actualitzarVista();
                } else {
                    statusLabel.setText("❌ Movimient invàlid.");
                }
            }
        });

        // Afegir components a la finestra
        add(towersPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        // Actualitzar la vista amb els discs inicials
        actualitzarVista();
        setVisible(true);
    }

    // Métode per actualitzar l'interfaç depenent l'estat del model
    public void actualitzarVista() {
        for (int i = 0; i < model.getMAX_PILES(); i++) {
            Integer[] discos = model.getPiles()[i].toArray(new Integer[0]);

            
            // Reiniciar tots els botons
            for (int j = 0; j < model.getTOTAL_DISCS(); j++) {
                towerButtons[i][j].setText("");
                towerButtons[i][j].setBackground(null);
            }

            // Mostrar els discs en la pila 
           for (int j = 0; j < discos.length; j++) {
                towerButtons[i][model.getTOTAL_DISCS() - discos.length + j].setText(String.valueOf(j+1));
                towerButtons[i][model.getTOTAL_DISCS() - discos.length + j].setBackground(Color.CYAN);
            }
        }
        
}
}