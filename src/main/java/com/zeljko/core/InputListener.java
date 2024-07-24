package com.zeljko.core;

import com.zeljko.graphics.Model3D;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static com.zeljko.utils.Constants.UNIT_MOVEMENT;


public class InputListener implements KeyListener, ActionListener {

    private List<Model3D> models = new ArrayList<>();
    private boolean shouldDraw = false;
    private int currentModelIndex = -1;

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentModelIndex == -1 || models.isEmpty()) return;
        Model3D currentModel = models.get(currentModelIndex);
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_Z:
                currentModel.scale(1.1f);
                break;
            case KeyEvent.VK_X:
                currentModel.scale(0.9f);
                break;
            case KeyEvent.VK_I:
                currentModel.translate(0, 0, UNIT_MOVEMENT);
                break;
            case KeyEvent.VK_O:
                currentModel.translate(0, 0, -UNIT_MOVEMENT);
                break;
            case KeyEvent.VK_J:
                currentModel.translate(UNIT_MOVEMENT, 0, 0);
                break;
            case KeyEvent.VK_K:
                currentModel.translate(-UNIT_MOVEMENT, 0, 0);
                break;
            case KeyEvent.VK_N:
                currentModel.translate(0, UNIT_MOVEMENT, 0);
                break;
            case KeyEvent.VK_M:
                currentModel.translate(0, -UNIT_MOVEMENT, 0);
                break;
            case KeyEvent.VK_LEFT:
                currentModel.rotate(1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                currentModel.rotate(-1, 0);
                break;
            case KeyEvent.VK_UP:
                currentModel.rotate(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                currentModel.rotate(0, 1);
                break;
            case KeyEvent.VK_Q:
                if (!models.isEmpty()) {
                    models.get(currentModelIndex).setSelected(false);
                    currentModelIndex = (currentModelIndex + 1) % models.size();
                    models.get(currentModelIndex).setSelected(true);
                    System.out.println("Selected model " + currentModelIndex);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Model3D newModel;
        String type = e.getActionCommand();
        if ("rectangle".equals(type)) {
            newModel = new Model3D(5.0, 1.5, 2.0);
        } else {
            System.out.println("Not implemented yet");
            return;
        }
        newModel.translate(Math.random() * 10 - 5,
                Math.random() * 10 - 5,
                Math.random() * 10 - 5);

        models.add(newModel);
        if (currentModelIndex == -1) {
            currentModelIndex = 0;
            newModel.setSelected(true);
        } else {
            models.get(currentModelIndex).setSelected(false);
            currentModelIndex = models.size() - 1;
            newModel.setSelected(true);
        }
        setShouldDraw(true);
    }

    public List<Model3D> getModels() {
        return models;
    }

    public Model3D getCurrentModel() {
        if (currentModelIndex >= 0 && currentModelIndex < models.size()) {
            return models.get(currentModelIndex);
        }
        return null;
    }

    public void setModels(List<Model3D> models) {
        this.models = models;
    }

    public boolean isShouldDraw() {
        return shouldDraw;
    }

    public void setShouldDraw(boolean shouldDraw) {
        this.shouldDraw = shouldDraw;
    }

    public int getCurrentModelIndex() {
        return currentModelIndex;
    }

    public void setCurrentModelIndex(int currentModelIndex) {
        this.currentModelIndex = currentModelIndex;
    }

}