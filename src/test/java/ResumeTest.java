
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class ResumeTest{

    @Before
    public void setUp(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/resume", "mbugua", "4545");
    }

    @After
    public void tearDown(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM resume *;";
            con.createQuery(sql).executeUpdate();
        }
    }

//    @Test
//    public void resume_instantiatesCorrectly_true() {
//        Resume resume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
//        assertEquals(true, resume instanceof Resume);
//    }
//
//    @Test
//    public void resume_instantiatesWithTitle_String() {
//        Resume resume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
//        assertEquals("Intern", resume.getTitle());
//    }
//
//    @Test
//    public void resume_instantiatesWithCompany_String() {
//        Resume resume = new Resume("Intern", "Google","Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
//        assertEquals("Google", resume.getCompany());
//    }
//
//    @Test
//    public void resume_instantiatesWithLocation_String() {
//        Resume resume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
//        assertEquals("Kariamburi", resume.getLocation());
//    }
//
//    @Test
//    public void resume_instantiatesWithDescription_String() {
//        Resume resume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
//        assertEquals("Trained on algorithm", resume.getDescription());
//    }
//
//    @Test
//    public void resume_instantiatesWithDate_String() {
//        Resume resume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
//        assertEquals("17th March 2010", resume.getDate());
//    }
//
//     @Test
//    public void resume_instantiatesWithEnd_String() {
//        Resume resume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
//        assertEquals("17th December 2010", resume.getEnd());
//    }

    @Test
    public void equals_returnsTrueIfAllInstancesAreTheSame_true(){
        Resume firstResume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
        Resume secondResume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
        assertTrue(firstResume.equals(secondResume));
    }

    @Test
    public void save_returnsTrueIfInstancesAreTheSame(){
        Resume myResume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
        myResume.save();
        assertTrue(Resume.all().get(0).equals(myResume));
    }

    @Test
    public void all_returnAllInstancesOfResumeAs_True(){
        Resume firstResume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
        firstResume.save();
        Resume secondResume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
        secondResume.save();
        assertEquals(true, Resume.all().get(0).equals(firstResume));
        assertEquals(true, Resume.all().get(1).equals(secondResume));
    }
    @Test
    public void save_assignsIdToObject(){
        Resume resume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
        resume.save();
        Resume savedResume = Resume.all().get(0);
        assertEquals(resume.getId(), savedResume.getId());
    }

    @Test
    public void getId_resumeInstantiatesWithAnId(){
        Resume resume = new Resume("Intern", "Google", "Kariamburi", "Trained on algorithm", "17th March 2010", "17th December 2010");
        resume.save();
        assertTrue(resume.getId()>0);
    }

}
