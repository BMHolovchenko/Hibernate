package webapp.servlets.projects;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Project;
import service.HandleBodyUtil;
import service.ProjectService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/projects")
public class ProjectServlet extends HttpServlet {

    private ProjectService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            Project project = new Project();
            project.setId(Long.parseLong(deleteId));
            service.delete(project);
            resp.sendRedirect("/projects");
        } else {
            List<Project> all = service.getAll();
            Object[] projects = all.toArray();
            req.setAttribute("projects", projects);
            req.getRequestDispatcher("/jsp/projects.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Project> modelFromStream = HandleBodyUtil.
                getModelFromStream(req.getInputStream(), Project.class);
        modelFromStream.ifPresent(project -> service.create(project));
        System.out.println("Created project with status code:" + resp.getStatus());
        resp.sendRedirect("/projects");
    }

    @Override
    public void init() throws ServletException {
        this.service = (ProjectService) getServletContext().getAttribute("projectService");
    }
}