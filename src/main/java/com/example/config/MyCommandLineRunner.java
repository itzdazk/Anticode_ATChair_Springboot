//package com.example.config;
//
//import com.example.model.Customer;
//import com.example.model.Role;
//import com.example.repository.CustomerRepository;
//import com.example.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
//@Component
//public class MyCommandLineRunner implements CommandLineRunner {
//
//    @Autowired
//    private CustomerRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Thêm role "admin" cho người dùng có tên đăng nhập là "admin"
//        Customer customer = userRepository.findByUsername("admin@gmail.com");
//        if (customer != null && !hasAdminRole(customer)) {
//            Role adminRole = roleRepository.findByName("ADMIN");
//            customer.getRoles().add(adminRole);
//            userRepository.save(customer);
//        }
//    }
//
//    // Hàm kiểm tra xem người dùng đã có role "admin" chưa
//    private boolean hasAdminRole(Customer customer) {
//        for (Role role : customer.getRoles()) {
//            if (role.getName().equals("ADMIN")) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
//
