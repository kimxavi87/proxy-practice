package com.kimxavi.example;

import static org.junit.Assert.assertTrue;

import com.kimxavi.example.proxy.Subject;
import com.kimxavi.example.proxy.SubjectProxy;
import com.kimxavi.example.proxy.SubjectService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
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
            Object invoke = method.invoke(subject, args);
            System.out.println("dynamic proxy after job");
            return invoke;
        }
    });

    @Test
    public void dynamicProxy() {
        dynamicProxySubject.job();
    }

    @Test
    public void cglibProxy() {
        Subject subject = (Subject) Enhancer.create(Subject.class, new MethodInterceptor() {
            Subject subject = new SubjectService();
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("cglib proxy before job");
                Object invoke = method.invoke(subject, args);
                System.out.println("cglib proxy after job");
                return invoke;
            }
        });

        subject.job();
    }
}
