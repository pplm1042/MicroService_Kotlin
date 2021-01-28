package com.microservices.chapter4

import com.microservices.chapter4.Customer.Telephone
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomer = arrayOf(Customer(1, "Kotlin"),
                Customer(2, "Spring"),
                Customer(3, "Microservice", Telephone("+44", "7123456789")))
    }

    val customers = ConcurrentHashMap<Int, Customer>(initialCustomer.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    override fun searchCustomers(nameFilter:String) = customers.filter {
        it.value.name.contains(nameFilter, true)
    }.map(Map.Entry<Int, Customer>::value).toFlux()

    override fun createCustomer(customerMono: Mono<Customer>): Mono<*> =
            customerMono.map {
                customers[it.id] = it
                Mono.empty<Any>()
    }
}
