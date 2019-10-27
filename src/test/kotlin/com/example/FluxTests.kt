package com.example

import com.example.model.Person
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

@SpringBootTest
class FluxTests {

	@Test
	fun `flux - expect exact objectmatch`() {
		val persons = Flux.just(
			Person(
				firstName = "Madalin",
				lastName = "Costin"
			),
			Person(
				firstName = "Random",
				lastName = "Person"
			)
		)

		StepVerifier.create(persons)
			.expectNext(
				Person(
					firstName = "Madalin",
					lastName = "Costin"
				)
			)
			.expectNext(
				Person(
					firstName = "Random",
					lastName = "Person"
				)
			)
			.verifyComplete()
	}

	@Test
	fun `flux - expect property match`() {
		val persons = Flux.just(
			Person(
				firstName = "Madalin",
				lastName = "Costin"
			),
			Person(
				firstName = "Random",
				lastName = "Person"
			)
		)

		StepVerifier.create(persons)
			.expectNextMatches {
				it.firstName == "Madalin"
			}
			.expectNextMatches {
				it.firstName == "Random"
			}
			.verifyComplete()
	}

	@Test
	fun `flux - expect count`() {
		val persons = Flux.just(
			Person(
				firstName = "Madalin",
				lastName = "Costin"
			),
			Person(
				firstName = "Random",
				lastName = "Person"
			)
		)

		StepVerifier.create(persons)
			.expectNextCount(2)
			.verifyComplete()
	}

	@Test
	fun `flux - expect error class IllegalArgumentException`() {
		val error = Flux.error<Person>(IllegalArgumentException("test"))

		StepVerifier.create(error)
			.expectError(IllegalArgumentException::class.java)
			.verify()
	}

	@Test
	fun `flux - expect error class IllegalArgumentException with message`() {
		val error = Flux.error<Person>(IllegalArgumentException("test"))

		StepVerifier.create(error)
			.expectErrorMatches {
				it is IllegalArgumentException && it.message == "test"
			}
			.verify()
	}
}
