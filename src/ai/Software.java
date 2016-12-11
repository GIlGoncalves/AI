/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author gil
 */
public class Software extends GuiAgent{
    
    protected Inicio myGui;
    
    
    @Override
    protected void setup () {
        
        this.myGui = new Inicio(this);
        myGui.setVisible(true);
      
    }
    
    
     public class SendMessageMan extends CyclicBehaviour {
        String liga;
        
        public SendMessageMan(String conteudo) {
        
            liga=conteudo;
        
        }
        
        
        @Override
        public void action() {
        
            AID receiver = new AID();
            receiver.setLocalName("Man1");
            
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent(liga);
            myAgent.send(msg);
            block(1000);
            
        
        
        }
        
        
     }
    
     
     
      @Override
    protected void onGuiEvent(GuiEvent ge) {
     
        int command = ge.getType();
        
        
            
            String conteudo = (String) ge.getSource();
            AID recevir = new AID();
            recevir.setLocalName("Man1");
            
             ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
             
             msg.addReceiver(recevir);
             send(msg);
        
        
    
    } 
    
    
    
    
    
    
}
