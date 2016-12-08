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

	protected void setup(){
		super.setup();
		
		
		this.addBehaviour(new ReceiveBehaviour());
	}
	
	
	
		

	/*
	 * 
	 * Behaviour principal de receber  
	 * 
	 */
	private class ReceiveBehaviour extends CyclicBehaviour {
		
		@Override
		public void action(){
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			MessageTemplate mtC = MessageTemplate.MatchOntology("CRITIC");
			MessageTemplate mtRespCritico = MessageTemplate.and(mt, mtC);
			ACLMessage msg = receive(mtRespCritico);
			
			if(msg != null){
				
				try {
					
					ligacritic = (LigaB) msg.getContentObject();
					ligacritic.criaRes(ligacritic.getNJogos());
					int i = 0;
					SequentialBehaviour seq = new SequentialBehaviour();
					 ParallelBehaviour par = new ParallelBehaviour( ParallelBehaviour.WHEN_ALL );
					 
					for(Prediction a : ligacritic.getPred()){
					 par.addSubBehaviour( CriticBehaviour(i,a.getSiglaA(),a.getSiglaB(),ligacritic.getNome()));	
						
					  
						
						a.setIndex(i);
						i++;
						
					}
					 seq.addSubBehaviour(par);
					seq.addSubBehaviour(new sendMessageMan());
					addBehaviour(seq);
					
				} catch (Exception e) {
					// Nao deu
				}
				                             
			}else block();
			
			
		}
		}
	


	/*
	*
	*Behaviours que recebem mensagens dos agentes 
	*
	*/
public class receiveMessageAE extends SimpleBehaviour {


	  
	  private boolean finished = false;
		
		
		
		@Override
		public void action(){
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
			MessageTemplate mtE = MessageTemplate.MatchOntology("ESTADO");
			MessageTemplate mtRespEstado = MessageTemplate.and(mt, mtE);
			ACLMessage msg = receive(mtRespEstado);
			
			if(msg != null){    
				ligacritic.updateRes(Integer.valueOf(msg.getConversationId()),0,  Float.valueOf(msg.getContent()));
			finished = true;
		}else block();
		
		
		}

	  public boolean done() {
	    return finished;
	  }
	}

		
	
	public class receiveMessageAJ extends SimpleBehaviour {


		  
		  private boolean finished = false;
			
			
			
			@Override
			public void action(){
			
				MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
				MessageTemplate mtJ = MessageTemplate.MatchOntology("JOGADORES");
				MessageTemplate mtRespJog= MessageTemplate.and(mt, mtJ);
				ACLMessage msg = receive(mtRespJog);
				
				if(msg != null){    
			
					ligacritic.updateRes(Integer.valueOf(msg.getConversationId()),1,  Float.valueOf(msg.getContent()));
				finished = true;
			}else block();
			
			
			}

		  public boolean done() {
		    return finished;
		  }
		}
	

	
	public class receiveMessageAH extends SimpleBehaviour {


		  
		  private boolean finished = false;
			
			
			
			@Override
			public void action(){
			
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
			MessageTemplate mtH = MessageTemplate.MatchOntology("HISTORICO");
			MessageTemplate mtRespHist= MessageTemplate.and(mt, mtH);
			ACLMessage msg = receive(mtRespHist);
			
			if(msg != null){    
			
				ligacritic.updateRes(Integer.valueOf(msg.getConversationId()),2,  Float.valueOf(msg.getContent()));
				finished = true;
			}else block();
			
			
			}

		  public boolean done() {
		    return finished;
		  }
		}
	

public class receiveMessageACL extends SimpleBehaviour {


  
  private boolean finished = false;
	
	@Override
	public void action() 
	{
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		MessageTemplate mtC = MessageTemplate.MatchOntology("CLASS");
		MessageTemplate mtRespClass= MessageTemplate.and(mt, mtC);
		ACLMessage msg = receive(mtRespClass);
		
		if(msg != null){    
			
			ligacritic.updateRes(Integer.valueOf(msg.getConversationId()),3,  Float.valueOf(msg.getContent()));
			
			 finished = true;
			
		}else {
		      block();
	    }
		
	}

  public boolean done() {
    return finished;
  }
}

	/*
	*
	*Behaviours que mandam mensagens aos agentes 
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
		String liga;
		String id;
		public sendMessageACL(String av, String bv,int id,String liga){
			super();
			this.a= av;
			this.b= bv;
			this.id = Integer.toString(id);
			this.liga = liga;
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
				s= s.concat(":");
				s=s.concat(liga);
				msg.setContent(s);
				msg.addReceiver(receiver);
				myAgent.send(msg);
		
			
		}
	
			
		}
	
	/*
	*
	*Behaviour que manda liga ja com previsoes (e preve com os valores do res)
	*
	*/
	private class sendMessageMan extends OneShotBehaviour{
		private LigaB liga;
		
		public sendMessageMan(){
			super();
		this.liga = new LigaB();
		}
		
		@Override 
		public void action(){
			System.out.println("MANDANDO");
			
			AID receiver = new AID();
			receiver.setLocalName("Man1");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setOntology("CRITIC");
			
			try {
				this.liga=ligacritic;
				
				msg.setContentObject(this.liga);
				msg.addReceiver(receiver);
				myAgent.send(msg);
			} catch (Exception e) {
				System.out.println("NAO DEU " );
				 e.printStackTrace();
			
			}
			
		}
		
		
	}
	
	/*
	*
	*encadeia todos os procedimentos para preencer um jogo no res 
	*
	*/
	
	private ParallelBehaviour CriticBehaviour(int i ,String at, String bt , String liga){
		
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
			seq4.addSubBehaviour(new sendMessageACL(at,bt,i,liga));
			seq4.addSubBehaviour(new receiveMessageACL());
			
			par.addSubBehaviour(seq1);
			par.addSubBehaviour(seq2);
			par.addSubBehaviour(seq3); 
			par.addSubBehaviour(seq4); 
			return par;
		
	}


}
