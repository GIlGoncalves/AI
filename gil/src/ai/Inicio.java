/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gil
 */
public class Inicio extends javax.swing.JFrame {

    private final String [] ligaPortuguesa = {"Arouca","Belenenses","Benfica","Boavista","Desp. Chaves"
    ,"Estoril","FC Porto","Feirense","Marítimo","Moreirense","Nacional","Paços Ferreira"
    ,"Rio Ave","Sp. Braga","Sporting","Tondela","V. Guimarães","V. Setúbal"};
    private Leitura leitura;
    private NomesEquipas equipa;
    private TrataInformacao info;
    
    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        this.leitura= new Leitura();
        this.equipa = new NomesEquipas();
        this.info = new TrataInformacao();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        equipaCasa = new javax.swing.JComboBox<>();
        ligas = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        equipaFora = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\gil\\Pictures\\frases-de-futebol.jpg")); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel1.setText("Equipa da casa");

        ligas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Liga Espanhola", "Premier League", "Bundesliga", "Serie A", "Liga Portuguesa", "Ligue 1" }));
        ligas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ligasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ligasMousePressed(evt);
            }
        });

        jLabel3.setText("Selecione a liga");

        jLabel4.setText("Equipa fora");

        jButton1.setText("apostar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(ligas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(equipaCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(equipaFora, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton1)))
                .addContainerGap(349, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(ligas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(equipaCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(equipaFora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ligasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ligasMouseClicked
        // TODO add your handling code here:
        int i;
        if(this.ligas.getSelectedItem().toString().equals("Liga Portuguesa")) {
             this.equipaCasa.setVisible(true);
            this.equipaFora.setVisible(true);
             this.equipaCasa.removeAllItems();
            this.equipaFora.removeAllItems();
            
             for(i=0;i<this.ligaPortuguesa.length;i++) { 
             
            this.equipaCasa.addItem(this.ligaPortuguesa[i]);
            this.equipaFora.addItem(this.ligaPortuguesa[i]);
             }
        }
        else {
           
            this.equipaCasa.setVisible(false);
            this.equipaFora.setVisible(false);
            
            this.equipaCasa.removeAllItems();
            this.equipaFora.removeAllItems();
             
        }
      
        
        
    }//GEN-LAST:event_ligasMouseClicked

    private void ligasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ligasMousePressed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_ligasMousePressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        
        if(this.equipaCasa.getSelectedItem().toString().equals(this.equipaFora.getSelectedItem().toString())) {
        
             JOptionPane.showMessageDialog(null, "As equipas tem de ser diferentes para poder apostar","Equipas invalidas",JOptionPane.ERROR_MESSAGE);
        
        }
        
        else {
            this.info = new TrataInformacao();
            
            String liga = this.ligas.getSelectedItem().toString();
            String equipaC = this.equipaCasa.getSelectedItem().toString();
            String equipaF = this.equipaFora.getSelectedItem().toString();
            String [ ]equipas;
            equipas =this.equipa.procuraEquipa(liga, equipaC, equipaF);
            
           
            
            this.info.equipa(this.leitura.lerJogadores(equipas[0]));
             
        System.out.println(this.info.toString());
        
            this.info.tamanhao();
             
        }  
        
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new Inicio().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> equipaCasa;
    private javax.swing.JComboBox<String> equipaFora;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> ligas;
    // End of variables declaration//GEN-END:variables
}
