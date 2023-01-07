/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import clases.Conexion;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jeremy
 */
public class GestionDeCamiones extends javax.swing.JFrame {

    /**
     * Creates new form GestionDeCamiones
     */
    String user = Login.user;
    public static int id_camionUpdate = 0;

    DefaultTableModel model = new DefaultTableModel();

    public GestionDeCamiones() {
        initComponents();

        this.setTitle("Gestion de Camiones - Sesion de " + user);
        this.setSize(640, 355);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        try {

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select ID, marca, modelo, estatus from camiones where marca != 'No Asignado' and modelo != 'No Asignado'");

            ResultSet rs = pst.executeQuery();

            tabla_camiones = new JTable(model);
            jScrollPane1.setViewportView(tabla_camiones);

            model.addColumn("ID");
            model.addColumn("Marca");
            model.addColumn("Modelo");
            model.addColumn("Estatus");

            while (rs.next()) {
                Object fila[] = new Object[4];

                for (int i = 0; i < 4; i++) {


                        fila[i] = rs.getObject(i + 1);
                    
                }

                model.addRow(fila);

            }

            cn.close();

            tabla_camiones.addMouseListener(new MouseAdapter() {

                // Evento de mouse para saber que fila selecciono el usuario 
                @Override
                public void mouseClicked(MouseEvent e) {

                    int fila_point = tabla_camiones.rowAtPoint(e.getPoint());
                    int columna_point = 0;

                    if (fila_point > -1) {
                        
                        id_camionUpdate = (int) model.getValueAt(fila_point, columna_point);
                        new InformacionCamion().setVisible(true);
                    }
                }
            });

        } catch (SQLException e) {
            System.err.println("Error, al rellenar la tabla de Camiones: \n " + e);
            JOptionPane.showMessageDialog(null, "Error, al rellenar la tabla de Camiones, contacte con el Administrador");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_camiones = new javax.swing.JTable();
        label_Titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_camiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla_camiones);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 610, 235));

        label_Titulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        label_Titulo.setForeground(new java.awt.Color(255, 153, 0));
        label_Titulo.setText("Gestion de Camiones");
        getContentPane().add(label_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionDeCamiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionDeCamiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionDeCamiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionDeCamiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionDeCamiones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_Titulo;
    private javax.swing.JTable tabla_camiones;
    // End of variables declaration//GEN-END:variables
}