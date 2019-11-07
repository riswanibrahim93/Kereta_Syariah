package kereta_syariah;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Vertex {
    public String label;
    public boolean wasVisited;
    
    public Vertex(String Label)
    {
        label = Label;
        wasVisited = false;
    }
}
