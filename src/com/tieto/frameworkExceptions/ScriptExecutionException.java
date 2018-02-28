package com.tieto.frameworkExceptions;

import com.tieto.frameworkServices.core.scenarioResult.ScenarioResultObject;

@SuppressWarnings("serial")
public class ScriptExecutionException extends RuntimeException {
	ScenarioResultObject sroForReference= new ScenarioResultObject();
    public ScriptExecutionException(String message) {
        super(message);
       sroForReference.setReasonIfFailed(message);
    }

    public ScriptExecutionException(String message, Exception cause) {
        super(message, cause);
       sroForReference.setReasonIfFailed(message);
    }
}
