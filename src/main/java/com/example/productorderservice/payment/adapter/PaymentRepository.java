package com.example.productorderservice.payment.adapter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productorderservice.payment.domain.Payment;

interface PaymentRepository extends JpaRepository<Payment, Long> {
}
