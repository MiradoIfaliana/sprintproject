package framework;

import java.lang.reflect.Method;

public class Mapping {
    String className;
    String methodName;
    Class theclass;
    Method themethod;
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public Class getTheclass() {
        return theclass;
    }
    public void setTheclass(Class theclass) {
        this.theclass = theclass;
    }
    public Method getThemethod() {
        return themethod;
    }
    public void setThemethod(Method themethod) {
        this.themethod = themethod;
    }

    
    
    
}