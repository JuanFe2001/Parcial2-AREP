/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.parcial2;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import static javax.swing.UIManager.get;
import static spark.Spark.port;


/**
 *
 * @author juan.vivas-m
 */
public class searchFunctions2 {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM";

    public static void main(String[] args) throws IOException {

        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
    }

    public class SparkWebServer {

        public static void main(String... args) {
            port(getPort());
            get("hello", (req, res) -> "Hello Docker!");
        }

        private static int getPort() {
            if (System.getenv("PORT") != null) {
                return Integer.parseInt(System.getenv("PORT"));
            }
            return 4567;
        }

    }

    public static String linearSearch(List<String> lista, String valor) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(valor)) {
                return new Gson().toJson("{'index': " + i + "}");
            }
        }

        return new Gson().toJson("{'index': -1, 'message': 'Elemento no encontrado'}");
    }

    private static int binarySearchRecursive(List<String> lista, String valor, int inicio, int fin) {
        if (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            if (lista.get(medio).compareTo(valor) == 0) {
                return medio;
            }

            if (lista.get(medio).compareTo(valor) < 0) {
                return binarySearchRecursive(lista, valor, medio + 1, fin);
            } else {
                return binarySearchRecursive(lista, valor, inicio, medio - 1);
            }
        }
        return -1;
    }

    public static String binarySearch(List<String> lista, String valor) {
        int resultado = binarySearchRecursive(lista, valor, 0, lista.size() - 1);
        return new Gson().toJson("{'index': " + resultado + "}");
    }

    private static String htmlPage() {
        String response = "/ HTTP/1.1\n\r"
                + "200 OK\n\r"
                + "\n\r"
                + "<!DOCTYPE html>\n\r"
                + "<html>\n\r"
                + "    <head>\n\r"
                + "        <title>Form Example</title>\n\r"
                + "        <meta charset=\"UTF-8\">\n\r"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n\r"
                + "    </head>\n\r"
                + "    <body>\n\r"
                + "        <h1>Form with GET</h1>\n\r"
                + "        <form action=\"/hello\">\n\r"
                + "            <label for=\"name\">Name:</label><br>\n\r"
                + "            <input type=\"text\" id=\"name\" name=\"name\" value=\"Class([java.lang.String])\"><br><br>\n\r"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n\r"
                + "        </form> \n\r"
                + "        <div id=\"getrespmsg\"></div>\n\r"
                + "\n\r"
                + "        <script>\n\r"
                + "            function loadGetMsg() {\n\r"
                + "                let nameVar = document.getElementById(\"name\").value;\n\r"
                + "                const xhttp = new XMLHttpRequest();\n\r"
                + "                xhttp.onload = function() {\n\r"
                + "                    document.getElementById(\"getrespmsg\").innerHTML =\n\r"
                + "                    this.responseText;\n\r"
                + "                }\n\r"
                + "                xhttp.open(\"GET\", \"/consulta?comando=\"+nameVar);\n\r"
                + "                xhttp.send();\n\r"
                + "            }\n\r"
                + "        </script>\n\r"
                + "\n\r"
                + "        <h1>Form with POST</h1>\n\r"
                + "        <form action=\"/hellopost\">\n\r"
                + "            <label for=\"postname\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"postname\" name=\"name\" value=\"Class([java.lang.String])\"><br><br>\n\r"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n\r"
                + "        </form>\n\r"
                + "        \n\r"
                + "        <div id=\"postrespmsg\"></div>\n\r"
                + "        \n\r"
                + "        <script>\n\r"
                + "            function loadPostMsg(name){\n\r"
                + "                let url = \"/consultaPost?comando=\" + name.value;\n\r"
                + "\n\r"
                + "                fetch (url, {method: 'POST'})\n\r"
                + "                    .then(x => x.text())\n\r"
                + "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n\r"
                + "            }\n\r"
                + "        </script>\n\r"
                + "    </body>\n\r"
                + "</html>";

        return response;
    }
}
