package edu.escuelaing.arem.servidorweb.load;

import edu.escuelaing.arem.servidorweb.annotations.Web;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class LoadAll {
    private HashMap<String, Method> classMethods;

    public LoadAll(){
        this.classMethods = new HashMap<>();
    }

    public void start(){
        String packageClass = "edu.escuelaing.arem.servidorweb.webServices";
        System.out.println(packageClass);
        Reflections rf = new Reflections(packageClass, new SubTypesScanner(false));

        Object[] objects = rf.getSubTypesOf(Object.class).toArray();

        for (Object o: objects){
            try {
                System.out.println(getClass(o));
                Class cl = Class.forName(getClass(o));
                for (Method m : cl.getDeclaredMethods()){
                    if (m.isAnnotationPresent(Web.class)){
                        classMethods.put(m.getAnnotation(Web.class).value(), m);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAResource (String resource){
        return classMethods.containsKey(resource);
    }

    public String loadResource(String input){
        String resource = null;
        try {
            System.out.println(input);
            resource = (String) classMethods.get(input).invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return resource;
    }

    private String getClass(Object o) {
        String[] s = o.toString().split(" ");
        return s[1];
    }

}
