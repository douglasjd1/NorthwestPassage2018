package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class GameController extends Controller
{
    private FormFactory formFactory;

    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }
    public Result getWelcome()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String name = form.get("name");

        session().put("name", name);
        return ok(views.html.welcome.render());
    }

    public Result postStart()
    {
        return ok(views.html.start.render());
    }

    public Result postEastFromEngland()
    {
        return ok(views.html.eastfromengland.render(session().get("name")));
    }

    public Result postNorthFromEngland()
    {
        return ok(views.html.northfromengland.render(session().get("name")));
    }

    public Result postWestFromEngland()
    {
        return ok(views.html.westfromengland.render(session().get("name")));
    }

    public Result postEastEnd()
    {
        return ok(views.html.eastend.render(session().get("name")));
    }

    public Result postWestEnd()
    {
        return ok(views.html.westend.render(session().get("name")));
    }

    public Result postHomePort()
    {
        return ok(views.html.homeport.render(session().get("name")));
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }
}
