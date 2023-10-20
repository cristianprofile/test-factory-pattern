package com.example.testpattern

import examples.factory.Order
import examples.factory.Orders
import examples.factory.Products
import examples.factory.Users
import org.junit.Test
import kotlin.test.assertTrue


class TestJavaUsingNew
{

    @Test
    fun testJavaNewBuilder() {

        val order = Order("reference", "name")
        val order2 = Order(order.reference, "name2")
        val order3 = Order("reference2", order.name)

        assertTrue (order.reference == "reference")
        assertTrue (order2.reference == "reference" &&  order2.name == "name2")
        assertTrue (order3.reference == "reference2" &&  order3.name == "name")

    }
    
}

