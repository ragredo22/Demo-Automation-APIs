package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class DeleteUser implements Task {

    private final Integer user;

    public DeleteUser(Integer user) {

        this.user = user;
    }

    public static Performable from(int user){
        return instrumented(DeleteUser.class, user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Delete.from("/users/"+user));
    }
}
