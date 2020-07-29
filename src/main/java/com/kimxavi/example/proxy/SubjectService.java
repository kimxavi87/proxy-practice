package com.kimxavi.example.proxy;

public class SubjectService implements Subject {

    @Override
    public void job() {
        System.out.println("REAL JOB");
    }
}
