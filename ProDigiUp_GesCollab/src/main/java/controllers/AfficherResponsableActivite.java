package controllers;

import dao.ResponsableActiviteDao;
import entities.ResponsableActivite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author asolanas
 */
@WebServlet("/afficher_ra")
@SuppressWarnings("serial")
public class AfficherResponsableActivite extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String raIdParam = req.getParameter("id");
        int raId = 1;
        if(raIdParam != null && !raIdParam.isEmpty()){
            try{
                raId = Integer.parseInt(raIdParam);
            }catch(NumberFormatException e){
                
            }
        }
        
        ResponsableActiviteDao raDao = new ResponsableActiviteDao();
       
        ResponsableActivite ra = raDao.read(raId);
        
        req.setAttribute("ra", ra);
        req.getRequestDispatcher("/WEB-INF/jsp/afficherResponsableActivite.jsp").forward(req, resp);
    }

}
