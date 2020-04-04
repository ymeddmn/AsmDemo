package com.asm.mage.plugin.visit;

import com.asm.mage.plugin.log.CLog;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

/**
 * author  :mayong
 * function:
 * date    :2020-03-29
 **/
public class DemoClassVisitor extends ClassVisitor {
    public DemoClassVisitor(int api) {
        super(api);
    }

    public DemoClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals("p1") || name.equals("print")) {
            return new MethodVisitor(api, methodVisitor) {

                @Override
                public void visitInsn(int opcode) {
                    super.visitInsn(opcode);
                    CLog.log("visitInsn", opcode);
                }

                @Override
                public void visitCode() {
                    super.visitCode();
                    System.out.println("开始执行方法");
                }

                @Override
                public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                    CLog.log("visitMethodInsn", opcode, owner, name, desc);
                    if (name.equals("p1")) {
                        super.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                        super.visitLdcInsn("哈哈哈哈哈");
                        super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                    } else if (name.equals("print2")) {
                        super.visitVarInsn(Opcodes.ASTORE,11);
                        super.visitVarInsn(Opcodes.ALOAD,11);
                        super.visitLdcInsn("我是插入的代码");
                        super.visitMethodInsn(INVOKEVIRTUAL, "com/sitech/paas/asmproject/Demo", "p1", "(Ljava/lang/String;)V", false);
                        super.visitVarInsn(Opcodes.ALOAD,11);
                    }
                    super.visitMethodInsn(opcode, owner, name, desc, itf);
                }

                @Override
                public void visitEnd() {
                    super.visitEnd();
                    System.out.println("方法执行结束");
                }
            };
        }
        return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
            @Override
            public void visitParameter(String name, int access) {
                super.visitParameter(name, access);
            }
        };

    }
}
