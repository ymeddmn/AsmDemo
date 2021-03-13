package com.asm.mage.plugin.utils;

import com.asm.mage.plugin.visit.classvisitor.AniminalReplace2CatClassVisitor;
import com.asm.mage.plugin.visit.classvisitor.ClsReplaceClassVisitor;
import com.asm.mage.plugin.visit.classvisitor.MethodInterceptorMethodVisitor;
import com.asm.mage.plugin.visit.methodvisitor.Human2MethodVisitor;

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
        System.out.println("类名：" + clsName);
        if (clsName.equals("Client.class")) {
            return new AniminalReplace2CatClassVisitor(ASM5, classWriter);
        } else if (clsName.equals("MethodInterceptor.class")) {
            return new MethodInterceptorMethodVisitor(ASM5, classWriter);
        }
        return new ClassVisitor(ASM5, classWriter) {
            String clsName;

            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                clsName = name;
                super.visit(version, access, name, signature, superName, interfaces);
            }


            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
                System.out.println("类名："+clsName+"：方法名："+name+":desc:"+desc);
                if (clsName.equals("com/sitech/paas/asmproject/clsreplace/Human2") && name.equals("setListener2")) {
                    System.out.println("扫描human2");
                    return new Human2MethodVisitor(api, methodVisitor);
                }

                return new MethodVisitor(api, methodVisitor) {
                    @Override
                    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                        if (desc.equals("(Lcom/sitech/paas/asmproject/clsreplace/Human$StudyListener;)V") && name.equals("setListener")) {
                            methodVisitor.visitTypeInsn(NEW, "com/sitech/paas/asmproject/clsreplace/Test");
                            methodVisitor.visitInsn(DUP);
                            methodVisitor.visitVarInsn(ALOAD, 3);
                            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/sitech/paas/asmproject/clsreplace/Test",
                                    "<init>", "(Lcom/sitech/paas/asmproject/clsreplace/Human$StudyListener;)V", false);
                            methodVisitor.visitVarInsn(ASTORE, 4);
                            methodVisitor.visitVarInsn(ALOAD, 2);
                            methodVisitor.visitVarInsn(ALOAD, 4);
                            System.out.println("开始重新设置监听");

                        }
                        super.visitMethodInsn(opcode, owner, name, desc, itf);
                    }
                };
            }
        };
    }
}
