package com.springboot.blog.springbootblogrestapi.utils;

import com.springboot.blog.springbootblogrestapi.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class EncryptedPasswordGenerator {

    static boolean isPersonEligibleForRetirement(User person, Predicate<User> predicate){
        return predicate.test(person);
    }
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("password"));
        System.out.println(passwordEncoder.encode("admin"));


    }

//    List<User> sortMe(List<User> users){
//        Collections.sort(users, new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
//        return users;
//    }
}
