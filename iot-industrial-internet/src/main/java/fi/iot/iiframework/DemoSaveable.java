/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iiframework;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author atte
 */
@Entity
public class DemoSaveable {
    @Id
    @NotBlank
    private int i;
    @NotBlank
    private String s;

    public DemoSaveable(int i, String s) {
        this.i = i;
        this.s = s;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
