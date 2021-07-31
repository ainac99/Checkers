package io;

import java.util.Collections;
import java.util.Scanner;

import dames.Resultat;

public class Consola {
	// Reset
    public static final String RESET = "\033[0m";  // Text Reset

    public static final String ESBORRA = "\033c"; //Esborra l'ultim escrit
    public static final String CLEAR = "\033[2J"; //Clear screen
    public static final String HOME = "\033[H"; //Ves al home
    public static final String GOINICI = "\033[1;1H\033c";
    
    public static final String IMPRIMIRJUGADA = "\033[24;1H"; //imprimeix a la fila 24
    public static final String ENTRAMOVIMENT = "\033[26;1H"; //imprimeix a la fila 26
    public static final String LINIA_BUIDA = "                                                                                                                  ";
    
    
    public static final String BLINK = "\033[5m";
    public static final String NO_BLINK = "\033[25m";
    
    public static final String LIGHT = "\033[2m";
    
    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    //Missatges
    public static String CRUCIFIX_1 = imprimeixALaLinea(1, 20) + ".-=====-.\n"; //20
    public static String CRUCIFIX_2 = imprimeixALaLinea(2, 20) + "| .\"\"\". |\n"; //20
    public static String CRUCIFIX_3 = imprimeixALaLinea(3, 20) + "| |   | |\n"; //20
    public static String CRUCIFIX_4 = imprimeixALaLinea(4, 20) + "| |   | |\n"; //20
    public static String CRUCIFIX_5 = imprimeixALaLinea(5, 20) + "| '---' |\n"; //20
    public static String CRUCIFIX_6 = imprimeixALaLinea(6, 20) + "|       |\n"; //20
    public static String CRUCIFIX_7 = imprimeixALaLinea(7, 20) + "|       |\n"; //20
    public static String CRUCIFIX_8 = imprimeixALaLinea(8, 1) + " .-================-'       '-================-.\n";
    public static String CRUCIFIX_9 = imprimeixALaLinea(9, 1) + " |  _                                          |\n";
    public static String CRUCIFIX_10 = imprimeixALaLinea(10, 1) + " |.'o\\                                    __   |\n";
    public static String CRUCIFIX_11 = imprimeixALaLinea(11, 1) + " | '-.'.                                .'o.`  |\n";
    public static String CRUCIFIX_12 = imprimeixALaLinea(12, 1) + " '-==='.'.=========-.       .-========.'.-'===-'\n";
    public static String CRUCIFIX_13 = imprimeixALaLinea(13, 8) + "'.`'._    .===,     |     _.-' /\n"; //8
    public static String CRUCIFIX_14 = imprimeixALaLinea(14, 10) + "'._ '-./  ,//\\   _| _.-'  _.'\n"; //10
    public static String CRUCIFIX_15 = imprimeixALaLinea(15, 13) + "'-.| ,//'  \\-'  `   .-'\n"; //13
    public static String CRUCIFIX_16 = imprimeixALaLinea(16, 16) + "`//'_`--;    ;.-'\n"; //16
    public static String CRUCIFIX_17 = imprimeixALaLinea(17, 18) + "`\\._ ;|    |\n"; //18
    public static String CRUCIFIX_18 = imprimeixALaLinea(18, 21) + "\\`-'  . |\n"; //21
    public static String CRUCIFIX_19 = imprimeixALaLinea(19, 21) + "|_.-'-._|\n";
    public static String CRUCIFIX_20 = imprimeixALaLinea(20, 21) + "\\  _'_  /\n";
    public static String CRUCIFIX_21 = imprimeixALaLinea(21, 21) + "|; -:- | \n";
    public static String CRUCIFIX_22 = imprimeixALaLinea(22, 21) +"|| -.- \\ \n";
    public static String CRUCIFIX_RESTA = "                     |;     .\\\n"
    + "                     / `'\\'`\\-;\n"
    + "                    ;`   '. `_/\n"
    + "                    |,`-._;  .;\n"
    + "                    `;\\  `.-'-;\n"
    + "                     | \\   \\  |\n"
    + "                     |  `\\  \\ |\n"
    + "                     |   )  | |\n"
    + "                     |  /  /` /\n"
    + "                     | |  /|  |\n"
    + "                     | | / | /\n"
    + "                     | / |/ /|\n"
    + "                     | \\ / / |\n"
    + "                     |  /o | |\n"
    + "                     |  |_/  |\n"
    + "                     |       |\n"
    + "                     |       |\n"
    + "                     |       |\n"
    + "                     |       |\n"
    + "                     |       |\n"
    + "                     |       |\n"
    + "                     '-=====-'";		
    		
    private static final String CALAVERES =
    		"                                           .-. _)/\n"
    		+ "                                          (0,0) .\\\n"
    		+ "                                           (u)   ()\n"
    		+ "      .-.                           _\\)  .-=\"=-.//  \n"
    		+ "     (o,o)                            \\,//==\\=== \n"
    		+ "      (e)                              ()  =====            .-.\n"
    		+ "    .-=\"=-.  \\(_           .-.         _____ =,=           (a.a)\n"
    		+ "   //==I==\\\\,/            (d.b)       ()--___(0V0)  (/_     (=)\n"
    		+ "  ()  =\"=  ()              (u)        ||()----'      \\, ___.=\"==-._\n"
    		+ "   \\`(0V0)               .-=\"-.       |' \\\\           ()---` ==\\==\\\\\n"
    		+ "  /|) ||\\\\              //==/=\\\\    ==\"   \\'                   =\"= ()\n"
    		+ "      || \\\\  ==.       () ==== ()_/_    ==\"               ____(0V0) \\`\n"
    		+ " jgs  ()  ()    \\,      `\\\"=      `                      ()---` // (|\\\n"
    		+ "     //  //      \\\\ ___(0);`               \\)/ .-.       ||    //\n"
    		+ "    '/  '/        ()---'  \\\\                /,(o,o)      |'   ()\n"
    		+ "    \"== \"==                \\\\              ()  (w)     ==\"     \\\\\n"
    		+ "                            ()      /_ ___  \\\\,=\",              \\`\n"
    		+ "         .-.               //       '-()-()   =/=\\\\            ==\"\n"
    		+ "        (o.o)             '/         //\\\\||  ==== ()           .-.   \\(_\n"
    		+ "         (n)              \"==       /`  \\\\|  =\"=  `|          (-.-)  ,/\n"
    		+ "       .-=\"=-.  \\)                ==\"    `(0V0)    '--         (-)  ()\n"
    		+ "      // =T= \\\\,/                                            .-=\"=.//\n"
    		+ "     () ==|== ()                                            //==I==`\n"
    		+ "      \\  =\"=              TAULES !                     _\\__()  ===\n"
    		+ "      /)(0V0)                              /  \\) .-.   -' `   (0V0)\n"
    		+ "        // \\\\                                 //(e.e)        // //\n"
    		+ "       //   \\\\      .-.  \\)                  ()  (=)        // //\n"
    		+ "      ()     ()    (o.o) ,|                   \\\\.=\"=.      () ()\n"
    		+ "       \\\\    ||     (m)  ()               ()   ==/==\\\\      \\\\ \\\\\n"
    		+ "  jgs   \\'   '|     =\"=//                //\\\\  ===  ()       \\` \\`\n"
    		+ "      ==\"     \"==  //=T=   ()           /`  \\(0V0) _/      ==\" ==\"\n"
    		+ "                  _\\` === //\\\\        ==\"      || (|\\      ,==\n"
    		+ "   (/_   .-.       /\\ (0V0)  `\\                ||          .\\\n"
    		+ "    \\,  (o.o)           \\\\    \"==              ()   /_____  \\\\\n"
    		+ "     ()  (=)             ()   ==.      .==      \\\\  ` '--()  ()\n"
    		+ "      \\\\.=\"=-.  (|/     //     /,     ,/         \\`       \\\\ ||\n"
    		+ "        ==|==\\\\,/      '/     //     //         ==\"        \\\\||      \\)/\n"
    		+ "         ===  ()       \"==   ()     ()            .-.      (0A0)    ,/\n"
    		+ "         =.=                  \\\\   //            (a,a)      =\"=    ()\n"
    		+ "        (0V0)____              \\\\ //              (o)        ==\\= //\n"
    		+ "         \\\\ ----()\\            (0A0)       \\(/  .-=\"=.        .==='\n"
    		+ "          \\\\      `\\            =,=          \\,//==/=\\\\     //  (w)\n"
    		+ "           ()       \"==        =====          () ==== ()   ()  (o'o)\n"
    		+ "          //                  .==I==.            =\"=  |'    `\\  '-'\n"
    		+ "         '/                 //  (=)  \\\\     ____(0V0)/|\\     /|)\n"
    		+ "         \"==               ()  (d'b)  ()   ()----`//\n"
    		+ "                            \\`  '-'  `/     \\\\   //\n"
    		+ "                           /|\\       /\\\\     \\` ()\n"
    		+ "                                           ==\"   \\\\\n"
    		+ "                                                  \\`\n"
    		+ "                                                ==\"\n";
    
    private static final String GAME_OVER_2 = "\n"
    		+ " ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ \n"
    		+ "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗\n"
    		+ "██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝\n"
    		+ "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\n"
    		+ "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\n"
    		+ " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝\n"
    		+ "                                                                          \n";
    
    private static final String GAME_OVER_2_1 = " ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ \n";
    private static final String GAME_OVER_2_2 = "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗\n";
    private static final String GAME_OVER_2_3 = "██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝\n";
    private static final String GAME_OVER_2_4 = "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\n";
    private static final String GAME_OVER_2_5 = "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\n";
    private static final String GAME_OVER_2_6 = " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝\n";

    private static final String YOU_WIN_1 =   "██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗██╗███╗   ██╗\n";
	private static final String YOU_WIN_2 =   "╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██║████╗  ██║\n";
	private static final String YOU_WIN_3 =   " ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║██╔██╗ ██║\n";
	private static final String YOU_WIN_4 =	 "  ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║██║╚██╗██║\n";
	private static final String YOU_WIN_5 =	 "   ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝██║██║ ╚████║\n";
	private static final String YOU_WIN_6 =	 "   ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝\n";

    
    public static void netejaPantalla() {
		System.out.print(HOME);
		System.out.println(CLEAR);
		System.out.println(RESET);
	}
    
    public static void imprimeixResultat(Resultat resultat) throws InterruptedException {
    	switch(resultat) {
	    	case GUANYADA:
	    		animaGameOver();
	    		break;
	    	case PERDUDA:
	    		imprimeixCrucifix();
	    		animaYouWin();
	    		break;
	    	case TAULES:
	    		imprimeixCalaveres();
	    		break;
    	}
    }
    
    public static void imprimeixGameOver() throws InterruptedException {
    	System.out.print(imprimeixALaLinea(31, 11) + GAME_OVER_2_1);
    	System.out.print(imprimeixALaLinea(32, 11) + GAME_OVER_2_2);
    	System.out.print(imprimeixALaLinea(33, 11) + GAME_OVER_2_3);
    	System.out.print(imprimeixALaLinea(34, 11) + GAME_OVER_2_4);
    	System.out.print(imprimeixALaLinea(35, 11) + GAME_OVER_2_5);
    	System.out.print(imprimeixALaLinea(36, 11) + GAME_OVER_2_6);
    	Thread.sleep(500);
    	
    }
    
    public static void imprimeixLletraALletra(String s) throws InterruptedException {
    	for (int i = 0; i < s.length(); ++i) {
    		System.out.print(imprimeixALaLinea(16, 70+i) + s.charAt(i));
    		Thread.sleep(50);
    	}
    }
    
    public static void imprimeixYouWin() throws InterruptedException {
    	System.out.println(imprimeixALaLinea(8, 65)  + YOU_WIN_1);
    	System.out.println(imprimeixALaLinea(9, 65)  + YOU_WIN_2);
    	System.out.println(imprimeixALaLinea(10, 65) + YOU_WIN_3);
    	System.out.println(imprimeixALaLinea(11, 65) + YOU_WIN_4);
    	System.out.println(imprimeixALaLinea(12, 65) + YOU_WIN_5);
    	System.out.println(imprimeixALaLinea(13, 65) + YOU_WIN_6);
    	
    }
    
    private static void parpalleja() throws InterruptedException {
    	for (int i = 31; i <= 36; ++i) {
    		System.out.print(imprimeixALaLinea(i, 11) + LINIA_BUIDA);
    	}
    	Thread.sleep(350);
    }
    
    public static void animaGameOver() throws InterruptedException {
    	
    	imprimeixGameOver();
    	parpalleja();
    	imprimeixGameOver();
    	parpalleja();
    	imprimeixGameOver();
    	parpalleja();
    	imprimeixGameOver();
    	
    	
    	for (int i = 30; i >= 8; --i) {
    		System.out.print(imprimeixALaLinea(i, (-20 + i)) + GAME_OVER_2_1);
    		System.out.print(imprimeixALaLinea(i+1, -19+i) + LINIA_BUIDA);
    		Thread.sleep(35);
    	}
    	
    	for (int i = 31; i >= 9; --i) {
    		System.out.print(imprimeixALaLinea(i, (-21 + i)) + GAME_OVER_2_2);
    		System.out.print(imprimeixALaLinea(i+1, -20+i) + LINIA_BUIDA);
    		Thread.sleep(35);
    	}
    	
    	for (int i = 32; i >= 10; --i) {
    		System.out.print(imprimeixALaLinea(i, (-22 + i)) + GAME_OVER_2_3);
    		System.out.print(imprimeixALaLinea(i+1, -21+i) + LINIA_BUIDA);
    		Thread.sleep(35);
    	}
    	
    	for (int i = 33; i >= 11; --i) {
    		System.out.print(imprimeixALaLinea(i, (-23 + i)) + GAME_OVER_2_4);
    		System.out.print(imprimeixALaLinea(i+1, -22+i) + LINIA_BUIDA);
    		Thread.sleep(35);
    	}
    	
    	for (int i = 34; i >= 12; --i) {
    		System.out.print(imprimeixALaLinea(i, (-24 + i)) + GAME_OVER_2_5);
    		System.out.print(imprimeixALaLinea(i+1, -23+i) + LINIA_BUIDA);
    		Thread.sleep(35);
    	}
    	
    	for (int i = 35; i >= 13; --i) {
    		System.out.print(imprimeixALaLinea(i, (-25 + i)) + GAME_OVER_2_6);
    		System.out.print(imprimeixALaLinea(i+1, -24+i) + LINIA_BUIDA);
    		Thread.sleep(35);
    	}
    	
    	System.out.println(HOME);
    	
    }
    
    public static void animaYouWin() throws InterruptedException {
    	System.out.print(BLINK);
    	imprimeixYouWin();
    	Thread.sleep(2000);
    	System.out.print(NO_BLINK);
    	imprimeixYouWin();
    }
    
    public static void imprimeixCrucifix() throws InterruptedException {
    	System.out.print(CRUCIFIX_1);
    	System.out.print(CRUCIFIX_2);
    	System.out.print(CRUCIFIX_3);
    	System.out.print(CRUCIFIX_4);
    	System.out.print(CRUCIFIX_5);
    	System.out.print(CRUCIFIX_6);
    	System.out.print(CRUCIFIX_7);
    	System.out.print(CRUCIFIX_8);
    	System.out.print(CRUCIFIX_9);
    	System.out.print(CRUCIFIX_10);
    	System.out.print(CRUCIFIX_11);
    	System.out.print(CRUCIFIX_12);
    	System.out.print(CRUCIFIX_13);
    	System.out.print(CRUCIFIX_14);
    	System.out.print(CRUCIFIX_15);
    	System.out.print(CRUCIFIX_16);
    	System.out.print(CRUCIFIX_17);
    	System.out.print(CRUCIFIX_18);
    	System.out.print(CRUCIFIX_19);
    	System.out.print(CRUCIFIX_20);
    	System.out.print(CRUCIFIX_21);
    	System.out.print(CRUCIFIX_22);
    	System.out.print(CRUCIFIX_RESTA);
    }
    
    public static String preguntaSiUnaAltra() throws InterruptedException {
    	imprimeixLletraALletra("Una altra? (s/n) ");
    	Scanner entradaConsola = new Scanner(System.in);	
		return entradaConsola.nextLine();
    	
    }
    
    private static void imprimeixTaules() throws InterruptedException {
    	System.out.println(imprimeixALaLinea(31, 1) + CALAVERES);
    }
    
    public static void imprimeixBarra(int current, int total, int guanyades, int perdudes, int taules) {
    	
	    StringBuilder string = new StringBuilder(140);   
	    int percent = (int) (current * 100 / total);
	    string
	        .append('\r')
	        .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
	        .append(String.format(" %d%% [", percent))
	        .append(String.join("", Collections.nCopies(percent, WHITE_BACKGROUND_BRIGHT + " " + RESET)))
	        .append(String.join("", Collections.nCopies(100 - percent, " ")))
	        .append(']')
	        .append(String.join("", Collections.nCopies(current == 0 ? (int) (Math.log10(total)) : (int) (Math.log10(total)) - (int) (Math.log10(current)), " ")))
	        .append(String.format(" %d/%d", current, total));
	
	   // Consola.imprimeixALaLinea(24, 1);
	    System.out.print(string);
	    System.out.print("  g: " + (guanyades*100)/total + "% p: " + (perdudes*100)/total + " % t: " + (taules*100)/total + "%");
	}
    
    public static void imprimeixBarraAmbResultat(int guanyades, int perdudes, int taules, int total) {
    	StringBuilder string = new StringBuilder(140);   
	    int percentGuanyades = (int) (guanyades * 100 / total);
	    int percentPerdudes = (int) (perdudes * 100 / total);
	    int percentTaules = (int) (taules * 100 / total);
	    int percentTotal = 100;
	    string
	        .append('\r')
	        .append(String.join("", Collections.nCopies(percentTotal == 0 ? 2 : 2 - (int) (Math.log10(percentTotal)), " ")))
	        .append(String.format(" %d%% [", percentTotal))
	        .append(String.join("", Collections.nCopies(percentGuanyades, GREEN_BACKGROUND + " " + Consola.RESET)))
	        .append(String.join("", Collections.nCopies(percentPerdudes, RED_BACKGROUND + " " + Consola.RESET)))
	        .append(String.join("", Collections.nCopies(percentTaules, WHITE_BACKGROUND_BRIGHT + " " + Consola.RESET)))
	        .append(']')
	        .append(String.join("", Collections.nCopies(total == 0 ? (int) (Math.log10(total)) : (int) (Math.log10(total)) - (int) (Math.log10(total)), " ")))
	        .append(String.format(" %d/%d", total, total));
	
	    //Consola.imprimeixALaLinea(24, 1)
	    System.out.print(string);
    }
    
    public static void imprimeixCalaveres() {
    	System.out.print(CALAVERES);
    }
    
    private static String imprimeixALaLinea(int fila, int col) {
    	return "\033[" + fila + ";" + col + "H";
    }
}
