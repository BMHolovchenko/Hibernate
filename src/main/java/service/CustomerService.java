package service;

import Dao.CustomerDao;
import model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    private static CustomerService instance;
    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);
    private static final CustomerDao dao = CustomerDao.getInstance();

    public static CustomerService getInstance() {
        if (instance == null){
            instance = new CustomerService();
        }
        return instance;
    }

    public List<Customer> getAll() {
        return CustomerDao.getInstance().getAll();
    }

    public void create(Customer customer) {
        dao.create(customer);
    }

    public Optional<Customer> get(long id) {
        return dao.get(id);
    }

    public void delete(Customer customer) {
        dao.delete(customer);
    }

    public void update(Customer customer) {
        dao.update(customer);
    }

}