/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private ManagerPaneView view = null;

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
        // display place holder
        view.getSearchTxt().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                if (view.getSearchTxt().getText().equals("Search")) {
                    view.getSearchTxt().setText("");
                    view.getSearchTxt().setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (view.getSearchTxt().getText().equals("") || view.getSearchTxt().getText().equals("Search")) {
                    view.getSearchTxt().setText("Search");
                    view.getSearchTxt().setForeground(new Color(153, 153, 153));
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

        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                actionAdd();
            }
        });
        view.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                actionEdit();
            }
        });
        view.getDelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                actionDelete();
            }
        });
        view.getSyncButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updateData();
            }
        });
    }
    
    
}
