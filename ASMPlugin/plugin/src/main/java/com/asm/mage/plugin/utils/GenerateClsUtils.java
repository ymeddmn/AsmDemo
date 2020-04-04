package com.asm.mage.plugin.utils;

import com.asm.mage.plugin.LifecycleClassVisitor;
import com.asm.mage.plugin.visit.DemoClassVisitor;
import com.asm.mage.plugin.visit.MainActivityClassVisitor;
import com.asm.mage.plugin.visit.Okhttp3ClassVisitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;

import static org.objectweb.asm.Opcodes.ASM5;


/**
 * author  :mayong
 * function:
 * date    :2020-03-29
 **/
public class GenerateClsUtils {
    private static String[] canChange = {"MainActivity", "Demo","okhttp3/Callback"};

    public static boolean needBeenChange(String name) {
        for (String clsName:canChange){
            if(name.contains(clsName)){
                return true;
            }
        }
        return false;
    }

    public static ClassVisitor getClassVisitor(String clsName, ClassWriter classWriter) {
       /* if (clsName.contains("MainActivity")) {
            return new MainActivityClassVisitor(ASM5, classWriter);
        } else */if (clsName.contains("Demo")) {
            return new DemoClassVisitor(ASM5, classWriter);
        }else if(clsName.contains("okhttp3/Callback")){
            return new Okhttp3ClassVisitor(ASM5,classWriter);
        }

        return new LifecycleClassVisitor(ASM5, classWriter);
    }
}
