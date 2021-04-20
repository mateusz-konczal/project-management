package com.project.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaskStatus {
    TO_DO("Do zrobienia"),
    IN_PROGRESS("W trakcie"),
    ON_HOLD("Wstrzymano"),
    COMPLETED("Ukończono");

    String friendlyName;
}
