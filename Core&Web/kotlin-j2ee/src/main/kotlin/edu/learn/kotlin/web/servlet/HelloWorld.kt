package edu.learn.kotlin.web.servlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "HelloWorld", urlPatterns = ["/HelloWorld"])
class HelloWorld : HttpServlet() {
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp?.writer?.println("Hello World by Kotlin !!!")
    }

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        doPost(req,resp)
    }
}