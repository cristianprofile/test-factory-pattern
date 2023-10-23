package com.example.testpattern

import examples.factory.Order
import examples.factory.Orders
import examples.factory.Products
import examples.factory.Users
import org.junit.Test
import kotlin.test.assertTrue


class TestLombok
{
    
    @Test
    fun testJavaLombokBuilder() {
        
        val product = Products.createProduct().withName("pepe").build()
        assertTrue (product.name.equals("pepe"))

        val product2= product.
        withReference("reference2").withName("angel").withReference("reference2")

        assertTrue (product.name.equals("pepe"))
        assertTrue (product2.name.equals("angel"))
        assertTrue (product2.reference.equals("reference2"))
    }
    
}

