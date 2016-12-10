package ai;
import java.util.GregorianCalendar;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
//Agente que ve estado de uma equipa antes de uma jogo
public class AE  extends Agent{

        @Override
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
				String equipas = msg.getContent();
				seq.addSubBehaviour(new sendMessageC(msg.getConversationId(), equipas));
				myAgent.addBehaviour(seq);
				
			}else block();
			
		
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
			msg.setOntology("ESTADO");
			msg.setConversationId(id);
			//////
			Leitura l = new Leitura();
			GregorianCalendar dataC = l.ultimoJogo(equipa1, liga);
			GregorianCalendar dataF = l.ultimoJogo(equipa2, liga);
			float res;
                       // se for 1 significa que a equipa da casa jogou primeiro se for -1 foi a equipa fora  
                        if(dataC.before(dataF)) {
                        
                            res=1;
                        }
                        else
                        {
                           if(dataC.after(dataF)) {
                           
                               res=-1;
                           }
                           else res=0;
                        }
                                
                       
			/////
			msg.setContent(String.valueOf(res));
			msg.addReceiver(receiver);
			myAgent.send(msg);
			
			
		}
	
			
		}

}
