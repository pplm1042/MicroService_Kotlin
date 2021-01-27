package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {
    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping(value = ["/customer/{id}"])
        fun getCustomer(@PathVariable id : Int) : ResponseEntity<Any> {
            val customer = customerService.getCustomer(id)
            return if (customer != null)
                ResponseEntity(customer, HttpStatus.OK)
            else
                ResponseEntity(ErrorResponse("Customer Not Found", "customer '$id' not found"), HttpStatus.NOT_FOUND)
    }

    @PostMapping(value = ["/customer/"])
    fun createCustomer(@RequestBody customer: Customer) : ResponseEntity<Unit?> {
        customerService.createCustomer(customer)
        return ResponseEntity<Unit?>(null, HttpStatus.CREATED)
    }

    @DeleteMapping(value = ["/customer/{id}"])
    fun deleteCustomer(@PathVariable id: Int) : ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping(value = ["/customer/{id}"])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer : Customer) :
    ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping(value = ["/customers"])
    fun getCustomer(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customerService.searchCustomers(nameFilter)
}

//    @RequestMapping(value = ["customers"], method = arrayOf(RequestMethod.GET))
//    fun getCustomers() = customers.map(Map.Entry<Int, Customer>::value).toList()

//    @RequestMapping(value = ["/customer/{id}"], method = arrayOf(RequestMethod.GET))
//    fun getCustomer(@PathVariable id: Int) = customers[id]

//    @RequestMapping(value = ["/customer/"], method = arrayOf(RequestMethod.POST))
//    fun createCustomer(@RequestBody customer: Customer) {
//        customers[customer.id] = customer
//    }

//    @RequestMapping(value = ["/customer/{id}"], method = arrayOf(RequestMethod.DELETE))
//    fun deleteCustomer(@PathVariable id: Int) = customers.remove(id)

//    @RequestMapping(value = ["/customer/{id}"], method = arrayOf(RequestMethod.PUT))
//    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer)
//    {
//        customers.remove(id)
//        customers[customer.id] = customer
//    }


//    fun getCustomers(@RequestParam(required = false, defaultValue = "")
//    nameFilter: String) =
//            customers.filter {
//                it.value.name.contains(nameFilter, true)
//            }.map(Map.Entry<Int, Customer>::value).toList()
//}