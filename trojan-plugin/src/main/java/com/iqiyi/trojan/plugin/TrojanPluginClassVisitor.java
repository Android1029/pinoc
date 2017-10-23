package com.iqiyi.trojan.plugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by zhaolifei on 2017/8/19.
 */

public class TrojanPluginClassVisitor extends ClassVisitor {

    private String className;

    public TrojanPluginClassVisitor(String name, ClassWriter cw) {
        super(Opcodes.ASM5, cw);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        className = name;
        System.out.println("Name " + name + " signature " + signature + " superName " + superName);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        System.out.println("Name " + name + " signature " + signature + " desc " + desc);
        return new TrojanPluginMethodVisitor(api, mv, access, className, name, desc);
        //return new TestMethodVisitor(api, mv);
    }
}
