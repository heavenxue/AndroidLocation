package com.iapppay.lixue.compiler;
public final class ClassValidator {
    static boolean isPublic(Element annotatedClass) {
        return annotatedClass.getModifiers().contains(PUBLIC);
    }

    static boolean isPrivate(Element annotatedClass) {
        return annotatedClass.getModifiers().contains(PRIVATE);
    }

    static boolean isAbstract(Element annotatedClass) {
        return annotatedClass.getModifiers().contains(ABSTRACT);
    }

    static String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
    }
}
