# 📘 Deep Dive into Spring AOP Example: Full Code Explanation

This guide provides a **line-by-line**, **real-world**, and **conceptual** explanation of your Spring Boot application using **Aspect-Oriented Programming (AOP)**.

---

## ⚙️ Technologies Used
- Spring Boot `2.6.0`
- Spring AOP
- AspectJ (via `aspectjrt` and `aspectjweaver`)
- Java 17
- Gradle Build System

---

## 🔁 Real World Analogy for AOP

Imagine a **security guard** at a bank.

- You don’t change the way bank employees (code) work.
- Instead, the guard **intercepts** them at entry/exit to do security checks.
- This is similar to **aspects** intercepting business logic in code.

AOP = Add behaviors like logging, security, validation **without touching the core logic.**

---

# 1️⃣ Main Class - App.java

```
@SpringBootApplication(scanBasePackages = "com.mavenprojectdemo")
@EnableAspectJAutoProxy
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
```

### 🔍 Explanation:
- `@SpringBootApplication`: Main annotation to start the Spring Boot app.
- `scanBasePackages`: Tells Spring where to scan for components.
- `@EnableAspectJAutoProxy`: Enables AOP support (i.e., allow aspects to be applied).

🧠 Real World: Like turning on "Surveillance Mode" in a building. Now guards (aspects) are active.

---

# 2️⃣ Controller - Api.java

```
@RestController
public class Api {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String userLogin() {
        userService.logIn();
        return "User login endpoint called successfully!";
    }
}
```

### 🔍 Explanation:
- `@RestController`: Defines an HTTP endpoint.
- `@GetMapping("/")`: Maps GET requests to `/` URL.
- `userService.logIn()`: This method call will be intercepted by AOP.

🧠 Real World: A user rings the doorbell (`GET /`). The guard is triggered before and after the user goes in (`logIn()` is called).

---

# 3️⃣ Service - UserService.java (Assumed)

You must have this class (or similar):

```java
@Component
public class UserService {
    public void logIn() {
        System.out.println("User logged in");
    }

    public void logOut() {
        System.out.println("User logged out");
    }
}
```

---

# 4️⃣ Aspect - Logging.java

This is the **core of AOP**.

```
@Aspect
@Component
public class Logging {
```

- `@Aspect`: Declares this class is an Aspect.
- `@Component`: Allows Spring to detect it.

---

### ✅ Advice 1: `@Around`

```java
@Around("execution(public void com.mavenprojectdemo.service.UserService.logIn())")
public void loggingAdvice3() {
    System.out.println("Before and After invoking method logIn");
}
```

🔍 `@Around`: Runs before *and* after `logIn()` method.

🧠 Real World: Security cam recording both entry and exit.

---

### ✅ Advice 2: `@AfterThrowing`

```java
@AfterThrowing("execution(public void com.mavenprojectdemo.service.UserService.logOut())")
public void loggingAdvice4() {
    System.out.println("Exception thrown in logOut method");
}
```

🔍 `@AfterThrowing`: Only runs if `logOut()` throws an exception.

🧠 Real World: Security guard logs incident *only if* a fight breaks out.

---

### ✅ Advice 3: `@AfterReturning`

```java
@AfterReturning("execution(public void com.mavenprojectdemo.service.UserService.logOut())")
public void loggingAdvice5() {
    System.out.println("AfterReturning advice for logOut is run");
}
```

🔍 `@AfterReturning`: Runs **only if method completes successfully**.

🧠 Real World: Logging an event *only if everything went well.*

---

### ✅ Advice 4: `@Before` + `@Pointcut`

```java
@Pointcut("execution(public * com.mavenprojectdemo.service.UserService.*(..))")
public void pointCut() {}
@Pointcut("execution(public * com.mavenprojectdemo.service.UserService.*(..))")
public void pointCut1() {}

@Before("pointCut() || pointCut1()")
public void loggingAdvice6() {
    System.out.println("Before advice using pointcut is executed");
}
```

🔍 `@Pointcut`: Reusable method matchers.
🔍 `@Before`: Runs before *any* method in `UserService`.

🧠 Real World: Guard checks ID of *anyone* entering any room.

---

# 🏗 Gradle Build

### `build.gradle` includes all required dependencies:

```groovy
implementation 'org.springframework.boot:spring-boot-starter-aop'
implementation 'org.aspectj:aspectjweaver:1.9.7'
```

🧠 Without these, the "guard" (aspect) has no uniform and tools.

---

# ✅ Execution Flow Summary

1. Spring Boot starts the app.
2. `Api.java` maps `GET /` to `userService.logIn()`.
3. Aspect class `Logging.java` intercepts:
   - `@Before` logs entry.
   - `@Around` wraps before/after logic.
4. `logOut()` method (if called) is monitored for:
   - Success: `@AfterReturning`
   - Failure: `@AfterThrowing`

---

# 🧪 Try It Out

1. Call `/` endpoint → should see multiple logs from AOP advices.
2. Modify `logOut()` to throw exception → watch `@AfterThrowing` trigger.

---

# ✅ Conclusion

Spring AOP is a **powerful way to separate concerns**:
- No need to add logging/validation in every service class.
- Just declare it once, and apply it through aspects.

🧠 Think of it as “plugins” or “interceptors” around your business code.

---

@Atharva Kumar ,ISE, DSCE.
