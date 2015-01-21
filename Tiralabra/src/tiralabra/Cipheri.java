package tiralabra;


import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Sisältää tiedoston salausalgoritmin ja avaimen.
 * @author pii
 */
public class Cipheri {
    private int avain;
    
    /**
     *Salaa salausalgoritmiä ja avainta käyttäen saamansa tekstin.
     * @param teksti
     * @return uusiTeksti
     */
    public String Salaa(String teksti) {
        return "uusiTeksti";
    }
    
    /**
     *Luo saamastaan tekstistä tiedoston.
     * @param teksti
     * @return tiedosto
     */
    public String LuoTiedosto(String teksti) {
        return "uusiTiedosto";
    }
    
    /**
     *Avaa avausalgoritmillä ja avainta käyttäen Salaa -metodi salaaman tekstin.
     * @param teksti
     * @return salauksestaAvattuTeksti
     */
    public String Avaa(String teksti) {
        return "avattuTeksti";
    }
}
