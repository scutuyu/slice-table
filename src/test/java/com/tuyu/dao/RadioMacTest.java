package com.tuyu.dao;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * radioMac的导出与导入测试
 * <p>类似  e9:29:71:9e:e2:98</p>
 * @author tuyu
 * @date 8/9/18
 * Talk is cheap, show me the code.
 */
public class RadioMacTest {

    private Random randoms = new Random(6L);
    private File file = new File("src/main/resources/radio-mac.txt");

    /**
     * 生成mac地址并导出 测试
     */
    @Test
    public void testGenerate() {
        int num = 100;
        generate(num);
        System.out.println("generate " + num + " radioMac");
    }

    /** 生成指定个数的mac地址 */
    private void generate(int num) {
        List<String> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(getRandomRadioMac());
        }
        exportFile(list);
    }

    /** 得到一个随机的mac地址字符串 */
    private String getRandomRadioMac() {
        final String[] up = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        return new StringBuilder(up[randoms.nextInt(16)])
                .append(up[randoms.nextInt(16)])
                .append(":")
                .append(up[randoms.nextInt(16)])
                .append(up[randoms.nextInt(16)])
                .append(":")
                .append(up[randoms.nextInt(16)])
                .append(up[randoms.nextInt(16)])
                .append(":")
                .append(up[randoms.nextInt(16)])
                .append(up[randoms.nextInt(16)])
                .append(":")
                .append(up[randoms.nextInt(16)])
                .append(up[randoms.nextInt(16)])
                .append(":")
                .append(up[randoms.nextInt(16)])
                .append(up[randoms.nextInt(16)])
                .toString();
    }

    /**
     * 测试exportFile方法是否管用
     */
    @Test
    public void testWriter() {
        exportFile(Arrays.asList("hello"));
    }

    /** 将数据到出到预定的文件 */
    private void exportFile(List<String> list) {

        try {
            Writer writer = new PrintWriter(file);
            for (String s : list) {
                writer.write(s + "\n");
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试readFile的正确性
     */
    @Test
    public void testReadFile() {
        readFile();
    }

    /** 从预定文件读文件 */
    private void readFile() {
        try {
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
                i++;
            }
            System.out.println("get " + i + " records");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
