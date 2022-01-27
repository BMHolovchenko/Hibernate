package webapp.servlets.companies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import service.CompanyService;
import service.HandleBodyUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/companies")
public class CompanyServlet extends HttpServlet {

    private CompanyService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            Company company = new Company();
            company.setId(Long.parseLong(deleteId));
            service.delete(company);
            resp.sendRedirect("/companies");
        } else {
            List<Company> all = service.getAll();
            Object[] companies = all.toArray();
            req.setAttribute("companies", companies);
            req.getRequestDispatcher("/jsp/companies.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Company> modelFromStream = HandleBodyUtil.
                getModelFromStream(req.getInputStream(), Company.class);
        modelFromStream.ifPresent(company -> service.create(company));
        System.out.println("Created company with status code:" + resp.getStatus());
        resp.sendRedirect("/companies");
    }

    @Override
    public void init() throws ServletException {
        this.service = (CompanyService) getServletContext().getAttribute("companyService");
    }
}