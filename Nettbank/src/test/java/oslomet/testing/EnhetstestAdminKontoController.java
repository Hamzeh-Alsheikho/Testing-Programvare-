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

    public void hentAllKontiOK(){

        // arrage
        Konto konto1 = new Konto();
        Konto konto2 = new Konto();
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
    public void registrerKonto(){

       Konto konto = new Konto();

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.registrerKonto(any(Konto.class))).thenReturn("OK");

        String resultat = adminKontoController.registrerKonto(konto);

        assertEquals("OK", resultat);

    }

    @Test
    public void registrerKontoFeil(){

        Konto konto = new Konto();

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKontoController.registrerKonto(konto);

        assertEquals("Ikke innlogget", resultat);
    }


    @Test
    public void endreKonto(){

       Konto konto = new Konto();

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.endreKonto(any(Konto.class))).thenReturn("OK");

        String restulat = adminKontoController.endreKonto(konto);

        assertEquals("OK", restulat);
    }

    @Test
    public void endreKontoFeil() {

        Konto konto = new Konto();

        when(sjekk.loggetInn()).thenReturn(null);

        String restulat = adminKontoController.endreKonto(konto);

        assertEquals("Ikke innlogget", restulat);
    }

    @Test
    public void slettKontoOK(){

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.slettKonto(any())).thenReturn("OK");

        String restulat = adminKontoController.slettKonto("01010110523");

        assertEquals("OK", restulat);
    }

    @Test
    public void slettKontoFeil() {

        when(sjekk.loggetInn()).thenReturn(null);

        String restulat = adminKontoController.slettKonto("01010110523");

        assertEquals("Ikke innlogget", restulat);
    }

}