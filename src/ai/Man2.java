package ai;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
//Sender:sijade.Chines;receiver:sijade.Chines
public class Man2  extends Agent{

	protected void setup(){
		super.setup();
		this.addBehaviour(new ReceiveBehaviour());
	}
	
	/*
	 * 
	 * ->Critic [Equipa] Estado
	 * ->Critic [Equipa] Jogadores
	 * ->Critic [Equipa,Equipa] Historico
	 * ->Critic [Equipa] Classificacao
	 * 
	 * ->AE [Float,Equipa] Estado
	 * ->AJ [float,Equipa] Jogadores
	 * ->AH [INT] Historico
	 * ->ACL [INT,Equipa] Classificacao
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
	 * ->Critic [Float,Equipa] Estado
	 * ->Critic [float,Equipa] Jogadores
	 * ->Critic [INT] Historico
	 * ->Critic [INT,Equipa] Classificacao
	 * 
	 * ->AE [Equipa] Estado
	 * ->AJ [Equipa] Jogadores
	 * ->AH [Equipa,Equipa] Historico
	 * ->ACL [Equipa] Classificacao
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
