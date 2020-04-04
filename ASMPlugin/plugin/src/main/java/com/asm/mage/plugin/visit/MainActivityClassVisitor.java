package com.asm.mage.plugin.visit;

import com.asm.mage.plugin.log.CLog;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

/**
 * author  :mayong
 * function:
 * date    :2020-03-29
 **/
public class MainActivityClassVisitor extends ClassVisitor {
    public MainActivityClassVisitor(int api) {
        super(api);
    }

    public MainActivityClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return new MethodVisitor(api) {
            @Override
            public void visitInsn(int opcode) {
                super.visitInsn(opcode);
//                CLog.log("visitInsn", opcode);
            }

            @Override
            public void visitLineNumber(int line, Label start) {
                super.visitLineNumber(line, start);
            }

            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }


        };
    }


}
