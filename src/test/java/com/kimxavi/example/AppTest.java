package com.kimxavi.example;

import static org.junit.Assert.assertTrue;

import com.kimxavi.example.proxy.Subject;
import com.kimxavi.example.proxy.SubjectProxy;
import com.kimxavi.example.proxy.SubjectService;
import org.junit.Test;
import sun.tools.jconsole.inspector.XObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Subject subject = new SubjectProxy();

    @Test
    public void proxyPattern() {
        subject.job();
    }

    Subject dynamicProxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, new InvocationHandler() {

        Subject subject = new SubjectService();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("dynamic proxy before job");
            System.out.println(proxy.getClass());
            Object invoke = method.invoke(subject, args);
            System.out.println("dynamic proxy after job");
            return invoke;
        }
    });

    @Test
    public void dynamicProxy() {
        dynamicProxySubject.job();
    }
}
