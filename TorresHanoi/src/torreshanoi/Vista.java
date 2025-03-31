package torreshanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vista extends JFrame {

    private Model model;
    private JButton[][] towerButtons;  // Botones de los discos en cada torre
    private JLabel statusLabel;        // Estado del juego
    private int origenSeleccionado = -1; // Torre de origen seleccionada
    private int discoSeleccionado = -1;  // Disco seleccionado (valor real)

    public Vista(Model model) {
        this.model = model;

        // Configuración de la ventana
        setTitle("Torres de Hanoi 🏗️");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal para las torres
        JPanel towersPanel = new JPanel(new GridLayout(1, model.getMAX_PILES(), 10, 10));
        towerButtons = new JButton[model.getMAX_PILES()][model.getTOTAL_DISCS()];

        // Crear torres con botones de discos
        for (int i = 0; i < model.getMAX_PILES(); i++) {
            final int torreIndex = i; // Variable final para la torre

            JPanel tower = new JPanel(new GridLayout(model.getTOTAL_DISCS(), 1));
            tower.setBorder(BorderFactory.createTitledBorder("Torre " + i));

            // 🔥 Permitir clics en cualquier parte de la torre (incluyendo espacios vacíos)
            tower.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    intentarMoverDisco(torreIndex); // Ahora se puede soltar en cualquier parte de la torre
                }
            });

            for (int j = 0; j < model.getTOTAL_DISCS(); j++) {
                JButton disco = new JButton();
                disco.setEnabled(false);
                disco.setBackground(Color.LIGHT_GRAY);

                disco.addActionListener(e -> seleccionarDisco(torreIndex, disco));

                towerButtons[i][j] = disco;
                tower.add(disco);
            }

            towersPanel.add(tower); //  Añadir la torre completa al panel
        }

        // Etiqueta de estado del juego
        statusLabel = new JLabel("Haz clic en un disco para seleccionarlo.");
        add(towersPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        // Actualizar la vista con los discos iniciales
        actualitzarVista();
        setVisible(true);
    }

    // Método para manejar la selección de discos con el mouse
    private void seleccionarDisco(int torre, JButton disco) {
        if (origenSeleccionado == -1) {
            // Verificar si la torre tiene discos
            if (model.getPiles()[torre].isEmpty()) {
                statusLabel.setText("❌ No hay discos en esta torre.");
                return;
            }

            // Seleccionar el disco superior
            origenSeleccionado = torre;
            discoSeleccionado = model.getPiles()[torre].peek(); // Obtener el disco en la cima
            statusLabel.setText("📌 Disco " + discoSeleccionado + " seleccionado de Torre " + torre);
        }
    }

    // Método para intentar mover el disco a otra torre
    private void intentarMoverDisco(int torreDestino) {
        if (origenSeleccionado == -1) {
            statusLabel.setText("⚠️ Primero selecciona un disco.");
            return;
        }

        if (origenSeleccionado == torreDestino) {
            statusLabel.setText("❌ No puedes mover el disco a la misma torre.");
            return;
        }

        boolean exito = model.moureDisc(origenSeleccionado, torreDestino);
        if (exito) {
            statusLabel.setText("✅ Disco " + discoSeleccionado + " movido de Torre " + origenSeleccionado + " a Torre " + torreDestino);
            actualitzarVista();
        } else {
            statusLabel.setText("❌ Movimiento inválido.");
        }

        // Resetear selección
        origenSeleccionado = -1;
        discoSeleccionado = -1;
    }

    // Método para actualizar la vista con el estado del modelo
    public void actualitzarVista() {
        for (int i = 0; i < model.getMAX_PILES(); i++) {
            Integer[] discos = model.getPiles()[i].toArray(new Integer[0]);

            // Reiniciar todos los botones
            for (int j = 0; j < model.getTOTAL_DISCS(); j++) {
                towerButtons[i][j].setText("");
                towerButtons[i][j].setBackground(Color.LIGHT_GRAY);
                towerButtons[i][j].setEnabled(false);
            }

            // Mostrar los discos en la pila
            for (int j = 0; j < discos.length; j++) {
                int index = model.getTOTAL_DISCS() - discos.length + j;
                towerButtons[i][index].setText(String.valueOf(discos[j]));
                towerButtons[i][index].setBackground(Color.CYAN);
                towerButtons[i][index].setEnabled(true);
            }
        }
    }
}
