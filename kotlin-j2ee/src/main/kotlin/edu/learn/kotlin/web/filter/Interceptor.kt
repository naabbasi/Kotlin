package edu.learn.kotlin.web.filter

import javax.servlet.*
import javax.servlet.annotation.WebFilter

//@WebFilter(filterName = "Interceptor", urlPatterns = arrayOf("/*"))
@WebFilter("/*")
class Interceptor : Filter {
    override fun destroy() {
        println("Interceptor has been destroy ...")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        println("Hey ...")
        chain?.doFilter(request, response)
    }

    override fun init(p0: FilterConfig?) {
        println("Interceptor has been initialized ...")
    }
}