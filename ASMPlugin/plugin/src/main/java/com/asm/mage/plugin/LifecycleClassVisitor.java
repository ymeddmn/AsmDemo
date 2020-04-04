package com.asm.mage.plugin;

import com.asm.mage.plugin.log.CLog;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.ModuleVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import static org.objectweb.asm.Opcodes.GETSTATIC;

/**
 * author  :mayong
 * function:
 * date    :2020-03-28
 **/
public class LifecycleClassVisitor extends ClassVisitor {
    private String superClsName;

    public LifecycleClassVisitor(int api) {
        super(api);
    }

    public LifecycleClassVisitor(int api, ClassVisitor cv) {
        super(Opcodes.ASM5, cv);

    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.superClsName = superName;
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                if (superClsName.equals("Lokhttp3/Callback") && name.equals("onResponse")) {
                    super.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    super.visitLdcInsn("哈哈哈哈哈");
                    super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

                }
                super.visitMethodInsn(opcode, owner, name, desc, itf);

            }
        };
    }
}
