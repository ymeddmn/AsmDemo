package com.asm.mage.plugin.visit.methodvisitor;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.NEW;

/**
 * author  :mayong
 * function:默认的方法处理器
 * date    :2021/3/13
 **/
public class DefaultMethodVisitor extends MethodVisitor {
    public DefaultMethodVisitor(int api) {
        super(api);
    }

    public DefaultMethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (desc.equals("(Lcom/sitech/paas/asmproject/clsreplace/Human$StudyListener;)V") && name.equals("setListener")) {
            mv.visitTypeInsn(NEW, "com/sitech/paas/asmproject/clsreplace/Test");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKESPECIAL, "com/sitech/paas/asmproject/clsreplace/Test",
                    "<init>", "(Lcom/sitech/paas/asmproject/clsreplace/Human$StudyListener;)V", false);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 4);
            System.out.println("开始重新设置监听");
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
    @Override
    public void visitParameter(String name, int access) {
        super.visitParameter(name, access);
        log("visitParameter" + name);
    }

    @Override
    public void visitAttribute(Attribute attr) {
        super.visitAttribute(attr);
        log("visitAttribute" + attr.type+":toString:"+attr.toString());
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
