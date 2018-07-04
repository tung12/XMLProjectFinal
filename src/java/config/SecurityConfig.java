/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.Connections;

/**
 *
 * @author ntien
 */
public class SecurityConfig {
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();
    static {
        init();
    }
 
    
    private static void init() {
 
        // Cấu hình cho vai trò USER
        List<String> urlPatternsUser = new ArrayList<String>();
 
        urlPatternsUser.add("/userInfo");
        urlPatternsUser.add("/employeeTask");
 
        mapConfig.put(ROLE_USER, urlPatternsUser);
 
        // Cấu hình cho vai trò ADMIN
        List<String> urlPatternsAdmin = new ArrayList<String>();
 
        urlPatternsAdmin.add("/userInfo");
        urlPatternsAdmin.add("/managerTask");
 
        mapConfig.put(ROLE_ADMIN, urlPatternsAdmin);
    }
 
    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }
 
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
 

}
