package main;

import service.StudentService;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService()
public class MainDiduduadi {

    public static void main(String[] argv) {
        Endpoint.publish("http://localhost:9000/student-service", new StudentService());
    }
}
