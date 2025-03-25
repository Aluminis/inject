# Aluminis Inject

A dependency-injection based framework mod for simplifying creation of minecraft mods. 
To understand how to use this mod, it is expected to have a basic understanding of 
[Object-Oriented Programming](https://en.wikipedia.org/wiki/Object-oriented_programming) and 
[Dependency Injection](https://en.wikipedia.org/wiki/Dependency_injection)



## Why 

Before we can dive into features, let's talk a little bit about why you might want to use this mod... rather
than standard fabric development. A downfall of fabric, there is no automatic injection: Fabric doesn’t "inject" objects into your classes.
You’re responsible for instantiating and registering everything explicitly. Basically, you make many different classes
then instantiate them manually and finally wire it all together. This is fine for small mods, where not much wiring/registration
is required, but when you're working on larger, more complex mods... it can become disorganized and confusing fast.

### 1. Manual Boilerplate
In Fabric, you have to explicitly register every single item, block, event listener, etc. with no abstraction layer. 
This means there is more repetitive code, especially as your mod scales up.

### 2. Scaling Pain
A small Fabric mod feels clean and simple.

A large Fabric mod? Now you’re wrangling dozens or hundreds of manual Registry.register() calls, 
event registrations, etc. Without a central container or automation system (like a DI container), scaling to big 
projects requires building your own abstractions.

## Core Features

### Automatic dependency injection (Autowiring)

Autowiring is a feature that automatically injects dependencies into a class without requiring
explicit configuration or manual wiring. Instead of writing configuration code to specify which implementation 
should be injected, the framework automatically figures it out based on certain rules (in this case, an annotation).

Of course, some configuration may still be needed for non-class based providers, if you want to use them. 
For more information, view the [Google Guice Wiki](https://github.com/google/guice/wiki).

## Getting Started

First, set up the fabric entrypoint named `aluminis-inject:bootstrap` pointing to a class that extends 
`net.aluminis.inject.model.AbstractModule`. Aluminis inject will instantiate this class, and 
use it to autoload your services.

```json
"aluminis-inject:bootstrap": [
  "net.aluminis.inject.test.module.TestModule"
]
```

In your module entrypoint, be sure to implement the `provideModId()` method, to provide your modId. 
This can be injected 

```java
import com.google.inject.Provides;
import net.aluminis.inject.api.annotation.ModId;
import net.aluminis.inject.api.model.AbstractModule;

public class TestModule extends AbstractModule {

    @Provides // Optional (Tells autoloader to make this value a provider)
    @ModId    // Optional (Tells autoloader to make this value available with the annotation @ModId)
    public String provideModId() {
        return "aluminis-inject";
    }

    public TestModule() {
        LoggerFactory.getLogger(MOD_ID).info("TestModuleInit worked!");
    }
}
```

Make your services! Any class with the `@AutoLoad` annotation will be automatically loaded to the mod container.

```java
@AutoLoad()
public class TestService {
    public TestService() {
        
    }
}
```

Services can be injected with other services, or providers too. Autowiring is done automatically! For more information,
view the [Google Guice Wiki](https://github.com/google/guice/wiki)

```java
@AutoLoad()
public class TestService {
    @Inject
    public TestService(@ModId String modId) {
        LoggerFactory.getLogger(modId).info("TestService worked!");
    }
}
```