# Easy Injector

![Java 8 CI](https://github.com/neutron-pro/easy-java-injector/actions/workflows/test.yml/badge.svg?job=Build+%26+Test+Java+8)
![Java 11 CI](https://github.com/neutron-pro/easy-java-injector/actions/workflows/test.yml/badge.svg?job=Build+%26+Test+Java+11)
![Java 17 CI](https://github.com/neutron-pro/easy-java-injector/actions/workflows/test.yml/badge.svg?job=Build+%26+Test+Java+17)
![Java 21 CI](https://github.com/neutron-pro/easy-java-injector/actions/workflows/test.yml/badge.svg?job=Build+%26+Test+Java+21)
![Java 23 CI](https://github.com/neutron-pro/easy-java-injector/actions/workflows/test.yml/badge.svg?job=Build+%26+Test+Java+23)
![Java 24 CI](https://github.com/neutron-pro/easy-java-injector/actions/workflows/test.yml/badge.svg?job=Build+%26+Test+Java+24)

A lightweight and extensible **Dependency Injection (DI)** framework for Java.  
Designed to be simple, modular, and flexible, it supports **scanning, adapters, scopes, and filtering** to fit various environments (Bukkit plugins, microservices, standalone apps, etc.).

---

## ✨ Features

- **Automatic Scanning**  
  Detect classes and providers from your packages automatically.

- **Scopes**  
  Built-in `@Singleton` and `@Prototype` scopes with extensibility for custom scopes.

- **Adapters**  
  Perform actions automatically when classes or instances are injected.

- **Filters**  
  Control what gets scanned depending on your environment (e.g., Development vs Production).

- **Manual Providers**  
  Register your own instances even without annotations.

- **Modules**  
  Keep your project modular and inject dependencies across multiple external modules.

---

## 🔧 Installation

Add **EasyInjector** in your project with **Maven** or **Gradle** :

<details>
<summary>Maven</summary>

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.Neutron-Pro</groupId>
    <artifactId>easy-java-injector</artifactId>
    <version>1.0.0</version>
</dependency>
```

</details>

<details>
<summary>Gradle</summary>

```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.Neutron-Pro:easy-java-injector:1.0.0"
}
```

</details>

---

## 📝 Example

### 1. Create an Injectable Service

```java
@Inject("root")
@Provider(EntityService.class)
@Singleton
public class ImplEntityService implements EntityService {
    private final Map<UUID, Entity> entityMap = new HashMap<>();
    
    @Override
    public void register(Entity entity) {
        this.entityMap.put(entity.id(), entity);
    }
}
```

### 2. Create an Adapter

```java
public class EntityAdapter implements InstanceAdapter<Entity> {
    private final EntityService entityService;

    public EntityAdapter(EntityService entityService) {
        this.entityService = entityService;
    }

    @Override
    public Class<Entity> type() {
        return Entity.class;
    }

    @Override
    public void adapt(Entity entity) {
        this.entityService.register(entity);
    }
}
```

### 3. Create a Prototype

```java
@Inject("root")
@Prototype
public class TestEntity implements Entity { }
```

### 4. Create the InjectorService and a new Injector

```java
final EasyInjectorService easyInjectorService = SimpleEasyInjectorService.createDefault();

final Injector injector = easyInjectorService.injectors()
    .create(easyInjectorService.configurations().of("root", Singleton.class));

injector.scanner().scan(EntityService.class.getClassLoader(), "com.example.package");

injector.adapters().add(injector.create(EntityAdapter.class));

injector.scanner()
    .find("com.example.package.entity")
    .inject();

final EntityService entityService = injector.providers().of(EntityService.class).of();
```

---

## 🎛 Filters Example

The filters must be registered before scanning!

```java
injector.scanner()
    .filters()
    .register(clazz -> !DebugOnly.class.isAssignableFrom(clazz));
```

👉 Here, all classes annotated with `DebugOnly` will be ignored by the scan.

---

## 🧩 Scan & Modules

* Native support for modular projects: each module can declare its own injectable classes, and the injector will automatically resolve them via the scanner.

The scans should only be performed once when adding your project's class loaders and modules to the injector.
This will create providers for all classes that have the correct @Inject annotation (with the same name as your injector).
If no scan has been performed, no classes can be injected.

```java
injector.scanner().scan(module.classloader(), module.packageRoot());
```

If your module has multiple root packages, simply run the scan once per package. It is not impossible to rerun the analysis for each package.

```java
for (final String packageRoot : modules.packages()) {
    injector.scanner().scan(module.classLoader(), packageRoot);
}
```

---

## 📖 Roadmap

* Complete unit tests.

---

## 🤝 Contribuer

Issues and PRs are welcome!

---

## 📜 License

This project is distributed under the **MIT** license.

