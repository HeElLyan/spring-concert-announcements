package ru.itis.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public class RecommendationTimeLogger {

    @Around("execution(* ru.itis.services.NewsPostService+.recommend(..))")
    private Object log(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Recommendation taken time: " + (end - start));
        return proceed;
    }
}
