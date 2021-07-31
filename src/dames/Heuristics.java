package dames;


public class Heuristics {
	
	private Taulell t;

	/*
	 * VARIABLES: XX blanques - XX negres
	 * 
	 * ok 1: PIECE COUNT num. de fitxes normals (max)
	 * ok 2: KING COUNT num. de dames (max)
	 * ok 3: PIN peces que no es poden moure (min)
	 * ok 4: BALANCE: diferencia de peces entre esquerra i dreta (min)
	 * ...5: ANGLE[2]: encourage men on the back two ranks to move forward (dep.)
	 * ...6: BACKROW: encourage men to keep the backrank covered. (dep.)
	 * ok 7: CENTRALITY: checkers on the center of the board (max)
	 * ok 8: ADVANCEMENT: how advanced a checker is (dep.)
	 * ...9: SHADOW: squares that can never again be defended by a man (min) 
	 * 10: KING CENTRALIZE: center control for kings
	 * ok 11: CORNERS: penalties or bonuses to aid king play in double corners
	 * ok 12: MOBILITY: degree to which pieces are free or restricted in their movements.
	 * ...13: RUNAWAY: ability to advance unimpeded and become a king
	 * ...14: FORK: si s'amenacen dues peces alhora (max)
	 * 
	 */
	//
		
	private int[] pesos1;
	private int[] pesos2;
	
	public Heuristics (Taulell t, int[] pesos1, int[] pesos2) {
		this.t = t;
		
		this.pesos1 = pesos1;
		this.pesos2 = pesos2;

	}
	
	private int fitxes() {
		return t.getNumPecesBlanques() - t.getNumPecesNegres();
	}
	
	private int dames() {
		return t.getNumDamesBlanques() - t.getNumDamesNegres();
	}
	
	private int pecesBloquejades() {
		int a = t.getNumPecesBloquejades(Jugador.BLANQUES);
		int b = t.getNumPecesBloquejades(Jugador.NEGRES);
		return a-b;
	}
	
	private int balance() {
		
		int blanquesEsquerra = 0;
		int blanquesDreta = 0;
		int negresEsquerra = 0;
		int negresDreta = 0;
		
		for (int casella : t.getPeces(Jugador.BLANQUES)) {
			if (casella%10 <= 4) ++blanquesEsquerra;
			else ++blanquesDreta;
		}
		
		for (int casella : t.getPeces(Jugador.NEGRES)) {
			if (casella%10 <= 4) ++negresEsquerra;
			else ++ negresDreta;
		}
		
		int balanceBlanques = Math.abs(blanquesEsquerra - blanquesDreta);
		int balanceNegres = Math.abs(negresEsquerra - negresDreta);
				
		return balanceBlanques-balanceNegres;
	}
	
	private int center() {
		int centerBlanques = 0;
		int centerNegres = 0;
		
		for (int casella: t.getPeces(Jugador.BLANQUES)) {
			if (casella%10 > 2 && casella%10 < 7) ++centerBlanques;
		}
		for (int casella : t.getPeces(Jugador.NEGRES)) {
			if (casella%10 > 2 && casella%10 < 7) ++centerNegres;
		}
		return centerBlanques-centerNegres;
	}
	
	private int avançat() {
		
		int puntuacio = 0;
		
		for (int fitxa : t.getFitxes(Jugador.BLANQUES)) {
			int fila = fitxa/10;
			puntuacio -= fila;
		}
		for (int fitxa : t.getFitxes(Jugador.NEGRES)) {
			int fila = fitxa/10;
			puntuacio -= fila;
		}
		
		for (int dama : t.getDamesBlanques()) {
			int fila = dama/10;
			puntuacio += fila;
		}
		for (int dama : t.getDamesNegres()) {
			int fila = dama/10;
			puntuacio += fila;
		}
		
		return puntuacio;
		
	}
	
	private int doubleCorner() {
		int k = 0;
		if (t.getPeces(Jugador.BLANQUES).contains((Integer) 67)) ++k;
		if (t.getPeces(Jugador.BLANQUES).contains((Integer) 76)) ++k;
		if (t.getPeces(Jugador.BLANQUES).contains((Integer) 78)) ++k;
		if (t.getPeces(Jugador.BLANQUES).contains((Integer) 87)) ++k;
		
		if (t.getPeces(Jugador.NEGRES).contains((Integer) 12)) --k;
		if (t.getPeces(Jugador.NEGRES).contains((Integer) 21)) --k;
		if (t.getPeces(Jugador.NEGRES).contains((Integer) 23)) --k;
		if (t.getPeces(Jugador.NEGRES).contains((Integer) 32)) --k;
		
		return k;
	}

	private int mobilitat() {
		return t.getJugades(Jugador.BLANQUES).size() - t.getJugades(Jugador.NEGRES).size();
	}
	
	/*private int indefensables() {
		int indefensablesBlanques = 0;
		int indefensablesNegres = 0;
		
		for (int casella: t.getFitxes(Jugador.BLANQUES)) {
			
		}
		
		
	}*/
	
	
	public int getScore1() {
		
		//System.out.println("Situacio: ");
		//t.imprimeixTaulell();
		
		int score = pesos1[0] * fitxes() 
				+ pesos1[1] * dames() 
				+ pesos1[2] * pecesBloquejades()
				+ pesos1[3] * balance()
				+ pesos1[4] * center()
				+ pesos1[5] * avançat()
				+ pesos1[6] * doubleCorner()
				+ pesos1[7] * mobilitat();
		
		/*System.out.println(pesos1[0]+ " * " + fitxes() + " (fitxes) + " 
				+ pesos1[1] + " * " + dames() + " (dames) + "  
				+ pesos1[2] + " * " + pecesBloquejades() + " (bloq.) + " 
				+ pesos1[3]+ " * " + balance() + " (balance) + "
				+ pesos1[4] + " * " + center() + " (center) + "
				+ pesos1[5] + " * " + avançat() + " (avanç) + "
				+ pesos1[6] + " * " + doubleCorner() + " (corner) + "
				+ pesos1[7] + " * " + mobilitat() + "(mob)");*/

		return score;
	}
	
	public int getScore2() {
		int score = pesos2[0] * fitxes() 
				+ pesos2[1] * dames() 
				+ pesos2[2] * pecesBloquejades()
				+ pesos2[3] * balance()
				+ pesos2[4] * center()
				+ pesos2[5] * avançat()
				+ pesos2[6] * doubleCorner()
				+ pesos2[7] * mobilitat();
		return -score;
	}
	
	
}
