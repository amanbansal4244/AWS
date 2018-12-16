package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/****
 * In order 'HelloHandler' class to be consider as Lambda Function, we have to implement the RequestHandler
 * and has to provide Input and Output objects.
 * 
 * Using this any input and output can be serialized.
 * 
 * @author bansal
 *
 */
public class HelloHandler implements RequestHandler<HelloInput, HelloOutput>
{

    public HelloOutput handleRequest(HelloInput i, Context cntxt)
    {
        HelloOutput o = new HelloOutput();
        o.setMessage("Hello " + i.getName());
        o.setFunctionName(cntxt.getFunctionName());
        o.setMemoryLimit(cntxt.getMemoryLimitInMB());
        cntxt.getLogger().log(i.getName() + " said hello");
        return o;
    }
}