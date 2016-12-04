/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import org.xml.sax.SAXException;

/**
 *
 * @author gil
 */
public class Leitura {
    private int count;
    
    
    public Leitura() {
        this.count=0;
        
    }
    
    public List<Jogador> lerJogadores(String nomeEquipa)   {
        List<Jogador> jogadores = new ArrayList<>();
        try{
            URL path = Leitura.class.getResource("./Liga-NOS/"+nomeEquipa+".xml");
           File g = new File(path.getFile());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(g);

        
        doc.getDocumentElement().normalize();

	
        NodeList nList = doc.getElementsByTagName("jogador");
        
        for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);

		

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
			String nome = eElement.getElementsByTagName("nome").item(0).getTextContent();
			String posicao = eElement.getElementsByTagName("posicao").item(0).getTextContent();
			int idade =Integer.parseInt(eElement.getElementsByTagName("idade").item(0).getTextContent());
			int valor = Integer.parseInt(eElement.getElementsByTagName("valor").item(0).getTextContent());
                        int s = Integer.parseInt(eElement.getElementsByTagName("status").item(0).getTextContent());
                        boolean status = (s==1);
                        
                    Jogador p = new Jogador(nome,posicao,idade,valor,status);    
                   
                    jogadores.add(p.clone());
                       
		}
	}
        
    } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
        e.printStackTrace();
    }
  
        
        
        
        
        return jogadores;
        
            
    }
     
    /*
    Devolve a sigla da equipa e os respectivos pontos
    */
    
    private void getFile(String dirPath) {
        this.count=0;
    URL path = Leitura.class.getResource(dirPath);
    File f = new File(path.getFile());
    File[] files = f.listFiles();
   

    if (files != null)
    for (File file1 : files) {
        this.count++;
         
        File file = file1;
        if (file.isDirectory()) {
            getFile(file.getAbsolutePath()); 
        }
    }
}
    
public int lerJornadas(String equipaCasa) throws IOException{
        
    
         int pontosCasa=0;
         
         int i=1;
        getFile("./Liga-NOS/Jogos/Resultados/");
        
         while(i<=this.count) {
        
             try{
            
            URL path = Leitura.class.getResource("./Liga-NOS/Jogos/Resultados/jornada"+i+".xml");
            File g = new File(path.getFile());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(g);
            i++;
        
        doc.getDocumentElement().normalize();

	
        NodeList nList = doc.getElementsByTagName("jogo");
        
        for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);

		

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

            Element eElement = (Element) nNode;
            String equipaC = eElement.getElementsByTagName("equipa_casa").item(0).getTextContent();
            String resultado =eElement.getElementsByTagName("resultado").item(0).getTextContent();
            String equipaF = eElement.getElementsByTagName("equipa_fora").item(0).getTextContent();
            
            String [] partes;
            partes = resultado.split("-");
            int [] resultados = new int [2];
            resultados [0] = Integer.parseInt(partes[0]);
            resultados [1] = Integer.parseInt(partes[1]);
            
            if(equipaC.equals(equipaCasa)) {
            
                if(resultados[0]>resultados[1]) {
                          pontosCasa +=3;
                          
                }
                else {
                
                     if(resultados[0]==resultados[1]) {
                          pontosCasa +=1;
                          
                }
                    
                
                }
            
            }
            
            else {
            
                    if(equipaF.equals(equipaCasa)) {
            
                if(resultados[1]>resultados[0]) {
                          pontosCasa +=3;
                          
                }
                else {
                
                     if(resultados[0]==resultados[1]) {
                          pontosCasa +=1;
                          
                }
                     
                }
            
            }  
            
            }
          
            
	}
        
    }
             } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
        
                 e.printStackTrace();
        
                 
    }
        
 }
    return pontosCasa;
    
    }
        
        
        
        
        
    
}
