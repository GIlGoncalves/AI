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
        super.setup();
        this.myGui = new Inicio(this);
        myGui.setVisible(true);
        this.addBehaviour(new ReceiveCEnas());
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
     
     
        AID receiver = new AID();
		receiver.setLocalName("Man1");
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setOntology("INFOLIGA");
		msg.addReceiver(receiver);
		  String conteudo = (String) ge.getSource();
		msg.setContent(conteudo);
		
		send(msg);
        
            
          
          
        
        
    
    } 
    
      private class ReceiveCEnas extends CyclicBehaviour {
  		public void action() {
  			ACLMessage msg = null;
  			if ((msg = myAgent.receive()) != null)
  			{
  			System.out.println("==== New message received ===");
  			System.out.println("= Sender : "+msg.getSender().getLocalName());
  			try {
  				 LigaB newliga = (LigaB) msg.getContentObject();
  				
  				myGui.updateL(newliga);
  				
  				
  				
  			} catch (Exception e) {
  				// Nao deu
  			}
  		
  			
  			} else {
  			block();
  			}
  		}
  	}
    
    
    
    
    
}
