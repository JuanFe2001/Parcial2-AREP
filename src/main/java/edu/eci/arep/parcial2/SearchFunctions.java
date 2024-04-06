/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.eci.arep.parcial2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static spark.Spark.*;

public class SearchFunctions {

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {
        configurePort();
        configureStaticFiles();
        configureRoutes();
    }

    private static void configurePort() {
        int port = getPortFromEnvironment();
        port(port);
    }

    private static void configureStaticFiles() {
        staticFiles.location("/public");
    }

    private static void configureRoutes() {
        get("/hello", (req, res) -> "¡Hola!");
        configureLinearSearchRoute();
        configureBinarySearchRoute();
    }

    private static void configureLinearSearchRoute() {
        get("/linear-search", (req, res) -> {
            String inputList = req.queryParams("inputlist");
            String value = req.queryParams("value");
            String response = performLinearSearch(inputList, value);
            res.type("application/json");
            return response;
        });
    }

    private static void configureBinarySearchRoute() {
        get("/binary-search", (req, res) -> {
            String inputList = req.queryParams("inputlist");
            String value = req.queryParams("value");
            String response = performBinarySearch(inputList, value);
            res.type("application/json");
            return response;
        });
    }

    private static int getPortFromEnvironment() {
        String portStr = System.getenv("PORT");
        return (portStr != null) ? Integer.parseInt(portStr) : 4500;
    }

    private static String performLinearSearch(String inputListStr, String valueStr) {
        List<String> inputList = Arrays.asList(inputListStr.split(","));
        String output;
        if (inputList.contains(valueStr)) {
            output = generateSearchResponse("linearSearch", inputListStr, valueStr, inputList.indexOf(valueStr));
        } else {
            output = generateSearchResponse("linearSearch", inputListStr, valueStr, "Value not found");
        }
        return output;
    }

    private static String performBinarySearch(String inputListStr, String valueStr) {
        List<String> inputList = Arrays.asList(inputListStr.split(","));
        Collections.sort(inputList);
        int index = binarySearchRecursive(inputList, valueStr, 0, inputList.size() - 1);
        String output;
        if (index != -1) {
            output = generateSearchResponse("binarySearch", inputListStr, valueStr, index);
        } else {
            output = generateSearchResponse("binarySearch", inputListStr, valueStr, "Value not found");
        }
        return output;
    }

    private static String generateSearchResponse(String operation, String inputList, String value, Object output) {
        return String.format("{\"operation\": \"%s\", \"inputlist\": \"%s\", \"value\": \"%s\", \"output\": \"%s\"}",
                operation, inputList, value, output);
    }

    private static int binarySearchRecursive(List<String> inputList, String value, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            int compare = value.compareTo(inputList.get(mid));
            if (compare == 0) {
                return mid;
            }
            if (compare < 0) {
                return binarySearchRecursive(inputList, value, left, mid - 1);
            }
            return binarySearchRecursive(inputList, value, mid + 1, right);
        }
        return -1;
    }
}
