import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestAddress {
    Address address;
    Object expectedRegion;
    Object expectedIsCapital;

    public TestAddress(Address address, Object expectedRegion, Object expectedIsCapital) {
        this.address = address;
        this.expectedRegion = expectedRegion;
        this.expectedIsCapital = expectedIsCapital;
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] resposta = new Object[][] {
                { new Address("Brasília", "DF"), "Centro-Oeste", true },
                { new Address("Anápolis", "GO"), "Centro-Oeste", false },
                { new Address("Fortaleza", "CE"), "Nordeste", true },
                { new Address("Arapiraca", "AL"), "Nordeste", false },
                { new Address("Curitiba", "RS"), "Sul", false },
                { new Address("ASDFA", "SADFASDF"), "Estado não identificado", false },
        };
        return Arrays.asList(resposta);
    }

    @Test
    public void testGetRegion() {
        assertEquals(expectedRegion, address.getRegion());
    }

    @Test
    public void testIsCapital() {
        assertEquals(expectedIsCapital, address.isCapital());
    }

}
