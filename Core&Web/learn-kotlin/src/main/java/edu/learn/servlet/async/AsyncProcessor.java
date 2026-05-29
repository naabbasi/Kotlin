package edu.learn.servlet.async;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;

public class AsyncProcessor implements Runnable {
    private AsyncContext asyncContext;
    private PrintWriter out;

    public AsyncProcessor(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    public void run() {
        try {
            out = asyncContext.getResponse().getWriter();
            for(int i = 0 ; i < 4; i++){
                Thread.sleep(2000);
                out.println("Processing item number " + i + "<br/>");
                out.flush();
            }
            out.close();
            asyncContext.complete();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
