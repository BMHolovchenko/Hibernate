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
import java.util.List;
import java.util.Optional;

@WebServlet("/developers")
public class DeveloperServlet extends HttpServlet {

    private DeveloperService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            Developer developer = new Developer();
            developer.setId(Long.parseLong(deleteId));
            service.delete(developer);
            resp.sendRedirect("/developers");
        } else {
            List<Developer> all = null;
            all = service.getAll();
            Object[] developers = all.toArray();
            req.setAttribute("developers", developers);
            req.getRequestDispatcher("/jsp/developers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Developer> modelFromStream = HandleBodyUtil.
                getModelFromStream(req.getInputStream(), Developer.class);
        modelFromStream.ifPresent(developer -> service.create(developer));
        System.out.println("Created developer with status code:" + resp.getStatus());
        resp.sendRedirect("/developers");
    }

    @Override
    public void init() throws ServletException {
        this.service = (DeveloperService) getServletContext().getAttribute("developerService");
    }
}