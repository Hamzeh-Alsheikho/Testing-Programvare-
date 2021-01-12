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

   /* @Test
    public void hentAlleFeil() {

        // arrage
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Kunde> resultat = adminKontoController.hentAlle();

        // assert
        assertNull(resultat);
    }


    @Test
    public void lagreKundeOK(){

        Kunde kunde = new Kunde();

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.registrerKunde(any(Kunde.class))).thenReturn("OK");

        String resultat = adminKontoController.lagreKunde(kunde);

        assertEquals("OK", resultat);

    }

    @Test
    public void lagreKundeFeil(){

        Kunde kunde = new Kunde();

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKontoController.lagreKunde(kunde);

        assertEquals("Ikke logget inn", resultat);

    }

    @Test
    public void endreOK(){

        Kunde kunde = new Kunde();

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        String restulat = adminKontoController.endre(kunde);

        assertEquals("OK", restulat);
    }

    @Test
    public void endreFeil() {

        Kunde kunde = new Kunde();

        when(sjekk.loggetInn()).thenReturn(null);

        String restulat = adminKontoController.endre(kunde);

        assertEquals("Ikke logget inn", restulat);
    }

    @Test
    public void slettOK(){

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.slettKunde(any())).thenReturn("OK");

        String restulat = adminKundeController.slett("01010110523");

        assertEquals("OK", restulat);
    }

    @Test
    public void slettFeil() {

        when(sjekk.loggetInn()).thenReturn(null);

        String restulat = adminKundeController.slett("01010110523");

        assertEquals("Ikke logget inn", restulat);
    }

    */

}