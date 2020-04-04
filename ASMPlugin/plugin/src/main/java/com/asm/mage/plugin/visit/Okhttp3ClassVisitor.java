package com.asm.mage.plugin.visit;

import com.asm.mage.plugin.log.CLog;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;

/**
 * author  :mayong
 * function:
 * date    :2020-04-04
 **/
public class Okhttp3ClassVisitor extends ClassVisitor {

    public Okhttp3ClassVisitor(int api) {
        super(api);
    }

    public Okhttp3ClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
        CLog.log("进入okhttp3构造");
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
//                if ("enqueue".equals(name)) {
//                    super.visitVarInsn(Opcodes.ASTORE,11);
//                    super.visitVarInsn(Opcodes.ALOAD,11);
//                    super.visitMethodInsn(INVOKEINTERFACE, "com/sitech/paas/asmproject/Okhttp3", "getCallback", "(Lokhttp3/Callback;)Lcom/sitech/paas/asmproject/CustomOk3Callback;");
//                }
                CLog.log("进入okhttp3");
                if("onResponse".equals(name)){
                    CLog.log("onResponse:"+name);
                    super.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    super.visitLdcInsn("哈哈哈哈哈");
                    super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

                }
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }

            @Override
            public void visitVarInsn(int opcode, int var) {
                super.visitVarInsn(opcode, var);

            }
        };
    }
}
