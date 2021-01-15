package oslomet.testing;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKontoController {

    @InjectMocks
    // denne skal testes
    private AdminKontoController adminKontoController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test

    public void hentAllKonti_LoggetInn(){

        // arrage
        List<Transaksjon> transaksjonList = new ArrayList<>();
        transaksjonList.add( new Transaksjon(1, "01234567890", 12, "15012021", "Mat", "0", "12345678901"));

        Konto konto1 = new Konto("02019912345", "98765432109",
                100000, "Dagligkonto", "NOK", transaksjonList);
        Konto konto2 = new Konto("03010098765", "23456789012",
                20000, "Sparekonto", "NOK", transaksjonList);
        List<Konto> kontolist = new ArrayList<>();
        kontolist.add(konto1);
        kontolist.add(konto2);

        when(sjekk.loggetInn()).thenReturn("11111111111");
        Mockito.when(repository.hentAlleKonti()).thenReturn(kontolist);

        // act
        List<Konto> resultat = adminKontoController.hentAlleKonti();

        // assert
        assertEquals(kontolist, resultat);
    }

    @Test
    public void hentAlleKonti_IkkeLoggetInn() {

        // arrage
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = adminKontoController.hentAlleKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void registrerKonto_LoggetInn(){

        Konto konto = new Konto("03010098765", "23456789012",
                20000, "Sparekonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.registrerKonto(any(Konto.class))).thenReturn("OK");

        String resultat = adminKontoController.registrerKonto(konto);

        assertEquals("OK", resultat);

    }

    @Test
    public void registrerKonto_IkkeLoggetInn(){

        Konto konto = new Konto("03010098765", "23456789012",
                20000, "Sparekonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKontoController.registrerKonto(konto);

        assertEquals("Ikke innlogget", resultat);
    }


    @Test
    public void endreKonto_LoggetInn(){

       Konto konto = new Konto("03010098765", "23456789012",
               20000, "Sparekonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.endreKonto(any(Konto.class))).thenReturn("OK");

        String restulat = adminKontoController.endreKonto(konto);

        assertEquals("OK", restulat);
    }

    @Test
    public void endreKonto_IkkeLoggetInn() {

        Konto konto = new Konto("03010098765", "23456789012",
                20000, "Sparekonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn(null);

        String restulat = adminKontoController.endreKonto(konto);

        assertEquals("Ikke innlogget", restulat);
    }

    @Test
    public void slettKonto_LoggetInn(){

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.slettKonto(any())).thenReturn("OK");

        String restulat = adminKontoController.slettKonto("23456789012");

        assertEquals("OK", restulat);
    }

    @Test
    public void slettKonto_IkkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        String restulat = adminKontoController.slettKonto("23456789012");

        assertEquals("Ikke innlogget", restulat);
    }

}