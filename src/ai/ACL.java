package ai;
import java.io.IOException;

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
		String liga;
	 public sendMessageC(String s,String eq) {
		id = s;
	
		String[] parts = eq.split(":");
		equipa1 = parts[0];
		equipa2 = parts[1];
		liga = parts[2];
		
		
	}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("Critic");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setOntology("CLASS");
			msg.setConversationId(id);
			Leitura l = new Leitura();
			float foraP= 0,casaP= 0;
			//ISTO CRASHA
			try {
				casaP = (float) l.lerJornadas(equipa1, liga);
				 foraP = (float)l.lerJornadas(equipa2, liga);
			} catch (IOException e) {
				// Nao deu
			}
			foraP= 2;casaP= 5;
			float res = 3;//casaP-foraP;
			msg.setContent(String.valueOf(res));
			
			
			
			//
				msg.addReceiver(receiver);
				myAgent.send(msg);
			
			
		}
	
			
		}
}
