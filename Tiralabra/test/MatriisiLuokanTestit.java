/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tiralabra.Matriisi;
import tiralabra.VastausMatriisi;

/**
 *
 * @author pisapisa
 */
public class MatriisiLuokanTestit {
    Matriisi nelio;
    Matriisi suorakulmio;
    Matriisi m;
    
    public MatriisiLuokanTestit() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.nelio = new Matriisi(new double[4], 2, 2);
        this.suorakulmio = new Matriisi(new double[32], 4, 8);
        this.m = new Matriisi(new double[4], 2, 2);
        this.nelio.data[0] = 1;
        this.nelio.data[1] = 2;
        this.nelio.data[2] = 3;
        this.nelio.data[3] = 4;
        for (int i = 0; i < this.m.data.length; i++) {
            this.m.data[i] = 2;
        }
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void rakentaakoMatriisinOikein() {
        assertTrue(nelio.onNelio());
        assertFalse(suorakulmio.onNelio());
        assertEquals(2, this.nelio.leveys);
        assertEquals(8, this.suorakulmio.korkeus);
    }
    
    @Test
    public void kertooVakiollaOikein() {
        this.nelio.vakiollaKertominen(3);
        assertEquals(3, this.nelio.data[0], 0);
    }
    
     @Test
    public void laskeekoSummanOikein() {
        assertTrue(this.nelio.summa(this.m));
        assertEquals(4, m.data[1], 0);
    }
    
    @Test
    public void laskeeErotuksenOikein() {
        assertTrue(this.nelio.erotus(this.m));
        assertEquals(0, m.data[1], 0);
    }
}
