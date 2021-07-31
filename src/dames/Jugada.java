package dames;

import java.util.ArrayList;

public class Jugada {
	
	private int iniciInt; //casella inici en format número
	private ArrayList<Integer> destiInt; //caselles desti en format número
	
	private boolean mat;
	private Jugador jugador;
	private Fitxa fitxa;
	private int destiFinal;
	
	private String iniciString; //casella inici en format lletraNumero
	private ArrayList<String> destiString; //caselles desti en format lletraNumero
	
	public Jugada (int inici, ArrayList<Integer> desti, Jugador jugador, Fitxa fitxa) {
		 this.iniciInt = inici;
		 this.destiInt = desti;
		 
		 this.iniciString = tradueixAString(inici);
		 this.destiString = new ArrayList<String>();
		 
		 if (desti.size() > 0) {
			 for (int d : desti) {
				 this.destiString.add(tradueixAString(d));
			 }
		 }
		 
		 mat = Math.abs(destiInt.get(0)%10 - iniciInt%10) > 1;
		 
		 this.jugador = jugador;
		 this.fitxa = fitxa;
		 
	}
	
	public Jugada (String inici, ArrayList<String> desti, Jugador jugador, Fitxa fitxa) {
		
		this.iniciString = inici;
		this.destiString = desti;

		this.iniciInt = tradueixAInt(inici);
		
		this.destiInt = new ArrayList<Integer>();
		
		if (desti.size() > 0) {
			for (String d : desti) {
				destiInt.add(tradueixAInt(d));
			}
		}

		
		mat = Math.abs(destiInt.get(0)%10 - iniciInt%10) > 1;
		
		this.jugador = jugador;
		this.fitxa = fitxa;
	}
	
	public int getIniciInt() { return iniciInt; }
	public ArrayList<Integer> getDestiInt() { return destiInt; }
	public String getIniciString() { return iniciString; }
	public ArrayList<String> getDestiString() { return destiString; }
	public boolean getMat() {return mat;}
	public Jugador getJugador() {return jugador;}
	public int getLlargadaMat() {return destiInt.size();}
	public int getDestiFinal() {
		int k = destiInt.size();
		return destiInt.get(k-1);
	}
	public Fitxa getFitxa() {return fitxa;}
	
	public void setIniciInt(int inici) { this.iniciInt = inici; }
	public void setDestiInt(ArrayList<Integer> desti) { this.destiInt = desti; }
	public void setIniciString(String inici) { this.iniciString = inici; }
	public void setDestiString(ArrayList<String> desti) { this.destiString = desti; }
	public void setMat(boolean mat) { this.mat = mat; }
	public void setJugador(Jugador jugador) { this.jugador = jugador; }
	public void setFitxa(Fitxa f) {this.fitxa = fitxa;}
	
	private static String tradueixAString(int casella) {
		
		String result;
		
		switch (casella%10) {
			case 1 : 
				result = "a";
				break;
			case 2 : 
				result = "b";
				break;
			case 3 : 
				result = "c";
				break;
			case 4 : 
				result = "d";
				break;
			case 5 : 
				result = "e";
				break;
			case 6 : 
				result = "f";
				break;
			case 7 : 
				result = "g";
				break;
			case 8 : 
				result = "h";
				break;
			default : result = "error";
		}
		
		result += (9 - casella/10);
		
		return result;
	}
	
	public static int tradueixAInt(String casella) {
		
		int result = 10 * (9-Character.getNumericValue(casella.charAt(1)));
		
		switch (casella.charAt(0)) {
		case 'a':
			result += 1;
			break;
		case 'b':
			result += 2;
			break;
		case 'c':
			result += 3;
			break;
		case 'd':
			result += 4;
			break;
		case 'e':
			result += 5;
			break;
		case 'f':
			result += 6;
			break;
		case 'g':
			result += 7;
			break;
		case 'h':
			result += 8;
			break;
		}
		
		return result;
	}
	
	private static String getNotacioDames(int casella) {
		return "" + getNotacioDames(casella/10, casella%10);
	}
	
	private static int getNotacioDames(int i, int j) {
		return (i-1)*4 + (j/2) + (j%2);
	}
	
	public String toString() {
		return jugador + " " + fitxa + " " + iniciString + " " + destiString;
	}
	
	public String getDestiToString() {
		String res = "";
		for (String desti : destiString) {
			res += " " + desti;
		}
		return res;
	}
	
	public String toStringDames() {
		ArrayList<String> destiNotacioDames = new ArrayList<String>();
		for (int casella : destiInt) {
			destiNotacioDames.add(getNotacioDames(casella));
		}
		
		return jugador + " " + fitxa + " " + getNotacioDames(iniciInt) + " " + destiNotacioDames;
	}
}
