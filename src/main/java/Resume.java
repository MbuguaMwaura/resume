import org.sql2o.*;

import java.util.List;

public class Resume{
    private String title;
    private String company;
    private String location;
    private String description;
    private String start;
    private String enddate;
    private int id;
    
    public Resume(String title, String company, String location, String description, String start, String enddate) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.start = start;
        this.enddate = enddate;
    }
    public String getTitle(){
        return title;
    }
    public String getCompany(){
        return company;
    }
    public String getLocation(){
        return location;
    }
    public String getDescription(){
        return description;
    }
    public String getDate(){
        return start;
    }
    public String getEnd(){
        return enddate;
    }
    public int getId(){
        return id;
    }

    public static List<Resume> all(){
        String sql  = "SELECT id, title, company, location, description, start, enddate FROM resume";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Resume.class);
        }

    }

    @Override
    public boolean equals(Object otherResume){
        if(!(otherResume instanceof Resume)){
            return false;
        } else {
            Resume newResume = (Resume) otherResume;
            return this.getTitle().equals(newResume.getTitle()) &&
                    this.getCompany().equals(newResume.getCompany()) &&
                    this.getLocation().equals(newResume.getLocation()) &&
                    this.getDescription().equals(newResume.getDescription()) &&
                    this.getDate().equals(newResume.getDate()) &&
                    this.getEnd().equals(newResume.getEnd()) &&
                    this.getId() == newResume.getId();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO resume (title, company, location, description, start, enddate) VALUES (:title, :company, :location, :description, :start, :enddate)";
           this.id = (int) con.createQuery(sql, true)
                    .addParameter("title", this.title)
                    .addParameter("company", this.company)
                    .addParameter("location", this.location)
                    .addParameter("description", this.description)
                    .addParameter("start", this.start)
                    .addParameter("enddate", this.enddate)
                    .executeUpdate()
                   .getKey();
        }
    }


}