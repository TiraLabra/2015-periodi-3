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
     *
     */
    public double[] matriisi;

    /**
     *
     */
    public int leveys;

    /**
     *
     */
    public int korkeus;

    /**
     *
     */
    public boolean onNelio;
    
    /**
     *
     * @param m
     * @param l
     * @param k
     */
    public Matriisi(double[] m, int l, int k) {
        if(l == k) {
            this.onNelio = true;
        } else {
            this.onNelio = false;
        }
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    /**
     *
     * @param m
     * @return
     */
    public Matriisi summa(Matriisi m) {
        Matriisi uusiMatriisi = new Matriisi(new double[1], 1, 1);
        return uusiMatriisi;
    }
    
    /**
     *
     * @param m
     * @return
     */
    public Matriisi erotus(Matriisi m) {
        Matriisi uusiMatriisi = new Matriisi(new double[1], 1, 1);
        return uusiMatriisi;
    }
    
    /**
     *
     * @param m
     * @return
     */
    public Matriisi kertolasku(Matriisi m) {
        Matriisi uusiMatriisi = new Matriisi(new double[1], 1, 1);
        return uusiMatriisi;
    }
    
    /**
     *
     * @param m
     * @return
     */
    public Matriisi determinantti(Matriisi m) {
        Matriisi uusiMatriisi = new Matriisi(new double[1], 1, 1);
        return uusiMatriisi;
    }
    
    /**
     *
     * @param m
     * @return
     */
    public Matriisi transpoosi(Matriisi m) {
        Matriisi uusiMatriisi = new Matriisi(new double[1], 1, 1);
        return uusiMatriisi;
    }
}
