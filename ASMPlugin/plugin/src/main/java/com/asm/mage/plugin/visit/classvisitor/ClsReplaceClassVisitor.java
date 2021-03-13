package com.asm.mage.plugin.visit.classvisitor;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * author  :mayong
 * function:将某个类全局替换为另一个类
 * date    :2021/3/13
 **/
public class ClsReplaceClassVisitor extends MethodVisitor {
    int opcode1 = -1;
    int opcode2 = -1;

    public ClsReplaceClassVisitor(int api) {
        super(api);
    }

    public ClsReplaceClassVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var);
        System.out.println("visitVarInsn操作数索引：" + var);
        if (opcode1 == -1) {
            opcode1 = opcode;
        }
        if(opcode1!=-1&&opcode2==1){
            opcode2 = opcode;
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        System.out.println("进入方法：visitMethodInsn：类名：" + owner + "方法名：" + name+"：desc："+desc);
        if(name.equals("setListener")){
            mv.visitTypeInsn(NEW, "com/sitech/paas/asmproject/clsreplace/Test");
            mv.visitInsn(DUP);
            System.out.println("两个操作数：" + opcode1 + "::" + opcode2);
            mv.visitVarInsn(ALOAD, opcode2);
            mv.visitMethodInsn(INVOKESPECIAL, "com/sitech/paas/asmproject/clsreplace/Test", "<init>", "(Lcom/sitech/paas/asmproject/clsreplace/Human$StudyListener;)V", false);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, opcode1);
            mv.visitVarInsn(ALOAD, 4);
        }

        super.visitMethodInsn(opcode, owner, name, desc, itf);

    }


}
