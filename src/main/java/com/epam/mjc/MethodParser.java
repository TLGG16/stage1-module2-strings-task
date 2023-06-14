package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {
    public MethodSignature parseFunction(String signatureString) {

        String arg = signatureString.substring(signatureString.indexOf("(") + 1, signatureString.lastIndexOf(")"));
        String[] list = arg.replace(",", "").split(" ");
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (list.length > 1) {
            for (int i = 0; i < list.length; i += 2) {
                arguments.add(new MethodSignature.Argument(list[i], list[i + 1]));
            }
        }

        String[] signature = signatureString.replace("(", " ").replace(")", " ").split(" ");
        String modifier = "", type, name;
        if (signature[0].equals("public") || signature[0].equals("private") ||  signature[0].equals("protected")) {
            modifier = signature[0];
            type = signature[1];
            name = signature[2];
        } else {
            type = signature[0];
            name = signature[1];
        }

        MethodSignature obj = new MethodSignature(name, arguments);
        if (!modifier.equals("")) {
            obj.setAccessModifier(modifier);
        }
        obj.setReturnType(type);
        return obj;
    }
}
