package com.zhu.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class testReadObject {
    public static void main(String[] args) throws Exception {
        Set<String> set = new HashSet<String>();
        set.add("11111");
        set.add("22222");
        System.out.println(set);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\template\\set.obj"))) {
            oos.writeObject(set);
        }
        set.clear();
        System.out.println(set);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\template\\set.obj"))) {
            set = (Set<String>) ois.readObject();
        }

        System.out.println(set);
    }
}
