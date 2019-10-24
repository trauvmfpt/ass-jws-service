package main;

import com.sun.net.httpserver.HttpServer;
import service.*;

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
        //
        Endpoint userEndpoint = Endpoint.create(new UserService());
        userEndpoint.publish(httpServer.createContext("/user"));
        //
        Endpoint placeEndpoint = Endpoint.create(new PlaceService());
        placeEndpoint.publish(httpServer.createContext("/place"));
        //
        Endpoint searchEndpoint = Endpoint.create(new SearchService());
        searchEndpoint.publish(httpServer.createContext("/search"));
        //
        Endpoint commentEndpoint = Endpoint.create(new CommentService());
        commentEndpoint.publish(httpServer.createContext("/comment"));
        //
        Endpoint ratingEndpoint = Endpoint.create(new RatingService());
        ratingEndpoint.publish(httpServer.createContext("/rate"));
        //
        Endpoint imageEndpoint = Endpoint.create(new ImageService());
        imageEndpoint.publish(httpServer.createContext("/image"));
        //
        Endpoint postEndpoint = Endpoint.create(new PostService());
        postEndpoint.publish(httpServer.createContext("/post"));
        httpServer.start();
        System.out.println("Services run on port: 9000");
    }
}
