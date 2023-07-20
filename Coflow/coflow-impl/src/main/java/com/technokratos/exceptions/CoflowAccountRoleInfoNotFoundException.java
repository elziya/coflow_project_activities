package com.technokratos.exceptions;

public class CoflowAccountRoleInfoNotFoundException extends CoflowNotFoundException {

    public CoflowAccountRoleInfoNotFoundException() {
        super("Account role info not found");
    }
}
