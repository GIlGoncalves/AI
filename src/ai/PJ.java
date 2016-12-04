package ai;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

//Agente que procura os jogos de uma liga 
public class PJ  extends Agent{

	protected void setup(){
		super.setup();
		this.addBehaviour(new ReceiveBehaviour());
	}
	
	
	private class ReceiveBehaviour extends CyclicBehaviour {
		
		@Override
		public void action(){
			
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			MessageTemplate mtJ = MessageTemplate.MatchOntology("JOGOS");
			MessageTemplate mtRespJogo = MessageTemplate.and(mt, mtJ);
			ACLMessage msg = receive(mtRespJogo);
			
			if(msg != null){
				LigaB newliga;
				try {
					newliga = (LigaB) msg.getContentObject();
					SequentialBehaviour seq = new SequentialBehaviour();
					
					seq.addSubBehaviour(new sendMessage(newliga));
					myAgent.addBehaviour(seq);
				} catch (Exception e) {
					// Nao deu
				}
				
			}
			
				
			}
		}
	
	
	
	private class sendMessage extends OneShotBehaviour{
		LigaB liga;
		public sendMessage(LigaB liga){
			super();
			this.liga= liga;
		}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("Man1");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setOntology("JOGOS");
			try {
				//Inserir na liga  as  predictions com as Equipas a e B ,  o int diz se a equipa a joga em casa ou ao e o resultado a 0
				
				msg.setContentObject(this.liga);
				msg.addReceiver(receiver);
				myAgent.send(msg);
			} catch (Exception e) {
				// Nao deu
			
			}
			
		}
	
			
		}
		
		
	
}
