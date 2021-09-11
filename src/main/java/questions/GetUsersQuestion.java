package questions;

import models.Datum;
import models.Users;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.json.Json;

public class GetUsersQuestion implements Question {




    @Override
    public Users answeredBy(Actor actor) {


        return SerenityRest.lastResponse().as(Users.class);
    }
}
