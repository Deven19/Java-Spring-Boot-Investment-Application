package com.dc.investmentapplication.controller;

import com.dc.investmentapplication.entity.User;
import com.dc.investmentapplication.helper.GlobalHelper;
import com.dc.investmentapplication.service.UserService;
import com.dc.investmentapplication.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.sql.SQLDataException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    /*
    @PostMapping("/login")
    public ResponseEntity<String > login(@RequestBody User user) {
        return ResponseEntity.ok("All Ok");
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
    */

    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> createUser(@Valid @RequestBody User user, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        try {
            String res = userService.createUser(user);
            //return new ResponseEntity<>(res, HttpStatus.CREATED) ;
            ApiResponse<String > response = new ApiResponse<>(HttpStatus.OK.value(), "User created successfully", res);
//            response.setRequestId(request.getHeader("Request-ID"));
//            response.setPath(request.getRequestURI());
//            response.setMethod(request.getMethod());
//            response.setHeaders(GlobalHelper.getHeadersMap(request));
//            response.setUser(request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "Anonymous");
//            response.setServer(request.getLocalName());
            response.setDuration(System.currentTimeMillis() - start);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<String  > response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "User creation failed", e.getMessage());
//            response.setRequestId(request.getHeader("Request-ID"));
//            response.setPath(request.getRequestURI());
//            response.setMethod(request.getMethod());
//            response.setHeaders(GlobalHelper.getHeadersMap(request));
//            response.setUser(request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "Anonymous");
//            response.setServer(request.getLocalName());
            response.setDuration(System.currentTimeMillis() - start);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
