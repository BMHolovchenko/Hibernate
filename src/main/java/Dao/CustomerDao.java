package Dao;

import model.Customer;

public class CustomerDao extends AbstractDao <Customer>{

    private static CustomerDao instance;

    public CustomerDao() {
        super(Customer.class);
    }

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }
}
