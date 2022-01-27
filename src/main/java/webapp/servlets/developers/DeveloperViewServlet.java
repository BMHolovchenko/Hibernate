package webapp.servlets.developers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Developer;
import service.DeveloperService;
import service.HandleBodyUtil;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/developers/*")
public class DeveloperViewServlet extends HttpServlet {

    private DeveloperService service;

    @Override
    public void init() throws ServletException {
        this.service = (DeveloperService) getServletContext().getAttribute("developerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String id = requestURI.replace("/developers/", "");
        if ("new".equalsIgnoreCase(id)) {
            req.setAttribute("developer", new Developer());
            req.setAttribute("isNew", true);

            req.getRequestDispatcher("/jsp/developer.jsp").forward(req, resp);
        }
        Optional<Developer> developerOptional = service.get(Long.parseLong(id));
        if (developerOptional.isPresent()) {
            Developer dev = developerOptional.get();
            req.setAttribute("developer", dev);
            req.getRequestDispatcher("/jsp/developer.jsp").forward(req, resp);
        }
        resp.sendRedirect("/developers");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Developer.class)
                .ifPresent(developer -> {
                    service.update(developer);
                });
        resp.sendRedirect("/developers");
    }

}