package oslomet.testing;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import oslomet.testing.API.BankController;
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
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentKundeInfo_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKonti_LoggetInn()  {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
        720, "Lønnskonto", "NOK", null);

        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn()  {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    //Hitomi
    @Test
    public void hentTransaksjoner_LoggetInn(){
        // arrange

        Konto konto1 = new Konto("03010098765", "12345678901",
                20000, "Sparekonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentTransaksjoner(anyString(),anyString(),anyString())).thenReturn(konto1);

        // act

        Konto resultat = bankController.hentTransaksjoner("12345678901", "01012000", "01012100");

        // assert
        assertEquals(konto1, resultat);
    }

    //Hitomi
    @Test
    public void hentTransaksjoner_IkkLoggetInn(){

        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        Konto resultat = bankController.hentTransaksjoner("12345678901", "01012000", "01012100");

        // assert
        assertNull(resultat);
    }

    //Hitomi
    @Test
    public void hentSaldi_LoggetInn(){

        List<Konto> saldiList = new ArrayList<>();

        Konto konto1 = new Konto("05010187654", "56789012345",
                20000, "Sparekonto", "NOK", null);
        Konto konto2 = new Konto("06010276543", "67890123456",
                10000, "Dagligkonto", "NOK", null);

        saldiList.add(konto1);
        saldiList.add(konto2);

        //
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.hentSaldi(anyString())).thenReturn(saldiList);

        //
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertEquals(resultat, saldiList);
    }

    //Hitomi
    @Test
    public void hentSaldi_IkkLoggetInn(){
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertNull(resultat);
    }

// Hamzeh part
    @Test
    public void registrerBetaling_LoggetInn(){
        Transaksjon transaksjon = new Transaksjon();

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.registrerBetaling(any())).thenReturn("OK");

        String resultat = bankController.registrerBetaling(transaksjon);

        assertEquals("OK", resultat);
    }
    @Test
    public void registrerBetaling_IkkLoggetInn(){

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = bankController.registrerBetaling(null);

        assertEquals(null, resultat);
    }

    @Test
    public void utforBetaling_LoggetInn(){
    List<Transaksjon> transaksjons = new ArrayList<>();
    Transaksjon transaksjon1 = new Transaksjon();
    Transaksjon transaksjon2 = new Transaksjon();
    transaksjons.add(transaksjon1);
    transaksjons.add(transaksjon2);
    when(sjekk.loggetInn()).thenReturn("01010110523");
    when(repository.hentBetalinger(anyString())).thenReturn(transaksjons);
        // act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        // assert
        assertEquals(transaksjons, resultat);
    }
    @Test
    public void utforBetaling_IkkLoggetInn(){
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> resultat = bankController.utforBetaling(111111);

        // assert
        assertNull(resultat);
    }

//Maja
    @Test
    public void hentBetalinger_LoggetInn(){

        Transaksjon transaksjon1 = new Transaksjon();
        Transaksjon transaksjon2 = new Transaksjon();

        List<Transaksjon> transaksjonList = new ArrayList<>();

        transaksjonList.add(transaksjon1);
        transaksjonList.add(transaksjon2);

        Mockito.when(repository.hentBetalinger(anyString())).thenReturn(transaksjonList);

        List<Transaksjon> resulat = bankController.hentBetalinger();

        assertEquals(transaksjonList, resulat);

    }
    @Test
    public void hentBetalinger_IkkLoggetInn(){

        when (sjekk.loggetInn()).thenReturn(null);

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertNull(resultat);

    }

    @Test
    public void endreKundeInfo_LoggetInn(){
        Kunde kunde = new Kunde();

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Kunde kunde1 = new Kunde();

        Mockito.when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        String restulat = bankController.endre(kunde1);
        Assert.assertEquals("OK", restulat);
    }

    @Test
    public void endreKundeInfo_IkkLoggetInn(){

        Kunde kunde1 = new Kunde();

        when(sjekk.loggetInn()).thenReturn(null);

        Mockito.when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("Feil");

        String restulat = bankController.endre(kunde1);
        Assert.assertEquals("Feil", restulat);
    }
}

