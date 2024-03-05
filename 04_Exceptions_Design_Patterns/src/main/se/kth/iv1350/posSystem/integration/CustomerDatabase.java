package se.kth.iv1350.posSystem.integration;

import se.kth.iv1350.posSystem.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

class CustomerDatabase {
    private static final CustomerDatabase CUSTOMER_DB = new CustomerDatabase();
    private final List<Customer> registeredCustomers = new ArrayList<>();

    private CustomerDatabase() {
        setRegisteredCustomers();
    }

    private void setRegisteredCustomers() {
        registeredCustomers.add(new Customer("9304050000", "John Doe", "0712344567"));
        registeredCustomers.add(new Customer("8712300010", "Jane Doe", "0798765432"));
        registeredCustomers.add(new Customer("5006141755", "Dave Joe", "0701020304"));
        registeredCustomers.add(new Customer("2101012031", "Dina Joe", "0765748392"));
    }

    static CustomerDatabase getCustomerDB() {
        return CUSTOMER_DB;
    }

    CustomerDTO getCustomerDTO(String customerID) throws CustomerRegistrationException {
        for (Customer customer : registeredCustomers) {
            if (customer.getCustomerID().equals(customerID)) {
                return new CustomerDTO(customerID, customer.getName(), customer.getMobileNo());
            }
        }
        throw new CustomerRegistrationException(customerID);
    }

    static class Customer {
        private final String customerID;
        private final String name;
        private final String mobileNo;

        Customer(String customerID, String name, String mobileNo) {
            this.customerID = customerID;
            this.name = name;
            this.mobileNo = mobileNo;
        }

        String getCustomerID() {
            return customerID;
        }

        String getName() {
            return name;
        }

        String getMobileNo() {
            return mobileNo;
        }
    }
}
