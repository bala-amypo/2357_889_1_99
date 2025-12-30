package com.example.demo.config;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DoubleBraceFixerAspect {

    // Intercepts the .save() method on any repository
    @Around("execution(* com.example.demo.repository.*.save(..))")
    public Object sanitizeEntities(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        if (args.length > 0) {
            Object entity = args[0];

            // Check if the object is an Anonymous Inner Class (Double Brace Initialization)
            if (entity != null && entity.getClass().isAnonymousClass()) {
                
                // Fix Vendor objects
                if (entity instanceof Vendor) {
                    Vendor safe = new Vendor();
                    // Copy data from the 'bad' anonymous object to the 'clean' new object
                    BeanUtils.copyProperties(entity, safe); 
                    args[0] = safe; // Swap the argument
                } 
                // Fix DepreciationRule objects
                else if (entity instanceof DepreciationRule) {
                    DepreciationRule safe = new DepreciationRule();
                    BeanUtils.copyProperties(entity, safe);
                    args[0] = safe; // Swap the argument
                }
            }
        }
        
        // Proceed with the (potentially swapped) argument
        return joinPoint.proceed(args);
    }
}