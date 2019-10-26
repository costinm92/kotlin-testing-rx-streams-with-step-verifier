package com.example

import com.example.model.Person
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import reactor.test.StepVerifier

@SpringBootTest
class MonoTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun `mono - expect exact objectmatch`() {
		val person = Person(
			firstName = "Madalin",
			lastName = "Costin"
		).toMono()

		StepVerifier.create(person)
			.expectNext(
				Person(
					firstName = "Madalin",
					lastName = "Costin"
				)
			)
			.verifyComplete()
	}

	@Test
	fun `mono - expect property match`() {
		val person = Person(
			firstName = "Madalin",
			lastName = "Costin"
		).toMono()

		StepVerifier.create(person)
			.expectNextMatches {
				it.firstName == "Madalin"
			}
			.verifyComplete()
	}

	@Test
	fun `mono - expect error class IllegalArgumentException`() {
		val error = Mono.error<Person>(IllegalArgumentException("test"))

		StepVerifier.create(error)
			.expectError(IllegalArgumentException::class.java)
			.verify()
	}

	@Test
	fun `mono - expect error class IllegalArgumentException with message`() {
		val error = Mono.error<Person>(IllegalArgumentException("test"))

		StepVerifier.create(error)
			.expectErrorMatches {
				it is IllegalArgumentException && it.message == "test"
			}
			.verify()
	}
}
