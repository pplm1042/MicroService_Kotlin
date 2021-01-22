package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ExampleService : ServiceInterface {
    @Value(value = "\${service.message.text}")
    private lateinit var text: String
    override fun getHello(name : String) = "$text $name"


//    // 이 값은 7이어야 한다.
//    @Value(value = "#{4+3}")
//    private lateinit var result1 : Number
//
//    // 이 값은 one.value 나누기 another.value이어야 한다.
//    @Value(value = "{\${one.value} div \${another.value}}")
//    private lateinit var result2 : Number
//
//    // 이 값은 one.value와 another.value가 같아야 한다.
//    @Value(value = "{\${one.value} eq \${another.value}}")
//    private lateinit var result3 : Comparable<Boolean>
//
//    // 이 값은 one.value와 another.value이어야 한다.
//    @Value(value = "#{\${one.value} and \${another.value}}")
//    private lateinit var result4 : Comparable<Boolean>
//
//    // 이 값은 변수가 설정에 없으면 값이 hello로 설정된다.
//    @Value(value = "\${service.message.simple:hello}")
//    private lateinit var result5 : String
//
//    // some.value가 영어 또는 숫자이면 true로 설정한다.
//    @Value("#{ '\${some.value}' matches '[a-zA-Z\\s]+' }")
//    private lateinit var result6 : Comparable<Boolean>

}