package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
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
        List<Transaksjon> transaksjonslist = new ArrayList<>();
        Transaksjon transaksjon1 = new Transaksjon(1, "23456789012", 512.44, "13012021", "Bok", "Lene","12345678901");
        Transaksjon transaksjon2 = new Transaksjon(2, "12345678901", 358.13, "15012021", "Mat", " Markus","23456789012");

        transaksjonslist.add(transaksjon1);
        transaksjonslist.add(transaksjon2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentTransaksjoner(anyString(),anyString(),anyString())).thenReturn((Konto) transaksjonslist);

        // act
        Konto resultat = bankController.hentTransaksjoner("12345678901", "01012000", "01012100");

        // assert
        assertEquals(transaksjonslist, resultat);
    }

    //Hitomi
    @Test
    public void hentTransaksjoner_IkkLoggetInn(){

    }

    //Hitomi
    @Test
    public void hentSaldi_LoggetInn(){

    }

    //Hitomi
    @Test
    public void hentSaldi_IkkLoggetInn(){

    }
// Hamzeh part
    @Test
    public void registrerBetaling_LoggetInn(){

        when(sjekk.loggetInn()).thenReturn("11111111111");

        Mockito.when(repository.registrerBetaling(any())).thenReturn("OK");

        String resultat = bankController.registrerBetaling(null);

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

    }
    @Test
    public void hentBetalinger_IkkLoggetInn(){

    }

    @Test
    public void endreKundeInfo_LoggetInn(){

    }
    @Test
    public void endreKundeInfo_IkkLoggetInn(){

    }
}

