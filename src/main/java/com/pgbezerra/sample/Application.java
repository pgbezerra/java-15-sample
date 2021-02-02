package com.pgbezerra.sample;

import java.util.List;
import java.util.Random;

import com.pgbezerra.sample.model.User;
import com.pgbezerra.sample.model.enums.UserType;
import com.pgbezerra.sample.repository.impl.JdbcTemplateImpl;
import com.pgbezerra.sample.repository.impl.UserRepositoryImpl;
import com.pgbezerra.sample.service.UserService;
import com.pgbezerra.sample.service.impl.UserServiceImpl;

public class Application {

    public static void main(String[] args) {
    	UserService service = new UserServiceImpl(new UserRepositoryImpl(new JdbcTemplateImpl()));
    	User user = null;
        for(int i = 0; i < 10; i++) {
        	int type = new Random().nextInt(10);
        	UserType userType = switch(type) {
	        	case 1,2 -> UserType.ADMIN;
	        	case 3,4,5,6,7 -> UserType.COMMOM;
	        	default -> UserType.UNKNOW;
        	};
        	user = new User(null, "Sample", userType);
        	service.insert(user);
        }
        user = new User(1, "Update", UserType.COMMOM);
        service.update(user);
        Object obj = service.findById(3);
        
        if(obj instanceof User usr) 
        	System.out.println("Instance: " + usr);
        
        User u2 = service.findById(1);
        List<User> users = service.findAll();
        users.forEach(System.out::println);
        System.out.println(u2);
        Integer deleteID = new Random().nextInt(100);
        System.out.printf("Deleted %s: %s\n", deleteID, service.deleteById(deleteID));
        user = new User(null, "Sample", UserType.ADMIN);
        users = service.findAll();
        users.forEach(System.out::println);
    }

}
