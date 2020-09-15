package br.gov.sp.fatec.projetomaven;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.gov.sp.fatec.projetomaven.helpers.Calc;

/**
 * Unit test for Calculator App.
 */
public class AppTest {
   
	@Test
    public void testSum() {
		Calc calculator = new Calc();
		assertEquals(new Float(2), calculator.sum());
    }
	
	@Test
    public void testSubtraction() {
		Calc calculator = new Calc();
		assertEquals(new Float(0), calculator.subtraction());
    }
	
	@Test
    public void testMultiplication() {
		Calc calculator = new Calc(new Float(3), new Float(6));
		assertEquals(new Float(18), calculator.multiplication());
    }
	
	@Test
    public void testDivision() {
		Calc calculator = new Calc(new Float(3), new Float(6));
		assertEquals(new Float(2), calculator.division());
    }
}
