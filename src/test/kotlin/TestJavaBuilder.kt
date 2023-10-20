package com.example.testpattern

import examples.factory.Orders
import org.junit.Test
import kotlin.test.assertTrue


class TestJavaBuilder {

    @Test
    fun testJavaBuilder() {

        val orderWithReferenceBuilder = Orders.createOrder().withReference("R1")

        val createOrder = orderWithReferenceBuilder.build()
        val createOrder2 = orderWithReferenceBuilder.withName("name").build()

        assertTrue(createOrder.reference == "R1")
        assertTrue(createOrder2.reference == "R1" && createOrder2.name == "name")

    }
}

