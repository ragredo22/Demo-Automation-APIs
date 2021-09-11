package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutUsers implements Task {

    private static Object userPutInfo;

    public PutUsers(Object userPutInfo){
        this.userPutInfo = userPutInfo;
    }

    public static Performable modUser(Object userPutInfo){
        return instrumented(PutUsers.class, userPutInfo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Put.to("/users/2").with(
                requestSpecification -> requestSpecification
                        .contentType(ContentType.JSON).body(userPutInfo)
                )

        );
    }
}
