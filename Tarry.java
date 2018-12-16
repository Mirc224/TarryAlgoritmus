/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarry;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author mino
 */
public class Tarry {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int temp = 0;
        Algoritmus graf = new Algoritmus();
        temp = graf.zistiPocetKomponentov(graf.getHrany(), true);
        graf.setpKomponentov(temp);
        graf.zistiMosty();
        graf.zistiArtikulacie();
    }
    
}
