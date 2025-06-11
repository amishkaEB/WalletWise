
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.Crypto;
import utils.DBConnection;


public class MLogin {
    public boolean isAuthorized (String currentP) throws Exception {
        String query = "Select pwd from settings limit 1";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        rs.next();
        String realPwd = Crypto.decrypt(rs.getString(1));
        
        if (!realPwd.equals(currentP)){
            return false;
        } 
        
        return true;
        
    }
}
