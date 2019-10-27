# Kotlin testing rx streams with StepVerifier

## Overview
Unit testing is a software testing method by which individual units of source code 
are tested to determine whether they are fit for use.

### What is StepVerifier?
StepVerifier is an instrument for testing reactive streams. It provides a declarative way of creating a 
verifiable script for an async Publisher sequence, by expressing expectations about the events that will 
happen upon subscription. The verification must be triggered after the terminal expectations (completion, 
error, cancellation) have been declared, by calling one of the verify() methods.

In short, StepVerifier is an instrument that the developers can use to tests reactive streams.

### Methods
* Create a StepVerifier for a Publisher: use create(Publisher) or withVirtualTime(Supplier<Publisher>)
* Set up individual value expectations:  use expectNext, expectNextMatches(Predicate), assertNext(Consumer), expectNextCount(long) or expectNextSequence(Iterable).
* Trigger subscription actions during the verification: use thenRequest(long) or thenCancel().
* Finalize the test scenario: use expectComplete(), expectError(), expectError(Class), expectErrorMatches(Predicate), or thenCancel().
* Trigger the verification of the resulting StepVerifier on its Publisher: use either verify() or verify(Duration).
* If any expectations failed, an AssertionError will be thrown indicating the failures.

## Example
In this example, we will look at some of the methods listed above

### Gradle dependency
We need a testCompile dependency in order to be able to use StepVerifier in our tests:
```
testCompile(group = "io.projectreactor", name = "reactor-test", version = "3.2.3.RELEASE")
```

### Model
For this example, we have created a dummy [model class](/src/main/kotlin/com/example/model/Person.kt) that will be used in our tests:
```
data class Person(
    val firstName: String,
    val lastName: String
)
```

### Writing the tests
* [Mono tests](/src/test/kotlin/com/example/MonoTests.kt)

* [Flux tests](/src/test/kotlin/com/example/FluxTests.kt)

## References
* [projectreactor documentation](https://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html)
* [Unit testing 0 Wikipedia](https://en.wikipedia.org/wiki/Unit_testing)
