package com.example.productorderservice.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

interface PaymentRepository extends JpaRepository<Payment, Long> {
}
