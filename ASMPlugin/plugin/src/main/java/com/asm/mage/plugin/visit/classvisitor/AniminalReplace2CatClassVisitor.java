package com.asm.mage.plugin.visit.classvisitor;

import com.asm.mage.plugin.log.CLog;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.ModuleVisitor;
import org.objectweb.asm.Opcodes;


/**
 * author  :mayong
 * function:将Client类中的Animinal替换为Animinal的子类Cat
 * date    :2020-03-28
 **/
public class AniminalReplace2CatClassVisitor extends ClassVisitor {

    private boolean isClient;//是否是Client类

    public AniminalReplace2CatClassVisitor(int api) {
        super(api);
    }

    public AniminalReplace2CatClassVisitor(int api, ClassVisitor cv) {
        super(Opcodes.ASM5, cv);

    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);

        if ("com/sitech/paas/asmproject/anim/Client".equals(name)) {
            isClient = true;
        }
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if(desc.equals("Lcom/sitech/paas/asmproject/anim/Animinal;")){
            desc = "Lcom/sitech/paas/asmproject/anim/Cat;";
        }
        return super.visitField(access, name, desc, signature, value);
    }

    boolean isAnim = false;
    String methodName = "";

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (name.equals("<init>")||name.equals("eat")) {
            MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);

            return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
                @Override
                public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                    methodName = name;
                    if("<init>".equals(name)&&owner.equals("com/sitech/paas/asmproject/anim/Animinal")){
                        owner="com/sitech/paas/asmproject/anim/Cat";
                    }
                    if(name.equals("eat")){
                        owner="com/sitech/paas/asmproject/anim/Cat";
                    }
                    super.visitMethodInsn(opcode, owner, name, desc, itf);
                }

                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                    if(desc.equals("Lcom/sitech/paas/asmproject/anim/Animinal;")){
                        desc="Lcom/sitech/paas/asmproject/anim/Cat;";
                    }
                    super.visitFieldInsn(opcode, owner, name, desc);
                }

                @Override
                public void visitTypeInsn(int opcode, String type) {
                    if(type.equals("com/sitech/paas/asmproject/anim/Animinal")){
                        type="com/sitech/paas/asmproject/anim/Cat";
                    }
                    super.visitTypeInsn(opcode, type);
                }
            };
        } else {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }
    private void log(String msg){
        System.out.println("日志："+msg);
    }
    @Override
    public void visitEnd() {
        super.visitEnd();
        isClient = false;
    }
}
