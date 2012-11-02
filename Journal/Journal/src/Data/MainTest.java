/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import GUI.Login;
import java.sql.Date;

/**
 *
 * @author
 */
 
 
public class MainTest {

 
    public static void main(String[] args) {
        Date d = new Date(63, 0, 16),c = new Date(64,0,16);	// January 16, 1963
	System.out.println(c.getYear() - d.getYear());
    }
}