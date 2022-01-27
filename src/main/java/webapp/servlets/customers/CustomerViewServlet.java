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
import java.util.Optional;

@WebServlet("/customers/*")
public class CustomerViewServlet extends HttpServlet {

    private CustomerService service;

    @Override
    public void init() throws ServletException {
        this.service = (CustomerService) getServletContext().getAttribute("customerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String id = requestURI.replace("/customers/", "");
        if ("new".equalsIgnoreCase(id)) {
            req.setAttribute("customer", new Customer());
            req.setAttribute("isNew", true);

            req.getRequestDispatcher("/jsp/customer.jsp").forward(req, resp);
        }
        Optional<Customer> customerOptional = service.get(Long.parseLong(id));
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/jsp/customer.jsp").forward(req, resp);
        }
        resp.sendRedirect("/customers");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Customer.class)
                .ifPresent(customer -> {
                    service.update(customer);
                });
        resp.sendRedirect("/customers");
    }

}