package ExamCreatorCore;

/**
 * The test class contains the information about the test. 
 * This includes the location of the file, the questions, the title and whether it is saved or not
 * 
 * @version 0.1.0
 * @author dphaighton
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test 
{
    private List<Question> questions;
    private String title;
    private File file;
    private boolean saved;
    
    
    /** This constructor should be called if the test is being created and not being read from a file.
     * The test will not be saved
     * @param title the title of the test
     * @param questions the List of questions that the test deals with
     */
    public Test(String title, List<Question> questions)
    {
        this.title = title;
        this.questions = questions;
        saved = false;
    }
    
    
    /**
     * This constructor is to be called when the test is being read from a file
     * The test is considered saved
     * @param file the file that represents the test
     * @throws IOException this occurs if the test does not exist
     */
    public Test(File file) throws IOException
    {
        title=file.getName();
        this.file = file;
        
        File files[] = file.listFiles();
        questions = new ArrayList(files.length);
        
        for(File f : files)
        {
            questions.add(new Question(f));
        }
        saved = true;
    }
    
    /**
     * 
     * @return the title of the test
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * 
     * @return the list of questions that the test contains
     */
    public List<Question> getQuestions()
    {
        return questions;
    }
    
    /**This will add a question to the test
     * 
     * @param question the question to be added
     */
    public void addQuestion(Question question)
    {
        questions.add(question);
    }
    
    /**This will remove and delete the question if it has a file
     * 
     * @param index the index of the question
     */
    public void removeQuestion(int index)
    {
        questions.remove(index).delete();
    }
    
    /** This will save the test as a folder under the parents folder. 
     * It will also save all the questions it has
     * 
     * @param parentFile the parent file
     * @throws IOException This is thrown if the directory does not exist
     */
    public void save(File parentFile) throws IOException
    {
        file = new File(parentFile.toString()+"\\"+title);
        file.mkdir();
        
        for(Question question : questions)
        {
            question.save(file);
        }
        
    }
    
    /**
     * This will delete the test's folder and all the files in it if it exists
     */
    public void delete()
    {
        if(!saved){return;}
        
        for(Question question : questions)
        {
            question.delete();
        }
        
        file.delete();
    }
    
    /**
     * 
     * @return whether or not the test is saved
     */
    public boolean isSaved()
    {
        return saved;
    }
    
}
