package com.splashbi.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReExecuteFailedCase implements IRetryAnalyzer {

    public static int retryCount=0;
    private int count = 0;
    private  int maxTry = 0; //Run the failed test 2 times
    Logger log= Logger.getLogger(ReExecuteFailedCase.class);

    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count < maxTry) { // Check if maxtry count is reached
                log.debug("Retrying " + iTestResult.getMethod().getMethodName() + " again and the count is " + (count + 1));
                count++;
                retryCount++;
                iTestResult.setAttribute("retried",true);
                iTestResult.setStatus(ITestResult.FAILURE);
                log.debug("Retry count for " + iTestResult.getMethod().getMethodName() + ":" + count);
                return true;
            }
        }
        else{
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        log.debug("Total Retry count :" + retryCount);
        return false;
    }
    public int getRetryCount(){
        return retryCount;
    }

}
