/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.input;

/**
 *
 * @author Matheus
 */
public class Primitive {
    String primitive;
    double value;

    public Primitive() {
    }

    public Primitive(String primitive) {
        this.primitive = primitive;
    }

    public Primitive(String primitive, double value) {
        this.primitive = primitive;
        this.value = value;
    }

    public String getPrimitive() {
        return primitive;
    }

    public void setPrimitive(String primitive) {
        this.primitive = primitive;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
