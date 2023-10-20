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
    }
    
}

