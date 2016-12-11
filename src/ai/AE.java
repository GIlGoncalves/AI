package ai;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
		
			
			
              
              TrataInformacao equipaCasa = new TrataInformacao();
              TrataInformacao equipaFora = new TrataInformacao();
              
               Map<String,List<Jogador>> equipaC = equipaCasa.equipa(l.lerJogadores(liga,equipa1));
               Map<String,List<Jogador>> equipaF = equipaFora.equipa(l.lerJogadores(liga,equipa2));
               float mediaIdadeF = equipaFora.mediaIdade();
               float mediaIdadeC = equipaCasa.mediaIdade();
              float res = 0;
			double restemp = 0;
                       // se for 1 significa que a equipa da casa jogou primeiro se for -1 foi a equipa fora  
                        if(dataC.before(dataF)) {
                        
                        	 double res1 = (5 - mediaIdadeC) * 0.25;
                        	 double res2 = (2 - mediaIdadeF)* 0.25; 
                        	  restemp =  res1-res2;
                 }
                        else
                        {
                           if(dataC.after(dataF)) {
                           
                        	   double res1 = (2 - mediaIdadeC) * 0.25;
                          	 double res2 = (5 - mediaIdadeF)* 0.25; 
                          	  restemp = (float)res1-res2;
                           }
                           else{  restemp = (mediaIdadeC-mediaIdadeF)*0.25;}
                        }
                                
                       
			/////
                        res = (float) restemp;
			msg.setContent(String.valueOf((float)res));
			msg.addReceiver(receiver);
			myAgent.send(msg);
			
			
		}
	
			
		}

}
