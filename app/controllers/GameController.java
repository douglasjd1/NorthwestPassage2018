package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Int;
import views.html.helper.form;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.*;

public class GameController extends Controller
{
    List<String> crewMembers = new ArrayList<>();
    private FormFactory formFactory;
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
        crewMembers.clear();
        initializeCrewMembers();
        DynamicForm form = formFactory.form().bindFromRequest();
        String name = form.get("name");
        session().put("name", name);

        return ok(views.html.start.render(session().get("name"),crewMembers.size(), crewMembers));
    }

    public Result getStart()
    {
        crewMembers.clear();
        initializeCrewMembers();
        return ok(views.html.start.render(session().get("name"),crewMembers.size(), crewMembers));
    }

    public String lostCrewMember()
    {
        String crewMember = crewMembers.get(0);
        crewMembers.remove(0);
        return crewMember;
    }

    public void initializeCrewMembers()
    {
        crewMembers.add("Mickey Mouse");
        crewMembers.add("King Kong");
        crewMembers.add("Daffy Duck");
        crewMembers.add("Jet Li");
        crewMembers.add("Ezra Bridger");
        crewMembers.add("Doctor Who");
        crewMembers.add("Happy Feet");
        crewMembers.add("Bob");
        crewMembers.add("George Washington");
        crewMembers.add("Karim Abdul-Jabar");
    }

    public Result postEastFromEngland()
    {
        String injuredCrewMember = lostCrewMember();
        if(crewMembers.size() < 5)
        {
            return ok(views.html.oakisland.render(session().get("name"),crewMembers.size(), crewMembers));
        }
        else
        {
            return ok(views.html.eastfromengland.render(session().get("name"), crewMembers.size(), injuredCrewMember, crewMembers));
        }
    }

    public Result postNorthFromEngland()
    {
        Random random = new Random();
        int santaChance = random.nextInt(4) + 1;
        if(santaChance == 1)
            return ok(views.html.meetingwithsanta.render(session().get("name"),crewMembers.size(), crewMembers));
        return ok(views.html.northfromengland.render(session().get("name"),crewMembers.size(), crewMembers));
    }

    public Result postWestFromEngland()
    {
        String injuredCrewMember = lostCrewMember();
        return ok(views.html.westfromengland.render(session().get("name"), crewMembers.size(), injuredCrewMember, crewMembers));
    }

    public Result postEastEnd()
    {
        return ok(views.html.eastend.render(session().get("name"),crewMembers.size(), crewMembers));
    }

    public Result postWestEnd()
    {
        Random random = new Random();
        int leadChance = random.nextInt(5) + 1;

        if(leadChance == 1)
            return ok(views.html.westendleadpaint.render(session().get("name"),crewMembers.size(), crewMembers));
        return ok(views.html.westend.render(session().get("name"),crewMembers.size(), crewMembers));
    }

    public Result postHomePort()
    {
        return ok(views.html.homeport.render(session().get("name"),crewMembers.size(), crewMembers));
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }
}
