package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Int;
import views.html.helper.form;

import javax.inject.Inject;
import java.util.Random;

public class GameController extends Controller
{
    private FormFactory formFactory;
    private final String PLAYER_NAME_KEY = "name";
    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }
    public Result getWelcome()
    {
        return ok(views.html.welcome.render());
    }

    public Result postStart()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String name = form.get("name");
        session().put(PLAYER_NAME_KEY, name);
        session().put("crewMembers", "10");
        return ok(views.html.start.render(session().get("name")));
    }

    public void lostCrewMember()
    {
        int crewMembers = Integer.parseInt(session().get("crewMembers")) - 1;
        session().put("crewMembers", String.valueOf(crewMembers));
    }

    public Result postEastFromEngland()
    {
        lostCrewMember();
        if(Integer.parseInt(session().get("crewMembers")) < 5)
        {
            return ok(views.html.oakisland.render(session().get("name"), Integer.parseInt(session().get("crewMembers"))));
        }
        else
        {
            return ok(views.html.eastfromengland.render(session().get("name"), Integer.parseInt(session().get("crewMembers"))));
        }
    }

    public Result postNorthFromEngland()
    {
        return ok(views.html.northfromengland.render(session().get("name"), Integer.parseInt(session().get("crewMembers"))));
    }

    public Result postWestFromEngland()
    {
        lostCrewMember();
        return ok(views.html.westfromengland.render(session().get("name"), Integer.parseInt(session().get("crewMembers"))));
    }

    public Result postEastEnd()
    {
        return ok(views.html.eastend.render(session().get("name"), Integer.parseInt(session().get("crewMembers"))));
    }

    public Result postWestEnd()
    {
        Random random = new Random();
        int chance = random.nextInt(4) + 1;
        if(chance == 1)
            return ok(views.html.meetingwithsanta.render(session().get("name"), Integer.parseInt(session().get("crewMembers"))));
        return ok(views.html.westend.render(session().get("name"), Integer.parseInt(session().get("crewMembers"))));
    }

    public Result postHomePort()
    {
        return ok(views.html.homeport.render(session().get("name"), Integer.parseInt(session().get("crewMembers"))));
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }
}
