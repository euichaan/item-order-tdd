package com.example.productorderservice.order;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.ProductService;
import com.example.productorderservice.product.ProductSteps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class OrderApiTest extends ApiTest {

	@Test
	void 상품주문() {
		ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
		final CreateOrderRequest request = 상품주문요청_생성();
		// when
		ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(request)
			.when()
			.post("/orders")
			.then()
			.log().all()
			.extract();

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	private CreateOrderRequest 상품주문요청_생성() {
		final Long productId = 1L;
		final int quantity = 2;
		return new CreateOrderRequest(productId, quantity);
	}
}
