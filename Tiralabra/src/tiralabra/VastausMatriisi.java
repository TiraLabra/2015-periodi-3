/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra;

/**
 *Edustaa matriisien summan, erotuksen ja kertolaskun tulosta. Sisältää tiedon onnistuiko laskutoimitus ja, jos onnistui viitteen uuteen matriisiiin.
 * @author pisapisa
 */
public class VastausMatriisi {
    public boolean onnistui;
    public Matriisi m;
    
    /**
     *VastausMatriisin kontruktori.
     * @param o, onnistuiko laskutoimitus
     * @param m, tuloksen matriisi
     */
    public VastausMatriisi(boolean o, Matriisi m) {
        this.onnistui = o;
        this.m = m;
    }
}
