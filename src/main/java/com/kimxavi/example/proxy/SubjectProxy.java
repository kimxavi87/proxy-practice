package com.kimxavi.example.proxy;

public class SubjectProxy implements Subject {

    Subject subject = new SubjectService();

    @Override
    public void job() {
        doProxyBeforeJob();
        subject.job();
        doProxyAfterJob();
    }

    public void doProxyBeforeJob() {
        System.out.println("before job");
    }

    public void doProxyAfterJob() {
        System.out.println("after job");
    }
}
