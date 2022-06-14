package com.estudo.task.api.test;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {

	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost/tasks-backend";
		RestAssured.port = 8001;
	}
	
	@Test
	public void deveRetornarTarefas() {
		RestAssured
			.given()
				.log().all()
			.when()
				.get("/todo")
			.then()
				.statusCode(200)
				.log().all();
	}
	
	@Test
	public void deveAdicionarTarefaComSucesso() {
		RestAssured
			.given()
				.body("{ \"task\": \"Tarefa RestAssured\", \"dueDate\": \"2100-06-10\"}")
				.contentType(ContentType.JSON)
				.log().all()
			.when()
				.post("/todo")
			.then()
				.statusCode(201)
				.log().all();
	}
	
	@Test
	public void naoDeveAdicionarTarefaComDataPassada() {
		RestAssured
			.given()
				.log().all()
				.body("{ \"task\": \"Tarefa RestAssured\", \"dueDate\": \"2010-06-10\"}")
				.contentType(ContentType.JSON)
			.when()
				.post("/todo")
			.then()
				.statusCode(400)
				.log().all();
	}
}
