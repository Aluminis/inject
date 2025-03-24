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

### Autoloading Services

## Getting Started