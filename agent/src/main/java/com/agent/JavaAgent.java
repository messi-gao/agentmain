package com.agent;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JavaAgent {
    public static void agentmain(String arg, Instrumentation ins) {
        System.out.println("agentmain 启动成功。。。。");
        Stream.of(ins.getAllLoadedClasses()).filter(aClass -> "Test".equals(aClass.getName()))
                .findFirst().ifPresent(aClass -> {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("C:/ideaworkspace/agentmain/app/target/classes/Test.class"));
                ClassDefinition classDefinition = new ClassDefinition(aClass, bytes);
                ins.redefineClasses(classDefinition);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnmodifiableClassException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Test转换完成");
        });
        System.out.println("agentmain 启动完成。。。。");
    }
}
