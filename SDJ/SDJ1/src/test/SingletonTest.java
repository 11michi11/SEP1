package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import controler.VIAController;

class SingletonTest {

    
    @Test
    void test() {
	VIAController c1=VIAController.getInstance();
	VIAController c2=VIAController.getInstance();
	assertEquals(c1, c2);
	assertEquals(true, c2 == c1);
    }

}
