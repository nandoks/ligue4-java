import java.util.Scanner;

public class main{

	public static int grille[][];			// creation de la variable tableau bidimensionnel grille


	public static void main(String[] args){
		jeu();  // debute le jeux
	}

	public static void jeu(){

		Scanner input = new Scanner(System.in);

		initialiseGrille();					// initialise la grille avec que des 0 pour toutes les cases du tableau
		int joueur = 1;    					// le jeux commence avec le joueur 1
		
		afficheGrille();  			// affiche la grille vide
		
		//boucle du jeu qui se repete jusqu`a qu`un joueur ait gagne la partie ou que le match soit nul
		while(!aGagne() || !matchNul()){
			
			System.out.printf("Quel coup pour le joueur %d ? Où?\n", joueur);
			int colone = input.nextInt();						// prend le input du joueur pour la colone dans laquelle jouer
		
			if(colone >= 0 && colone <= 6){ 		
				jouer(joueur, colone);					// joue le coup du joueur dans la colone correspondante
				if(joueur == 1){								// si le joueur 1 vient de jouer,on change de joueur
					joueur = 2;
				} else {										// si ce n`est pas le joueur 1 qui joue, on change de joueur
					joueur = 1;
				}
				
			}else{												
				/*  si la valeur est en dehors de l`intervalle [0;6], un message
				s`affiche en disant que c`est impossible de jouer dans cette case*/
				System.out.println("La valeur rentree n'est pas dans la grille.\nMerci de choisir une valeur comprise entre 0 et 6.\n");

			}
			afficheGrille();								// affiche la grille a l'etat du n-imeme coup
			
		}

		if(joueur == 2){				// avant sortir de la boucle du jeu notre joueur changera d`apres notre code
			joueur = 1;					// ici si le joueur 1 a donne le dernier coup et a gagne, la valeur affecte a	
		} else {						// joueur est 2, donc on change ça pour ne pas rendre le mauvais resultat
			joueur = 2;
		}

		// en sortant de la boucle on veut afficher si le match a été gagné par quelqu'un ou si le match est nul
		if(aGagne() == true){   					// si le jeu a été gané par quelqu'un il affiche qui a gagné
			System.out.println("Le joueur " + joueur +" a gagné \n"); 
		}else{ 									// si le match est nul il affiche match nul
			System.out.println("Le match est nul");
		}
	}
	// fin jeu

	// initialise la grille bidimensionelle de taille nxm
	static void initialiseGrille(){
		grille = new int[6][7]; 	/* initialise une grille de 6 lignes et 7 colones dans lequel 
									toute les valeurs sont egales a 0 Java rempli deja ce 
									tableau avec la valeur 0 si rien n`est specifie pas besoin 
									d`une boucle pour affecter les valeurs de 0 a toutes les 
									cases du tableau */
	}
	//fin initialiseGrille

	// joue le coup correspondant a une colone entree par le joueur
	public static void jouer(int joueur, int colone){

		int ligne = 0;

		while(grille[ligne][colone]!=0){
			ligne++;
		}
		grille[ligne][colone] = joueur;

	}
	// fin jouer

	// fonction qui affiche la grille du jeu
	public static void afficheGrille(){
		
		final String [] symbole = new String[3];
		
		symbole[0] = "|   ";
		symbole[1] = "| X ";
		symbole[2] = "| O ";
		
		//traite les lignes du tableau
		for(int ligne = grille.length - 1; ligne >= 0; ligne --){
			//traite les colones du tableau
			for(int colone = 0; colone<grille[ligne].length; colone++){

				System.out.print(symbole[grille[ligne][colone]]); // affiche le symbole corrsepondant a la valeur de la case donnee
			}
			System.out.println("|");
		}
		for(int z=0;z<=grille.length;z++){			// affiche le chiffre des colones sous la grille du jeu
			System.out.print("  "+ z + " ");
		}
		System.out.println("\n"); // ajoute une ligne entre la fin de la grille et ce qui viendra apres
	}
	//fin afficheGrille

	
	// cette fonction teste s'il existe un alignement dans n'importe quelle direction de la grille
	// en faisant appel aux fonctions qui testent les differentes directions: horizontale, verticale et diagonales(montantes et descendantes)
	public static boolean aGagne(){
		if(aGagneHor() || aGagneVert() || aGagneDiagMont() || aGagneDiagDesc()){
			return true;				// si un joueur a gagne dans n`importe quel direction, rend true
		}
		return false;
	}
	//fin aGagne

	// teste s'il existe un alignement de 4 valeurs du joueur sur une ligne horizontale
	public static boolean aGagneHor(){


		for(int ligne = 0; ligne < grille.length; ligne ++){
			for(int colone = 0; colone<=3; colone++){		
				/* en parametrant la condition d'arret a la 3e colone, on assure
				 qu'on ne sortira jamais de notre grille mais qu'on
				testera toute les possibilités horizontales*/
				int a = grille[ligne][colone];										
				int b = grille[ligne][colone + 1];						
				int c = grille[ligne][colone + 2];
				int d = grille[ligne][colone + 3];

				// si les 4 valeurs d'une ligne horizontale sont les memes
				if(a != 0 && a == b && b == c && c == d){  
					return true;
				}//fin if
			}//fin deuxieme for
		}//fin premier for
		return false;
										
	}
	//fin aGagneHor

	// teste s'il existe un alignement de 4 valeurs du joueur sur une ligne verticale
	static boolean aGagneVert(){


		for(int ligne = 0; ligne <= 2; ligne ++){							// en parametrant la condition ligne <= 2 a la 5e ligne, on assure
			for(int colone = 0; colone<grille[ligne].length; colone++){		// qu'on ne sortira jamais de notre grille mais qu'on	
																			// testera toute les possibilités verticales
				//les variables a,b,c,d sont les cases a tester respectivement de la premiere a la 4e case
				int a = grille[ligne][colone];									
				int b = grille[ligne + 1][colone];								
				int c = grille[ligne + 2][colone];
				int d = grille[ligne + 3][colone];

				if(a != 0 & a == b & b == c & c == d){ 						 // si les 4 valeurs d'une ligne verticale sont les memes
					return true;
				}
			}
		}
		return false;
	}

	// teste s'il existe un alignement de 4 valeurs du joueur sur une ligne diagonale montante
	public static boolean aGagneDiagMont(){
		
		/* en parametrant la condition ligne <= 2 et colone <= 3, on assure
		 qu'on ne sortira jamais de notre grille mais qu'on	
		 testera toute les possibilités diagonales motantes*/


		for(int ligne = 0; ligne <= 2; ligne ++){					
			for(int colone = 0; colone <= 3; colone++){			
				//les variables a,b,c,d sont les cases a tester respectivement de la premiere a la 4e case
				int a = grille[ligne][colone];							
				int b = grille[ligne + 1][colone+1];								
				int c = grille[ligne + 2][colone+2];
				int d = grille[ligne + 3][colone+3];

				if(a != 0 & a == b & b == c & c == d){ 						 // si les 4 valeurs d'une ligne verticale sont les memes
					return true;
				}//fin if
			}//fin deuxieme for
		}//fin premier for
		return false;			
	}
	// fin aGagneDiagMont

	// teste s'il existe un alignement de 4 valeurs du joueur sur une ligne diagonale descendante
	public static boolean aGagneDiagDesc(){

		int a, b, c, d;		// variables qui testeront les valeurs des 4 cases alignés

		for(int ligne = grille.length - 1 ; ligne >= 3; ligne --){		// en parametrant la condition ligne >=3 et colone <= 3, on assure

			for(int colone = 0; colone<= 3; colone++){					// qu'on ne sortira jamais de notre grille mais qu'on	
																		// testera toute les possibilités diagonales descendantes
				// testera toute les possibilités verticales
				a = grille[ligne][colone];								
				b = grille[ligne - 1][colone + 1];								
				c = grille[ligne - 2][colone + 2];
				d = grille[ligne - 3][colone + 3];

				if(a != 0 & a == b & b == c & c == d){ 						 // si les 4 valeurs d'une ligne verticale sont les memes
					return true;
				}
			}
		}
		return false;	
	}
	//fin aGagneDiagDesc

	
	// teste si la grille est pleine si le nombre de coups est le nombre max de cases de la grille
	// dans notre etude de cas, 42 correspond a la grille complete
	public static boolean matchNul(){					
		int comptage = 0; 											// variable qui contera le nombre de coups donnés à
		for(int ligne = 0; ligne <= grille.length - 1; ligne++){
			for(int colone = 0; colone < grille[ligne].length; colone++){
				if(grille[ligne][colone] != 0){
					comptage++;										// a chaque appel si la case du tableau est differente de 0
				}													// ce qui correspond à un coup on augmente de 1 la valeur de coup
			}														
		}
		if(comptage == 42){							// quand coup sera egal a 42 (nombre max de coups possibles dans la grille)
			return true;							// matchNul retournera true
		}else{										// sinon matchNul retourne false
			return false;					
		}
	}
	//fin matchNul
}
