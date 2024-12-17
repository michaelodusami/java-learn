### **Predefined Annotation Types in Java**

Java provides several built-in annotation types to simplify coding, enhance compiler checks, and control runtime behavior. These are broadly divided into:

1. **Annotations Used by the Compiler**
2. **Meta-Annotations (Annotations for Other Annotations)**

Let’s explore each category step by step:

---

### **1. Annotation Types Used by the Compiler**

These annotations directly impact the compiler's behavior:

#### **@Deprecated**
- Marks an element (method, class, or field) as **deprecated**, signaling that it should not be used.
- **Compiler Action:** Generates a warning when the deprecated element is used.
- **Javadoc Integration:** Use the `@deprecated` tag in Javadoc to explain why it’s deprecated.

**Example:**
```java
/**
 * @deprecated This method is outdated; use newMethod() instead.
 */
@Deprecated
static void deprecatedMethod() { }
```

- **New in Java SE 9:** The `forRemoval` attribute specifies if the element is scheduled for removal.
  ```java
  @Deprecated(forRemoval = true)
  static void soonToBeRemovedMethod() { }
  ```

---

#### **@Override**
- Ensures that a method is correctly overriding a method from its superclass.
- **Compiler Action:** Throws an error if the method is not a valid override.

**Example:**
```java
class Parent {
    void display() { }
}

class Child extends Parent {
    @Override
    void display() { // Correct override
    }
}
```

**Why Use It?**  
Prevents subtle bugs caused by typos or mismatches in method signatures.

---

#### **@SuppressWarnings**
- Suppresses specific compiler warnings (e.g., for deprecated methods or unchecked operations).
- Warnings belong to **categories**, like `unchecked` or `deprecation`.

**Example: Suppressing Warnings**
```java
@SuppressWarnings("deprecation")
void useDeprecatedMethod() {
    deprecatedMethod(); // No warning
}

// Suppress multiple warnings
@SuppressWarnings({"unchecked", "deprecation"})
void someMethod() { ... }
```

---

#### **@SafeVarargs** (Java SE 7+)
- Applied to methods or constructors with **variable arguments (varargs)**.
- Suppresses warnings about **potentially unsafe operations** with varargs.

**Example:**
```java
@SafeVarargs
public final <T> void safeVarargsMethod(T... args) {
    for (T arg : args) {
        System.out.println(arg);
    }
}
```

---

#### **@FunctionalInterface** (Java SE 8+)
- Marks an interface as a **functional interface** (an interface with exactly one abstract method).
- **Compiler Action:** Throws an error if additional abstract methods are added to the interface.

**Example:**
```java
@FunctionalInterface
interface MyFunctionalInterface {
    void execute();
}
```

**Why Use It?**  
Functional interfaces are the foundation of **lambda expressions**.

---

### **2. Meta-Annotations (Annotations for Annotations)**

These annotations control how other annotations behave.

#### **@Retention**
- Specifies how long an annotation should be retained:
  - **SOURCE:** Available only in source code (ignored by compiler).
  - **CLASS:** Retained in `.class` files but not available at runtime.
  - **RUNTIME:** Retained in `.class` files and available during runtime via reflection.

**Example:**
```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation { }
```

---

#### **@Documented**
- Marks an annotation to be included in **Javadoc**.
- By default, annotations are not documented in Javadoc.

**Example:**
```java
import java.lang.annotation.Documented;

@Documented
@interface MyDocumentedAnnotation { }
```

---

#### **@Target**
- Specifies the **Java elements** where an annotation can be applied.
- Common `ElementType` values:
  - **ANNOTATION_TYPE:** For other annotations.
  - **METHOD:** For methods.
  - **FIELD:** For fields.
  - **TYPE:** For classes, interfaces, or enums.

**Example:**
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@interface MethodAnnotation { }
```

---

#### **@Inherited**
- Allows annotations to be **inherited** by subclasses.
- **Default Behavior:** Annotations are **not inherited** unless explicitly marked with `@Inherited`.

**Example:**
```java
import java.lang.annotation.Inherited;

@Inherited
@interface ParentAnnotation { }

@ParentAnnotation
class Parent { }

class Child extends Parent { } // Child inherits ParentAnnotation
```

---

#### **@Repeatable** (Java SE 8+)
- Allows an annotation to be applied **multiple times** to the same element.
- Requires a **container annotation** to hold repeated annotations.

**Example:**
```java
import java.lang.annotation.Repeatable;

// Define the container annotation
@interface Authors {
    Author[] value();
}

@Repeatable(Authors.class)
@interface Author {
    String name();
}

// Use the repeating annotation
@Author(name = "Alice")
@Author(name = "Bob")
class Book { }
```

---

### **Quick Summary Table**

| Annotation        | Purpose                                                    | Key Use Case                                      |
|--------------------|------------------------------------------------------------|--------------------------------------------------|
| `@Deprecated`     | Marks an element as outdated                                | Warn users about using old APIs                 |
| `@Override`       | Verifies proper method overriding                           | Avoid subtle bugs during method overriding       |
| `@SuppressWarnings`| Suppresses compiler warnings                               | Ignore warnings for specific code areas          |
| `@SafeVarargs`    | Suppresses warnings for varargs                             | Safe usage of variable arguments                |
| `@FunctionalInterface`| Marks an interface as a functional interface           | Ensures compatibility with lambda expressions    |
| `@Retention`      | Defines how long annotations are retained                   | Source, compile-time, or runtime retention       |
| `@Documented`     | Includes annotations in Javadoc                             | Document annotations for API consumers          |
| `@Target`         | Specifies valid annotation locations                        | Restrict annotation usage to specific elements   |
| `@Inherited`      | Allows annotations to be inherited by subclasses            | Propagate class-level metadata to child classes |
| `@Repeatable`     | Enables multiple instances of the same annotation           | Simplify repetitive metadata specifications      |

---

With these annotations, you can enforce best practices, reduce boilerplate code, and leverage the power of Java's type system effectively.