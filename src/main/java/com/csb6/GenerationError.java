package com.csb6;

import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.types.TCType;

public class GenerationError extends RuntimeException {

    public GenerationError(TCType type) {
        this(type, "unsupported type");
    }

    public GenerationError(TCType type, String message) {
        super(String.format("Type '%s' not translated (%s)", type, message));
    }

    public GenerationError(TCDefinition def) {
        this(def, String.format("unsupported definition type '%s'", def.getClass().getSimpleName()));
    }

    public GenerationError(TCDefinition def, String message) {
        super(String.format("Definition '%s' not translated (%s)", def.name, message));
    }

    public GenerationError(String message)
    {
        super(message);
    }
}
