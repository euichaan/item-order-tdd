package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.example.productorderservice.ApiTest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class ProductApiTest extends ApiTest {

	private final ProductSteps productSteps = new ProductSteps();

	@Test
	void 상품등록() {
		final var request = productSteps.상품등록요청_생성();

		// API 요청
		final var response = productSteps.상품등록요청(request);

		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void 상품조회() {
		productSteps.상품등록요청(productSteps.상품등록요청_생성());
		Long productId = 1L;
		// when
		ExtractableResponse<Response> response = RestAssured.given().log().all()
			.when()
			.get("/products/{productId}", productId)
			.then()
			.log().all()
			.extract();

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
	}
}
