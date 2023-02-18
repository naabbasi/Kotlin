package edu.learn.servlet.async;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@WebServlet(urlPatterns = {"/async"},asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; chartset=utf-8");
        resp.setHeader("Cache-Control","private");
        resp.setHeader("Pragma","no-cache");
        PrintWriter writer = resp.getWriter();
        writer.println("Processing your request ....<br/>");
        writer.flush();

        final AsyncContext asyncContext = req.startAsync(req,resp);
        asyncContext.setTimeout(10 * 60 * 1000);

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new AsyncProcessor(asyncContext));

        // attach listener to respond to lifecycle events of this AsyncContext
        asyncContext.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) {
                log("onComplete called");
            }
            public void onTimeout(AsyncEvent event) {
                log("onTimeout called");
            }
            public void onError(AsyncEvent event) {
                log("onError called");
            }
            public void onStartAsync(AsyncEvent event) {
                log("onStartAsync called");
            }
        });
    }
}
