package com.example.testpattern

import examples.factory.Order
import examples.factory.Orders
import examples.factory.Products
import examples.factory.Users
import org.junit.Test
import kotlin.test.assertTrue


class TestKotlin
{

    @Test
    fun testKotlinBuilder() {
        val user1 = Users.build()
        val user2 = Users.build(name = "cristian")
        val user3 = Users.build(name = "jose", surname = "gomez")
        val userWithNewId = user3.copy(id = "33")
        
        assertTrue (user1.name != null && user1.surname!=null && user1.id!=null )
        assertTrue (user2.name == "cristian")
        assertTrue (user3.name == "jose" && user3.surname == "gomez")
        assertTrue (userWithNewId.id == "33" && user3.name == "jose" && user3.surname == "gomez")

    }
}

