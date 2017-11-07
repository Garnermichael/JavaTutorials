package com.zetcode.version;

import com.zetcode.version.service.DBVersionService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Version", urlPatterns = {"/Version"})
public class Version extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String page = "showVersion.jsp";
        
        String version = DBVersionService.getMySQLVersion();

        request.setAttribute("version", version);

        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Returns version of MySQL";
    }
}
