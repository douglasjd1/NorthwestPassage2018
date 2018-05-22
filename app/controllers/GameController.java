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

        if(session().get("name").equals("Darth Vader"))
            initializeVaderCrew();
        else
            initializeCrewMembers();

        DynamicForm form = formFactory.form().bindFromRequest();
        String name = form.get("name");
        session().put("name", name);
        String kittenImage = form.get("kitten");
        session().put("kitten", kittenImage + ".jpg");

        if(name.equals("Darth Vader"))
        {
            session().put("kitten", "eastereggvader.jpg");
            return ok(views.html.eastereggvader.render(session("kitten")));
        }

        return ok(views.html.start.render(session().get("name"),crewMembers.size(), crewMembers, session("kitten")));
    }

    public Result getStart()
    {
        crewMembers.clear();
        if(session().get("name").equals("Darth Vader"))
            initializeVaderCrew();
        else
            initializeCrewMembers();
        return ok(views.html.start.render(session().get("name"),crewMembers.size(), crewMembers, session("kitten")));
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
            return ok(views.html.oakisland.render(session().get("name"),crewMembers.size(), crewMembers, session("kitten")));
        }
        else
        {
            return ok(views.html.eastfromengland.render(session().get("name"), crewMembers.size(), injuredCrewMember, crewMembers, session("kitten")));
        }
    }

    public Result postNorthFromEngland()
    {
        Random random = new Random();
        int santaChance = random.nextInt(4) + 1;
        if(santaChance == 1)
            return ok(views.html.meetingwithsanta.render(session().get("name"),crewMembers.size(), crewMembers, session("kitten")));
        return ok(views.html.northfromengland.render(session().get("name"),crewMembers.size(), crewMembers, session("kitten")));
    }

    public Result postWestFromEngland()
    {
        String injuredCrewMember = lostCrewMember();
        return ok(views.html.westfromengland.render(session().get("name"), crewMembers.size(), injuredCrewMember, crewMembers, session("kitten")));
    }

    public Result postEastEnd()
    {
        return ok(views.html.eastend.render(session().get("name"),crewMembers.size(), crewMembers, session("kitten")));
    }

    public Result postWestEnd()
    {
        Random random = new Random();
        int leadChance = random.nextInt(5) + 1;

        if(leadChance == 1)
            return ok(views.html.westendleadpaint.render(session().get("name"),crewMembers.size(), crewMembers, session("kitten")));
        return ok(views.html.westend.render(session().get("name"),crewMembers.size(), crewMembers, session("kitten")));
    }

    public Result postHomePort()
    {
        return ok(views.html.homeport.render(session().get("name"),crewMembers.size(), crewMembers, session("kitten")));
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }

    public Result postEasterEggVader()
    {
        return ok(views.html.eastereggvader.render(session("kitten")));
    }

    private void initializeVaderCrew()
    {
        crewMembers.add("Darth Sideous");
        crewMembers.add("Padme Amidala");
        crewMembers.add("Jar Jar Binks");
        crewMembers.add("Royal Guard");
        crewMembers.add("Boba Fett");
        crewMembers.add("Lando Clarissian");
        crewMembers.add("Grand Moff Tarkin");
        crewMembers.add("Darth Maul");
        crewMembers.add("Asajj Ventress");
        crewMembers.add("Savage Oppress");
    }
}
