package com.technokratos.exceptions;

import static com.technokratos.consts.CoflowMessages.LOGIN_BAD_CREDENTIALS_EXCEPTION_MESSAGE;

public class CoflowBadCredentials extends CoflowIllegalArgumentException{

    public CoflowBadCredentials() {
        super(LOGIN_BAD_CREDENTIALS_EXCEPTION_MESSAGE);
    }
}
