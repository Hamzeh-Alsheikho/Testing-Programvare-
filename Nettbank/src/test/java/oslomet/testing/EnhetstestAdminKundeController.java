package oslomet.testing;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.API.AdminKundeController;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController adminKundeController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test

    public void hentAll_LoggetInn(){

        // arrage
        Kunde kunde1 = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");
        Kunde kunde2 = new Kunde("02019912345",
                "Markus", "Henriksen", "Munchs gate 7", "0165",
                "Oslo", "11122233", "GodMorgen");
        List<Kunde> kundeliste = new ArrayList<>();
        kundeliste.add(kunde1);
        kundeliste.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        Mockito.when(repository.hentAlleKunder()).thenReturn(kundeliste);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertEquals(kundeliste, resultat);
    }

    @Test
    public void hentAlle_IkkeLoggetInn() {

        // arrage
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertNull(resultat);
    }


    @Test
    public void lagreKunde_LoggetInn(){

        Kunde kunde = new Kunde("02019912345",
                "Markus", "Henriksen", "Munchs gate 7", "0165",
                "Oslo", "11122233", "GodMorgen");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.registrerKunde(any(Kunde.class))).thenReturn("OK");

        String resultat = adminKundeController.lagreKunde(kunde);

        assertEquals("OK", resultat);

    }

    @Test
    public void lagreKunde_IkkeLoggetInn(){

        Kunde kunde = new Kunde("02019912345",
                "Markus", "Henriksen", "Munchs gate 7", "0165",
                "Oslo", "11122233", "GodMorgen");

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKundeController.lagreKunde(kunde);

        assertEquals("Ikke logget inn", resultat);

    }

    @Test
    public void endre_LoggetInn(){

        Kunde kunde = new Kunde("02019912345",
                "Markus", "Henriksen", "Munchs gate 7", "0165",
                "Oslo", "11122233", "GodMorgen");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        String restulat = adminKundeController.endre(kunde);

        assertEquals("OK", restulat);
    }

    @Test
    public void endre_IkkeLoggetInn() {

        Kunde kunde = new Kunde("02019912345",
                "Markus", "Henriksen", "Munchs gate 7", "0165",
                "Oslo", "11122233", "GodMorgen");

        when(sjekk.loggetInn()).thenReturn(null);

        String restulat = adminKundeController.endre(kunde);

        assertEquals("Ikke logget inn", restulat);
    }

    @Test
    public void slett_LoggetInn(){

        when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.slettKunde(any())).thenReturn("OK");

        String restulat = adminKundeController.slett("01010110523");

        assertEquals("OK", restulat);
    }

    @Test
    public void slett_IkkeLoggetInn() {

        when(sjekk.loggetInn()).thenReturn(null);

        String restulat = adminKundeController.slett("01010110523");

        assertEquals("Ikke logget inn", restulat);
    }


}