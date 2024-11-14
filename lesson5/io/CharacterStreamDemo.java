package lesson5.io;

import java.io.*;

public class CharacterStreamDemo {

    public static void main(String[] args) {
        // 在此处声明流对象，会自动关闭，无需手动关闭
        try (Writer writer = new FileWriter("lesson5/io/character.txt");
             Reader reader = new FileReader("lesson5/io/character.txt")) {
            String s = "让我们学习 Java！\n";
            writer.write(s);
            // 刷新此输出流并强制写出所有缓冲的输出字符
            // 要是不刷新，结果会有什么不一样呢？
            writer.flush();

            int content;
            while((content = reader.read()) != -1) {
                System.out.print((char)content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
