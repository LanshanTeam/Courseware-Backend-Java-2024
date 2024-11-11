package lesson4.exception;

public class ExceptionHandlingDemo {

    //在方法中用throws关键字声明可能抛出的异常
    public static String print(String content) throws IllegalArgumentException{
        if(content == null) {
            //用throw关键字抛出实际异常
            throw new IllegalArgumentException("内容不可为空");
        }
        return content;
    }

    public static void main(String[] args) {
        try {
            String result = print(null);
            System.out.println("结果：" + result);
        }
        //捕获特定的异常
        catch (IllegalArgumentException e){
            //处理异常
            System.out.println("捕获异常：" + e);
        }
        //捕获所有其他异常
        catch (Exception e){
            //处理其余异常
            System.out.println("捕获其他异常" + e);
        }finally {
            //无论是否发生异常都会执行
            System.out.println("执行finally中的代码");
        }

    }
}
