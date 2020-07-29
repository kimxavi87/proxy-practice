package com.kimxavi.example;

import static org.junit.Assert.assertTrue;

import com.kimxavi.example.proxy.Subject;
import com.kimxavi.example.proxy.SubjectProxy;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Subject subject = new SubjectProxy();

    @Test
    public void proxy() {
        subject.job();
    }
}
