package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
//
    HashMap<String,Student> studentHashMap = new HashMap<>();
    HashMap<String,Teacher> teacherHashMap = new HashMap<>();
    HashMap<String, List<String>> studentTeacherPairHashMap = new HashMap<>();

    public void addStudent(Student student)
    {
        if(!studentHashMap.containsKey(student))
        {
            studentHashMap.put(student.getName(),student);
        }
    }

    public void addTeacher(Teacher teacher)
    {
        if(!teacherHashMap.containsKey(teacher))
        {
            teacherHashMap.put(teacher.getName(), teacher);
        }
    }

    public void addStudentTeacherPair(String student, String teacher)
    {
        List<String> students = new ArrayList<>();
        if(studentTeacherPairHashMap.containsKey(teacher))
        {
            students = studentTeacherPairHashMap.get(teacher);
            students.add(student);
            studentTeacherPairHashMap.put(teacher,students);
        }
        else {
            students.add(student);
            studentTeacherPairHashMap.put(teacher, students);
        }
    }
    public Student getStudentByName(String name)
    {
        if(studentHashMap.containsKey(name))
        {
            return studentHashMap.get(name);
        }
        else
        {
            return null;
        }
    }

    public Teacher getTeacherByName(String name)
    {
        if(teacherHashMap.containsKey(name))
        {
            return teacherHashMap.get(name);
        }
        else
        {
            return null;
        }
    }

    public List<String> getStudentsByTeacherName(String teacher)
    {
        if(studentTeacherPairHashMap.containsKey(teacher))
        {
           return studentTeacherPairHashMap.get(teacher);
        }
        else
        {
            return null;
        }
    }

    public List<String> getAllStudents()
    {
        List<String> studentList = new ArrayList<>();

        for (Map.Entry<String,Student> studentEntry : studentHashMap.entrySet()){
            Student student = studentEntry.getValue();
            studentList.add(student.getName());
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacher)
    {
       if(studentTeacherPairHashMap.containsKey(teacher))
       {
           List<String> studentList = studentTeacherPairHashMap.get(teacher);

           for(String student : studentList)
           {
              studentTeacherPairHashMap.remove(student);
           }
           studentTeacherPairHashMap.remove(teacher);
       }

       if(teacherHashMap.containsKey(teacher))
       {
           teacherHashMap.remove(teacher);
       }
    }

    public void deleteAllTeachers()
    {
        for(String teacher : teacherHashMap.keySet())
        {
            List<String> studentList = studentTeacherPairHashMap.get(teacher);

            for(String student : studentList)
            {
               studentTeacherPairHashMap.remove(student);
            }

            if(studentTeacherPairHashMap.containsKey(teacher))
            {
                studentTeacherPairHashMap.remove(teacher);
            }

            if(teacherHashMap.containsKey(teacher))
            {
                teacherHashMap.remove(teacher);
            }
        }
    }
}
