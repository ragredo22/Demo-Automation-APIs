import models.Datum;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;

import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.GetUsersQuestion;
import tasks.GetUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;


@RunWith(SerenityRunner.class)
public class SerenityInitialTests {

    private static final String restApiUrl = "http://localhost:5000/api";
    private static final int Code = 200;



    @Test
    public void getUsers() {

        Actor director = Actor.named("Rhonal").whoCan(CallAnApi.at(restApiUrl));
        Actor a = Actor.named("cosa").whoCan(CallAnApi.at(restApiUrl));

        a.attemptsTo(GetUsers.fromPage(1));


        //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
        Assert.assertEquals(SerenityRest.lastResponse().statusCode(), equals(Code));

        //director.should(seeThat(ResponseCode.was(), is(Code) ));

        /*Datum user = new GetUsersQuestion().answeredBy(director).getData()
                .stream().filter(x -> x.getId() == 1).findFirst().get();

        director.should(seeThat("Usuario no es nulo", act -> user, notNullValue()));
        director.should(seeThat("El Email del usuario", act -> user.getEmail().equalsIgnoreCase("janet.weaver@reqres.in")));
*/
    }

    @Test
    public void getUserFail() {

        Actor director = Actor.named("Manager").whoCan(CallAnApi.at(restApiUrl));

        director.attemptsTo(GetUsers.fromPage(2));

        //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(300);

        Assert.assertEquals(SerenityRest.lastResponse().statusCode(), equals(300));


    }
}
