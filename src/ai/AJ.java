package ai;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
//Agente que verifica o score de um jogador 
public class AJ  extends Agent{

	protected void setup(){
		super.setup();
		this.addBehaviour(new ReceiveBehaviour());
	}
	
	
private class ReceiveBehaviour extends CyclicBehaviour {
		
		@Override
		public void action(){
			
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			MessageTemplate mtJ = MessageTemplate.MatchOntology("JOGADORES");
			MessageTemplate mtRespJog = MessageTemplate.and(mt, mtJ);
			ACLMessage msg = receive(mtRespJog);
			
			if(msg != null){
				SequentialBehaviour seq = new SequentialBehaviour();
				
				seq.addSubBehaviour(new sendMessageC(msg.getConversationId()));
				myAgent.addBehaviour(seq);
				
			}
			
				
			}
		}
	
	
	
	private class sendMessageC extends OneShotBehaviour{
		String id ;
	 public sendMessageC(String s) {
		id = s;
	}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("Critic");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setOntology("JOGADORES");
			msg.setConversationId(id);
			
			
			// meter o conteudo como sendo o calculo e 	msg.setContent("");
			
			
			float res = 1;
			msg.setContent(String.valueOf(res));
			
			//
				msg.addReceiver(receiver);
				myAgent.send(msg);
			
			
		}
	
			
		}
}
