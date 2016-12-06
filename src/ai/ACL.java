package ai;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
//Agente que verifica a classificaçao de uma equipa 
public class ACL  extends Agent{

	protected void setup(){
		super.setup();
		this.addBehaviour(new ReceiveBehaviour());
	}
	
	
private class ReceiveBehaviour extends CyclicBehaviour {
		
		@Override
		public void action(){
			
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			MessageTemplate mtJ = MessageTemplate.MatchOntology("CLASS");
			MessageTemplate mtRespJog = MessageTemplate.and(mt, mtJ);
			ACLMessage msg = receive(mtRespJog);
			
			if(msg != null){
				SequentialBehaviour seq = new SequentialBehaviour();
				String equipas = msg.getContent();
				//SLB:FCP
				seq.addSubBehaviour(new sendMessageC(msg.getConversationId(),equipas));
				myAgent.addBehaviour(seq);
				
			}
			
				
			}
		}
	
	
	
	private class sendMessageC extends OneShotBehaviour{
		String id ;
		String equipa1;
		String equipa2;
	 public sendMessageC(String s,String eq) {
		id = s;
		//split
	}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("Critico");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setOntology("CLASS");
			msg.setConversationId(id);
			
			// meter o conteudo como sendo o calculo e 	msg.setContent("");
			
			float res = 1;//leitura 
			msg.setContent(String.valueOf(res));
			
			
			
			//
				msg.addReceiver(receiver);
				myAgent.send(msg);
			
			
		}
	
			
		}
}
