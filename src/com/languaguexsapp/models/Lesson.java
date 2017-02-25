package com.languaguexsapp.models;

import java.util.Date;

/**
 * Created by Frank on 25/02/2017.
 */
public class Lesson {
    int id;
    String name;
    Person personStudent;
    Person personTeacher;

    Date dateStart;
    Date dateEnd;
    int studentQualification;
    int teacherQualification;
    State state;

}
