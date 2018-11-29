package com.aotain.trafficDataCollection.test;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

import com.aotain.common.util.LocalConfig;

public class JEXLTest {
	
	private static final JexlEngine jexl = new JexlBuilder().cache(512).strict(true).silent(false).create();
    public static void main(String[] arg) throws Exception {
        // Assuming we have a JexlEngine instance initialized in our class named 'jexl':
        // Create an expression object for our calculation
        String calculateTax = LocalConfig.getInstance().getAreaKpiExl(); //e.g. "((G1 + G2 + G3) * 0.1) + G4";
        JexlExpression e = jexl.createExpression( calculateTax );

        // populate the context
        JexlContext context = new MapContext();
        context.set("x", 1.0);
        context.set("leftValue", 1.0);
        context.set("rightValue", -1.0);
        context.set("rightScore", 100.0);
        context.set("leftScore", 90.0);
        // ...

        // work it out
        Number result = (Number) e.evaluate(context);
        System.out.println(result);
    }
}
