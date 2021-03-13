package com.asm.mage.plugin.visit.methodvisitor;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.NEW;

/**
 * author  :mayong
 * function:
 * date    :2021/3/13
 **/
public class Human2MethodVisitor extends MethodVisitor {
    public Human2MethodVisitor(int api) {
        super(api);
    }

    public Human2MethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if (opcode == Opcodes.PUTFIELD
                && "com/sitech/paas/asmproject/clsreplace/Human2".equals(owner)
                && "listener2".equals(name)
                && "Lcom/sitech/paas/asmproject/clsreplace/Human2$StudyListener;".equals(desc)) {
            mv.visitTypeInsn(NEW, "com/sitech/paas/asmproject/clsreplace/Test2");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD,1);
            mv.visitMethodInsn(INVOKESPECIAL, "com/sitech/paas/asmproject/clsreplace/Test2", "<init>", "(Lcom/sitech/paas/asmproject/clsreplace/Human2$StudyListener;)V", false);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 4);
        } else {

        }
        super.visitFieldInsn(opcode, owner, name, desc);
    }

}
