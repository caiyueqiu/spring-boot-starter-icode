package com.icode.nio.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/6/3 8:20
 */
public class FilesDemo {
    public static void main(String[] args) throws Exception {
        FilesDemo filesDemo = new FilesDemo();
        filesDemo.walkFileTree();
    }

    private void walkFileTree() throws Exception {
        Path path = Paths.get("F:\\temp");
        // 查找一个名为01.txt的文件
        String fileToFind = File.separator + "01.txt";
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String fileString = file.toAbsolutePath().toString();
                if (fileString.endsWith(fileToFind)) {
                    System.out.println("找到文件：" + file.toAbsolutePath());
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void delete() throws Exception {
        Path path = Paths.get("F:\\temp\\temp02\\03.txt");
        // 删除一个文件或者目录
        Files.delete(path);
    }

    private void move() throws Exception {
        Path src = Paths.get("F:\\temp\\temp01\\01.txt");
        Path dest = Paths.get("F:\\temp\\temp02\\03.txt");
        // 于将文件从一个路径移动到另一个路径
        Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    private void copy() throws Exception {
        Path src = Paths.get("F:\\temp\\temp01\\01.txt");
        Path dest = Paths.get("F:\\temp\\temp02\\02.txt");
        Files.copy(src, dest);
        // 覆盖现有文件
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    private void files() throws Exception {
        Path path = Paths.get("F:\\temp\\temp01");
        Path directory = Files.createDirectory(path);
        // 如果目录存在，抛出FileAlreadyExistsException
        System.out.println("新创建的目录：" + directory);
    }
}
