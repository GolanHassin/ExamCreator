/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamCreatorCore;

/**
 *
 * @author David
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Test 
{
    private ArrayList<Question> questions;
    private String title;
    private File file;
    private boolean saved;
    
    public Test(String title, ArrayList<Question> questions)
    {
        this.title = title;
        this.questions = questions;
        saved = false;
    }
    
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
    
    
    public String getTitle()
    {
        return title;
    }
    
    public ArrayList<Question> getQuestions()
    {
        return questions;
    }
    
    public void addQuestion(Question question) throws FileNotFoundException
    {
        question.save(file);
    }
    
    public void removeQuestion(int index)
    {
        questions.remove(index).delete();
    }
    
    
    public void save(File parentFile) throws IOException
    {
        file = new File(parentFile.toString()+"\\"+title);
        file.mkdir();
        
        for(Question question : questions)
        {
            question.save(file);
        }
        
    }
    
    public void delete()
    {
        if(!saved){return;}
        
        for(Question question : questions)
        {
            question.delete();
        }
        
        file.delete();
    }
    
    public boolean isSaved()
    {
        return saved;
    }
    
}
