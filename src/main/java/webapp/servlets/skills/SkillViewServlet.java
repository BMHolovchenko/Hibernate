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
import java.util.Optional;

@WebServlet("/skills/*")
public class SkillViewServlet extends HttpServlet {

    private SkillService service;

    @Override
    public void init() throws ServletException {
        this.service = (SkillService) getServletContext().getAttribute("skillService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String id = requestURI.replace("/skills/", "");
        if ("new".equalsIgnoreCase(id)) {
            req.setAttribute("skill", new Skill());
            req.setAttribute("isNew", true);

            req.getRequestDispatcher("/jsp/skill.jsp").forward(req, resp);
        }
        Optional<Skill> skillOptional = service.get(Long.parseLong(id));
        if (skillOptional.isPresent()) {
            Skill skill = skillOptional.get();
            req.setAttribute("skill", skill);
            req.getRequestDispatcher("/jsp/skill.jsp").forward(req, resp);
        }
        resp.sendRedirect("/skills");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Skill.class)
                .ifPresent(skill -> {
                    service.update(skill);
                });
        resp.sendRedirect("/skills");
    }

}