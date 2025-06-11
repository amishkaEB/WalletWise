
package controller;

import model.MLogin;

public class CLogin {
    private final MLogin model;

    public CLogin(MLogin model) {
        this.model = model;
    }
    public boolean isAuthorized(String currentP) throws Exception{
        return model.isAuthorized(currentP);
    }
}
