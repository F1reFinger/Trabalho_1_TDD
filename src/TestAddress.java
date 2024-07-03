import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestAddress {

    Address addressDF;
    Address addressGO;
    Address addressCE;
    Address addressAL;
    Address addressRS;
    Address addressNotDefined;

    @Before
    public void setup() {
        addressDF = new Address("Brasília", "DF");
        addressGO = new Address("Anápolis", "GO");
        addressCE = new Address("Fortaleza", "CE");
        addressAL = new Address("Arapiraca", "AL");
        addressRS = new Address("Curitiba", "RS");
        addressNotDefined = new Address("ASDFA", "SADFASDF");
    }

    @Test
    public void testDF() {
        assertEquals(true, addressDF.isCapital());
        assertEquals("Centro-Oeste", addressDF.getRegion());
    }

    @Test
    public void testGO() {
        assertEquals(false, addressGO.isCapital());
        assertEquals("Centro-Oeste", addressGO.getRegion());
    }

    @Test
    public void testCE() {
        assertEquals(true, addressCE.isCapital());
        assertEquals("Nordeste", addressCE.getRegion());
    }

    @Test
    public void testAL() {
        assertEquals(false, addressAL.isCapital());
        assertEquals("Nordeste", addressAL.getRegion());
    }

    @Test
    public void testRS() {
        assertEquals(false, addressRS.isCapital());
        assertEquals("Sul", addressRS.getRegion());
    }

    @Test
    public void testNotDefined() {
        assertEquals(addressNotDefined.isCapital(), false);
        assertEquals(addressNotDefined.getRegion(), "Estado não identificado");
    }
}
