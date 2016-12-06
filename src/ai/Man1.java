package ai;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

//Manager 
public class Man1  extends Agent{
public HashSet<LigaB> apostas;
	protected void setup(){
		super.setup();
		apostas = new HashSet<>();
		this.addBehaviour(new ReceiveBehaviour());
	}
	

	private Boolean CheckLiga (String nome){
		Boolean ret = false;
		for(LigaB a : this.apostas){
			if(a.getNome().equals(nome)) ret = true ;
		}
		//ver data
		return ret;
		
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
			MessageTemplate mtI = MessageTemplate.MatchOntology("INFOLIGA");
			MessageTemplate mtPedidoLiga = MessageTemplate.and(mt, mtI);
			ACLMessage msg = receive(mtPedidoLiga);
		
			if(msg!=null){
				String lig = msg.getContent();
				if(!CheckLiga(lig)){
					
					LigaB newliga = new LigaB(lig);
					SequentialBehaviour seq = new SequentialBehaviour();
					
					seq.addSubBehaviour(new sendMessageJogos(newliga));
					seq.addSubBehaviour(new receiveMessageJogos());
					myAgent.addBehaviour(seq);
					
				}//falta else
				
			}
		}
		}
	
	
	/*
	 * 
	 * Behaviours que recebe mensagens dos Agentes 
	 * 
	 */
	private class receiveMessageJogos extends ReceiveBehaviour{
		
		@Override
		public void action(){
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		MessageTemplate mtJ = MessageTemplate.MatchOntology("JOGOS");
		MessageTemplate mtRespJogo = MessageTemplate.and(mt, mtJ);
		ACLMessage msg = receive(mtRespJogo);
		
		if(msg != null){
			LigaB newliga;
			try {
				newliga = (LigaB) msg.getContentObject();
				SequentialBehaviour seq = new SequentialBehaviour();
				
				seq.addSubBehaviour(new sendMessageCritico(newliga));
				seq.addSubBehaviour(new receiveMessageCritico());
				myAgent.addBehaviour(seq);
			} catch (Exception e) {
				// Nao deu
			}
			
		}
		
		
		}
	}
	
	private class receiveMessageCritico extends ReceiveBehaviour{
		
		@Override
		public void action(){
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		MessageTemplate mtC = MessageTemplate.MatchOntology("CRITIC");
		MessageTemplate mtRespCritico = MessageTemplate.and(mt, mtC);
		ACLMessage msg = receive(mtRespCritico);
		
		if(msg != null){
			LigaB newliga;
			try {
				newliga = (LigaB) msg.getContentObject();
				SequentialBehaviour seq = new SequentialBehaviour();
				
				seq.addSubBehaviour(new sendMessageLiga(newliga));
				
				
				//add liga as listas
				
				myAgent.addBehaviour(seq);
			} catch (Exception e) {
				// Nao deu
			}
			
		}
		
		
		}
	}
	
	
	/*
	 * 
	 * Behaviour que mandam mensagens aos Agentes 
	 * 
	 */
	
	
	private class sendMessageJogos extends OneShotBehaviour{
		LigaB liga;
		public sendMessageJogos(LigaB liga){
			super();
			this.liga= liga;
		}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("PJ");
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.setOntology("JOGOS");
			try {
				msg.setContentObject(this.liga);
				msg.addReceiver(receiver);
				myAgent.send(msg);
			} catch (Exception e) {
				// Nao deu
			
			}
			
		}
		
		
	}
	
	private class sendMessageCritico extends OneShotBehaviour{
		LigaB liga;
		public sendMessageCritico(LigaB liga){
			super();
			this.liga= liga;
		}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("Critic");
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.setOntology("CRITIC");
			try {
				msg.setContentObject(this.liga);
				msg.addReceiver(receiver);
				myAgent.send(msg);
			} catch (Exception e) {
				// Nao deu
			
			}
			
		}
		
		
	}
	
	private class sendMessageLiga extends OneShotBehaviour{
		LigaB liga;
		public sendMessageLiga(LigaB liga){
			super();
			this.liga= liga;
		}
		@Override 
		public void action(){
			AID receiver = new AID();
			receiver.setLocalName("Software");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setOntology("INFOLIGA");
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
