package com.asm.mage.plugin.utils;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * author  :mayong
 * function:
 * date    :2020-06-17
 **/
public class Utils {

    public static void print(MethodVisitor mv,String dest,FieldListener fieldListener){
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        // pushes the "Hello World!" String constant
        fieldListener.visit();
        // invokes the 'println' method (defined in the PrintStream class)
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", dest);
    }
   public interface FieldListener{
        void visit();
    }
}
