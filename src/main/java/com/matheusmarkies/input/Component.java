/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matheusmarkies.input;

import java.util.ArrayList;

/**
 *
 * @author Matheus
 */
public class Component {
    private String definition;
    private ArrayList<Primitive> primitives = new ArrayList<>();

    private int priority = 0;

    public Component(String definition, int priority) {
        this.definition = definition;
        this.priority = priority;
    }

    public String getDefinition() {
        return definition;
    }

    public ArrayList<Primitive> getPrimitives() {
        return primitives;
    }

    public int getPriority() {
        return priority;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setPrimitives(ArrayList<Primitive> primitives) {
        this.primitives = primitives;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
