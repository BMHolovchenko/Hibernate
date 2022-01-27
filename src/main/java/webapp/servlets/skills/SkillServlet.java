package webapp.servlets.skills;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Skill;
import service.HandleBodyUtil;
import service.SkillService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/skills")
public class SkillServlet extends HttpServlet {

    private SkillService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            Skill skill = new Skill();
            skill.setId(Long.parseLong(deleteId));
            service.delete(skill);
            resp.sendRedirect("/skills");
        } else {
            List<Skill> all = service.getAll();
            Object[] skills = all.toArray();
            req.setAttribute("skills", skills);
            req.getRequestDispatcher("/jsp/skills.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Skill> modelFromStream = HandleBodyUtil.
                getModelFromStream(req.getInputStream(), Skill.class);
        modelFromStream.ifPresent(skill -> service.create(skill));
        System.out.println("Created skill with status code:" + resp.getStatus());
        resp.sendRedirect("/skills");
    }

    @Override
    public void init() throws ServletException {
        this.service = (SkillService) getServletContext().getAttribute("skillService");
    }
}