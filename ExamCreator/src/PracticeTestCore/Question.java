/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeTestCore;

/**
 *@version 0.1.0
 * @author David
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.script.*;

public class Question 
{
    
    
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine scriptEngine = manager.getEngineByName("javascript");
    
    private String title, questionText, answer;
    private File file;
    private String script;
    float value;
    boolean ready=false;
    boolean saved;
    /**
     * 
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
     * 
     * @param file The javaScript file where the question is defined
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
    
    /**
     * 
     * @return the title of the question
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * 
     * @return the question text
     */
    public String getQuestionText()
    {
        return questionText;
    }
    
    
    /**
     * 
     * @return the answer to the question
     */
    public String getAnswer()
    {
        return answer;
    }
    
    public boolean isCorrect(String answer)
    {
        return answer.equalsIgnoreCase(answer);
    }
    
    
    public float getValue()
    {
        return value;
    }
    
    public void update() throws ScriptException
    {
        scriptEngine.eval(script);
        
        value=Float.parseFloat(scriptEngine.get("value").toString());
        questionText = scriptEngine.get("text").toString();
        answer = scriptEngine.get("answer").toString();
        
        
        ready=true;
    }
    
    
    public File getFile()
    {
        return file;
    }
    
    public boolean isSaved()
    {
        return saved;
    }
    
}
