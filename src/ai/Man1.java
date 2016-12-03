package ai;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
//Sender:sijade.Chines;receiver:sijade.Chines
public class Man1  extends Agent{

	protected void setup(){
		super.setup();
		this.addBehaviour(new ReceiveBehaviour());
	}
	
	/*
	 * 
	 * ->Software [LigaB] INFOLIGA
	 * ->PJ [LigaB] Jogos
	 * ->Critic [LigaB] Avaliar
	 * 
	 */
	private class ReceiveBehaviour extends CyclicBehaviour {
		
		@Override
		public void action(){
			ACLMessage msg = receive();
			if(msg != null){
				/*
				System.out.println("Ola mano " + msg.getSender() + "eu acho que " + msg.getContent());
				ACLMessage rsp = msg.createReply();
				
				if(msg.getContent().equals("Ping")){
					
					rsp.setContent("Pong");
					rsp.setPerformative(ACLMessage.INFORM);
				}
				else if(msg.getContent().equals("Pong")){
					rsp.setContent("Ping");
					rsp.setPerformative(ACLMessage.INFORM);
				}
				else {
					rsp.setContent("Raquete na testa");
					rsp.setPerformative(ACLMessage.NOT_UNDERSTOOD);
				}
				send(rsp);                                                                 */
			}
			block();
		}
		}
	/*
	 * 
	 * ->Sofwtare [LigaB] INFOLIGA
	 * ->PJ [LigaB] Jogos
	 * ->Critic [LigaB] Avaliar
	 * 
	 */
	private class sendMessage extends SimpleBehaviour{
		
		@Override
		public int onEnd(){
			
			myAgent.doDelete();
			return 0; 
		}
		
		@Override 
		public void action(){
			
			AID receiver = new AID();
			receiver.setLocalName("pingaponga");
			
			
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			long time = System.currentTimeMillis();
			msg.setConversationId(" " + time);
			msg.addReceiver(receiver);
			

				
				msg.setContent("Ping");
				
		
		
			myAgent.send(msg);
			
		}
		
		@Override 
		public boolean done(){
			return true;
		}
	}
}
