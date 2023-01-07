/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import clases.Conexion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jeremy
 */
public class RegistrarViaje extends javax.swing.JFrame {

    /**
     * Creates new form GestionarCamion
     */
    String user = Login.user;
    String modelo = "", marca = "", productos = "", tipo_remolque = "";
    int id_camion = 0;
    int id_remolque = 0;

    public RegistrarViaje() {
        initComponents();

        this.setTitle("Registro de Viajes - Sesion de " + user);
        this.setSize(900, 415);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        try {

            DefaultTableModel model = new DefaultTableModel();

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select ID, productos, tipo_remolque, estatus from camiones where productos != 'No Asignado' and tipo_remolque != 'No Asignado' and estatus != 'Ocupado'");

            ResultSet rs = pst.executeQuery();

            tabla_remolques = new JTable(model);
            jScrollPane_remolque.setViewportView(tabla_remolques);

            model.addColumn("ID");
            model.addColumn("Productos");
            model.addColumn("Tipo Remolque");

            while (rs.next()) {
                Object fila[] = new Object[3];

                for (int i = 0; i < 3; i++) {

                    fila[i] = rs.getObject(i + 1);

                }

                model.addRow(fila);

            }

            cn.close();

            tabla_remolques.addMouseListener(new MouseAdapter() {

                // Evento de mouse para saber que fila selecciono el usuario 
                @Override
                public void mouseClicked(MouseEvent e) {

                    int fila_point = tabla_remolques.rowAtPoint(e.getPoint());

                    if (fila_point > -1) {

                        id_remolque = (int) model.getValueAt(fila_point, 0);
                        productos = model.getValueAt(fila_point, 1).toString();
                        tipo_remolque = model.getValueAt(fila_point, 2).toString();

                    }
                }
            });

        } catch (SQLException e) {
            System.err.println("Error, al rellenar la tabla de llenar remolques (RegistrarViaje): \n " + e);
            JOptionPane.showMessageDialog(null, "Error, al rellenar la tabla de Remolques, contacte con el Administrador");
        }

        try {
            DefaultTableModel model = new DefaultTableModel();

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select ID, marca, modelo, estatus from camiones where marca != 'No Asignado' and modelo != 'No Asignado' and estatus != 'Ocupado'");

            ResultSet rs = pst.executeQuery();

            tabla_camiones = new JTable(model);
            jScrollPane_camiones.setViewportView(tabla_camiones);

            model.addColumn("ID");
            model.addColumn("Marca");
            model.addColumn("Modelo");
            model.addColumn("Estatus");

            while (rs.next()) {
                Object fila1[] = new Object[4];

                for (int i = 0; i < 4; i++) {

                    fila1[i] = rs.getObject(i + 1);

                }

                model.addRow(fila1);

            }

            cn.close();

            tabla_camiones.addMouseListener(new MouseAdapter() {

                // Evento de mouse para saber que fila selecciono el usuario 
                @Override
                public void mouseClicked(MouseEvent e) {

                    int fila_point = tabla_camiones.rowAtPoint(e.getPoint());

                    if (fila_point > -1) {

                        id_camion = (int) model.getValueAt(fila_point, 0);
                        marca = model.getValueAt(fila_point, 1).toString();
                        modelo = model.getValueAt(fila_point, 2).toString();

                    }
                }
            });

        } catch (SQLException e) {
            System.err.println("Error, al rellenar la tabla de Camiones (RegistrarViaje): \n " + e);
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

        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_viaje = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_conductor = new javax.swing.JTextField();
        boton_registrar = new javax.swing.JButton();
        jScrollPane_remolque = new javax.swing.JScrollPane();
        tabla_remolques = new javax.swing.JTable();
        jScrollPane_camiones = new javax.swing.JScrollPane();
        tabla_camiones = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("Registro de Viajes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Viaje:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        txt_viaje.setBackground(new java.awt.Color(255, 153, 0));
        txt_viaje.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_viaje.setForeground(new java.awt.Color(255, 255, 255));
        txt_viaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_viaje.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_viaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 230, 29));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Conductor:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        txt_conductor.setBackground(new java.awt.Color(255, 153, 0));
        txt_conductor.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_conductor.setForeground(new java.awt.Color(255, 255, 255));
        txt_conductor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_conductor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_conductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 230, 29));

        boton_registrar.setBackground(new java.awt.Color(255, 153, 0));
        boton_registrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        boton_registrar.setForeground(new java.awt.Color(255, 255, 255));
        boton_registrar.setText("Registrar");
        boton_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_registrarActionPerformed(evt);
            }
        });
        getContentPane().add(boton_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 230, 40));

        tabla_remolques.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane_remolque.setViewportView(tabla_remolques);

        getContentPane().add(jScrollPane_remolque, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 380, 140));

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
        jScrollPane_camiones.setViewportView(tabla_camiones);

        getContentPane().add(jScrollPane_camiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 380, 140));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Selecciona un Remolque");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Selecciona un Camion");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_registrarActionPerformed

        String viaje = txt_viaje.getText();
        String conductor = txt_conductor.getText();

        if (!viaje.equals("") && !modelo.equals("") && !conductor.equals("") && !productos.equals("") && id_camion != 0 && id_remolque != 0) {

            try {

                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "insert into camiones values(?,?,?,?,?,?,?,?)");

                pst.setInt(1, 0);
                pst.setString(2, conductor);
                pst.setString(3, viaje);
                pst.setString(4, marca);
                pst.setString(5, modelo);
                pst.setString(6, productos);
                pst.setString(7, tipo_remolque);
                pst.setString(8, "Ocupado");
                pst.executeUpdate();

                txt_viaje.setBackground(Color.green);
                txt_conductor.setBackground(Color.green);
                tabla_camiones.setBackground(Color.green);
                tabla_remolques.setBackground(Color.green);

                
                cn.close();
                
                try {
                    
                    Connection cn2 = Conexion.conectar();
                    PreparedStatement pst2 = cn2.prepareStatement(
                        "update camiones set estatus = 'Ocupado' where ID = '" + id_camion + "'");
                    
                    pst2.executeUpdate();
                    
                    Connection cn3 = Conexion.conectar();
                    PreparedStatement pst3 = cn3.prepareStatement(
                        "update camiones set estatus = 'Ocupado' where ID = '" + id_remolque + "'");
                    
                    pst3.executeUpdate();
                    
                    cn2.close();
                    cn3.close();
                    
                } catch (SQLException e) {
                    System.out.println("Error al Colocar Camiones y Remolques Utilizados a ocupados: \n " + e);
                    JOptionPane.showMessageDialog(null, "Error al Colocar Camiones y Remolques Utilizados a ocupados, Contacte con el Administrador");
                }
                JOptionPane.showMessageDialog(null, "Viaje Registrado con Exito");
                this.dispose();

            } catch (SQLException e) {
                System.err.println("Error al registrar viaje: \n" + e);
                JOptionPane.showMessageDialog(null, "Error al registrar viaje, Contacte con el Administrador");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes llenar Y/O Seleccionar los campos");
        }
        
        

    }//GEN-LAST:event_boton_registrarActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrarViaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarViaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarViaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarViaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarViaje().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_registrar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane_camiones;
    private javax.swing.JScrollPane jScrollPane_remolque;
    private javax.swing.JTable tabla_camiones;
    private javax.swing.JTable tabla_remolques;
    private javax.swing.JTextField txt_conductor;
    private javax.swing.JTextField txt_viaje;
    // End of variables declaration//GEN-END:variables
}
