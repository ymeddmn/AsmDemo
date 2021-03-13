package com.asm.mage.plugin.visit.classvisitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * author  :mayong
 * function:Method1的method1方法调用完成后，调用易信Method1的printMethod1类方法，printMethod1类方法的参数是Method1的对象
 * date    :2021/3/12
 **/
public class MethodInterceptorMethodVisitor extends ClassVisitor {
    public MethodInterceptorMethodVisitor(int api) {
        super(api);
    }

    public MethodInterceptorMethodVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    boolean method1HasBeen;

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (name.equals("print")) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
            return new MethodVisitor(api, methodVisitor) {
                @Override
                public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                    super.visitMethodInsn(opcode, owner, name, desc, itf);
                    if (owner.equals("com/sitech/paas/asmproject/methodinterceptor/Method1") && name.equals("method1")) {
                        method1HasBeen = true;
                        methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "com/sitech/paas/asmproject/methodinterceptor/Method1", "printMethod1", "(Lcom/sitech/paas/asmproject/methodinterceptor/Method1;)V", false);
                    }
                }

            };
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

}
