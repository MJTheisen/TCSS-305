package gui;

import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;
import image.PixelImage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/**
 * This program works as a rudimentary image editor that has seven filter functions
 * The file follows the instruction put forth in 2021_4AU-TCSS305A-PRGA#03-rev1.pdf
 *
 *
 **/
public class SnapShopGUI {

    int filters = 7;
    JFrame frame;
    JPanel topPanel, bottomPanel;
    JLabel middleLabel;
    JButton filterButton, openButton, saveButton, closeButton;
    PixelImage image;
    JFileChooser fileChooser;
    File nameOfFile;
    List<Filter> filterList;
    List<JButton> buttonList;
    int result;

    public SnapShopGUI() {
        // title frame Req1.1
        frame = new JFrame("TCSS 305 – Programming Assignment 3 (Michael Theisen)");
        fileChooser = new JFileChooser(".");
        filterList = new ArrayList<>();
        buttonList = new ArrayList<>();
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new FlowLayout());
        middleLabel = new JLabel();
        // makes it sizable to the image Req3.2
        middleLabel.setHorizontalAlignment(JLabel.CENTER);
        middleLabel.setVerticalAlignment(JLabel.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(middleLabel, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        // add filters to button array Req1.2
        filterList.add(new EdgeDetectFilter());
        filterList.add(new EdgeHighlightFilter());
        filterList.add(new FlipHorizontalFilter());
        filterList.add(new FlipVerticalFilter());
        filterList.add(new GrayscaleFilter());
        filterList.add(new SharpenFilter());
        filterList.add(new SoftenFilter());

        // make an ArrayList of buttons
        for (int i = 0; i < filters; i++) {
            buttonList.add(i, createFilterButton(filterList.get(i)));
            topPanel.add(buttonList.get(i));
        }
        openButton = new JButton("Open...");
        saveButton = new JButton("Save As...");
        closeButton = new JButton("Close Image");

        //invisible at first Req1.3
        saveButton.setEnabled(false);
        closeButton.setEnabled(false);
        //adds buttons
        bottomPanel.add(openButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(closeButton);
        //gives the actionlistener to the button
        openButton.addActionListener(new OptionActionListener());
        saveButton.addActionListener(new OptionActionListener());
        closeButton.addActionListener(new OptionActionListener());
    }

    public void start() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // makes button then add filter
    public JButton createFilterButton(Filter currentFilter) {
        filterButton = new JButton(currentFilter.getDescription());
        class FilterActionListener implements ActionListener {
            // buttons apply filter
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFilter.filter(image);
                middleLabel.setIcon(new ImageIcon(image));
                frame.pack();
            }
        }
        filterButton.addActionListener(new FilterActionListener());
        filterButton.setEnabled(false);
        return filterButton;
    }

    class OptionActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameOfFile = fileChooser.getSelectedFile();
            // if open
            if (e.getSource() == openButton) {
                openButtonAction();
            }
            // if save
            if (e.getSource() == saveButton) {
                result  = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        image.save(fileChooser.getSelectedFile());
                    } catch (IOException a) {
                        JOptionPane.showMessageDialog(null, "The file cannot be overridden!");
                    }
                }
            }
            // if close
            if (e.getSource() == closeButton) {
                middleLabel.setIcon(null);
                saveButton.setEnabled(false);
                closeButton.setEnabled(false);
                for (int i = 0; i < filters; i++) {
                    buttonList.get(i).setEnabled(false);
                }
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        }
        // opens image Req2.1
        public void openButtonAction() {
            result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                nameOfFile = fileChooser.getSelectedFile();
                try {
                    image = PixelImage.load(nameOfFile);
                    middleLabel.setIcon(new ImageIcon(image));
                    // an image file was opened successfully so enable option buttons
                    saveButton.setEnabled(true);
                    closeButton.setEnabled(true);
                    for (int i = 0; i < filters; i++) {
                        buttonList.get(i).setEnabled(true);
                    }
                } catch (IOException e) {
                    //catch is used to stop file if not image Req6.1
                    JOptionPane.showMessageDialog(null, "The selected file did not contain an image!");
                }
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        }
    }
}