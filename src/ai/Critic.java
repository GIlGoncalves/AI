package ai;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
//Critico - Responsavel por decidir quem ganha
public class Critic  extends Agent{
private LigaB ligacritic;
private float [][]  res;
	protected void setup(){
		super.setup();
		ligacritic = new LigaB();
		
		this.addBehaviour(new ReceiveBehaviour());
	}
	
	
	private class CriticBehaviour extends OneShotBehaviour{
		String at ;
		String bt;
		int i;
		int casa;
		
 public CriticBehaviour(int iv ,Equipa a, Equipa b , int casav) {
		at = a.getNome();
		bt = b.getNome();
		casa = casav;
		i = iv;
		
	}
		@Override 
		public void action(){
			 ParallelBehaviour par = new ParallelBehaviour( ParallelBehaviour.WHEN_ALL );
			 SequentialBehaviour seq1 = new SequentialBehaviour();
			 SequentialBehaviour seq2 = new SequentialBehaviour();
			 SequentialBehaviour seq3 = new SequentialBehaviour();
			 SequentialBehaviour seq4 = new SequentialBehaviour();
				
				seq1.addSubBehaviour(new sendMessageAE(at,bt,i));
				seq1.addSubBehaviour(new receiveMessageAE());
				seq2.addSubBehaviour(new sendMessageAJ(at,bt,i));
				seq2.addSubBehaviour(new receiveMessageAJ());
				seq3.addSubBehaviour(new sendMessageAH(at,bt,i));
				seq3.addSubBehaviour(new receiveMessageAH());
				seq4.addSubBehaviour(new sendMessageACL(at,bt,i));
				seq4.addSubBehaviour(new receiveMessageACL());
				
				par.addSubBehaviour(seq1);
				par.addSubBehaviour(seq2);
				par.addSubBehaviour(seq3); 
				par.addSubBehaviour(seq4); 
				
				myAgent.addBehaviour(par);
				
		}
		
		
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
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			MessageTemplate mtC = MessageTemplate.MatchOntology("Critica");
			MessageTemplate mtRespCritico = MessageTemplate.and(mt, mtC);
			ACLMessage msg = receive(mtRespCritico);
			
			if(msg != null){
				LigaB newliga;
				try {
					newliga = (LigaB) msg.getContentObject();
					res = new float [newliga.Jogos.size()][4] ;
					int i = 0;
					SequentialBehaviour seq = new SequentialBehaviour();
					 ParallelBehaviour par = new ParallelBehaviour( ParallelBehaviour.WHEN_ALL );
					for(Prediction a : newliga.Jogos){
						par.addSubBehaviour(new CriticBehaviour(i,a.A,a.B,a.Casa) );
						i++;
					}
					seq.addSubBehaviour(par);
					seq.addSubBehaviour(new sendMessageMan());
					myAgent.addBehaviour(seq);
				} catch (Exception e) {
					// Nao deu
				}
				                             
			}
			
		}
		}
	
	
	
	private class receiveMessageAE extends ReceiveBehaviour{
		
		@Override
		public void action(){
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		MessageTemplate mtE = MessageTemplate.MatchOntology("ESTADO");
		MessageTemplate mtRespEstado = MessageTemplate.and(mt, mtE);
		ACLMessage msg = receive(mtRespEstado);
		
		if(msg != null){    
			res[Integer.valueOf(msg.getConversationId())][0] =  Float.valueOf(msg.getContent());
			
		}
		
		
		}
	}
	
	private class receiveMessageAJ extends ReceiveBehaviour{
		
		@Override
		public void action(){
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		MessageTemplate mtJ = MessageTemplate.MatchOntology("JOGADORES");
		MessageTemplate mtRespJog= MessageTemplate.and(mt, mtJ);
		ACLMessage msg = receive(mtRespJog);
		
		if(msg != null){    
			res[Integer.valueOf(msg.getConversationId())][1] =  Float.valueOf(msg.getContent());
			
		}
		
		
		}
	}
	
	private class receiveMessageAH extends ReceiveBehaviour{
		
		@Override
		public void action(){
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		MessageTemplate mtH = MessageTemplate.MatchOntology("HISTORICO");
		MessageTemplate mtRespHist= MessageTemplate.and(mt, mtH);
		ACLMessage msg = receive(mtRespHist);
		
		if(msg != null){    
			res[Integer.valueOf(msg.getConversationId())][2] =  Float.valueOf(msg.getContent());
			
		}
		
		
		}
	}
	
	
	private class receiveMessageACL extends ReceiveBehaviour{
		
		@Override
		public void action(){
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		MessageTemplate mtC = MessageTemplate.MatchOntology("CLASS");
		MessageTemplate mtRespClass= MessageTemplate.and(mt, mtC);
		ACLMessage msg = receive(mtRespClass);
		
		if(msg != null){    
			res[Integer.valueOf(msg.getConversationId())][3] =  Float.valueOf(msg.getContent());
			
		}
		
		
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
	
	
	private class sendMessageAE extends OneShotBehaviour{
		String a;
		String b;
		String id;
		public sendMessageAE(String av, String bv,int id){
			super();
			this.a= av;
			this.b= bv;
			this.id = Integer.toString(id);
		}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("AE");
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.setOntology("ESTADO");
			msg.setConversationId(id);
			
				String s = a.concat(":");
				s = s.concat(b);
				msg.setContent(s);
				msg.addReceiver(receiver);
				myAgent.send(msg);
		
			
		}
	
			
		}
	
	private class sendMessageAJ extends OneShotBehaviour{
		String a;
		String b;
		String id;
		public sendMessageAJ(String av, String bv,int id){
			super();
			this.a= av;
			this.b= bv;
			this.id = Integer.toString(id);
		}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("AJ");
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.setOntology("JOGADORES");
			msg.setConversationId(id);
			
				String s = a.concat(":");
				s = s.concat(b);
				msg.setContent(s);
				msg.addReceiver(receiver);
				myAgent.send(msg);
		
			
		}
	
			
		}
	
	private class sendMessageAH extends OneShotBehaviour{
		String a;
		String b;
		String id;
		public sendMessageAH(String av, String bv,int id){
			super();
			this.a= av;
			this.b= bv;
			this.id = Integer.toString(id);
		}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("AH");
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.setOntology("HISTORICO");
			msg.setConversationId(id);
			
				String s = a.concat(":");
				s = s.concat(b);
				msg.setContent(s);
				msg.addReceiver(receiver);
				myAgent.send(msg);
		
			
		}
	
			
		}
	
	
	private class sendMessageACL extends OneShotBehaviour{
		String a;
		String b;
		String id;
		public sendMessageACL(String av, String bv,int id){
			super();
			this.a= av;
			this.b= bv;
			this.id = Integer.toString(id);
		}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("ACL");
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.setOntology("CLASS");
			msg.setConversationId(id);
			
				String s = a.concat(":");
				s = s.concat(b);
				msg.setContent(s);
				msg.addReceiver(receiver);
				myAgent.send(msg);
		
			
		}
	
			
		}
	
	private class sendMessageMan extends OneShotBehaviour{
		LigaB liga;
		public sendMessageMan(){
			super();
			this.liga= new LigaB();
		}
		@Override 
		public void action(){
			
			//FAZ CRITICAS  COM O RES E DEPOIS MANDA
			AID receiver = new AID();
			receiver.setLocalName("Man1");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setOntology("Critica");
			try {
				msg.setContentObject(this.liga);
				msg.addReceiver(receiver);
				myAgent.send(msg);
			} catch (Exception e) {
				// Nao deu
			
			}
			
		}
		
		
	}

}
