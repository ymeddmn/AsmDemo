package com.asm.mage.plugin.utils;

import com.asm.mage.plugin.visit.classvisitor.AniminalReplace2CatClassVisitor;
import com.asm.mage.plugin.visit.classvisitor.ClsReplaceClassVisitor;
import com.asm.mage.plugin.visit.classvisitor.DefaultClassVisitor;
import com.asm.mage.plugin.visit.classvisitor.MethodInterceptorMethodVisitor;
import com.asm.mage.plugin.visit.methodvisitor.Human2MethodVisitor;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASM5;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.NEW;


/**
 * author  :mayong
 * function:
 * date    :2020-03-29
 **/
public class GenerateClsUtils {
    private static String[] canChange = {"MainActivity", "Demo", "okhttp3/Callback", "okhttp3/OkHttpClient.class", "Test.class"};

    public static boolean needBeenChange(String name) {
        for (String clsName : canChange) {
            if (name.contains(clsName)) {
                return true;
            }
        }
        return false;
    }

    public static ClassVisitor getClassVisitor(String clsName, ClassWriter classWriter) {
//        System.out.println("类名：" + clsName);
        if (clsName.equals("Client.class")) {
            return new AniminalReplace2CatClassVisitor(ASM5, classWriter);
        } else if (clsName.equals("MethodInterceptor.class")) {
            return new MethodInterceptorMethodVisitor(ASM5, classWriter);
        }
        return new DefaultClassVisitor(ASM5,classWriter);
    }
}
