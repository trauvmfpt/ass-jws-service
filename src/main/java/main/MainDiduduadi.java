package main;

import com.sun.net.httpserver.HttpServer;
import service.PlaceService;
import service.UserService;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@WebService()
public class MainDiduduadi {

    public static void main(String[] argv) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 9000), 16);

        Endpoint fooEndpoint = Endpoint.create(new UserService());
        fooEndpoint.publish(httpServer.createContext("/user"));

        Endpoint barEndpoint = Endpoint.create(new PlaceService());
        barEndpoint.publish(httpServer.createContext("/place"));
        httpServer.start();
        System.out.println("Ờ!");
    }
}
