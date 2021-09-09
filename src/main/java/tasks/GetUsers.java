package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUsers implements Task {

    private final int page;

    public GetUsers(int page){
        this.page = page;
    }

    public static Performable fromPage(int page){
        return instrumented(GetUsers.class, page) ;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(Get.resource("/users?page=" + page));

    }
}
