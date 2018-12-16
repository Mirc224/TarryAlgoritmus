/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarry;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mino
 */

public class Algoritmus {
private final boolean[][] hrany;  
private int pKomponentov;

    public Algoritmus() throws IOException {
        this.pKomponentov = 0;
    java.io.File subor = new File("graf.txt");
        Scanner citac = new Scanner(subor);
        int temp = citac.nextInt() + 1;
        this.hrany = new boolean [temp][temp];
        while (citac.hasNextLine()) {
            citac.nextLine();
            int temp1 = citac.nextInt();
            int temp2 = citac.nextInt();
            this.hrany[temp1][temp2] = true; 
            this.hrany[temp2][temp1] = true;
        }
        citac.close();
    }
    public int zistiPocetKomponentov(boolean[][] novyGraf, boolean prvesSpustenie) {
        boolean[][] graf = novyGraf;
        boolean[][] hranaPrvehoPrichodu = new boolean[graf.length][graf.length];
        boolean[][] smerPrechodu = new boolean[graf.length][graf.length];
        boolean[] objaveneVrcholy = new boolean[graf.length];
        for(int i = 0;i < graf.length; i++) {
            objaveneVrcholy[i] = false; 
            for(int j = 0; j < graf.length; j++) {
                smerPrechodu[i][j] = false;
                hranaPrvehoPrichodu[i][j] = false;
            }
        }
        boolean test = true;
        int vrchol = 1;
        if(prvesSpustenie) {
        System.out.println("Zadajte zaciatocny vrchol z rozsahu 1 az " + (graf.length-1));
        do {
            
            Scanner citac = new Scanner(System.in);
            int temp = citac.nextInt();
            if(temp < graf.length && temp > 0) {
                test = false;
                vrchol = temp;        
            } else {
                System.out.println("Zadali ste nespravny vrchol, skúste to znova.");
            }
        } while(test);
        }
        objaveneVrcholy[vrchol] = true;
        if(prvesSpustenie)
            System.out.println("Nasiel sa vrchol " + vrchol);
        int pocetKomponentov = 0;
        
        do {
            
            for(int i = 1; i < graf.length; i++) {
                test = false;
                for(int j = 1; j < graf.length; j++) {
                    if(graf[vrchol][j]) {
                        if(!smerPrechodu[vrchol][j]) {
                            test = true;
                            break;
                        }
                    }    
                }
                if(!test) 
                    break;
                if(graf[vrchol][i]) {
                    if(!smerPrechodu[vrchol][i]) {
                        if(!hranaPrvehoPrichodu[vrchol][i] && !hranaPrvehoPrichodu[i][vrchol]) {
                            if(!objaveneVrcholy[i]) {
                                    objaveneVrcholy[i] = true;
                                    hranaPrvehoPrichodu[vrchol][i] = true;
                                    smerPrechodu[vrchol][i] = true;
                                    vrchol = i;
                                    if(prvesSpustenie)
                                        System.out.println("Nasiel sa vrchol " + vrchol);
                                    break;
                                } else {
                                    smerPrechodu[vrchol][i] = true;
                                    vrchol = i;
                                    break;
                                }
                            } else {
                                int temp = 0;
                                for(int j = 1; j < graf.length; j++) {
                                    if(graf[vrchol][j]) {
                                        if(!smerPrechodu[vrchol][j]){
                                            if(!hranaPrvehoPrichodu[vrchol][j] && !hranaPrvehoPrichodu[j][vrchol]) {
                                                temp = j;
                                                if(!objaveneVrcholy[j]) {
                                                    objaveneVrcholy[j] = true;
                                                    hranaPrvehoPrichodu[vrchol][j] = true;
                                                    smerPrechodu[vrchol][j] = true;
                                                    vrchol = j;
                                                    if(prvesSpustenie)
                                                        System.out.println("Nasiel sa vrchol " + vrchol);
                                                    break;
                                                } else {
                                                    smerPrechodu[vrchol][j] = true;
                                                    vrchol = j;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if(temp == 0) {
                                    smerPrechodu[vrchol][i] = true;
                                    vrchol = i;
                                    break;
                                }
                                break;
                            }
                    }
                }
            }
            if(!test) {
               pocetKomponentov++;
               for(int i = 1; i < graf.length; i++) {
                    if(!objaveneVrcholy[i]) {
                        test = true;
                        vrchol = i;
                        objaveneVrcholy[vrchol] = true;
                        if(prvesSpustenie)
                            System.out.println("Nasiel sa vrchol " + vrchol);
                        break;
                    }
                } 
            }
            
        } while(test);
        if(prvesSpustenie)
            System.out.println("Pocet komponentov je : " + pocetKomponentov);
        return pocetKomponentov;
    }
    public void zistiMosty() {
        boolean graf[][] = this.hrany;
        boolean vyskusane[][] = new boolean[this.hrany.length][this.hrany.length];
        for(int i = 1;i < graf.length; i++) {
            vyskusane[i][i]= false;
            }
        System.out.print("Mosty : ");
            for(int i = 1;i < graf.length; i++) {
                for(int j = 1; j < graf.length; j++) {
                    if(this.hrany[i][j] && !vyskusane[i][j]) {
                        graf[i][j] = false;
                        graf[j][i] = false;
                        vyskusane[i][j] = true;
                        vyskusane[j][i] = true;
                        if(this.pKomponentov != this.zistiPocetKomponentov(graf, false)) {
                            System.out.print(i + " " + j + " ");
                        }
                        graf[i][j] = true;
                        graf[j][i] = true;
                    }
                }
            }
            System.out.println("");
    }
    public void zistiArtikulacie() {
        boolean graf[][] = new boolean[hrany.length][hrany.length];
        for(int i = 1;i < graf.length; i++) {
            for(int j = 1; j < graf.length; j++) {
                if(hrany[i][j]) {
                    graf[i][j] = true;
                } else {
                    graf[i][j] = false;
                }
            }
        }
        System.out.print("Artikulacie : ");
        for(int i = 1; i < graf.length; i++) {
            for(int j = 1; j < graf.length; j++) {
                if(this.hrany[i][j]) {
                    graf[i][j] = false;
                    graf[j][i] = false;
                }
            }
            int temp = this.zistiPocetKomponentov(graf, false)-1;
            if(this.pKomponentov < temp) {
                System.out.print(i + " ");
            }
            for(int j = 1; j < graf.length; j++) {
                if(this.hrany[i][j]) {
                    graf[i][j] = true;
                    graf[j][i] = true;
                }
            }
        }
        System.out.println("");
    }
    
    public boolean[][] getHrany() {
        return hrany;
    }
    public int getpKomponentov() {
        return pKomponentov;
    }
    public void setpKomponentov(int pKomponentov) {
        this.pKomponentov = pKomponentov;
    }
    
}

