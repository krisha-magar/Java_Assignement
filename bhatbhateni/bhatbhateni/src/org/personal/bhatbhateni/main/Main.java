/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal.bhatbhateni.main;

import org.personal.bhatbhateni.ui.AdminView;


public class Main {

    public static void main(String[] args) {
        AdminView home = new AdminView();
        home.setTitle("Bhatbhateni Store Management System");
        home.setLocationRelativeTo(null);
        home.setVisible(true);
    }
}
