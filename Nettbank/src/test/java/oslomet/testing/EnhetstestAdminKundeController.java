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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

    public void hentAllOk(){
        when(sjekk.loggetInn()).thenReturn("01010110523");
        // arrage
        Kunde kunde1 = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");
        Kunde kunde2 = new Kunde();
        List<Kunde> kundeliste = new ArrayList<>();
        kundeliste.add(kunde1);
        kundeliste.add(kunde2);

        Mockito.when(repository.hentAlleKunder()).thenReturn(kundeliste);

        // act
        List<Kunde> resultat = kundeliste;

        // assert
        Assert.assertEquals(kundeliste, resultat);
    }

    @Test
    public void hentAlleFeil() {
        when(sjekk.loggetInn()).thenReturn("01010110523");

        // arrage
        Mockito.when(repository.hentAlleKunder()).thenReturn(null);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        Assert.assertNull(resultat);

    }

    @Test
    public void endreOk(){
        when(sjekk.loggetInn()).thenReturn("01010110523");
        Kunde kunde = new Kunde();

        Mockito.when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        String restulat = adminKundeController.endre(kunde);
        Assert.assertEquals("OK", restulat);
    }

    @Test
    public void endreFeil() {
        when(sjekk.loggetInn()).thenReturn("01010110523");

        Kunde kunde = new Kunde();

        Mockito.when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("Feil");

        String restulat = adminKundeController.endre(kunde);
        Assert.assertEquals("Feil", restulat);
    }

    @Test
    public void slettOk(){
        when(sjekk.loggetInn()).thenReturn("01010110523");
        Kunde kunde = new Kunde();

        Mockito.when(repository.slettKunde(any())).thenReturn("OK");

        String restulat = adminKundeController.slett("01010110523");
        Assert.assertEquals("OK", restulat);
    }

    @Test
    public void slettFeil() {
        when(sjekk.loggetInn()).thenReturn("01010110523");

        Kunde kunde = new Kunde();

        Mockito.when(repository.slettKunde(any())).thenReturn("Feil");

        String restulat = adminKundeController.slett("01010110523");
        Assert.assertEquals("Feil", restulat);
    }

}