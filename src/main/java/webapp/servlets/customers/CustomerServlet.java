package webapp.servlets.customers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import service.CustomerService;
import service.HandleBodyUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private CustomerService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            Customer customer = new Customer();
            customer.setId(Long.parseLong(deleteId));
            service.delete(customer);
            resp.sendRedirect("/customers");
        } else {
            List<Customer> all = service.getAll();
            Object[] customers = all.toArray();
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("/jsp/customers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Customer> modelFromStream = HandleBodyUtil.
                getModelFromStream(req.getInputStream(), Customer.class);
        modelFromStream.ifPresent(customer -> service.create(customer));
        System.out.println("Created customer with status code:" + resp.getStatus());
        resp.sendRedirect("/customers");
    }

    @Override
    public void init() throws ServletException {
        this.service = (CustomerService) getServletContext().getAttribute("customerService");
    }
}