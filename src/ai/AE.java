package ai;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
//Agente que ve estado de uma equipa antes de uma jogo
public class AE  extends Agent{

	protected void setup(){
		super.setup();
		this.addBehaviour(new ReceiveBehaviour());
	}
	
	private class ReceiveBehaviour extends CyclicBehaviour {
		
		@Override
		public void action(){
			
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			MessageTemplate mtE = MessageTemplate.MatchOntology("ESTADO");
			MessageTemplate mtRespEstado = MessageTemplate.and(mt, mtE);
			ACLMessage msg = receive(mtRespEstado);
			
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
			msg.setOntology("ESTADO");
			msg.setConversationId(id);
			
			
			// meter o conteudo como sendo o calculo 	msg.setContent("");
			
			float res = 0;
			msg.setContent(String.valueOf(res));
			
			
			//
				msg.addReceiver(receiver);
				myAgent.send(msg);
			
			
		}
	
			
		}

}
