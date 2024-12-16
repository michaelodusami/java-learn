### Understanding the `@Retention` Annotation in Java

The `@Retention` annotation in Java is part of the `java.lang.annotation` package and is used to specify how long annotations with the annotated type are to be **retained**. Retention policies define whether an annotation is available only in the source code, in the compiled `.class` files, or at runtime during the program's execution.

---

### Key Points

1. **Purpose**:
   The `@Retention` annotation specifies the **retention policy** for an annotation. This determines the lifecycle of an annotation and where it can be accessed.

2. **Retention Policies**:
   The retention policy is specified using the `java.lang.annotation.RetentionPolicy` enum, which has three values:
   - **`SOURCE`**: 
     - The annotation is only available in the source code.
     - It is discarded by the compiler and does not appear in the `.class` files.
     - Use case: Documenting code or providing hints to the developer.
     - Example: `@Override`.

   - **`CLASS`**:
     - The annotation is present in the compiled `.class` files but is not available at runtime.
     - This is the default retention policy if no `@Retention` is specified.
     - Use case: Annotations processed by tools or frameworks during build time.

   - **`RUNTIME`**:
     - The annotation is present in the compiled `.class` files and is available at runtime through reflection.
     - Use case: Annotations used by frameworks, libraries, or tools at runtime for behavior modification (e.g., dependency injection or ORM frameworks like Hibernate).
     - Example: `@Deprecated`, `@SuppressWarnings`.

3. **Declaration**:
   `@Retention` is itself an annotation that can be applied to other annotation definitions. It uses the `RetentionPolicy` value as its argument.

---

### Syntax

Here's the basic syntax for using `@Retention`:

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // Specify the retention policy
public @interface MyAnnotation {
    String value();
}
```

---

### Retention Policies in Detail

#### 1. **SOURCE** (Annotations discarded after compilation)
Annotations with `RetentionPolicy.SOURCE` are purely for developer use during coding and are not included in the compiled `.class` files.

Example:
```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@interface ToDo {
    String task();
}

public class Example {
    @ToDo(task = "Refactor this method")
    public void myMethod() {
        // This annotation is visible to developers but won't exist in the bytecode.
    }
}
```

- The `@ToDo` annotation is used for developer notes and does not affect the program's runtime.

---

#### 2. **CLASS** (Annotations retained in the `.class` file but not at runtime)
Annotations with `RetentionPolicy.CLASS` are included in the compiled bytecode but are not loaded into the JVM at runtime. This is the default policy.

Example:
```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@interface Metadata {
    String info();
}

public class Example {
    @Metadata(info = "Class-Level Metadata")
    public void myMethod() {
        // The annotation is stored in the .class file but not available via reflection.
    }
}
```

- Use cases include tools that work with the compiled classes, such as lint tools or compilers.

---

#### 3. **RUNTIME** (Annotations available at runtime)
Annotations with `RetentionPolicy.RUNTIME` are retained in the `.class` files and are available through reflection at runtime. This policy is used for annotations that affect the program's behavior at runtime.

Example:
```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface Info {
    String author();
    int version();
}

public class Example {
    @Info(author = "John Doe", version = 1)
    public void myMethod() {
        System.out.println("Method logic here");
    }

    public static void main(String[] args) throws Exception {
        Method method = Example.class.getMethod("myMethod");
        Info info = method.getAnnotation(Info.class);

        if (info != null) {
            System.out.println("Author: " + info.author());
            System.out.println("Version: " + info.version());
        }
    }
}
```

**Output**:
```
Author: John Doe
Version: 1
```

- In this example, the `@Info` annotation is accessed using reflection, and its values are printed at runtime.

---

### Practical Use Cases

1. **Runtime Frameworks**:
   - Dependency injection frameworks like Spring or Guice rely on `RUNTIME` annotations (e.g., `@Autowired` in Spring).
   - ORM frameworks like Hibernate or JPA use annotations like `@Entity`, `@Table`, and `@Column`.

2. **Code Analysis**:
   - Build tools and compilers use `CLASS` retention annotations to check rules or generate additional code during compilation.

3. **Developer Notes**:
   - `SOURCE` retention annotations help document code or provide hints to other developers (e.g., `@Override`, `@SuppressWarnings`).

---

### Summary Table

| Retention Policy | Stored in `.class` File? | Available at Runtime? | Use Case                                   |
|-------------------|--------------------------|------------------------|--------------------------------------------|
| `SOURCE`          | No                       | No                     | Developer notes, documentation             |
| `CLASS`           | Yes                      | No                     | Build tools, bytecode manipulation         |
| `RUNTIME`         | Yes                      | Yes                    | Reflection, runtime frameworks, behaviors  |

---

### Best Practices
- Choose the **right retention policy** based on your use case:
  - Use `SOURCE` for annotations that are strictly for code readability or static analysis.
  - Use `CLASS` for annotations that are processed during build or compile time.
  - Use `RUNTIME` for annotations that affect runtime behavior or require reflection.
- Always document the purpose of custom annotations to ensure clarity for future developers.

Feel free to ask if you want further examples or clarifications!