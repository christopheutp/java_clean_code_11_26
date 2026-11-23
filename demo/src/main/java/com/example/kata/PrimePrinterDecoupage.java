package com.example.kata;

public class PrimePrinterDecoupage {
    public static void main(String[] args) {

        // configuration du programme
        final int MAX_PRIMES = 1000; // Nombre de nombres premier a generer
        final int ROWS_PER_PAGE = 50; // Nombre de ligne par page
        final int COLUMNS_PER_PAGE = 4; // Nombre de colonne par page
        final int MAX_PRIME_FACTORS = 30; // Taille maximale pour le suivi des multiples

        // Tableau contenant les nombre premiers trouvés
        int P[] = new int[MAX_PRIMES+1];


        // variables utilisées pour gerer la pagination
        int PAGENUMBER;
        int PAGEOFFSET;
        int ROWOFFSET;
        int C;

        // variable utilisées pour rechercher les nombres premier
        int J;
        int K;
        boolean JPRIME;
        int ORD;
        int SQUARE;
        int N=0;

        // tableau utilise pour suivte les multiple des nombres premiers
        int MULT[] = new int[MAX_PRIME_FACTORS+1];

        // Initialisation de la recherche des nombres premiers
        J=1; // nombre candidat initial
        K=1; // nombre de nombre premiers deja trouve
        P[1] = 2; // le premier nombre premier est a 2
        ORD = 2; // utilise pour les tests de devisibilité
        SQUARE = 9; // carré de 3 , premier seuil a surveiller


        // Partie 1
        // Géneration des nombres premiers

        while (K < MAX_PRIMES) {
            do {
                J += 2;
                if( J == SQUARE) {
                    ORD++;
                    SQUARE=P[ORD]*P[ORD];
                    MULT[ORD-1]=J;
                }
                N=2;
                JPRIME=true;
                while (N < ORD && JPRIME) {
                    while (MULT[N]<J)
                        MULT[N] += P[N] + P[N];
                    if (MULT[N] == J)
                        JPRIME=false;
                    N++;
                }
            } while (!JPRIME);
            K++;
            P[K]=J;
        }


        // Partie 2
        // Affichage des nombres premiers

        PAGENUMBER = 1;
        PAGEOFFSET = 1;
        while (PAGEOFFSET <= MAX_PRIMES) {
            System.out.print("The First ");
            System.out.print(Integer.toString(MAX_PRIMES));
            System.out.print(" Prime Numbers === Page ");
            System.out.print(Integer.toString(PAGENUMBER));
            System.out.println("\n");
            for (ROWOFFSET=PAGEOFFSET; ROWOFFSET <= PAGEOFFSET+ROWS_PER_PAGE-1; ROWOFFSET++) {
                for (C = 0; C <= COLUMNS_PER_PAGE - 1; C++)
                    if (ROWOFFSET + C * ROWS_PER_PAGE <= MAX_PRIMES)
                        System.out.printf("%10d", P[ROWOFFSET + C * ROWS_PER_PAGE]);
                System.out.println();
            }
            System.out.println("\n");
            PAGENUMBER++;
            PAGEOFFSET += ROWS_PER_PAGE*COLUMNS_PER_PAGE;

        }
    }
}
