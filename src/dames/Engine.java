package dames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import io.Consola;
import io.Log;


public class Engine {
	
	private static final int nivell = 7;
	private static final Partida tipus = Partida.FITXER;
	//private static final String pathFitxer = "jugada.txt";
	private static final String pathFitxer = "F:\\DADES\\Go\\src\\Checkers\\jugada.txt";
	private static final boolean impresions = false;

	private Log log;
	
	private int contadorMeu = 1;	
	private Taulell t;
	
	public Engine(Taulell t) {
		this.t = t;
		this.log = new Log(tipus, "Log.log");
	}
	
	/*public void probaScore() {
		
		t.imprimeixTaulell();
		Heuristics h = new Heuristics(t, pesos1, pesos2);
		System.out.println("score: " + h.getScore1());
		
	}*/
	
	
	public void start() throws IOException, InterruptedException {
		
		if (impresions) {
			t.imprimeixTaulell();
			System.out.println("");
		}
		
		jugaBlanques();
		
		while (t.getGuanyador() == null) {
			
			boolean correcta = llegeixIMouNegres();
			while (!correcta) correcta = llegeixIMouNegres();
			
			jugaBlanques();
	
		}
		
		String resultat = "";
		
		switch(t.getGuanyador()) {
			case NEGRES:
				System.out.println("Has guanyat!");
				resultat = "negres";
				break;
			case BLANQUES: 
				System.out.println("He guanyat!");
				resultat = "blanques";
				break;
			case Z:
				System.out.println("Taules!");
				resultat = "taules";
				break;
		}
		
		if (tipus == Partida.MAQUINAvsMAQUINA) {
			log.afegeix(resultat);
		}
	}
	
	public void jugaBlanques() throws IOException {
		
		
		ArrayList<Jugada> jugades = t.getJugades(Jugador.BLANQUES);
		Jugada millor;
		
		if (jugades.size() == 0) return;
		
		log.afegeix("----------------------------------------------------------------------------");
		log.afegeix(" BLANQUES:");
		
		millor = trobaMillorJugada(jugades);
		
		
		System.out.print(Consola.IMPRIMIRJUGADA + "Jo jugo: " + contadorMeu + " " + millor.toString());
		t.mou(millor);
	
		
		if (tipus == Partida.FITXER) {
			
			log.afegeix("" + contadorMeu + " " + millor.toString());
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(pathFitxer));
			writer.write("A " + contadorMeu + " " + millor.getIniciString() + millor.getDestiToString() + "                                                                   ");
			writer.close();
			log.afegeix("escric: " + "A " + contadorMeu + " " + millor.getIniciString() + millor.getDestiToString() + "                                                                   ");
			
			++contadorMeu;
		}
		
		else {
			log.afegeix("Jugada: " + millor.toString());
		}
		
		
		t.historicJugades.add(millor);
		t.imprimeixTaulell();

	}
	
	private boolean comprovaJugadaCorrecta(Jugada j, Jugador jug) {
		
		ArrayList<Jugada> jugades = t.getJugades(jug);
		//log("jugades:"+ jugades.toString());
		
		for (Jugada jugada : jugades) {
			if (jugada.getIniciInt() == j.getIniciInt() && jugada.getDestiFinal() == j.getDestiFinal()) {
				
				if (tipus == Partida.CONSOLA) System.out.print( Consola.ENTRAMOVIMENT + "                                                                ");
				return true;
			}
		}
		return false;
	}
	
	private boolean llegeixIMouNegres() throws IOException, InterruptedException {
		
		if (tipus == Partida.CONSOLA) {
		
			System.out.print(Consola.ENTRAMOVIMENT  + "Entra moviment: ");
			Scanner capt = new Scanner(System.in);	
			String jug = capt.nextLine();
			
			ArrayList<String> desti = new ArrayList<String>(Arrays.asList(jug.split(" ")));
			
			String inici = desti.get(0);
			
			if (inici.equals("desfer")) {
				int k = Integer.parseInt(desti.get(1));
				t.desferUltimesJugades(k);
				
				log.afegeix("-------------- desfem " + k + " jugades");
				
				t.imprimeixTaulell();
			}
			else {
			
				desti.remove(0);
				
				log.afegeix("----------------------------------------------------------------------------");
				log.afegeix("NEGRES:");
				
				Jugada jugada = new Jugada(inici, desti, Jugador.NEGRES, t.tablero(Jugada.tradueixAInt(inici)));
		
				if (!comprovaJugadaCorrecta(jugada, Jugador.NEGRES)) {
					System.out.print(Consola.ENTRAMOVIMENT + "                                        <- Jugada incorrecta !" + Consola.RESET);
					return false;
				}
		
				t.mou(jugada);
				t.historicJugades.add(jugada);
								
				log.afegeix("Jugada: " + jugada);
				
				log.generaResumJugades(t.getHistoricJugades());
				
				t.imprimeixTaulell();
		
				++contadorMeu;
				return true;
			}
		}
		
		if (tipus == Partida.FITXER) {
			
			BufferedReader reader = new BufferedReader(new FileReader(pathFitxer));
			String jugadaLlegida = reader.readLine();
			reader.close();
			
			ArrayList<String> jugadaSeparada = new ArrayList<String>(Arrays.asList(jugadaLlegida.split(" ")));
			
			String jugador = jugadaSeparada.get(0);
			
			if (jugador.equals("P")) {
								
				int contadorSeu = Integer.parseInt(jugadaSeparada.get(1));
				
				System.out.println("contador meu: " + contadorMeu + " contador seu: " + contadorSeu);
				
				if (contadorSeu == contadorMeu) {
					String inici = jugadaSeparada.get(2);
					
					ArrayList<String> desti = new ArrayList<String>();
					for(int k = 3; k < jugadaSeparada.size(); ++k) {
						desti.add(jugadaSeparada.get(k));
					}

					Jugada jugada = new Jugada(inici, desti, Jugador.NEGRES, t.tablero(Jugada.tradueixAInt(inici)));
			
					log.afegeix("----------------------------------------------------------------------------");
					log.afegeix("NEGRES:");
					
					if (!comprovaJugadaCorrecta(jugada, Jugador.NEGRES)) {
						//System.out.println("jugada incorrecta: " + jugada);
						Thread.sleep(1000);
						return false;
					}
					
					t.mou(jugada);
					t.historicJugades.add(jugada);
				
					log.afegeix("Jugada llegida: " + jugadaSeparada);
					log.afegeix(contadorMeu + " " + jugada);
					
					System.out.println("Ell juga: " + contadorSeu + " " + jugada);
					
					t.imprimeixTaulell();
					
					++contadorMeu;
					return true;
			
				}
				else {
					Thread.sleep(1000);
				}

				reader.close();
				return false;
				
			}
			else Thread.sleep(1000);
		}
		

		return false;
			
	}
	
	private Jugada trobaMillorJugada(ArrayList<Jugada> jugades) {
			
		    int millorValor = Integer.MIN_VALUE;
	
		    Jugada millorJugada = null;
		    
		    for (Jugada jug : jugades) {
		    	
		    	Taulell t2 = new Taulell(t);
		    	t2.mou(jug);
		    	
		    	
		    	int valor = minimax(0, Jugador.NEGRES, Integer.MIN_VALUE, Integer.MAX_VALUE, t2);
		    			   
		    	//log(jug.toString() + " valor " + valor);
		    	
		    	if (valor >= millorValor) {
		    		millorValor = valor;
		    		millorJugada = jug;
		    	}

		    }
			
		    return millorJugada;
		}
	
	private int minimax(int profunditat, Jugador jugador, int alpha, int beta, Taulell aux) {
		
	    Jugador guanyador = aux.getGuanyador();
	    
	    if (guanyador == Jugador.NEGRES)  return -1000 + profunditat;
	    if (guanyador == Jugador.BLANQUES) return  1000 - profunditat;
	    if (aux.getContadorMoviments() == 100)    return 0;
	    if (profunditat == nivell) {
	    	//Heuristics h = new Heuristics(aux);
	    	//return h.getScore1();
	    	return 0;
	    }
	    
	    
	    //ens toca a nosaltres
	    if (jugador == Jugador.BLANQUES) {
	    	
	        int v = Integer.MIN_VALUE;
	        
	        for (Jugada jug : aux.getJugades(Jugador.BLANQUES)) {
	                Taulell t2 = new Taulell(aux);
	                t2.mou(jug);
	                v = Math.max(v, minimax(profunditat+1, Jugador.NEGRES, alpha, beta, t2));
	                
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
	
	        	v = Math.min(v, minimax(profunditat+1, Jugador.BLANQUES, alpha, beta, t2));
	        	
	        	beta = Math.min(beta, v);
	        	if (beta <= alpha) break;
	        }
	       
	        return v;
	    }
	}	
		
	
		
	public static void netejaPantalla() {
		System.out.print(Consola.HOME);
		System.out.println(Consola.RESET);
		
	}
}
