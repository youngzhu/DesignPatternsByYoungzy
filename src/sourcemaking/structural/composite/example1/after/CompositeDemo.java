package sourcemaking.structural.composite.example1.after;

import java.util.ArrayList;

// 定义最基本的共同特性
interface AbstractFile {
    void ls();
}

class File implements AbstractFile {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public void ls() {
        System.out.println(CompositeDemo.compositeBuilder + name);
    }
}

class Directory implements AbstractFile {
    private String name;
    private ArrayList includeFiles = new ArrayList();

    public Directory(String name) {
        this.name = name;
    }

    public void add(Object o) {
        includeFiles.add(o);
    }

    public void ls() {
        System.out.println(CompositeDemo.compositeBuilder + name);
        CompositeDemo.compositeBuilder.append("    ");
        for (Object obj : includeFiles) {
            // 利用其共通性
            AbstractFile abstractFile = (AbstractFile)obj;
            abstractFile.ls();
        }

        CompositeDemo.compositeBuilder.setLength(CompositeDemo.compositeBuilder.length() - 3);
    }
}

public class CompositeDemo {
    public static StringBuilder compositeBuilder = new StringBuilder();

    public static void main(String[] args) {
        Directory music = new Directory("MUSIC");
        Directory scorpions = new Directory("SCORPIONS");
        Directory dio = new Directory("DIO");
        File track1 = new File("Don't wary, be happy.mp3");
        File track2 = new File("track2.m3u");
        File track3 = new File("Wind of change.mp3");
        File track4 = new File("Big city night.mp3");
        File track5 = new File("Rainbow in the dark.mp3");

        music.add(track1);
        music.add(scorpions);
        music.add(track2);
        scorpions.add(track3);
        scorpions.add(track4);
        scorpions.add(dio);
        dio.add(track5);

        music.ls();
    }
}
