package ExamCreatorCore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.script.*;

/**
 * The question class contains the questions data: the title, the questions text, value, and the answer
 * It also contains the file if the question is saved
 * 
 * 
 *@version 0.1.0
 * @author dphaighton
 */


public class Question 
{
    //the variables to look for in the javascript file
    public static final String VALUE_KEY="value",TEXT_KEY="text",ANSWER_KEY="answer";
    
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine scriptEngine = manager.getEngineByName("javascript");
    
    private String title, questionText, answer;
    private File file;
    private String script;
    float value;
    boolean ready=false;
    boolean saved;
    /**
     * This is to be called when making a new question
     * 
     * @param script the script text to be run that defines the fields of the question
     * @param title the title of the Question
     */
    public Question(String script, String title)
    {
        saved=false;
        this.script = script;
        
        this.title = title;
        
    }
    
    /**
     * This is to be called when the file is being read from a file
     * 
     * @param file The javaScript file where the question is defined
     * @throws java.io.FileNotFoundException this happens when the file cannot be found
     * 
     */
    public Question(File file) throws FileNotFoundException, IOException
    {
        saved=true;
        title = file.getName().substring(0, file.getName().length()-3);
        this.file = file;
        
        BufferedReader input = new BufferedReader(new FileReader(file));
        script="";
        String line;
        while((line=input.readLine())!=null){script+=line;}
        
        input.close();
    }
    
    /**This saves the Question as a javaScript file in the specified folder
     * 
     * 
     * @param parentFile the parentFile where the javaScript file should be saved in
     * @throws FileNotFoundException  if the file is not found, the exception is thrown
     */
    public void save(File parentFile) throws FileNotFoundException
    {
        file = new File(parentFile.toString()+"\\"+title+".js");
        PrintWriter writer = new PrintWriter(file);
        writer.print(script);
        writer.close();
        saved=true;
    }
    
    
    /**
     * if the file has been saved or read of disk, the file will be deleted
     */
    public void delete()
    {
        if(saved)
            file.delete();
    }
    
    /**This will return null if update hasn't been called
     * 
     * @return the title of the question
     */
    public String getTitle()
    {
        return title;
    }
    
    /** This will return null if update hasn't been called
     * 
     * @return the question text
     */
    public String getQuestionText()
    {
        return questionText;
    }
    
    
    /** This will return null if update hasn't been called
     * 
     * @return the answer to the question
     */
    public String getAnswer()
    {
        return answer;
    }
    
    /**
     * This will compare answers
     * @param answer the answer entered
     * @return true if the answers are equal
     */
    public boolean isCorrect(String answer)
    {
        return answer.equals(answer);
    }
    
    /**
     * This returns 0 if update hasn't been called
     * @return the value of the question/how many points its worth
     */
    public float getValue()
    {
        return value;
    }
    
    /**
     * This will give the answer, value, and text their values by running the javaScript script that defines the function.
     * @throws ScriptException 
     */
    public void update() throws ScriptException
    {
        scriptEngine.eval(script);
        
        value=Float.parseFloat(scriptEngine.get(VALUE_KEY).toString());
        questionText = scriptEngine.get(TEXT_KEY).toString();
        answer = scriptEngine.get(ANSWER_KEY).toString();
        
        
        ready=true;
    }
    
    /**
     * the method will return null if the file is yet to be saved
     * @return the file that defines the fields of the class
     */
    public File getFile()
    {
        return file;
    }
    
    /**
     * 
     * @return returns true if the question is stored on the computer
     */
    public boolean isSaved()
    {
        return saved;
    }
    
}
