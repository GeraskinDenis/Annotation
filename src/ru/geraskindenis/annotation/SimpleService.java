package ru.geraskindenis.annotation;

@Service(name = "NameOfSimpleService")
public class SimpleService {

    public SimpleService() {
        System.out.println("SimpleService: Constructor()");
    }

    @Init
    public void initService() {
        System.out.println("Method: initService()");
    }
}
