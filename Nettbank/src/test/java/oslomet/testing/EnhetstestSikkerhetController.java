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
import static org.junit.jupiter.api.Assertions.assertNull;
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
    private MockHttpSession session;

   @Test
    public void test_loggetInn() {

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

        session.setAttribute("Innlogget","Innlogget");

       String resultat = sikkerhetContoller.loggetInn();
       // assert
       assertEquals("Innlogget",resultat);

    }
    @Test
    public void test_loggetInn_Feil() {

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

        session.setAttribute("Innlogget",null);

       String resultat = sikkerhetContoller.loggetInn();
       // assert
       assertNull(resultat);
    }

    @Test
    public void test_sjekkLoggetInn() {
        when(bankRepository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");
        String resultat = sikkerhetContoller.sjekkLoggInn("12345678901", "HeiHeiHei");
        assertEquals("OK", resultat);
    }
    @Test
    public void test_sjekkLoggetInn_FeilPersonNummer() {
        String resultat = sikkerhetContoller.sjekkLoggInn("123456789012", "HeiHeiHei");
        assertEquals("Feil i personnummer", resultat);
    }

    @Test
    public void test_sjekkLoggetInn_FeilPassword() {
        String resultat = sikkerhetContoller.sjekkLoggInn("12345678901", "Hei");
        assertEquals("Feil i passord", resultat);
    }


    @Test
    public void test_loggInnAdmin_OK() {
        String resultat = sikkerhetContoller.loggInnAdmin("Admin", "Admin");
        assertEquals("Logget inn", resultat);
    }

    @Test
    public void test_loggInn_FeilBrukerAdmin() {
        String resultat = sikkerhetContoller.loggInnAdmin("admin", "Admin");
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void test_loggInn_FeilPasswordAdmin() {
        String resultat = sikkerhetContoller.loggInnAdmin("Admin", "admin");
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void test_loggUt() {

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

        session.setAttribute("Innlogget","Innlogget");
        sikkerhetContoller.loggUt();
        assertNull(session.getAttribute("Innlogget"));

    }

}