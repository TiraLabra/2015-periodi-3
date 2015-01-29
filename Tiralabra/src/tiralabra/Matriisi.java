package tiralabra;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pii
 */
public class Matriisi {

    /**
     *Sisältää matriisin arvot.
     */
    public double[] data;

    /**
     *Matriisin leveys.
     */
    public int leveys;

    /**
     *Matriisin korkeus.
     */
    public int korkeus;

    
    /**
     *Matriisin konstruktori.
     * @param m, matriisin arvot sisältävä taulukko
     * @param l, matriisin leveys
     * @param k, matriisin korkeus
     */
    public Matriisi(double[] m, int l, int k) {
        this.data = m;
        this.leveys = l;
        this.korkeus = k;
    }
    
    /**
     *Kertoo onko matriisi neliömatriisi.
     * @return true, jos on tai false jos ei
     */
    public boolean onNelio() {
        if(this.leveys == this.korkeus) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     *Kertoo annetulla vakiolla matriisin.
     * @param v, vakiokerroin
     * @return kertolaskun tulos
     */
    public boolean vakiollaKertominen(double v) {
        for (int i = 0; i < this.data.length; i++) {
           this.data[i] = v * this.data[i];
        }
        return true;
    }
    
    /**
     *Laskee matriisien kertolaskun.
     * @param m, toinen lakutoimituksen alkioista.
     * @return matriisien tulos
     */
//    public VastausMatriisi kertolasku(Matriisi m) {
//        if(this.leveys != m.korkeus) {
//            return new VastausMatriisi(false, new Matriisi(new double[1], 1, 1));
//        }
//        Matriisi uusiM = new Matriisi(new double[this.matriisi.length], this.korkeus, this.leveys);
//        for (int i = 0; i < m.korkeus; i++) {
//            for (int j = 0; j < this.leveys; j++) {
//                uusiM.matriisi[j+uusiM.leveys*i] = this.matriisi[j+this.leveys*i] + m.matriisi[i+m.korkeus*j];
//            }
//        }
//        
//        return;
//    }
    
    /**
     *Laskee matriisien erotuksen.
     * @param m, toinen lakutoimituksen alkioista.
     * @return matriisien tulos
     */
    public boolean erotus(Matriisi m) {
        if(this.korkeus != m.korkeus || this.leveys != m.leveys) {
           return false;
        }
        for (int i = 0; i < this.data.length; i++) {
           m.data[i] = this.data[i] - m.data[i];
        }
        return true;
    }
    
    /**
     *Laskee matriisien summan.
     * @param m, toinen lakutoimituksen alkioista.
     * @return matriisien tulos
     */
    public boolean summa(Matriisi m) {
        if(this.korkeus != m.korkeus || this.leveys != m.leveys) {
           return false;
        }
        for (int i = 0; i < this.data.length; i++) {
           m.data[i] = m.data[i] + this.data[i];
        }
        return true;
    }
    
    /**
     *Laskee matriisin determinantin.
     * @param m, toinen lakutoimituksen alkioista.
     * @return matriisin determinatti
     */
//    public int determinantti(Matriisi m) {
//        return 1;
//    }
//    
//    /**
//     *Muodostaa matriisin transpoosin. 
//     * @return Matriisin transpoosi.
//     */
//    public boolean transpoosi() {
//        Matriisi uusiM = new Matriisi(new double[this.data.length], this.korkeus, this.leveys);
//        for (int i = 0; i < this.korkeus; i++) {
//            for (int j = 0; j < this.leveys; j++) {
//                uusiM.data[j+uusiM.leveys*i] = 
//            }
//        }
//        return true;
//    }
}
