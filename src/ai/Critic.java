package ai;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
//Critico - Responsavel por decidir quem ganha
public class Critic  extends Agent{

	protected void setup(){
		super.setup();
		this.addBehaviour(new ReceiveBehaviour());
	}
	
	/*
	 * 
	 * ->Man2 [Float,Equipa] Estado - Estado do jogo que a equipa vai ter, (descanso,etc)
	 * ->Man2 [float,Equipa] Jogadores - Recebe score dos jogdres de uma equipa
	 * ->Man2 [INT] Historico  - Int representa se a eq da casa ganha , perde ou empata
	 * ->Man2 [INT,Equipa] Classificacao - Recebe a classificacao de uma equipa 
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
	 * Pede ao Man2 a informacao
	 * ->M2 [Equipa] Estado   
	 * ->M2 [Equipa] Jogadores
	 * ->M2 [Equipa,Equipa] Historico
	 * ->M2 [Equipa] Classificacao
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
