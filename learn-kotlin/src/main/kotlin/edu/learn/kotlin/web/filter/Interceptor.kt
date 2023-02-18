package edu.learn.kotlin.web.filter

import javax.servlet.*
import javax.servlet.annotation.WebFilter

@WebFilter(filterName = "Interceptor", urlPatterns = ["*"], asyncSupported = true)
class Interceptor : Filter {
    override fun destroy() {
        println("Interceptor has been destroy ...")
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        println("Intercepting request ...")
        chain.doFilter(request, response)
    }

    override fun init(filterConfig: FilterConfig) {
        println("Interceptor has been initialized ...")
    }
}