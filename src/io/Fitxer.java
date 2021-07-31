package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import dames.Dames;
import dames.Jugada;
import dames.Jugador;

public class Fitxer {
	
	private BufferedWriter writer;
	private BufferedReader reader;
	
	//private static final String pathFitxer = "jugada.txt";
	private static final String pathFitxer = "F:\\DADES\\Go\\src\\Checkers\\jugada.txt";

	
	public Fitxer() {		
		
	}
	
	public void afegeix(String s) throws IOException {
		writer = new BufferedWriter(new FileWriter(pathFitxer));
		writer.write(s);
		writer.close();
	}
	
	public Jugada llegeix()  throws IOException, InterruptedException {
		
		String llegit; 
		
		while (true) {
			reader = new BufferedReader(new FileReader(pathFitxer));
			llegit = reader.readLine();
			reader.close();
						
			if (llegit != null) {
				
				ArrayList<String> jugadaSeparada = new ArrayList<String>(Arrays.asList(llegit.split(" ")));
				String jugador = jugadaSeparada.get(0);
				
				if (jugador.equals("P")) {
					
					int contadorSeu = Integer.parseInt(jugadaSeparada.get(1));
					
					//System.out.println("contador meu: " + Dames.contadorMeu + " contador seu: " + contadorSeu);
					
					if (contadorSeu == Dames.contadorMeu) {
						String inici = jugadaSeparada.get(2);
						
						ArrayList<String> desti = new ArrayList<String>();
						for(int k = 3; k < jugadaSeparada.size(); ++k) {
							desti.add(jugadaSeparada.get(k));
						}
		
						 return new Jugada(inici, desti, Jugador.NEGRES, Dames.t.tablero(Jugada.tradueixAInt(inici)));
					
					} else {
						Thread.sleep(500);
					}
				} else {
					Thread.sleep(500);
				}
			} else {
				Thread.sleep(500);
			}
		}
	}
}
	
