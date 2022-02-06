package ru.geraskindenis.annotation;

@Service(name = "NameOfLazyService")
public class LazyService {

    public LazyService() {
        System.out.println("LazyService: Constructor()");
    }

    @Init
    public void lazyInit() throws Exception {
        System.out.println("Method: lazyInit()");
    }
}
