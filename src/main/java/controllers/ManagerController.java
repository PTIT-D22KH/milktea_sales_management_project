/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import views.admin.ManagerPaneView;

/**
 *
 * @author P51
 */
public abstract class ManagerController {
    protected ManagerPaneView view = null;
    
    public ManagerController() {
        
    }

    public ManagerPaneView getView() {
        return view;
    }

    public void setView(ManagerPaneView view) {
        if (this.view != view) {
            this.view = view;
            addEvent();
        } else {
            this.view = view;
        }
        
    }

    public abstract void actionAdd();

    public abstract void actionSearch();

    public abstract void actionDelete();

    public abstract void actionEdit();

    public abstract void updateData();
    
    private void addEvent() {
        
        view.getSearchTxt().addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent event) {
                if (view.getSearchTxt().getText().equals("Search")) {
                    view.getSearchTxt().setText("");
                    
                }
            }
            
            public void focusLost(FocusEvent event) {
                if (view.getSearchTxt().getText().equals("") || view.getSearchTxt().getText().equals("Search")) {
                    view.getSearchTxt().setText("Search");
                }
            }
        });
        
        view.getSearchTxt().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionSearch();
                }
            }
        });
        
        
        view.getAddButton().addActionListener(event -> actionAdd());
        view.getEditButton().addActionListener(event -> actionEdit());
        view.getDelButton().addActionListener(event -> actionDelete());
        view.getSyncButton().addActionListener(event -> updateData());
        
    }
    
    
}
