/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tiralabra.Matriisi;

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
        this.m = new Matriisi(new double[8], 4, 2);
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
        assertTrue(nelio.onNelio);
        assertFalse(suorakulmio.onNelio);
        assertEquals(2, this.nelio.leveys);
        assertEquals(8, this.suorakulmio.korkeus);
    }
    
//    @Test
//    public void kertooVakiollaOikein() {
//        Matriisi uusi = this.nelio.vakiollaKertominen(3);
//        for (int i = 0; i < uusi.matriisi.length; i++) {
//            assertEquals(9, uusi.matriisi[i]);
//        }
//    }
}
