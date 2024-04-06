/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.eci.arep.parcial2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static spark.Spark.*;

public class ServiceProxy {
    
    
    

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {
        configureStaticFiles();
        configurePort();
        configureRoutes();
    }

    private static void configureStaticFiles() {
        staticFiles.location("/public");
    }

    private static void configurePort() {
        int port = getPort();
        port(port);
    }

    private static void configureRoutes() {
        get("/", (req, res) -> {
            res.redirect("/Page.html");
            return null;
        });

        configureLinearSearchRoute();
        configureBinarySearchRoute();
    }

    private static void configureLinearSearchRoute() {
        get("/linear-search", (req, res) -> {
            res.type("application/json");
            String output = invokeRemoteService("http://ec2-54-166-183-43.compute-1.amazonaws.com:4500/linear-search?" + req.queryString());
            return output;
        });
    }

    private static void configureBinarySearchRoute() {
        get("/binary-search", (req, res) -> {
            res.type("application/json");
            String output = invokeRemoteService("http://ec2-54-167-24-40.compute-1.amazonaws.com:4500/binary-search?" + req.queryString());
            return output;
        });
    }

    private static int getPort() {
        String portStr = System.getenv("PORT");
        return (portStr != null) ? Integer.parseInt(portStr) : 4567;
    }

    private static String invokeRemoteService(String url) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println(response);
        System.out.println("GET DONE");
        return response.toString();
    }
}
