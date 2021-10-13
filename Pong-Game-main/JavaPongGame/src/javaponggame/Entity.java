/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaponggame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public interface Entity {
    public Dimension getSize();

    public Point getLocation();

    public void setLocation(Point p);

    public void paint(Graphics g);
}
