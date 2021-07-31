package dames;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import io.Consola;
import io.Log;

public class Main {
	
	Taulell t;
	Jugador j = Jugador.BLANQUES;
	
	private static final String taulellInicial =  
			  ".n.n...n\n"
			+ "b...b.B.\n"
			+ "...n...n\n"
			+ "......n.\n"
			+ "...b.n.n\n"
			+ "b.......\n"
			+ ".b.b...n\n"
			+ "b.b.N...\n";
	
	private static final String classicTaulellInicial =  
			  ".n.n.n.n\n"
			+ "n.n.n.n.\n"
			+ ".n.n.n.n\n"
			+ "........\n"
			+ "........\n"
			+ "b.b.b.b.\n"
			+ ".b.b.b.b\n"
			+ "b.b.b.b.\n";
	
	private static int numPartides = 100;
	private static final int nivellProfunditat = 4;
	private static final Partida tipusPartida = Partida.MAQUINAvsMAQUINA;
	
	//private static final int[] pesos1 = {100, 200, 10, 1, 1, 0, 2, 3};
	//private static final int[] pesos2 = {100, 0, 0, 0, 0, 0, 0, 0};
	
	private static final int[] pesosBlanques = new int[8];
	private static final int[] pesosNegres = {100, 100, 0, 0, 0, 0, 0, 0};
	
	private static int guanyades = 0;
	private static int perdudes = 0;
	private static int taules = 0;
	
	private static Log log = new Log(tipusPartida, "Log.log");
	
	private static void provesPesos() throws IOException, InterruptedException {
		
		ArrayList<int[]> llistaPesos;
		Log logProvesPesos = new Log(tipusPartida, "LogProvesPesos.log");
		
		BufferedReader br = new BufferedReader(new FileReader("llistaPesos.txt"));
		
		String linia;
		
		while ((linia = br.readLine()) != null) {
			ArrayList<String> pesosSeparats = new ArrayList<String>(Arrays.asList(linia.split(" ")));
			
			for (int i = 0; i < pesosSeparats.size(); ++i) {
				pesosBlanques[i] = Integer.parseInt(pesosSeparats.get(i));
			}
			
			System.out.println("Partides amb pesosBlanques: " + Arrays.toString(pesosBlanques));
			començaPartides(pesosBlanques);
			
			logProvesPesos.afegeix(Arrays.toString(pesosBlanques) + " " + guanyades + " " + perdudes + " " + taules);
			
		}
	}

	
	private static void registraResultat(Resultat resultat) {
		switch(resultat) {
		case GUANYADA:
			++guanyades;
			return;
		case PERDUDA: 
			++perdudes;
			return;
		case TAULES:
			++taules;
			return;
		}
	}
	
	private static void començaPartides(int[] pesosBlanques) throws IOException, InterruptedException {
		
		guanyades = 0;
		perdudes = 0;
		taules = 0;
		
		for (int i = 1; i <= numPartides; ++i) {
					
			log.afegeix("partida " + i);
			
			Dames dames = new Dames(classicTaulellInicial, tipusPartida, nivellProfunditat, log, pesosBlanques, pesosNegres);
			Resultat resultat = dames.start();
			
			//Resultat resultat = dames.prova();
			
			registraResultat(resultat);
			
			if (tipusPartida == Partida.MAQUINAvsMAQUINA) {
				Consola.imprimeixBarra(i,  numPartides, guanyades, perdudes, taules);
			}

			if (tipusPartida == Partida.CONSOLA) {
				
				Consola.imprimeixResultat(resultat);
				
				String resposta = Consola.preguntaSiUnaAltra();
				if (resposta.equals("s")) {
					++numPartides;
				}
				else Consola.netejaPantalla();
			}

		}
		
		if (tipusPartida == Partida.MAQUINAvsMAQUINA) {
						
			Consola.imprimeixBarraAmbResultat(guanyades, perdudes, taules, numPartides);
			System.out.println(" g: " + (guanyades*100)/numPartides + "% p: " + (perdudes*100)/numPartides + "% t: " + (taules*100)/numPartides + "%");
		
			log.afegeix("\n ----------------------------- \n RESUM DE " + numPartides + " PARTIDES AMB PARAMETRES:\n "
					+ "pesos blanques: " + Arrays.toString(pesosBlanques) + "\n"
					+ "pesos negres: " + Arrays.toString(pesosNegres) + "\n\n"
					+ "  guanyades: " + guanyades + "\n"
					+ "  perdudes: " + perdudes + "\n"
					+ "  taules: " + taules + "\n");
		}

	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Consola.netejaPantalla();
		provesPesos();
		//començaPartides(new int[] {100, 200, 10, 1, 1, 0, 2, 3});
		
	}
		
}
