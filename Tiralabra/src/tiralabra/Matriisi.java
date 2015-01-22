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
    public double[] matriisi;

    /**
     *Matriisin leveys.
     */
    public int leveys;

    /**
     *Matriisin korkeus.
     */
    public int korkeus;

    /**
     *Kertoo onko matriisi neliömatriisi.
     */
    public boolean onNelio;
    
    /**
     *Matriisin konstruktori.
     * @param m, matriisin arvot sisältävä taulukko
     * @param l, matriisin leveys
     * @param k, matriisin korkeus
     */
    public Matriisi(double[] m, int l, int k) {
        if(l == k) {
            this.onNelio = true;
        } else {
            this.onNelio = false;
        }
        this.leveys = l;
        this.korkeus = k;
    }
    
    public Matriisi vakiollaKertominen(double v) {
        double[] t = new double[this.matriisi.length];
        Matriisi uusiM = new Matriisi(t, this.korkeus, this.leveys);
        for (int i = 0; i < this.matriisi.length; i++) {
           uusiM.matriisi[i] = v * this.matriisi[i];
        }
        return uusiM;
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
    public VastausMatriisi erotus(Matriisi m) {
        if(!this.onNelio || !m.onNelio) {
           return new VastausMatriisi(false, new Matriisi(new double[1], 1, 1));
        }
        Matriisi uusiM = new Matriisi(new double[this.matriisi.length], this.korkeus, this.leveys);
        for (int i = 0; i < this.matriisi.length; i++) {
           uusiM.matriisi[i] = this.matriisi[i] - m.matriisi[i];
        }
        return new VastausMatriisi(true, uusiM);
    }
    
    /**
     *Laskee matriisien summan.
     * @param m, toinen lakutoimituksen alkioista.
     * @return matriisien tulos
     */
    public VastausMatriisi summa(Matriisi m) {
        if(!this.onNelio || !m.onNelio) {
           return new VastausMatriisi(false, new Matriisi(new double[1], 1, 1));
        }
        Matriisi uusiM = new Matriisi(new double[this.matriisi.length], this.korkeus, this.leveys);
        for (int i = 0; i < this.matriisi.length; i++) {
           uusiM.matriisi[i] = m.matriisi[i] + this.matriisi[i];
        }
        return new VastausMatriisi(true, uusiM);
    }
    
    /**
     *Laskee matriisin determinantin.
     * @param m, toinen lakutoimituksen alkioista.
     * @return matriisin determinatti
     */
    public int determinantti(Matriisi m) {
        return 1;
    }
    
    /**
     *Muodostaa matriisin transpoosin. 
     * @return Matriisin transpoosi.
     */
    public Matriisi transpoosi() {
        Matriisi uusiMatriisi = new Matriisi(new double[1], 1, 1);
        return uusiMatriisi;
    }
}
