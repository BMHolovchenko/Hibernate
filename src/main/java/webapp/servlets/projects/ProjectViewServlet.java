package webapp.servlets.projects;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.Project;
import service.CompanyService;
import service.HandleBodyUtil;
import service.ProjectService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/projects/*")
public class ProjectViewServlet extends HttpServlet {

    private ProjectService service;

    @Override
    public void init() throws ServletException {
        this.service = (ProjectService) getServletContext().getAttribute("projectService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String id = requestURI.replace("/projects/", "");
        if ("new".equalsIgnoreCase(id)) {
            req.setAttribute("project", new Project());
            req.setAttribute("isNew", true);

            req.getRequestDispatcher("/jsp/project.jsp").forward(req, resp);
        }
        Optional<Project> projectOptional = service.get(Long.parseLong(id));
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            req.setAttribute("project", project);
            req.getRequestDispatcher("/jsp/project.jsp").forward(req, resp);
        }
        resp.sendRedirect("/projects");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Project.class)
                .ifPresent(project -> {
                    service.update(project);
                });
        resp.sendRedirect("/projects");
    }

}