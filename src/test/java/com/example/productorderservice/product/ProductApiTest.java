package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.adapter.ProductRepository;
import com.example.productorderservice.product.application.service.AddProductRequest;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class ProductApiTest extends ApiTest {

	@Autowired
	ProductRepository productRepository;

	@Test
	void 상품등록() {
		final AddProductRequest request = ProductSteps.상품등록요청_생성();

		// API 요청
		final var response = ProductSteps.상품등록요청(request);

		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void 상품조회() {
		ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
		Long productId = 1L;
		// when
		final var response = ProductSteps.상품조회요청(productId);

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
		assertThat(response.jsonPath().getLong("id")).isEqualTo(1);
	}

	@Test
	void 상품수정() {
		ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
		final Long productId = 1L;
		// when
		ExtractableResponse<Response> response = ProductSteps.상품수정요청(productId);

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(productRepository.findById(1L).get().getName()).isEqualTo("상품 수정");
	}
}
