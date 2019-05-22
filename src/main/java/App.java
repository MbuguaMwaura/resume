import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App{
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

          post("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();


            String title = request.queryParams("title");
            String company = request.queryParams("company");
            String location = request.queryParams("location");
            String description = request.queryParams("description");
            String date = request.queryParams("date");
            String end = request.queryParams("end");
            Resume newResume = new Resume(title, company, location, description, date, end);
            newResume.save();
              model.put("resume", Resume.all());
            model.put("template","templates/success.vtl");

            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
