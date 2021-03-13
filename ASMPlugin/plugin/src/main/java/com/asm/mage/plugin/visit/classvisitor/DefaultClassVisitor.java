package com.asm.mage.plugin.visit.classvisitor;

import com.asm.mage.plugin.visit.methodvisitor.Human2MethodVisitor;
import com.asm.mage.plugin.visit.methodvisitor.DefaultMethodVisitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * author  :mayong
 * function:
 * date    :2021/3/13
 **/
public class DefaultClassVisitor extends ClassVisitor {
    public DefaultClassVisitor(int api) {
        super(api);
    }

    public DefaultClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    String clsName;

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        clsName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
//                System.out.println("类名："+clsName+"：方法名："+name+":desc:"+desc);
        if (clsName.equals("com/sitech/paas/asmproject/clsreplace/Human2") && name.equals("setListener2")) {
            return new Human2MethodVisitor(api, methodVisitor);
        }

        return new DefaultMethodVisitor(api,methodVisitor);
    }
}
