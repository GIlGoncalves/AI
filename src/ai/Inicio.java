/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import jade.gui.GuiEvent;
import java.util.*;

import javax.swing.DefaultListModel;

/**
 *
 * @author gil
 */
public class Inicio extends javax.swing.JFrame {
  private final Map<String,String> nomes;
  private Software sof;
  
   
    
    /**
     * Creates new form Inicio
     */
  
    
    public Inicio () {
        
      
        
        initComponents();
        this.resultado.setVisible(false);
        this.vencedores.setVisible(false);
        this.nomes= new HashMap<>();
        this.nomes.put("Liga Portuguesa", "NOS");
        this.nomes.put("Liga Espanhola", "LFP");
        this.nomes.put("Premier League", "EPL");
        this.nomes.put("Bundesliga", "DFB");
        this.nomes.put("Serie A", "SA");
        this.nomes.put("Ligue 1", "FFF");
    
    }
  
    public Inicio(Software sofs) {
    	 this.sof = sofs;
        initComponents();
        this.nomes= new HashMap<>();
        this.nomes.put("Liga Portuguesa", "NOS");
        this.nomes.put("Liga Espanhola", "LFP");
        this.nomes.put("Premier League", "EPL");
        this.nomes.put("Bundesliga", "DFB");
        this.nomes.put("Serie A", "SA");
        this.nomes.put("Ligue 1", "FFF");
        
        
        
        this.apostar.setVisible(false);
        this.resultado.setVisible(false);
        this.vencedores.setVisible(false);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ligas = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        apostar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        vencedores = new javax.swing.JList();
        vencedores.setValueIsAdjusting(true);
        resultado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ligas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Liga Espanhola", "Premier League", "Bundesliga", "Serie A", "Liga Portuguesa", "Ligue 1" }));
        ligas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ligasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ligasMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Selecione a liga");

        apostar.setText("apostar");
        apostar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apostarMouseClicked(evt);
            }
        });

      //  vencedores.setColumns(20);
       // vencedores.setRows(5);
        jScrollPane1.setViewportView(vencedores);

        resultado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resultado.setForeground(new java.awt.Color(153, 0, 0));
        resultado.setText("Previs\u00F5es");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ai/o-jogador-de-futebol-em-um-fundo-abstrato_1048-450.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(ligas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(resultado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(apostar))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(ligas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(resultado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(apostar))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 public void updateL(LigaB liga){
	 DefaultListModel<String> model = new DefaultListModel<>();
		for(Prediction a : liga.getPred()){
			String s = new String();
			 if(a.getResultado()>1){
				if(a.getResultado()>=5)
					s =" SUPER VENCEDOR->"+ a.getSiglaA() +" vs "+a.getSiglaB();
					else s ="VENCEDOR->"+ a.getSiglaA() +" vs "+a.getSiglaB();
			}
				
			else if(a.getResultado()<-1){
				if(a.getResultado()<= -5)
				s = a.getSiglaA() +" vs "+a.getSiglaB()+"<-SUPER VENCEDOR";
				else s = a.getSiglaA() +" vs "+a.getSiglaB()+"<- VENCEDOR";
			}else
s = a.getSiglaA() +" EMPATE "+a.getSiglaB();
			
				
			model.addElement(s);
		}
		vencedores.removeAll();
		vencedores.setModel(model);
 }
    
    private void ligasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ligasMouseClicked
        // TODO add your handling code here:
        
        if(this.ligas.getSelectedItem().toString().equals("")) {
            
        	  this.apostar.setVisible(false);
              
              this.resultado.setVisible(false);
          
              this.vencedores.setVisible(false);
           
           
            
            
            
             }
        
        else {
        	 this.apostar.setVisible(true);
          
           
            
        }
      
        
        
    }//GEN-LAST:event_ligasMouseClicked

    private void ligasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ligasMousePressed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_ligasMousePressed

    private void apostarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apostarMouseClicked
        // TODO add your handling code here:
        String a = this.nomes.get(this.ligas.getSelectedItem().toString());
        
        this.resultado.setVisible(true);
           
        this.vencedores.setVisible(true);
            
        GuiEvent ge = new GuiEvent(a,1);
            
       
        sof.postGuiEvent(ge);

        
        
        
        
    }//GEN-LAST:event_apostarMouseClicked

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
    private javax.swing.JButton apostar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> ligas;
    private javax.swing.JLabel resultado;
    private javax.swing.JList vencedores;
    // End of variables declaration//GEN-END:variables
}
