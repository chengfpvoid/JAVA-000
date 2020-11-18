package com.cheng.configuration;

import com.cheng.bean.Klass;
import com.cheng.bean.School;
import com.cheng.bean.Student;
import com.cheng.properties.StudentProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(value = StudentProperties.class)
@ConditionalOnClass(Student.class)
public class SchoolAutoConfiguration {

    @Resource
    private StudentProperties studentProperties;

    @Bean
    @ConditionalOnMissingBean(Student.class)
    public Student student() {
        Student student = new Student();
        student.setId(studentProperties.getId());
        student.setName(studentProperties.getName());
        return student;
    }

    @Bean
    @ConditionalOnMissingBean(Klass.class)
    public Klass klass(){
        Klass klass = new Klass();
        List<Student> sutdents = new ArrayList<>();
        sutdents.add(student());
        klass.setStudents(sutdents);
        return klass;
    }

    @Bean
    @ConditionalOnMissingBean(School.class)
    public School school(){
        School school = new School();
        List<Klass> classes = new ArrayList<>();
        classes.add(klass());
        school.setClasses(classes);
        return school;
    }


}
