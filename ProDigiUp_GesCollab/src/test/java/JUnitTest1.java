/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import controllers.CreerPrestation;
import org.junit.jupiter.api.*;
import org.mockito.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.servlet.ServletException;
import java.io.IOException;
import static org.mockito.Mockito.*;

/**
 *
 * @author cberge
 */

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ CreerPrestation.class })
public class JUnitTest1 {
    
    public JUnitTest1() {
    }

    private CreerPrestation servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    
    @BeforeAll
    public static void setUpClass() {
         System.out.println("Exécution avant tous les tests (configurations globales).");
    
    }
    
    @AfterAll
    public static void tearDownClass() {
         System.out.println("Exécution après tous les tests (nettoyage global).");
    }
    
    
    @BeforeEach
    public void setUp() {
         System.out.println("Exécution avant chaque test (configuration).");
        servlet = new CreerPrestation();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("Exécution après chaque test (nettoyage).");
    }

    @Test
    public void testDoGet() throws ServletException, IOException, jakarta.servlet.ServletException {
        System.out.println("Test de la méthode doGet.");

        // Configure le mock HttpServletRequest pour renvoyer un RequestDispatcher mock
        when(request.getRequestDispatcher("/WEB-INF/jsp/creerPrestation.jsp")).thenReturn(requestDispatcher);

        // Appelle la méthode doPost de la servlet
        servlet.doGet(request, response);

    

        // Vérifie que la méthode getRequestDispatcher a été appelée avec le bon argument
        verify(request).getRequestDispatcher("/WEB-INF/jsp/creerPrestation.jsp");
        // Vérifie que la méthode forward a été appelée sur le RequestDispatcher mock
        verify(requestDispatcher).forward(request, response);
    }


}
