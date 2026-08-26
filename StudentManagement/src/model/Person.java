package model;

import validation.PersonValidation;

public class Person {
    private String id;
    private String fullname;
    private int age;

    public Person(String id, String fullname, int age) {
       setId(id);
       setFullname(fullname);
       setAge(age);
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public int getAge() {
        return age;
    }

    public void setId(String id) {
        PersonValidation.validateId(id);
        this.id = id;
    }

    public void setFullname(String fullname) {
        PersonValidation.validateFullName(fullname);
        this.fullname = fullname;
    }

    public void setAge(int age) {
        PersonValidation.validateAge(age);
        this.age = age;
    }

}
