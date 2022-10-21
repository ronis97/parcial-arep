package edu.escuelaing.arep;


import spark.Request;
import spark.Response;

import static spark.Spark.*;
public class SparkWebServer
{

    public static void main(String... args){
        staticFileLocation("/public");
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
        post("collatzreq", (req,res) ->getCollatz(req.queryParams("collatz")));
    }

    private static String getCollatz(String number){
        String respuesta = "{"
                + "<p>"
                +" \"operation\": \"collatzsequence\","
                + "<p>"
                + " \"input\":  13,"
                + "<p>"
                +"output\":";

        Integer numero = Integer.valueOf(number);
        String output = String.valueOf(numero);
        while (numero != 1){
            if (numero % 2 == 0){
                numero = numero / 2;
            }
            else{
                numero = 3 * numero + 1;
            }
            output += "->" + String.valueOf(numero);
        }
        respuesta += output
                + "<p>"
                + "}";
        return respuesta;
    }
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
