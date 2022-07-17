package org.example.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {


    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + iTestResult.getName() + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + iTestResult.getName() + " failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + iTestResult.getName() + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed, but it is in defined success ratio " + iTestResult.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }
}
