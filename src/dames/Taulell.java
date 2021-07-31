package dames;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import io.Consola;

/*
 * 	  A B C D E F G H
 * 	  1 2 3 4 5 6 7 8
 * 
 * 8  . N . N . N . N  8
 * 7  N . N . N . N .  7
 * 6  . N . N . N . N  6
 * 5  . . . . . . . .  5
 * 4  . . . . . . . .  4
 * 3  B . B . B . B .  3
 * 2  . B . B . B . B  2
 * 1  B . B . B . B .  1
 *   
 *    A B C D E F G H
 *    1 2 3 4 5 6 7 8
 *    
 *     
 */

/*
 * JO JUGO BLANQUES (B) = ORDINADOR
 * ELL JUGA NEGRES (N) = HUMÀ
 */

/*
 * 
 * NORMES:
 * 	- dames nomes van un endavant o endarrere
 *  - obligatori matar
 *  - obligatori matar amb dama primer i despres amb fitxes
 *  - obligatori matar el màxim nombre de fitxes.
 *  - si en 50 jugades no s'ha matat cap fitxa: taules
 *  - passar jugades: a6 b6 i si es mata entremig s'han de dir els punts intermitjos
 *  
 */


public class Taulell {
	
	/*private static final String taulellInicial =  
			  ".n.n.n.n\n"
			+ "n.n.n.n.\n"
			+ ".n.n.n.n\n"
			+ "........\n"
			+ "........\n"
			+ "b.b.b.b.\n"
			+ ".b.b.b.b\n"
			+ "b.b.b.b.\n";*/
	
	
    private Fitxa[][] taulell;
    private ArrayList<Integer> fitxesBlanques;
    private ArrayList<Integer> fitxesNegres;
    private ArrayList<Integer> damesBlanques;
    private ArrayList<Integer> damesNegres;
    private ArrayList<Jugada> jugadesRecursives;
    public ArrayList<Jugada> historicJugades;
    private String situacioInicial;
    private int blanquesInicials;
    private int negresInicials;
    
    private int contadorMoviments;
    
    public Taulell (String situacioInicial) {
    	
    	this.fitxesBlanques = new ArrayList<Integer>();
    	this.fitxesNegres = new ArrayList<Integer>();
    	this.damesBlanques = new ArrayList<Integer>();
    	this.damesNegres = new ArrayList<Integer>();
    	
    	
    	taulell = new Fitxa[9][9];
    	
    	setTaulell(situacioInicial);
    	
    	this.situacioInicial = situacioInicial;
    	
    	historicJugades = new ArrayList<Jugada>();
    	
    	this.contadorMoviments = 0;
    	

    }
    
    public Taulell (Taulell t) {
    	
    	this.taulell = new Fitxa[9][9];
    	
    	for (int i = 0; i < 9; ++i) {
    		for (int j = 0; j < 9; ++j) {
    			this.taulell[i][j] = t.tablero(i, j);
    		}
    	}
    	this.fitxesBlanques = new ArrayList<Integer> (t.fitxesBlanques);
    	this.fitxesNegres = new ArrayList<Integer> (t.fitxesNegres);
    	this.damesBlanques = new ArrayList<Integer> (t.damesBlanques);
    	this.damesNegres = new ArrayList<Integer> (t.damesNegres);
    	
    	historicJugades = new ArrayList<Jugada>(t.historicJugades);
    	
    	this.contadorMoviments = t.contadorMoviments;
    	this.situacioInicial = t.situacioInicial;
    	
    	this.blanquesInicials = t.blanquesInicials;
    	this.negresInicials = t.negresInicials;
    }
    
    
    public int contaPeces(Jugador jugador) {
    	if (jugador == Jugador.BLANQUES) return getNumPecesBlanques();
    	if (jugador == Jugador.NEGRES) return getNumPecesNegres();
    	return -1;
    }
    
    private boolean esBlanca(int casella) {
    	int i = casella/10;
    	int j = casella%10;
    	return esBlanca(i, j);
    }
    private boolean esBlanca(int i, int j) {
    	if (i < 1 || i > 8 || j < 1 || j > 8) return false;
    	return (taulell[i][j] == Fitxa.B || taulell[i][j] == Fitxa.BB); 
    }
    private boolean esBlanca(Fitxa f) {
    	return propietari(f) == Jugador.BLANQUES;
    }
    
    private Jugador propietari(Fitxa f) {
    	if (f == Fitxa.BB || f == Fitxa.B) return Jugador.BLANQUES;
    	if (f == Fitxa.N || f == Fitxa.NN) return Jugador.NEGRES;
    	return Jugador.Z;
    }
    
    
    private boolean esNegra(int casella) {
    	int i = casella/10;
    	int j = casella%10;
    	return esNegra(i, j);
    }
    private boolean esNegra(int i, int j) {
    	if (i < 1 || i > 8 || j < 1 || j > 8) return false;
    	return (taulell[i][j] == Fitxa.N || taulell[i][j] == Fitxa.NN); 
    }
    private boolean esNegra(Fitxa f) {
    	return (propietari(f) == Jugador.NEGRES);
    }
    
    private boolean esDama(int casella) {
    	int i = casella/10;
    	int j = casella%10;
    	return esDama(i, j);
    }
    private boolean esDama(int i, int j) {
    	if (i < 1 || i > 8 || j < 1 || j > 8) return false;
    	return (taulell[i][j] == Fitxa.NN || taulell[i][j] == Fitxa.BB);
    }
    private boolean esDama(Fitxa f) {
    	return (f == Fitxa.BB || f == Fitxa.NN);
    }
    
    private boolean esBuida(int casella) {
    	int i = casella/10;
    	int j = casella%10;
    	return esBuida(i, j);
    }
    private boolean esBuida(int i, int j) {
    	if (i < 1 || i > 8 || j < 1 || j > 8 || (i+j)%2 == 0) return false;
    	return (taulell[i][j] == Fitxa.Z);
    }

    private boolean esBuida(Fitxa f) {
    	return f == Fitxa.Z;
    }
    private static boolean esValida(int i, int j) {
    	if (i < 1 || i > 8 || j < 1 || j > 8 || (i+j)%2 == 0) return false;
    	return true;
    }
    public static boolean esValida(int casella) {
    	return esValida(casella/10, casella%10);
    }
    
    public int getNumPecesBlanques() { 
    	return fitxesBlanques.size() + damesBlanques.size(); }
    public int getNumPecesNegres() { return fitxesNegres.size() + damesNegres.size(); }
    public int getNumDamesBlanques() {return damesBlanques.size();}
    public int getNumDamesNegres() {return damesNegres.size();}
    
    public ArrayList<Integer> getFitxesBlanques() {return fitxesBlanques;}
    public ArrayList<Integer> getFitxesNegres() {return fitxesNegres;}
    public ArrayList<Integer> getDamesBlanques() {return damesBlanques;}
    public ArrayList<Integer> getDamesNegres() {return damesNegres;}
    public ArrayList<Jugada> getJugadesRecursives() {return jugadesRecursives;}
    public boolean getPartidaAcabada() {return (getNumPecesBlanques() == 0 || getNumPecesNegres() == 0);} 
	
    private ArrayList<Jugada> getJugadesObligades(Jugador jug) {
		
		//System.out.println("Estic mirant obligades de " + jug);
		
		ArrayList<Integer> fitxes;
		ArrayList<Integer> dames;
		if (jug == Jugador.BLANQUES) {
			fitxes = getFitxesBlanques();
			dames = getDamesBlanques();
		}
		else {
			fitxes = getFitxesNegres();
			dames = getDamesNegres();
		}
		
		ArrayList<Jugada> jugadesObligades = new ArrayList<Jugada>();
		
		int maxLlargada = 0;
		
		for (int dama : dames) {
			
			ArrayList<Jugada> jugades = buscaJugades(dama);
			
			
			for (Jugada jugada : jugades) {
				if (jugada.getMat()) {
					
					if (jugada.getLlargadaMat() >= maxLlargada) {
						if (jugada.getLlargadaMat() > maxLlargada) {
							jugadesObligades.clear();
						}
						jugadesObligades.add(jugada);
						maxLlargada = jugada.getLlargadaMat();
					}
					
				}
			}
			//System.out.println("dama: " + dama + " " + jugadesObligades);
		}
		if (jugadesObligades.size() > 0) return jugadesObligades;
		
		for (int fitxa: fitxes) {
			ArrayList<Jugada> jugades = buscaJugades(fitxa);
			
			for (Jugada jugada : jugades) {
				if (jugada.getMat()) {
					
					if (jugada.getLlargadaMat() >= maxLlargada) {
						if (jugada.getLlargadaMat() > maxLlargada) jugadesObligades.clear();
						jugadesObligades.add(jugada);
						maxLlargada = jugada.getLlargadaMat();
					}
					
				}
			}
			//System.out.println("fitxa: " + fitxa + " " + jugadesObligades);
		}
		
		return jugadesObligades;
		
	}
    
	private ArrayList<Jugada> getJugadesNoObligades(Jugador jug) {
		
		ArrayList<Integer> fitxes;
		ArrayList<Integer> dames;
		if (jug == Jugador.BLANQUES) {
			fitxes = getFitxesBlanques();
			dames = getDamesBlanques();
		}
		else {
			fitxes = getFitxesNegres();
			dames = getDamesNegres();
		}
		
		ArrayList<Jugada> jugadesNoObligades = new ArrayList<Jugada>();
		
		for (int dama : dames) {
			jugadesNoObligades.addAll(buscaJugades(dama));
		}
		
		for (int fitxa: fitxes) {
			jugadesNoObligades.addAll(buscaJugades(fitxa));
		}
		
		return jugadesNoObligades;
	}
	
	public ArrayList<Integer> getFitxes(Jugador jugador) {
		
		if (jugador == Jugador.BLANQUES) return getFitxesBlanques();
		if (jugador == Jugador.NEGRES) return getFitxesNegres();
		return null;
	}
	
	public ArrayList<Integer> getPeces(Jugador jugador) {
		
		ArrayList<Integer> peces = null;
		
		if (jugador == Jugador.BLANQUES) {
			peces = new ArrayList<Integer>(fitxesBlanques);
			peces.addAll(damesBlanques);
		}
		if (jugador == Jugador.NEGRES) {
			peces = new ArrayList<Integer>(fitxesNegres);
			peces.addAll(damesNegres);
		}
		
		return peces;	
	}
	
	public int getContadorMoviments() {return contadorMoviments;}
	public ArrayList<Jugada> getHistoricJugades() {return historicJugades;}
	public int getNumMortesBlanques() { return blanquesInicials-getNumPecesBlanques();}
	public int getNumMortesNegres() { return negresInicials - getNumPecesNegres();}
	
	public int getNumPecesBloquejades(Jugador jugador) {
		
		int k = 0;
		
		for (int casella : getPeces(jugador)) {
			if (buscaJugades(casella).size() == 0) ++k;
		}
		
		return k;
	}
	
	private void setTablero( int casella, Fitxa f) {
		taulell[casella/10][casella%10] = f;
	}
	private void setTablero( int i, int j, Fitxa f) {
		taulell[i][j] = f;
	}
	public Fitxa tablero(int casella) {
		return taulell[casella/10][casella%10];
	}
	private Fitxa tablero( int i, int j) {
		return taulell[i][j];
	}
	
	private void setTaulell(String situacio) {
	    	
	    	int i = 1;
	    	int j = 1;
	    	
	    	for (int k = 0; k < situacio.length(); ++k){
	    		
	    	    char c = situacio.charAt(k); 
	    	    
	    	    switch (c) {
		    	    case 'n':
		    	    	taulell[i][j] = Fitxa.N;
		    	    	fitxesNegres.add((10*i)+j);
		    	    	++j;
		    	    	break;
		    	    	
		    	    case 'b':
		    	    	taulell[i][j] = Fitxa.B;
		    	    	fitxesBlanques.add((10*i)+j);
		    	    	++j;
		    	    	break;
		    	    	
		    	    case 'B': 
		    	    	taulell[i][j] = Fitxa.BB;
		    	    	damesBlanques.add((10*i)+j);
		    	    	++j;
		    	    	break;
		    	    case 'N':
		    	    	taulell[i][j] = Fitxa.NN;
		    	    	damesNegres.add((10*i)+j);
		    	    	++j;
		    	    	break;
		    	    case '.':
		    	    	
		    	    	if ((i + j) % 2 == 1) taulell[i][j] = Fitxa.Z;
		    	    	else taulell[i][j] = Fitxa.X;
		    	    	++j;
		    	    	break;
		    	    	
		    	    case '\n':
		    	    	++i;
		    	    	j = 1;
		    	    	break;
		    	    	
		    	    default :
		    	    	System.out.println("error al llegir taulell");
	    	    }
	    	}
	    	
	    	blanquesInicials = getNumPecesBlanques();
	    	negresInicials = getNumPecesNegres();
	    }
	
	
    public void imprimeixTaulell()  {
    	
    	Consola.netejaPantalla();
    	
    	BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	try {
			out.write("\n");
    	
    	out.write("       A   B   C   D   E   F   G   H\n");
    	out.write("     ┌───┬───┬───┬───┬───┬───┬───┬───┐ \n");
    	for (int i = 1; i < 9; ++i) {
    		
    		out.write("   " + (9-i) + " ");
    		
    		for (int j = 1; j < 9; ++j) {
    			switch(taulell[i][j]) {
    				
    				case N :
    					out.write("|" + Consola.BLACK_BACKGROUND + "   " + Consola.RESET);
    					break;
    				case B : 
    					out.write("|" + Consola.WHITE_BACKGROUND_BRIGHT + "   " + Consola.RESET);
    					break;
    					
    				case NN : 
    					out.write("|" + Consola.WHITE_BRIGHT + Consola.BLACK_BACKGROUND + "───" + Consola.RESET);
    					break;
    				case BB : 
    					out.write("|" + Consola.BLACK_BRIGHT + Consola.WHITE_BACKGROUND_BRIGHT + "───" + Consola.RESET);

    					break;
    				case X : 
    					out.write("|   ");
    					break;
    				case Z : 
    					out.write("| · ");
    					break;
    				
    			}
    		}
    		
    		out.write("|   " + (9-i) );
    		if (i == 1 && getNumMortesNegres() > 0) {
    			out.write("   ");
				for (int k = 0; k < getNumMortesNegres(); ++k) {
					out.write(Consola.BLACK_BACKGROUND + "   " + Consola.RESET + " ");
				}
			}
			if (i == 2 && getNumMortesBlanques() > 0) {
				out.write("   ");
				for (int k = 0; k < getNumMortesBlanques(); ++k) {
					out.write(Consola.WHITE_BACKGROUND_BRIGHT + "   " + Consola.RESET + " ");
				}
			}
    		
			out.write("\n");
    		
    		
    		if (i != 8) out.write("     ├───┼───┼───┼───┼───┼───┼───┼───┤\n");
    	}
    	out.write("     └───┴───┴───┴───┴───┴───┴───┴───┘\n" );
    	out.write("       A   B   C   D   E   F   G   H\n\n");
    	
    	out.flush();
    	}
    	catch (IOException e) {
			e.printStackTrace();
		}
    }
 
    
    private void eliminaFitxa(Integer casella) throws InterruptedException {
    	
    	switch (tablero(casella)) {
    	case N:
    		fitxesNegres.remove(casella);
    		break;
    	case NN:
    		damesNegres.remove(casella);
    		break;
    	case B:
    		fitxesBlanques.remove(casella);
    		break;
    	case BB:
    		damesBlanques.remove(casella);
    		break;
    	default:
    		System.err.println("Error al eliminar la fitxa: " + casella);
    		throw new InterruptedException();
    	}
    	
    	setTablero(casella, Fitxa.Z);
    }
    
    private void afegeixFitxa(Integer casella, Fitxa fitxa) {
    	
    	if (!esBuida(casella)) {
    		System.err.println("Error al afegir la fitxa (casella no buida): " + fitxa + " a " + casella);
    		
    		return;
    	}
    	switch(fitxa) {
	    	case N:
	    		if (casella/10 == 8) {
	    			damesNegres.add(casella);
	    			contadorMoviments = 0;
	    			fitxa = Fitxa.NN;
	    		}
	    		else fitxesNegres.add(casella);
	    		break;
	    	case NN:
	    		damesNegres.add(casella);
	    		break;
	    	case B:
	    		if (casella/10 == 1) {
	    			damesBlanques.add(casella);
	    			contadorMoviments = 0;
	    			fitxa = Fitxa.BB;
	    		}
	    		else fitxesBlanques.add(casella);
	    		break;
	    	case BB:
	    		damesBlanques.add(casella);
	    		break;
	    	default:
	    		System.err.println("Error al afegir la fitxa: " + fitxa + " a " + casella);
	    		return;
    	}
    	
    	setTablero(casella, fitxa);
    }
    
    public void mou(Jugada jugada) {
    	
    	Integer inici = jugada.getIniciInt();
    	ArrayList<Integer> desti = jugada.getDestiInt();
    	Integer llargada = jugada.getLlargadaMat();
    	Integer destiFinal = desti.get(llargada-1);
    	
    	Fitxa fitxa = jugada.getFitxa();
    	
    	try {
			eliminaFitxa(inici);
		} catch (InterruptedException e) {
			System.out.println("tipus 1: " + jugada);
		}
    	
    	
		afegeixFitxa(destiFinal, fitxa);
    	
		if (jugada.getMat()) {
			
			Integer intermig = (desti.get(0)+inici)/2;
			try {
				eliminaFitxa(intermig);
			} catch (InterruptedException e) {
				System.out.println("tipus 2: " + jugada);
				System.out.println("el taulell esta:");
				imprimeixTaulell();
				//e.printStackTrace();
			}
			
			for (int k = 1; k < llargada; ++k) {
				intermig = (desti.get(k)+desti.get(k-1))/2;
				try {
					eliminaFitxa(intermig);
				} catch (InterruptedException e) {
					System.out.println("tipus 3: " + jugada);
					imprimeixTaulell();
				}
			}
			
			contadorMoviments = 0;
			
		}
    	
    	++contadorMoviments;
    	
    }
    
	/*public void mou(Jugada jugada) {
		
		++contadorMoviments;
		
		System.out.println("Anem a moure la jugada " + jugada);
		
		Integer inici = jugada.getIniciInt();
		ArrayList<Integer> desti = jugada.getDestiInt();
		Integer llargada = desti.size();
		Integer destiFinal = desti.get(llargada-1);
				
		Jugador jugador = jugada.getJugador();
		Fitxa fitxa = jugada.getFitxa();

		setTablero(inici, Fitxa.Z);
		setTablero(destiFinal, fitxa);
		
		if (jugador == Jugador.BLANQUES && esBuida(destiFinal) && destiFinal/10 == 1) {
			setTablero(destiFinal, Fitxa.BB);
			fitxesBlanques.remove(destiFinal);
			damesBlanques.add(destiFinal);
		}
		if (jugador == Jugador.NEGRES && esBuida(destiFinal) && destiFinal/10 == 8) {
			setTablero(destiFinal, Fitxa.NN);
			damesNegres.add(destiFinal);
			fitxesNegres.remove(destiFinal);
		}
		
		if (jugador == Jugador.BLANQUES) {
			if (esDama(fitxa)) {
				damesBlanques.remove(inici);
				damesBlanques.add(destiFinal);
			}
			else {
				fitxesBlanques.remove(inici);
				fitxesBlanques.add(destiFinal);
			}
		}
		else {
			if (esDama(fitxa)) {
				damesNegres.remove(inici);
				damesNegres.add(destiFinal);
			}
			else {
				fitxesNegres.remove(inici);
				fitxesNegres.add(destiFinal);
			}

		}
		
		if (jugada.getMat()) {
			
			contadorMoviments = 0;
			
			Integer intermig = (inici + desti.get(0))/2;
			Fitxa mort = tablero(intermig);
			setTablero(intermig, Fitxa.Z);
			if (jugador == Jugador.BLANQUES) {
				if (esDama(mort)) {
					damesNegres.remove(intermig);
				}
				else fitxesNegres.remove(intermig);
			}
			else if (jugador == Jugador.NEGRES){
				if (esDama(mort)) {
					damesBlanques.remove(intermig);
				}
				else fitxesBlanques.remove(intermig);
			}
			
			for (int k = 1; k < llargada; ++k) {
				intermig = (desti.get(k)+desti.get(k-1))/2;
				mort = tablero(intermig);
				setTablero(intermig, Fitxa.Z);
				if (jugador == Jugador.BLANQUES) {
					if (esDama(mort)) {
						damesNegres.remove(intermig);
					}
					else fitxesNegres.remove(intermig);
				}
				else if (jugador == Jugador.NEGRES){
					if (esDama(mort)) {
						damesBlanques.remove(intermig);
					}
					else fitxesBlanques.remove(intermig);
				}
			}
			
		}
		
		
	}

	/*public void desferMou() {
		setTaulell(situacioInicial);
						
		if (historicJugades.size() <= 1) return;
		
		for (int k = 0; k < historicJugades.size()-2; k++) {
			mou(historicJugades.get(k));
		}
		
		historicJugades.remove(historicJugades.size()-2);
		--contadorMoviments;
		
		imprimeixTaulell();
	}*/
	
    public void desferUltimesJugades(int jugades) {
    	
    	this.fitxesBlanques = new ArrayList<Integer>();
    	this.fitxesNegres = new ArrayList<Integer>();
    	this.damesBlanques = new ArrayList<Integer>();
    	this.damesNegres = new ArrayList<Integer>();
    	
    	taulell = new Fitxa[9][9];
    	setTaulell(situacioInicial);
    	
    	for (int k = 0; k < historicJugades.size()-jugades; ++k) {
    		System.out.println("Fem " + historicJugades.get(k));
			mou(historicJugades.get(k));
		
    	}
    	contadorMoviments -= jugades;

    }
    
    
	private ArrayList<Jugada> buscaJugades(int casella) {
		
		jugadesRecursives = new ArrayList<Jugada>();
	
		int i = casella/10;
    	int j = casella%10;
    	
    	Fitxa f = taulell[i][j];
    	Fitxa morta;
    	

		if (esBuida(i-1, j+1) && (esBlanca(casella) || esDama(casella))) {
			ArrayList<Integer> desti =  new ArrayList<Integer>();
			desti.add(10*(i-1)+(j+1));
			jugadesRecursives.add(new Jugada(casella, desti, propietari(taulell[i][j]),taulell[i][j]));
		}
		if (esBuida(i-1, j-1) && (esBlanca(casella) || esDama(casella))) {
			ArrayList<Integer> desti =  new ArrayList<Integer>();
			desti.add(10*(i-1)+(j-1));
			jugadesRecursives.add(new Jugada(casella, desti, propietari(taulell[i][j]),taulell[i][j]));
		}
		if (esBuida(i+1, j-1) && (esNegra(casella) || esDama(casella))) {
			ArrayList<Integer> desti =  new ArrayList<Integer>();
			desti.add(10*(i+1)+(j-1));
			jugadesRecursives.add(new Jugada(casella, desti, propietari(taulell[i][j]),taulell[i][j]));
		}
		if (esBuida(i+1, j+1) && (esNegra(casella) || esDama(casella))) {
			ArrayList<Integer> desti =  new ArrayList<Integer>();
			desti.add(10*(i+1)+(j+1));
			jugadesRecursives.add(new Jugada(casella, desti, propietari(taulell[i][j]),taulell[i][j]));
		}
		
		//mirem mats
		if (esBuida(i-2, j+2)) {
			
			morta = taulell[i-1][j+1];
			Integer desti = (i-2)*10 +(j+2);

			if (esBlanca(f) && esNegra(i-1, j+1)) {
				
				ArrayList<Integer> destins = new ArrayList<Integer>();
				
				destins.add(desti);
		
				setTablero(i-2, j+2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i-1, j+1, Fitxa.Z);
				
				recursiva(casella, destins, desti, f);
				
				setTablero(i-2, j+2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i-1, j+1, morta);
				
				destins.remove(desti);
			}
			else if (esDama(f) && esNegra(f) && esBlanca(i-1, j+1)) {

				ArrayList<Integer> destins = new ArrayList<Integer>();
				
				destins.add(desti);
				
				setTablero(i-2, j+2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i-1, j+1, Fitxa.Z);
				
				recursiva(casella, destins, desti, f);
				
				setTablero(i-2, j+2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i-1, j+1, morta);
				
				
				destins.remove(desti);
			}
		}
		if (esBuida(i-2, j-2)) {
			
			Integer desti = (i-2)*10+(j-2);
			morta = taulell[i-1][j-1];
			
			if (esBlanca(f) && esNegra(i-1, j-1)) {

				ArrayList<Integer> destins = new ArrayList<Integer>();
				
				destins.add(desti);
				
				setTablero(i-2, j-2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i-1, j-1, Fitxa.Z);
				
				recursiva(casella, destins, desti, f);
				
				setTablero(i-2, j-2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i-1, j-1, morta);
				
				
				destins.remove(desti);
			}
			else if (esDama(f) && esNegra(f) && esBlanca(i-1, j-1)) {

				ArrayList<Integer> destins = new ArrayList<Integer>();
				
				destins.add(desti);
				
				setTablero(i-2, j-2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i-1, j-1, Fitxa.Z);
				
				recursiva(casella, destins, desti, f);
				
				setTablero(i-2, j-2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i-1, j-1, morta);
				
				destins.remove(desti);

			}
		}
		if (esBuida(i+2, j-2)){
			
			Integer desti = (i+2)*10+(j-2);
			morta = taulell[i+1][j-1];
			
			if (esNegra(f) && esBlanca(i+1, j-1)) {

				ArrayList<Integer> destins = new ArrayList<Integer>();
				
				destins.add(desti);
				
				setTablero(i+2, j-2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i+1, j-1, Fitxa.Z);
				
				recursiva(casella, destins, desti, f);
				
				setTablero(i+2, j-2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i+1, j-1, morta);
				
				
				destins.remove(desti);

			}
			else if (esDama(f) && esBlanca(f) && esNegra(i+1, j-1)) {

				ArrayList<Integer> destins = new ArrayList<Integer>();
				
				destins.add(desti);
				
				setTablero(i+2, j-2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i+1, j-1, Fitxa.Z);
				
				recursiva(casella, destins, desti, f);
				
				setTablero(i+2, j-2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i+1, j-1, morta);
				
				
				destins.remove(desti);

			}
		}
		if (esBuida(i+2, j+2)) {
			Integer nova = (i+2)*10+(j+2);
			morta = taulell[i+1][j+1];
			
			if (esNegra(f) && esBlanca(i+1, j+1)) {
				
				ArrayList<Integer> destins = new ArrayList<Integer>();
				
				destins.add(nova);
				
				setTablero(i+2, j+2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i+1, j+1, Fitxa.Z);
				
				recursiva(casella, destins, nova, f);
				
				setTablero(i+2, j+2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i+1, j+1, morta);
				
				
				destins.remove(nova);

			}
			
			else if (esDama(f) && esBlanca(f) && esNegra(i+1, j+1)) {
				
				ArrayList<Integer> destins = new ArrayList<Integer>();
				
				destins.add(nova);
				
				setTablero(i+2, j+2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i+1, j+1, Fitxa.Z);
				
				recursiva(casella, destins, nova, f);
				
				setTablero(i+2, j+2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i+1, j+1, morta);
				
				
				destins.remove(nova);

			}
		}
		
		return jugadesRecursives;
				
	}
	
	private void recursiva(int original, ArrayList<Integer> destins, int actual, Fitxa f) {
				
		int i = actual/10;
		int j = actual%10;
		
		boolean alguna = false;
		Fitxa morta;
		
		//mirem mats
		
		if (esBuida(i-2, j+2)) {
			
			morta = taulell[i-1][j+1];
			Integer nova = (i-2)*10 +(j+2);

			if (esBlanca(f) && esNegra(i-1, j+1)) {

				alguna = true;
				
				destins.add(nova);
				
				setTablero(i-2, j+2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i-1, j+1, Fitxa.Z);
				
				recursiva(original, destins, nova, f);
				
				setTablero(i-2, j+2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i-1, j+1, morta);
				
				
				destins.remove(nova);
			}
			else if (esDama(f) && esNegra(f) && esBlanca(i-1, j+1)) {
				alguna = true;
				
				destins.add(nova);
				
				setTablero(i-2, j+2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i-1, j+1, Fitxa.Z);
				
				recursiva(original, destins, nova, f);
				
				setTablero(i-2, j+2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i-1, j+1, morta);
				
				
				destins.remove(nova);
			}
		}
		if (esBuida(i-2, j-2)) {
			
			Integer nova = (i-2)*10+(j-2);
			morta = taulell[i-1][j-1];
			
			if (esBlanca(f) && esNegra(i-1, j-1)) {

				
				alguna = true;
				
				destins.add(nova);
				
				setTablero(i-2, j-2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i-1, j-1, Fitxa.Z);
				
				recursiva(original, destins, nova, f);
				
				setTablero(i-2, j-2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i-1, j-1, morta);
				
				
				destins.remove(nova);
			}
			else if (esDama(f) && esNegra(f) && esBlanca(i-1, j-1)) {

				alguna = true;
				
				destins.add(nova);
				
				setTablero(i-2, j-2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i-1, j-1, Fitxa.Z);
				
				recursiva(original, destins, nova, f);
				
				setTablero(i-2, j-2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i-1, j-1, morta);
				
				destins.remove(nova);

			}
		}
		if (esBuida(i+2, j-2)){
			
			Integer nova = (i+2)*10+(j-2);
			morta = taulell[i+1][j-1];
			
			if (esNegra(f) && esBlanca(i+1, j-1)) {

				alguna = true;
				
				destins.add(nova);
				
				setTablero(i+2, j-2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i+1, j-1, Fitxa.Z);
				
				recursiva(original, destins, nova, f);
				
				setTablero(i+2, j-2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i+1, j-1, morta);
				
				
				destins.remove(nova);

			}
			else if (esDama(f) && esBlanca(f) && esNegra(i+1, j-1)) {

				alguna = true;
				
				destins.add(nova);
				
				setTablero(i+2, j-2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i+1, j-1, Fitxa.Z);
				
				recursiva(original, destins, nova, f);
				
				setTablero(i+2, j-2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i+1, j-1, morta);
				
				
				destins.remove(nova);

			}
		}
		if (esBuida(i+2, j+2)) {
			Integer nova = (i+2)*10+(j+2);
			morta = taulell[i+1][j+1];
			
			if (esNegra(f) && esBlanca(i+1, j+1)) {
				
				alguna = true;
				
				destins.add(nova);
				
				setTablero(i+2, j+2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i+1, j+1, Fitxa.Z);
				
				recursiva(original, destins, nova, f);
				
				setTablero(i+2, j+2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i+1, j+1, morta);
				
				
				destins.remove(nova);

			}
			
			else if (esDama(f) && esBlanca(f) && esNegra(i+1, j+1)) {
				
				alguna = true;
				
				destins.add(nova);
				
				setTablero(i+2, j+2, f);
				setTablero(i, j, Fitxa.Z);
				setTablero(i+1, j+1, Fitxa.Z);
				
				recursiva(original, destins, nova, f);
				
				setTablero(i+2, j+2, Fitxa.Z);
				setTablero(i, j, f);
				setTablero(i+1, j+1, morta);
				
				
				destins.remove(nova);

			}
		}
		
		if (! alguna) {
			Jugada jugada = new Jugada(original, new ArrayList<Integer>(destins), propietari(f), f);
			jugadesRecursives.add(jugada);
			return;
		}
		
	}

	 
	public Jugador getGuanyador() {
		if (getJugades(Jugador.NEGRES).size() == 0) return Jugador.BLANQUES;
		if (getJugades(Jugador.BLANQUES).size() == 0) return Jugador.NEGRES;
		if (getNumPecesBlanques() == 0) return Jugador.NEGRES; //ha guanyat
		if (getNumPecesNegres() == 0) return Jugador.BLANQUES; // hem guanyat
		if (getContadorMoviments() == 100) return Jugador.Z; // taules
		
		return null; //de moment no ha guanyat ningu
		
	}
	
	
	public ArrayList<Jugada> getJugades(Jugador jug) {
		ArrayList<Jugada> oblig = getJugadesObligades(jug);
		
		if (oblig.size() > 0) return oblig;
		ArrayList<Jugada> noOblig = getJugadesNoObligades(jug);
		return noOblig;
	}
	


}
