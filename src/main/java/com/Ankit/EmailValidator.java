package com.Ankit;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;


public class EmailValidator {

    public static boolean MXLookup( String domain) throws NamingException{
        InitialDirContext context = new InitialDirContext();
        Attributes attributes = context.getAttributes("dns:/" + domain, new String[]{"MX"});
        return attributes.get("MX") != null;
    }

    public static boolean emailValidator(String email){
        if(email.contains("@@") || email.matches(".*[^a-zA-Z0-9@._-].*") || email.contains("..") || email.contains(".@")
                || email.contains("@.")){
            return false;
        }else {
            if( email.matches("[a-zA-Z0-9._+\\-]+@[a-zA-Z0-9.-]+") && email.contains("@"))
            {
                int atIndex = email.indexOf("@");
                String domain = email.substring(atIndex + 1);
                try{
                return MXLookup(domain);
                }catch(NamingException e){
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
