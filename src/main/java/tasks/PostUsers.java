package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostUsers implements Task {

    private  final Object userInfo;

    public PostUsers(Object msg){
        this.userInfo = msg;
    }

    public static Performable postUser(Object userInfo){

        return instrumented(PostUsers.class, userInfo);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(Post.to("/register").with(
                requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                        .body(userInfo)
                )

        );

    }
}
