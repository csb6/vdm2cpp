package com.csb6.cpp.types;

/**
 * Represents a type that needs a definition (i.e. it can't be defined inline where it is used)
 */
public abstract class CPPDefType extends CPPType {

    public abstract String toDefString();

}
