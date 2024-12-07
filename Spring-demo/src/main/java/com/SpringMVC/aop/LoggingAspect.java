package com.SpringMVC.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(public * com.SpringMVC.service.MailService.*(..))")
    public void loggingPointcut(){}

    // 在执行UserService的每个方法前执行:
    @Before("execution(public * com.SpringMVC.service.UserService.*(..))")
    public void doAccessCheck() {
        System.err.println("[Before] do access check...");
    }

    // 使用@Around注解标明环绕通知方法
    @Around("loggingPointcut()")// 通过在通知方法形参位置声明ProceedingJoinPoint类型的形参，Spring会将这个类型的对象传给我们
    public Object roundLog(ProceedingJoinPoint joinPoint) {
        // 获取外界调用目标方法时传入的实参数组
        Object[] args = joinPoint.getArgs();
        // 获取目标方法的签名对象
        Signature signature = joinPoint.getSignature();
        // 获取目标方法的方法名
        String methodName = signature.getName();
        // 声明变量用来存储目标方法的返回值
        Object targetMethodReturnValue = null;
        try {
            // 在目标方法执行前
            System.out.println("[环绕通知 Before] 方法名：" + methodName + "，参数列表：" + "Arrays.asList(args)");
            // 通过ProceedingJoinPoint对象调用目标方法，目标方法的返回值一定要返回给外界调用者
            targetMethodReturnValue = joinPoint.proceed(args);
            // 在目标方法成功返回后
            System.out.println("[环绕通知 AfterReturning] 方法名：" + methodName + "，方法返回值：" + targetMethodReturnValue);
        } catch (Throwable e) { // 在目标方法抛异常后
            System.out.println("[环绕通知 AfterThrowing] 方法名：" + methodName + "，异常：" + e.getClass().getName());
        } finally { // 在目标方法最终结束后
            System.out.println("[环绕通知 After] 方法名：" + methodName);
        }
        return targetMethodReturnValue;
    }
}