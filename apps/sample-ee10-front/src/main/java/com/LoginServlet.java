package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody = req.getReader().lines().collect(Collectors.joining("\n"));
        Gson gson = new Gson();

        LoginInput input = gson.fromJson(reqBody, LoginInput.class);

        if(!input.getPassword().equals("password")){
            resp.setStatus(401);
            resp.setContentType("application/json");
            resp.getWriter().println("{\"error\": \"Invalid userId or password\"}");
            return;
        }

        LoginOutput output = new LoginOutput(){{
            setUserId(input.getLoginId());
            setUserName("Jhon Tester");
        }};

        resp.setContentType("application/json");
        resp.getWriter().println("{\"userId\": \"" + output.getUserId() + "\", \"userName\": \"" + output.getUserName() + "\"}");
    }
}