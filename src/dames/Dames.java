package dames;

import java.io.*;
import java.util.*;

import io.Consola;
import io.Fitxer;
import io.Log;


public class Dames {
	
	private Partida tipusPartida; //maquina vs maquina, llegir fitxer o consola
	private int nivellProfunditat;
	
	public static int contadorMeu = 1;
	public static Taulell t;
	private Log log;
	private Fitxer fitxer;
	private int[] pesos1;
	private int[] pesos2;
	
	//CONSTRUCTOR
	public Dames(String situacioInicial, Partida tipusPartida, int nivellProfunditat, Log log, int[] pesos1, int[] pesos2) throws IOException, InterruptedException {
		this.t = new Taulell(situacioInicial);
		this.tipusPartida = tipusPartida;
		this.log = log;
		this.nivellProfunditat = nivellProfunditat;
		if (tipusPartida == Partida.FITXER) this.fitxer = new Fitxer();
		this.pesos1 = pesos1;
		this.pesos2 = pesos2;
	}
	
	public Resultat prova() throws InterruptedException {
		t.imprimeixTaulell();
		Thread.sleep(2000);
		return Resultat.PERDUDA;
	}
	
	public Resultat start() throws IOException, InterruptedException {
		
		if (tipusPartida != Partida.MAQUINAvsMAQUINA) {
			t.imprimeixTaulell();
			System.out.println("");
		}
		//comencen blanques
		jugaBlanques();
		//t.imprimeixTaulell();
 		
		//mentre no hi hagi guanyador juguen negres i despres blanques
		while(t.getGuanyador() == null) {
			boolean correcta = llegeixIMouNegres();
			while (! correcta) correcta = llegeixIMouNegres();
			//t.imprimeixTaulell();
			
			if (t.getGuanyador() != null) break;
			
			jugaBlanques();
			//t.imprimeixTaulell();
		}
		
		if (tipusPartida == Partida.MAQUINAvsMAQUINA) {
			//log.generaResumJugades(t.getHistoricJugades());
		}
		
		switch(t.getGuanyador()) {
			case NEGRES:
				log.afegeix("..................... negres"); //imprimeix qui ha guanyat al log
				return Resultat.PERDUDA;
			case BLANQUES: 
				log.afegeix("..................... blanques"); //imprimeix qui ha guanyat al log
				return Resultat.GUANYADA;
			case Z:
				log.afegeix("..................... taules"); //imprimeix qui ha guanyat al log
				return Resultat.TAULES;
		}
		
		return Resultat.TAULES;
	}
	
	private void jugaBlanques() throws InterruptedException, IOException {
		
		//conté les jugades possibles de les blanques

		ArrayList<Jugada> jugades = t.getJugades(Jugador.BLANQUES);
		
		if (jugades.size() == 0) return; //blanques han perdut
		
		Jugada millor = trobaMillorJugada(jugades, Jugador.BLANQUES);
		t.mou(millor);
		t.historicJugades.add(millor);
		
		if (tipusPartida == Partida.FITXER) {
			log.afegeix("" + contadorMeu + " " + millor.toString());
			log.afegeix("escric: " + "A " + contadorMeu + " " + millor.getIniciString() + millor.getDestiToString() + "                                                                   ");
			fitxer.afegeix("A " + contadorMeu + " " + millor.getIniciString() + millor.getDestiToString() + "                                                                   ");
		}
		
		if (tipusPartida == Partida.CONSOLA) {
			log.afegeix("Jugada: " + millor.toString());
			t.imprimeixTaulell();
		}
		
		++contadorMeu;
	}
	
	private boolean comprovaJugadaCorrecta(Jugada jugada, Jugador jugador) {
		ArrayList<Jugada> jugadesPossibles = t.getJugades(jugador);
		
		for (Jugada jugadaPossible : jugadesPossibles) {
			if (jugadaPossible.getIniciInt() == jugada.getIniciInt() &&
					jugadaPossible.getDestiFinal() == jugada.getDestiFinal()) {
				if (tipusPartida == Partida.CONSOLA) System.out.print( Consola.ENTRAMOVIMENT + "                                                                ");
				return true;
			}
		}
		
		return false; //la jugada no apareix a les possibles
	}
	
	private Jugada llegeixNegres() throws InterruptedException, IOException {
		
		Jugada jugada = null;
		String jugadaString = "";
		
		if (tipusPartida == Partida.CONSOLA) {
			System.out.print(Consola.ENTRAMOVIMENT  + "Entra moviment: ");
			
			Scanner entradaConsola = new Scanner(System.in);	
			jugadaString = entradaConsola.nextLine();
			
			ArrayList<String> desti = new ArrayList<String>(Arrays.asList(jugadaString.split(" ")));
			String inici = desti.get(0);
			
			desti.remove(0);
			
			jugada = new Jugada(inici, desti, Jugador.NEGRES, t.tablero(Jugada.tradueixAInt(inici)));

		}
		
		else if (tipusPartida == Partida.FITXER) jugada = fitxer.llegeix();
		
		return jugada;
		
	}
	
	private boolean llegeixIMouNegres() throws InterruptedException, IOException {
		
		
		if (tipusPartida == Partida.MAQUINAvsMAQUINA) {	
			ArrayList<Jugada> jugades = t.getJugades(Jugador.NEGRES);
			
			if (jugades.size() == 0) return true; //les negres han perdut
			
			Jugada millor = trobaMillorJugada(jugades, Jugador.NEGRES);
			t.mou(millor);
			t.historicJugades.add(millor);
			
			++contadorMeu;
			
			return true;
		}
		
		else {
			
			Jugada jugadaLlegida = llegeixNegres();
			
			if (jugadaLlegida == null) return false;
			
			if (!comprovaJugadaCorrecta(jugadaLlegida, Jugador.NEGRES)) {
				System.out.print(Consola.ENTRAMOVIMENT + "                                        <- Jugada incorrecta !" + Consola.RESET);
				return false;
			}

			t.mou(jugadaLlegida);
			t.historicJugades.add(jugadaLlegida);
			t.imprimeixTaulell();
			++contadorMeu;
			
			log.afegeix("----------------------------------------------------------------------------");
			log.afegeix("NEGRES:");
			
			log.afegeix("Jugada: " + jugadaLlegida);
			
			log.generaResumJugades(t.getHistoricJugades());
		}
		
		
		return true;

	}
	
	private Jugada trobaMillorJugada(ArrayList<Jugada> jugades, Jugador jugador) throws InterruptedException {
		
	    int 	millorValor = Integer.MIN_VALUE;
	    Jugada millorJugada = null;
	    
    	int valor = 0;
	    
	    for (Jugada jug : jugades) {
	    	Taulell t2 = new Taulell(t);
	    	t2.mou(jug);

	    	switch (jugador) {
		    	case BLANQUES :
		    		valor = minimax(0, Jugador.NEGRES, Integer.MIN_VALUE, Integer.MAX_VALUE, t2, Jugador.BLANQUES, Jugador.NEGRES);
		    		valor += Math.random() * 3;
		    		break;
		    	case NEGRES :
		    		valor = minimax(0, Jugador.BLANQUES, Integer.MIN_VALUE, Integer.MAX_VALUE, t2, Jugador.NEGRES, Jugador.BLANQUES);
		    		valor += Math.random() * 3;
		    		break;
	    	}
	    		    	
	    	if (valor >= millorValor) {
	    		millorValor = valor;
	    		millorJugada = jug;
	    	}	
	    }
	  
	    //Thread.sleep(10000);
	    return millorJugada;
	}
	
	private int minimax(int profunditat, Jugador liToca, int alpha, int beta, Taulell aux, Jugador ptDeVista, Jugador contrincant) {
		
	    Jugador guanyador = aux.getGuanyador();
	    
	    if (guanyador == contrincant)  return Integer.MIN_VALUE;
	    if (guanyador == ptDeVista) return  Integer.MAX_VALUE;
	    if (guanyador == Jugador.Z)    return -100000;
	    if (profunditat == nivellProfunditat) {
	    	Heuristics h = new Heuristics(aux, pesos1, pesos2);
	    	switch (ptDeVista) {
		    	case BLANQUES:
		    		return h.getScore1();
		    	case NEGRES:
		    		return h.getScore2();
	    	}
	    }
	    
	    //ens toca a nosaltres
	    if (liToca == Jugador.BLANQUES) {
	    	
	        int v = Integer.MIN_VALUE;
	        
	        for (Jugada jug : aux.getJugades(Jugador.BLANQUES)) {
	                Taulell t2 = new Taulell(aux);
	                t2.mou(jug);
	                v = Math.max(v, minimax(profunditat+1, Jugador.NEGRES, alpha, beta, t2, ptDeVista, contrincant));
	                
	                alpha = Math.max(alpha, v);
	                if (beta <= alpha) break;
	        }
	        return v;
	    }
	    else {
	        int v = Integer.MAX_VALUE;
	        for(Jugada jug : aux.getJugades(Jugador.NEGRES)) {
	        	Taulell t2 = new Taulell(aux);
	        	t2.mou(jug);
	
	        	v = Math.min(v, minimax(profunditat+1, Jugador.BLANQUES, alpha, beta, t2, ptDeVista, contrincant));
	        	
	        	beta = Math.min(beta, v);
	        	if (beta <= alpha) break;
	        }
	       
	        return v;
	    }
	}
	
}
