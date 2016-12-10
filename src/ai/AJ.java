package ai;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.List;
import java.util.Map;
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
				String equipas = msg.getContent();
				seq.addSubBehaviour(new sendMessageC(msg.getConversationId(), equipas));
				myAgent.addBehaviour(seq);
				
			}else block();
		
				
			}
		
		}
	
	
	
	private class sendMessageC extends OneShotBehaviour{
		String id ;
                String nomeLiga;
                String nomeEquipaC;
                String nomeEquipaF;
	 public sendMessageC(String s,String equipa) {
		id = s;
                String[] parts = equipa.split(":");
		nomeEquipaC = parts[0];
		nomeEquipaF = parts[1];
		nomeLiga = parts[2];
                
                
	}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("Critic");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setOntology("JOGADORES");
			msg.setConversationId(id);
                       Leitura l = new Leitura();
                       TrataInformacao t = new TrataInformacao();
                       
                        Map<String,List<Jogador>> equipaC = t.equipa(l.lerJogadores(nomeLiga,nomeEquipaC));
                        Map<String,List<Jogador>> equipaF = t.equipa(l.lerJogadores(nomeLiga,nomeEquipaF));
                        float mediaJogadoresF = t.mediaJogadores();
                        float mediaJogadoresC = t.mediaJogadores();
                        float mediaIdadeF = t.mediaIdade();
                        float mediaIdadeC = t.mediaIdade();
                        
			float resJ = mediaJogadoresC-mediaJogadoresF;
                        float resI = mediaIdadeC-mediaIdadeF;
			
			// meter o conteudo como sendo o calculo e 	msg.setContent("");
			
			msg.setContent(String.valueOf(resJ));
                        msg.setContent(String.valueOf(resI));
			
			//
				msg.addReceiver(receiver);
				myAgent.send(msg);
			
			
		}
	
			
		}
}
