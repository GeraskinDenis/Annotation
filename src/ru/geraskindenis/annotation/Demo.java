package ru.geraskindenis.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static Map<String, Object> servicesMap = new HashMap<>();

    public static void main(String[] args) {

        /**
         * У объекта-класс получаем массив методов,
         * далее каждый метод проверяем на наличие аннотации Init.
         * Если метод одержит аннотацию - выводим сообщение.
         */
        inspectService(SimpleService.class);
        inspectService(LazyService.class);
        inspectService(String.class);

        /**
         * По имени класса (строка) находим класс,
         * далее у класса получаем конструктор,
         * далее с помощью конструктора создаём instance
         * и помещаем его в Map.
         */
        loadService("ru.geraskindenis.annotation.SimpleService");
        loadService("ru.geraskindenis.annotation.LazyService");
        loadService("java.lang.String");
        System.out.println(servicesMap);


    }

    static void inspectService(Class<?> service) {
        System.out.println("*** " + service + " ***");
        Method[] methods = service.getMethods();
        for (Method method : methods) {
            Annotation annotation = method.getAnnotation(Init.class);
            if (annotation != null) {
                System.out.println("This class has annotation 'Init'");
                break;
            }
        }
//
//        if (service.isAnnotationPresent(Service.class)) {
//            Service ann = service.getAnnotation(Service.class);
//            System.out.println(ann.name());
//        } else {
//            System.out.println("Annotation not found: " + service.getName());
//        }
    }

    static void loadService(String className) {

        // По названию класса получаем объект класса класс
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found by name: " + className);
            e.printStackTrace();
            return;
        }

        // Найденый класс проверяем на наличие аннотации Service
        if (!clazz.isAnnotationPresent(Service.class)) {
            return;
        }

        // Получаем конструктор класса
        Constructor<?> constructor = null;
        try {
            constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor getting error:");
            e.printStackTrace();
            return;
        }

        // C помощью конструктора создаём новый instance
        Object o;
        try {
            o = constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return;
        }

        servicesMap.put(clazz.getName(), o);
    }
}
