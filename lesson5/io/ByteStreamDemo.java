package lesson5.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ByteStreamDemo {

    public static void main(String[] args) {
        try {
            // 采用utf-8字符集将字符编码成字节
            byte[] bytes = "Let's study Java!\n".getBytes(StandardCharsets.UTF_8);
            // 实例化test.txt文件的输出流
            OutputStream os = new FileOutputStream("lesson5/io/byte.txt");
            for (byte b : bytes) {
                // 一次写入一个字节
                os.write(b);
            }
            // 不用了务必要关闭流
            os.close();

            // 实例化test.txt文件的输入流
            InputStream is1 = new FileInputStream("lesson5/io/byte.txt");
            int content; // 存储读取的一个字节
            while ((content = is1.read()) != -1) {
                System.out.print((char) content);
            }
            System.out.println();

            InputStream is2 = new FileInputStream("lesson5/io/byte.txt");
            // 声明字节数组
            byte[] outputBytes = new byte[1024];
            // 记录偏移量和读取的字节数
            int offset = 0, count;
            while ((count = is2.read(outputBytes, offset, 3)) != -1) { // 还有字节就继续读
                System.out.println(new String(outputBytes, offset, count));
                offset += count;
            }

            // 不用了务必要关闭流
            is1.close();
            is2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
