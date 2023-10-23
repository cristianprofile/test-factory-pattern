# Test factory pattern

*Reduce redundancy and enhancing clarity in test data generation.*

Imagine a world without repetitive code and with software tests that are clear, readable, and expressive. In this article we will explain a technique that brings us one step closer to that ideal.

As we delve into test writing we encounter a recurring problem that prevents us from easily creating the necessary test data to cover all possible scenarios within our code.

To address this, we will introduce a straightforward way to eliminate duplication in object construction, allowing us to write more understandable and effective tests without filling our test code with details irrelevant to the behavior we are testing.

In this article, we will use Kotlin for the example code, writing all class definitions in Java except for the last one to showcase Kotlin's more powerful solution.


## Test Cases using "new"
    


Imagine we have a class Order with the following structure in our code:


github.com/cristianprofile/test-factory-pattern/blob/main/src/main/java/examples/factory/Order.java#L1-L12

github.com/cristianprofile/test-factory-pattern/blob/main/src/main/java/examples/factory/Order.java#L1-L12



Within our test, we need to create three different orders:

- **order**: with reference "reference" and name "name."
- **order2**: taking the reference from order, which is "reference," and having a different name, which is "name2."
- **order3**: with a different reference, "reference2," and sharing the name with order, which is "name.


github.com/cristianprofile/test-factory-pattern/blob/main/src/test/kotlin/TestJavaUsingNew.kt?plain=1


This test demonstrates the creation of Order objects with different values. As you can see the test includes many unnecessary details related to object creation. If we introduce a new field or make changes to the constructor, we would have to modify many lines of code in the test, making it tightly coupled to how objects are created. This can lead to writing fragile tests that are not resilient to changes within our application.

## Improving Object Creation with Builders

The Builder pattern is a widely used design pattern in object-oriented programming designed to provide a flexible solution to challenges related to creating complex objects. Its main goal is to separate the process of building a complex object from its representation.

In scenarios where classes require detailed and complicated setup, implementing a test data builder becomes common practice. This builder defines and sets values for each constructor parameter of the class simplifying the process of creating and configuring complex objects in test environments.


github.com/cristianprofile/test-factory-pattern/blob/main/src/main/java/examples/factory/Orders.java?plain=1


As we can see, we have created a "withxxx" method for each attribute that we want to modify, assigning default values created with the Instancio library in Java.

Instancio is a library used to simplify object creation in unit tests and for generating test data. It facilitates the creation of complex objects, such as those used in tests, by providing a more concise and readable approach to configure attribute values for those objects.

Instead of manually creating object instances and explicitly defining each of their attributes Instancio allows us to define an object of the desired class with its initial values in a simpler way.


github.com/cristianprofile/test-factory-pattern/blob/main/src/test/kotlin/TestJavaBuilder.kt?plain=1

Main advantages of this new approach using builders are:

- The test code focuses only on attributes relevant to the test case.
It allows assigning values only to the necessary attributes, improving code clarity.
- Flexibility increases when creating objects with different attribute configurations.
- Test code fragility decreases, as changes in object construction require adjustments only in the Builder, not in every place where the object is created.
Test code becomes more readable and expressive, as each method in the Builder is responsible for assigning a specific value to an attribute making it easier to understand.

However, there is one aspect of the code that we can improve: the manual creation of "with" methods for each attribute we want to modify within the created builder.

## Decrease Duplicate Code with Lombok

Although the implemented pattern for test data generation provides many benefits, it also has one major drawback: developers end up writing a lot of redundant code.

To address this issue of redundant code we can take advantage of Lombok. We can eliminate the default constructor getter methods and automatically create a class to generate "with" methods by annotating the class with Lombok's @With, @NoArgsConstructor, and @AllArgsConstructor annotations.

Here is an example of what a new Product class would look like using the builder pattern with Lombok's support.


github.com/cristianprofile/test-factory-pattern/blob/main/src/main/java/examples/factory/Product.java?plain=1


github.com/cristianprofile/test-factory-pattern/blob/main/src/main/java/examples/factory/Products.java?plain=1



Products class is annotated with Lombok's NoArgsConstructor, AllArgsConstructor, and With annotations, which help automatically generate no-argument constructors, all-argument constructors, and methods for cloning objects with modified fields, similar to the "with" methods created earlier but without having to write the repetitive code manually.

Bonus track: Product class is annotated with Lombok's @with that allows to copy immutable objects created using new record java classes

Now let's see an example of a test created for this class.


github.com/cristianprofile/test-factory-pattern/blob/main/src/test/kotlin/TestLombok.kt?plain=1

## Decrease Code using Kotlin

For those who are not familiar with Kotlin it can be summarized as the Java of the year 2050. Created by JetBrains to write applications that run within the JVM with the power of more functional and less verbose languages than Java but without the learning complexity of languages like Scala or similar. At Profile we use it as a JDK-first choice when writing applications in our backend created with Spring. Let's look at the object construction pattern in Kotlin.

For this, we create a User class with a data class (analogous to Java's Record).

github.com/cristianprofile/test-factory-pattern/blob/main/src/main/kotlin/examples/factory/User.kt?plain=1


We create the factory for the User object (taking advantage of Kotlin's ability to manage default values within classes/functions to provide our builder with the values generated by Instancio).

github.com/cristianprofile/test-factory-pattern/blob/main/src/main/kotlin/examples/factory/Users.kt?plain=1


Using our class factory we take advantage of another Kotlin feature that allows us to pass values to functions by their attribute name only passing only those attributes that need to be overridden by those generated by Instancio.(named arguments/named parameters)

The object construction by name in Kotlin is based on the use of named arguments. When you define a function in Kotlin, you can assign a name to each function parameter. Then when calling the function you can provide the arguments in any order using the name of the corresponding parameter.


In this case we call the "build" function, overriding the value of the "name" attribute defined by Instancio and assigning the value "cristian."

    val user2 = Users.build(name = "cristian")


In this other case we call the "build" function, overriding the "name" attribute with "jose" and the "surname" attribute defined by Instancio with "gomez."

    val user3 = Users.build(name = "jose", surname = "gomez")


For this case, we use the "copy" method of Kotlin's data class, which allows us to copy the entire object and change the properties that we define within it (if only Java Records had this feature). In this case we modify the "id" attribute associated with the user3 created with the value "33."

    val userWithNewId = user3.copy(id = "33")

In this code repository we've explored various object creation patterns for testing  making it more effective and expressive. We've demonstrated how these patterns can be applied in Kotlin, a language equipped with powerful tools for minimizing code and improving readability. As you navigate through your development projects we encourage you to consider adopting these patterns to simplify your tests and, lastly , enhance your software's quality. Thank you for accompanying us on this journey and feel free to experiment with these techniques in your own projects.

github.com/cristianprofile/test-factory-pattern/blob/main/src/test/kotlin/TestKotlin.kt?plain=1

