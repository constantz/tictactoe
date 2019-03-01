import java.lang.*;
import java.util.Scanner;

public class tictactoe{

	int[] allevakken = {1, 2, 3, 4, 5, 6, 7, 8, 9}; //alle vakken zoals in output
	int zetten = 0;
	char[] rij1 = {'a', 'a', 'a'};
	char[] rij2 = {'a', 'a', 'a'};
	char[] rij3 = {'a', 'a', 'a'};		// 'a' zit in de vakjes als ze "leeg" zijn, worden gevuld
	char[] kol1 = {'a', 'a', 'a'};		// met 'x' en 'o' (kruisjes en rondjes)
	char[] kol2 = {'a', 'a', 'a'};
	char[] kol3 = {'a', 'a', 'a'};
	char[] dia1 = {'a', 'a', 'a'};
	char[] dia2 = {'a', 'a', 'a'};
	
	char[][] allrows = {rij1, rij2, rij3, kol1, kol2, kol3, dia1, dia2};
	
	public static void main(String[] args){
	
		boolean wint = false;
		int invoer, compinvoer;
		Scanner scanner = new Scanner(System.in);	
		tictactoe boter = new tictactoe();
		StringBuilder[] screen = boter.screen();			// methode screen in tictactoe maakt een screen
		boter.printScreen(screen);
		
		System.out.println("Speler aan zet; zet een kruisje door een getal in te voeren");
		invoer = scanner.nextInt();
		boter.allevakken[invoer-1] = 0; 
		boter.zetten = 1;
		System.out.println("\n\n\n\n\n\n\n");
		
		boter.spelerZet(invoer, 'x'); //eerste zet door speler
		boter.updateScreen(screen, invoer, 'x');
		boter.printScreen(screen);
		System.out.println("Zetten = " + boter.zetten);
		
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println("Computer speelt....");
				
		if (invoer == 5){
			int z = (int)(Math.random()*4);
			if (z == 0)	compinvoer = 1;
			else if (z == 1) compinvoer = 3;
			else if (z == 2) compinvoer = 7;								
			else compinvoer = 9;			
		}
		
		else{		
			compinvoer = 5;
		}	
		
		boter.allevakken[compinvoer - 1] = 0;	//wordt gebruikt bij check of niet nogmaals ingevoerd
		boter.zetten += 1;
		boter.spelerZet(compinvoer, 'o');	//eerste zet door computer
		boter.updateScreen(screen, compinvoer, 'o');
		boter.printScreen(screen);
		System.out.println("Zetten = " + boter.zetten);
		System.out.println("\n\n\n\n\n\n\n");
		
		while(boter.zetten < 9){
			System.out.println("Zet een kruisje door een getal in te voeren...."); //volgende zet speler
			invoer = scanner.nextInt();
			
			while (boter.allevakken[invoer - 1] == 0){
				System.out.println("Nummer al gespeeld; kies een ander nummer");
				invoer = scanner.nextInt();
			}
			
			boter.allevakken[invoer - 1] = 0;
			boter.zetten += 1;
							
			System.out.println("\n\n\n\n\n\n\n");
		
			boter.spelerZet(invoer, 'x'); 
			boter.updateScreen(screen, invoer, 'x');
			boter.printScreen(screen);
			System.out.println("Zetten = " + boter.zetten);
			
			
			if (boter.spelGewonnen(boter.allrows, 'x')){
				System.out.println("Speler wint!");
				wint = true;
				boter.zetten = 9;
			}
			
			boolean algezet = false;
			if (boter.zetten < 9){		//computerzet
				System.out.println("\n\n\n\n\n\n\n");
				System.out.println("Computer speelt....");
				
				boter.computerZet(boter.allrows, 'o', 'a');	// test eerst of iets moet
				if  (boter.computerGezet){
					compinvoer = boter.rekenOmNaarPlayerInput(boter.rijComputerZet, boter.colComputerZet);
					algezet = true;
				}
				
				else{
					boter.computerZet(boter.allrows, 'x', 'a');	// test eerst of iets moet
					if ((boter.computerGezet)&&(!algezet)){
						compinvoer = boter.rekenOmNaarPlayerInput(boter.rijComputerZet, boter.colComputerZet);
					}
					else {
						compinvoer = invoer;
						while(boter.allevakken[compinvoer-1] == 0) {
							compinvoer = boter.kiesVolgende(boter.allevakken, (compinvoer - 1)) + 1;
						}
					}
				}	
				boter.spelerZet(compinvoer, 'o');
				boter.updateScreen(screen, compinvoer, 'o');
				boter.printScreen(screen);
				boter.allevakken[compinvoer - 1] = 0;
				boter.zetten += 1;
				System.out.println("Zetten = " + boter.zetten);
			}			
			if (boter.spelGewonnen(boter.allrows, 'o')){
				System.out.println("Computer wint!");
				wint = true;
				boter.zetten = 9;
			}
			
		}
		if (!wint) System.out.println("Gelijkspel...");
	}
	
	
	
		
	StringBuilder[] screen(){
		StringBuilder[] screenlocal = new StringBuilder[11];
			screenlocal[0] = new StringBuilder ("       |       |       ");
			screenlocal[1] = new StringBuilder ("   1   |   2   |   3   ");
			screenlocal[2] = new StringBuilder ("       |       |       ");
			screenlocal[3] = new StringBuilder ("_______________________");
			screenlocal[4] = new StringBuilder ("       |       |       ");
			screenlocal[5] = new StringBuilder ("   4   |   5   |   6   ");
			screenlocal[6] = new StringBuilder ("       |       |       ");
			screenlocal[7] = new StringBuilder ("_______________________");
			screenlocal[8] = new StringBuilder ("       |       |       ");
			screenlocal[9] = new StringBuilder ("   7   |   8   |   9   ");
			screenlocal[10] = new StringBuilder ("       |       |       ");
		return screenlocal;
	}
		
	public void printScreen(StringBuilder[] screenToPrint){
		System.out.println(screenToPrint[0].toString());
		System.out.println(screenToPrint[1].toString());
		System.out.println(screenToPrint[2].toString());
		System.out.println(screenToPrint[3].toString());
		System.out.println(screenToPrint[4].toString());
		System.out.println(screenToPrint[5].toString());
		System.out.println(screenToPrint[6].toString());
		System.out.println(screenToPrint[7].toString());
		System.out.println(screenToPrint[8].toString());
		System.out.println(screenToPrint[9].toString());
		System.out.println(screenToPrint[10].toString());
	}
		
	public void updateScreen(StringBuilder[] screenToUpdate, int i, char c){
		if (i == 1 && c == 'x') {
			screenToUpdate[0].delete(1, 6).insert(1, "X   X");
			screenToUpdate[1].delete(3, 4).insert(3, "X");
			screenToUpdate[2].delete(1, 6).insert(1, "X   X");
		}
		else if (i == 1 && c == 'o') {
			screenToUpdate[0].delete(3, 4).insert(3, "O");
			screenToUpdate[1].delete(1, 6).insert(1, "O   O");
			screenToUpdate[2].delete(3, 4).insert(3, "O");
		}
		else if (i == 2 && c == 'x') {
			screenToUpdate[0].delete(9, 14).insert(9, "X   X");
			screenToUpdate[1].delete(11, 12).insert(11, "X");
			screenToUpdate[2].delete(9, 14).insert(9, "X   X");
		}
		else if (i == 2 && c == 'o') {
			screenToUpdate[0].delete(11, 12).insert(11, "O");
			screenToUpdate[1].delete(9, 14).insert(9, "O   O");
			screenToUpdate[2].delete(11, 12).insert(11, "O");
		}
		else if (i == 3 && c == 'x') {
			screenToUpdate[0].delete(17, 22).insert(17, "X   X");
			screenToUpdate[1].delete(19, 20).insert(19, "X");
			screenToUpdate[2].delete(17, 22).insert(17, "X   X");
		}
		else if (i == 3 && c == 'o') {
			screenToUpdate[0].delete(19, 20).insert(19, "O");
			screenToUpdate[1].delete(17, 22).insert(17, "O   O");
			screenToUpdate[2].delete(19, 20).insert(19, "O");
		}
		else if (i == 4 && c == 'x') {
			screenToUpdate[4].delete(1, 6).insert(1, "X   X");
			screenToUpdate[5].delete(3, 4).insert(3, "X");
			screenToUpdate[6].delete(1, 6).insert(1, "X   X");
		}
		else if (i == 4 && c == 'o') {
			screenToUpdate[4].delete(3, 4).insert(3, "O");
			screenToUpdate[5].delete(1, 6).insert(1, "O   O");
			screenToUpdate[6].delete(3, 4).insert(3, "O");
		}
		else if (i == 5 && c == 'x') {
			screenToUpdate[4].delete(9, 14).insert(9, "X   X");
			screenToUpdate[5].delete(11, 12).insert(11, "X");
			screenToUpdate[6].delete(9, 14).insert(9, "X   X");
		}
		else if (i == 5 && c == 'o') {
			screenToUpdate[4].delete(11, 12).insert(11, "O");
			screenToUpdate[5].delete(9, 14).insert(9, "O   O");
			screenToUpdate[6].delete(11, 12).insert(11, "O");
		}
		else if (i == 6 && c == 'x') {
			screenToUpdate[4].delete(17, 22).insert(17, "X   X");
			screenToUpdate[5].delete(19, 20).insert(19, "X");
			screenToUpdate[6].delete(17, 22).insert(17, "X   X");
		}
		else if (i == 6 && c == 'o') {
			screenToUpdate[4].delete(19, 20).insert(19, "O");
			screenToUpdate[5].delete(17, 22).insert(17, "O   O");
			screenToUpdate[6].delete(19, 20).insert(19, "O");
		}
		else if (i == 7 && c == 'x') {
			screenToUpdate[8].delete(1, 6).insert(1, "X   X");
			screenToUpdate[9].delete(3, 4).insert(3, "X");
			screenToUpdate[10].delete(1, 6).insert(1, "X   X");
		}
		else if (i == 7 && c == 'o') {
			screenToUpdate[8].delete(3, 4).insert(3, "O");
			screenToUpdate[9].delete(1, 6).insert(1, "O   O");
			screenToUpdate[10].delete(3, 4).insert(3, "O");
		}
		else if (i == 8 && c == 'x') {
			screenToUpdate[8].delete(9, 14).insert(9, "X   X");
			screenToUpdate[9].delete(11, 12).insert(11, "X");
			screenToUpdate[10].delete(9, 14).insert(9, "X   X");
		}
		else if (i == 8 && c == 'o') {
			screenToUpdate[8].delete(11, 12).insert(11, "O");
			screenToUpdate[9].delete(9, 14).insert(9, "O   O");
			screenToUpdate[10].delete(11, 12).insert(11, "O");
		}
		else if (i == 9 && c == 'x') {
			screenToUpdate[8].delete(17, 22).insert(17, "X   X");
			screenToUpdate[9].delete(19, 20).insert(19, "X");
			screenToUpdate[10].delete(17, 22).insert(17, "X   X");
		}
		else if (i == 9 && c == 'o') {
			screenToUpdate[8].delete(19, 20).insert(19, "O");
			screenToUpdate[9].delete(17, 22).insert(17, "O   O");
			screenToUpdate[10].delete(19, 20).insert(19, "O"); 
		}
	}
		
	public void spelerZet(int i, char c){
		if (i == 1) {
			rij1[0] = kol1[0] = dia1[0] = c;
		}
		else if (i == 2) {
			rij1[1] = kol2[0] = c;
		}
		else if (i == 3) {
			rij1[2] = kol3[0] = dia2[0] = c; 
		}
		else if (i == 4) {
			rij2[0] = kol1[1] = c;
		}
		else if (i == 5) {
			rij2[1] = kol2[1] = dia1[1] = dia2[1] = c;
		}
		else if (i == 6) {
			rij2[2] = kol3[1] = c;
		}
		else if (i == 7) {
			rij3[0] = kol1[2] = dia2[2] = c;
		}
		else if (i ==8) {
			rij3[1] = kol2[2] = c;
		}
		else if (i == 9) {
			rij3[2] = kol3[2] = dia1[2] = c;
		}
	}
	
	int inRij(char c, char[] cc){			// telt hoevaak char c voorkomt in array cc, geeft
		int i = 0;							// dat aantal terug als int inRij();
		for (int j = 0; j<cc.length; j++){
			if (cc[j] == c) {
				++i;
			}	
		}
		return i;
	}
	
	int rekenOmNaarPlayerInput(int columnnr, int rownr){
		if (columnnr == 1){
			if (rownr == 1) return 1;				// rekent een door de comp gespeelde positie in een
			else if (rownr == 2) return 2;			// array zoals rij1, rij2, kol3, dia1 etc.
			else if (rownr == 3) return 3;			// om naar een integer voor spelerZet methode
		}											// zodat de zet gespeeld kan worden als een spelerzet.
		else if (columnnr == 2){					// columnr en rownr moeten nog wel ergens gedefinieerd
			if (rownr == 1) return 4;				// worden, bijv: if dia2[0] {columnr = 3; rownr = 1;}
			else if (rownr == 2) return 5;
			else if (rownr == 3) return 6;
		}
		else if (columnnr == 3){
			if (rownr == 1) return 7;
			else if (rownr == 2) return 8;
			else if (rownr == 3) return 9;
		}
		return 0;
	}
	
	
	
	
	void computerZet(char[][] rijenVanDrie, char c, char d){	// char d is het "lege" karakter, a
		computerGezet = false;
		for(int i = 0; i < rijenVanDrie.length; i++){
			if ((inRij(c, rijenVanDrie[i]) == 2) && (inRij(d, rijenVanDrie[i]) == 1)){  // ALARM!!
				for(int j = 0; j<rijenVanDrie[i].length; j++){
					if (rijenVanDrie[i][j] == d){
						if (i == 0){	//rij1
							rijComputerZet = 1;
							colComputerZet = j + 1;		
						}
						else if (i == 1){ //rij2
							rijComputerZet = 2;
							colComputerZet = j + 1;
						}
						else if (i == 2){ //rij3
							rijComputerZet = 3;
							colComputerZet = j + 1;
						}
						else if (i == 3){   //kol1
							rijComputerZet = j + 1;
							colComputerZet = 1;
						}
						else if (i == 4){   //kol2
							rijComputerZet = j + 1;
							colComputerZet = 2;
						}
						else if (i == 5){   //kol3
							rijComputerZet = j + 1;
							colComputerZet = 3;
						}
						else if (i == 6){  //dia1
							rijComputerZet = j + 1;
							colComputerZet = j + 1;
						}
						else if (i == 7){ //dia2
							rijComputerZet = j + 1;
							if (j == 0) colComputerZet = 3;
							else if (j == 1) colComputerZet = 2;
							else if (j == 2) colComputerZet = 1;
						}
					}
					computerGezet = true;				
				}	
			}
		}
	}		
	boolean computerGezet = false;
	int rijComputerZet = 0;
	int colComputerZet = 0; // dit zijn de argumenten voor rekenOmNaarPlayerInput, na 
										// runnen methode computerZet
	boolean spelGewonnen(char[][] rijenVanDrie, char c){  //kijkt of het spel gewonnen is voor char c
		int k = 0;
		for(int i = 0; i < rijenVanDrie.length; i++){
			k = 0;
			for(int j = 0; j < rijenVanDrie[i].length; j++){
				if ((rijenVanDrie[i][j]) == c){
					++k;
				}
			}
			if (k == 3) return true;
		}
		return false;
	}
	
	int kiesVolgende(int[] ar, int j){			//	kiest de volgende index van een array, of gaat naar 0
		if ((j + 1) == ar.length) return 0;
		else return j + 1;
	}
}