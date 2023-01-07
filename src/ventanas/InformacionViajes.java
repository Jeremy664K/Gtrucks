/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.awt.Color;
import javax.swing.WindowConstants;
import java.sql.*;
import clases.Conexion;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jeremy
 */
public class InformacionViajes extends javax.swing.JFrame {

    /**
     * Creates new form InformacionCamion
     */
    String user = Login.user;
    int id_viaje = GestionDeViajes.id_viajeUpdate;
    String modelo = "", marca = "", productos = "", tipo_remolque = "";
    int id_camion = 0;
    int id_remolque = 0;

    public InformacionViajes() {
        initComponents();

        this.setTitle("Informacion de Viaje");
        this.setSize(695, 420);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select conductor, viaje from camiones where ID = '" + id_viaje + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                txt_conductor.setText(rs.getString(1));
                txt_viaje.setText(rs.getString(2));

            }

        } catch (SQLException e) {
            System.err.println("Error al Seleccionar Conductor y Viaje (InformacionViajes)");
            JOptionPane.showMessageDialog(null, "Error al Seleccionar Conductor y Viaje, Contacte con el Administrador");

        }
        try {

            DefaultTableModel model = new DefaultTableModel();

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select ID, marca, modelo from camiones where ID = '" + id_viaje + "' and marca != 'No Asignado' and modelo != 'No Asignado'");

            ResultSet rs = pst.executeQuery();

            tabla_camiones = new JTable(model);
            jScrollPane_camiones.setViewportView(tabla_camiones);

            model.addColumn("ID");
            model.addColumn("Marca");
            model.addColumn("Modelo");

            while (rs.next()) {
                Object fila1[] = new Object[3];

                for (int i = 0; i < 3; i++) {

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
            System.err.println("Error al llenar la tabla de Informacion Camion");
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla, Contacte con el Administrador");
        }

        try {

            DefaultTableModel model = new DefaultTableModel();

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select ID, productos, tipo_remolque, estatus from camiones where ID = '" + id_viaje + "' and productos != 'No Asignado' and tipo_remolque != 'No Asignado'");

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
            System.err.println("Error, al rellenar la tabla de llenar remolques (InformacionViajes): \n " + e);
            JOptionPane.showMessageDialog(null, "Error, al rellenar la tabla de Remolques, contacte con el Administrador");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_conductor = new javax.swing.JTextField();
        boton_mostrarTodo = new javax.swing.JButton();
        boton_mostrarPredeterminado = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_viaje = new javax.swing.JTextField();
        jScrollPane_remolque = new javax.swing.JScrollPane();
        tabla_remolques = new javax.swing.JTable();
        jScrollPane_camiones = new javax.swing.JScrollPane();
        tabla_camiones = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        boton_modificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("Informacion de Viaje");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Remolque;");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, -1, -1));

        txt_conductor.setBackground(new java.awt.Color(255, 153, 0));
        txt_conductor.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_conductor.setForeground(new java.awt.Color(255, 255, 255));
        txt_conductor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_conductor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_conductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, 29));

        boton_mostrarTodo.setBackground(new java.awt.Color(255, 153, 0));
        boton_mostrarTodo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        boton_mostrarTodo.setForeground(new java.awt.Color(255, 255, 255));
        boton_mostrarTodo.setText("Mostrar Todo");
        boton_mostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_mostrarTodoActionPerformed(evt);
            }
        });
        getContentPane().add(boton_mostrarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 230, 40));

        boton_mostrarPredeterminado.setBackground(new java.awt.Color(255, 153, 0));
        boton_mostrarPredeterminado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        boton_mostrarPredeterminado.setForeground(new java.awt.Color(255, 255, 255));
        boton_mostrarPredeterminado.setText("Mostrar Lo Predeterminado");
        boton_mostrarPredeterminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_mostrarPredeterminadoActionPerformed(evt);
            }
        });
        getContentPane().add(boton_mostrarPredeterminado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 230, 40));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Viaje:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        txt_viaje.setBackground(new java.awt.Color(255, 153, 0));
        txt_viaje.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txt_viaje.setForeground(new java.awt.Color(255, 255, 255));
        txt_viaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_viaje.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_viaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 230, 29));

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

        getContentPane().add(jScrollPane_remolque, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 380, 140));

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

        getContentPane().add(jScrollPane_camiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 380, 140));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Conductor:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Camion:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, -1, -1));

        boton_modificar.setBackground(new java.awt.Color(255, 153, 0));
        boton_modificar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        boton_modificar.setForeground(new java.awt.Color(255, 255, 255));
        boton_modificar.setText("Modificar");
        boton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_modificarActionPerformed(evt);
            }
        });
        getContentPane().add(boton_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 230, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_mostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_mostrarTodoActionPerformed

        try {

            DefaultTableModel model = new DefaultTableModel();

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select ID, productos, tipo_remolque, estatus from camiones where productos != 'No Asignado' and tipo_remolque != 'No Asignado'");

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
            System.err.println("Error, al rellenar la tabla de llenar remolques (InformacionViajes): \n " + e);
            JOptionPane.showMessageDialog(null, "Error, al rellenar la tabla de Remolques, contacte con el Administrador");
        }

        try {
            DefaultTableModel model = new DefaultTableModel();

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select ID, marca, modelo from camiones where marca != 'No Asignado' and modelo != 'No Asignado'");

            ResultSet rs = pst.executeQuery();

            tabla_camiones = new JTable(model);
            jScrollPane_camiones.setViewportView(tabla_camiones);

            model.addColumn("ID");
            model.addColumn("Marca");
            model.addColumn("Modelo");

            while (rs.next()) {
                Object fila1[] = new Object[3];

                for (int i = 0; i < 3; i++) {

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
            System.err.println("Error, al rellenar la tabla de Camiones (InformacionViajes): \n " + e);
            JOptionPane.showMessageDialog(null, "Error, al rellenar la tabla de Camiones, contacte con el Administrador");
        }
    }//GEN-LAST:event_boton_mostrarTodoActionPerformed

    private void boton_mostrarPredeterminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_mostrarPredeterminadoActionPerformed

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select conductor, viaje from camiones where ID = '" + id_viaje + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                txt_conductor.setText(rs.getString(1));
                txt_viaje.setText(rs.getString(2));

            }

        } catch (SQLException e) {
            System.err.println("Error al Seleccionar Conductor y Viaje (InformacionViajes)");
            JOptionPane.showMessageDialog(null, "Error al Seleccionar Conductor y Viaje, Contacte con el Administrador");

        }
        
        
        try {

            DefaultTableModel model = new DefaultTableModel();

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select ID, marca, modelo from camiones where ID = '" + id_viaje + "' and marca != 'No Asignado' and modelo != 'No Asignado'");

            ResultSet rs = pst.executeQuery();

            tabla_camiones = new JTable(model);
            jScrollPane_camiones.setViewportView(tabla_camiones);

            model.addColumn("ID");
            model.addColumn("Marca");
            model.addColumn("Modelo");

            while (rs.next()) {
                Object fila1[] = new Object[3];

                for (int i = 0; i < 3; i++) {

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
            System.err.println("Error al llenar la tabla de Informacion Camion");
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla, Contacte con el Administrador");
        }

        try {

            DefaultTableModel model = new DefaultTableModel();

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select ID, productos, tipo_remolque, estatus from camiones where ID = '" + id_viaje + "' and productos != 'No Asignado' and tipo_remolque != 'No Asignado'");

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
            System.err.println("Error, al rellenar la tabla de llenar remolques (InformacionViajes): \n " + e);
            JOptionPane.showMessageDialog(null, "Error, al rellenar la tabla de Remolques, contacte con el Administrador");
        }
    }//GEN-LAST:event_boton_mostrarPredeterminadoActionPerformed

    private void boton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_modificarActionPerformed
        
        String conductor = txt_conductor.getText();
        String viaje = txt_viaje.getText();
        
        try {
            
            if(!conductor.equals("") && !viaje.equals("") && !marca.equals("") && !modelo.equals("") && !productos.equals("") && !tipo_remolque.equals("")){
            
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                "update camiones set conductor = ?, viaje = ?, marca = ?, modelo = ?, productos = ?, tipo_remolque = ?, estatus = ? where ID = '" + id_viaje + "'");
            
            pst.setString(1, conductor);
            pst.setString(2, viaje);
            pst.setString(3, marca);
            pst.setString(4, modelo);
            pst.setString(5, productos);
            pst.setString(6, tipo_remolque);
            pst.setString(7, "Ocupado");
            pst.executeUpdate();
            
            txt_conductor.setBackground(Color.green);
            txt_viaje.setBackground(Color.green);
            tabla_camiones.setBackground(Color.green);
            tabla_remolques.setBackground(Color.green);
            
            JOptionPane.showMessageDialog(null, "Viaje Modificado con Exito");
            this.dispose();
            
            } else {
                JOptionPane.showMessageDialog(null, "Debes Llenar Y/O Seleccionar todos los Campos");
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println("Error al Modificar Viaje (InformacionViajes): \n" + e);
            JOptionPane.showMessageDialog(null, "Error al Modificar Viaje, Contacte con el Administrador");
        }
        
    }//GEN-LAST:event_boton_modificarActionPerformed

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
            java.util.logging.Logger.getLogger(InformacionViajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionViajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionViajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionViajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionViajes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_modificar;
    private javax.swing.JButton boton_mostrarPredeterminado;
    private javax.swing.JButton boton_mostrarTodo;
    private javax.swing.JLabel jLabel1;
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
