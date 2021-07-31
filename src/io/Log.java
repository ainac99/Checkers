package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dames.*;

public class Log {
	
	private BufferedWriter bw;
	
	public Log(Partida tipusPartida, String path) {		
		
		try {
	
			bw = new BufferedWriter(new FileWriter(new File(path), false));
			
		} catch (IOException e) {
			System.err.println("Error al fer el log");
		}
		
	}
	
	public void afegeix(String s) {
		try {
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
			
			bw.write(currentTimestamp + ": "+ s + "\n");
			
			bw.flush();
			
		} catch (IOException e) {
			System.err.println("Error al escriure al log");
		}
		
	}
	
	public void generaResumJugades(ArrayList<Jugada> historicJugades) {

			String resum = "\n";
			
			for (int i = 0; i < historicJugades.size(); ++i) {
				resum += i + ": " + historicJugades.get(i) + "\n";
			}
			afegeix(resum + "");

	}
	
}
