/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "userProfile")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListUserProfile {
    private List<UserProfile> listUserProfiles;

    public ListUserProfile(List<UserProfile> listUserProfiles) {
        this.listUserProfiles = listUserProfiles;
    }
    
    public List<UserProfile> getUserProfiles() {
        return listUserProfiles;
    }

    public void setUserProfiles(List<UserProfile> listUserProfiles) {
        this.listUserProfiles = listUserProfiles;
    }
}
