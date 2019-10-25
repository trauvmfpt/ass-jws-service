package service;

import com.google.gson.Gson;
import entity.Place;
import entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Seeder {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
//        placeSeeder();
        System.out.println(new Gson().toJson(new SearchService().searchByPost("a")));
    }

    private static void userSeeder() throws InvalidKeySpecException, NoSuchAlgorithmException{
        UserService userService = new UserService();
        // tao user
//        User user = new User();
//        user.setUsername("user01");
//        user.setEmail("user01@gmail.com");
//        user.setPassword("123456");
//        user.setAge(18);
//        user.setGender(1);
//        int[] roleIds = {1,3};
//        System.out.println(new Gson().toJson(userService.createUser(user, roleIds)));

        // update user
//        User user = userService.detail(2);
//        user.setAddress("asdfasdf");
//        user.setName("asdfasdfa");
//        user.getRoleSet().clear();
//        int[] roleIds = {1};
//        userService.updateUser(user,roleIds);

        //login
        User user = new User();
        user.setUsername("user01");
        user.setPassword("123456");
        User user2 = userService.login(user);
        System.out.println(user2.getToken());
    }
    private static void placeSeeder(){
        Place place = new Place();
        place.setAddress("Dragon Bridge");
        place.setName("Da Nang");
        new PlaceService().createPlace(place);
        //
        place.setAddress("BaNa Hill");
        place.setName("Da Nang");
        new PlaceService().createPlace(place);
        //
        place.setAddress("Old Street");
        place.setName("Hoi An");
        new PlaceService().createPlace(place);
        //
        place.setAddress("Guom Lake");
        place.setName("Ha Noi");
        new PlaceService().createPlace(place);
        //
        place.setAddress("Le Thi Rieng Park");
        place.setName("Ho Chi Minh City");
        new PlaceService().createPlace(place);
    }
    private static void postSeeder(){

    }
}
