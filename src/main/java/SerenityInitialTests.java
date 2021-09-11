import models.Datum;
import models.PutUsersModels;
import models.RegisterUserInfo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.GetUsersQuestion;
import questions.ResponseCode;
import tasks.DeleteUser;
import tasks.GetUsers;
import tasks.PostUsers;
import tasks.PutUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;


@RunWith(SerenityRunner.class)
public class SerenityInitialTests {

    private static final String restApiUrl = "http://localhost:5000/api";
    private static final int Code = 200;
    private static final String userInfo = "{\n" +
            "    \"name\": \"Cubo\",\n" +
            "    \"job\": \"Leader\",\n" +
            "    \"email\": \"george.bluth@reqres.in\",\n" +
            "    \"password\": \"pistol\"\n" +
            "}";
    Actor director = Actor.named("Rhonal").whoCan(CallAnApi.at(restApiUrl));


    @Test
    public void getUsers() {


    /*
        //Declaración del actor y la url fija del servicio - Estable
        Actor director = Actor.named("Rhonal").whoCan(CallAnApi.at(restApiUrl));

        //Petición GET a un servicio usando una task y valicación del status code - Estable
        director.attemptsTo(GetUsers.fromPage(2));
        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);

        //Petición get a un nodo especifico y validación de dos campos - Estable
        director.attemptsTo(Get.resource("/users/1"));

        director.should(
                seeThatResponse("User details should be correct",
                response -> response.statusCode(200)
                            .body("data.first_name", equalTo("George"))
                            .body("data.email", equalTo("george.bluth@reqres.in"))

                )
        );
*/
        //Validación de los nodos del response

        director.attemptsTo(GetUsers.fromPage(1));
        Datum user = new GetUsersQuestion().answeredBy(director).getData()
                .stream().filter(x -> x.getId() == 1).findFirst().orElse(null);

        director.should(seeThat("Usuario no es nulo", act -> user, notNullValue()));
        director.should(seeThat("El Email del usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in")));

    }

    @Test
    public void getUserFail() {

        Actor director = Actor.named("Manager").whoCan(CallAnApi.at(restApiUrl));

        director.attemptsTo(Get.resource("/users/23"));

        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(404);

        //Assert.assertEquals(SerenityRest.lastResponse().statusCode(), equals(300));


    }

    @Test

    public void postUser(){


        director.attemptsTo(PostUsers.postUser(userInfo));

        director.should(seeThat("El codigo de respuesta: " , new ResponseCode(), equalTo(200)));
    }

    @Test
    public void postUserwithModel(){

        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setName("Rhonal");
        registerUserInfo.setJob("Tester");
        registerUserInfo.setEmail("emma.wong@reqres.in");
        registerUserInfo.setPassword("Pruebas");
        director.attemptsTo(PostUsers.postUser(registerUserInfo));

        director.should(seeThat("El codigo de respuesta: " , new ResponseCode(), equalTo(200)));
    }

    @Test
    public void postUserwithModelFail(){

        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setName("Rhonal");
        registerUserInfo.setJob("Tester");
        registerUserInfo.setEmail("emma.wongs@reqres.in");
        registerUserInfo.setPassword("Pruebas");
        director.attemptsTo(PostUsers.postUser(registerUserInfo));

        director.should(seeThat("El codigo de respuesta: " , new ResponseCode(), equalTo(400)));
    }

    @Test
    public void putUserWithModel(){
        PutUsersModels putUsersModels = new PutUsersModels();
        putUsersModels.setName("Rhonal");
        putUsersModels.setJob("Test");

        director.attemptsTo(PutUsers.modUser(putUsersModels));

        director.should(seeThat("El codigo de respuesta es: ", new ResponseCode(), equalTo(200)));

    }


    @Test

    public void deleteUser(){

        director.attemptsTo(DeleteUser.from(1));
        director.should(seeThat("El codigo de respuesta fue: ", new ResponseCode(), equalTo(204)));
    }




}
