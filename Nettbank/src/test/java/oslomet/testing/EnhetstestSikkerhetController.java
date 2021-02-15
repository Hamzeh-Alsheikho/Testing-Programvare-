package oslomet.testing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockHttpSession;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestSikkerhetController {

    @InjectMocks
    private Sikkerhet sikkerhetContoller;

    @Mock
    private BankRepository bankRepository;
    private AdminRepository adminRepository;

    @Mock
    MockHttpSession session;

    @Before
    public void initSession() {

        Map<String, Object> attributes = new HashMap<String, Object>();

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = (String) invocation.getArguments()[0];
                return attributes.get(key);
            }
        }).when(session).getAttribute(anyString());

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = (String) invocation.getArguments()[0];
                Object value = invocation.getArguments()[1];
                attributes.put(key, value);
                return null;
            }
        }).when(session).setAttribute(anyString(), any());

    }

    @Test
    public void test_sjekkLoggetInn() {
        // arrange
        when(bankRepository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        // setningen under setter ikke attributten, dvs. at det ikke er mulig å sette en attributt i dette oppsettet
        session.setAttribute("Innlogget", "12345678901");

        // act
        String resultat = sikkerhetContoller.sjekkLoggInn("12345678901", "HeiHeiHei");
        // assert
        assertEquals("OK", resultat);
    }
    @Test
    public void test_sjekkLoggetInn_FeilPersonNummer() {
        // arrange
        when(bankRepository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        // setningen under setter ikke attributten, dvs. at det ikke er mulig å sette en attributt i dette oppsettet
        session.setAttribute("Innlogget", "123456789012");

        // act
        String resultat = sikkerhetContoller.sjekkLoggInn("123456789012", "HeiHeiHei");
        // assert
        assertEquals("Feil i personnummer", resultat);
    }

    @Test
    public void test_sjekkLoggetInn_FeilPassword() {
        // arrange
        when(bankRepository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        // setningen under setter ikke attributten, dvs. at det ikke er mulig å sette en attributt i dette oppsettet
        session.setAttribute("Innlogget", "12345678901");

        // act
        String resultat = sikkerhetContoller.sjekkLoggInn("12345678901", "Hei");
        // assert
        assertEquals("Feil i passord", resultat);
    }


    @Test
    public void test_loggInnAdmin_OK() {
        // arrange
        when(bankRepository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        // setningen under setter ikke attributten, dvs. at det ikke er mulig å sette en attributt i dette oppsettet
        session.setAttribute("Innlogget", "Admin");

        // act
        String resultat = sikkerhetContoller.loggInnAdmin("Admin", "Admin");
        // assert
        assertEquals("Logget inn", resultat);
    }

    @Test
    public void test_loggInn_FeilBrukerAdmin() {
        // arrange
        when(bankRepository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        // setningen under setter ikke attributten, dvs. at det ikke er mulig å sette en attributt i dette oppsettet
        session.setAttribute("Innlogget", "Admin");

        // act
        String resultat = sikkerhetContoller.loggInnAdmin("admin", "Admin");
        // assert
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void test_loggInn_FeilPasswordAdmin() {
        // arrange
        when(bankRepository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        // setningen under setter ikke attributten, dvs. at det ikke er mulig å sette en attributt i dette oppsettet
        session.setAttribute("Innlogget", "Admin");

        // act
        String resultat = sikkerhetContoller.loggInnAdmin("Admin", "admin");
        // assert
        assertEquals("Ikke logget inn", resultat);
    }
}