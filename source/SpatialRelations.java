package code;

import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.TextAlignment;
import java.io.InputStreamReader;
import java.lang.ClassLoader;
import java.io.BufferedReader;

public class SpatialRelations {
    Pane pane;
    Label prompt, label;
    TextField tField;
    Circle circle, circle2;
    
    public Pane createCircle() {
        pane = new Pane();

        Scanner scanner = null;
        try
        {
            BufferedReader file = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data/infile.txt")));
            scanner = new Scanner(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        int circle_val1 = scanner.nextInt();
        int circle_val2 = scanner.nextInt();
        double circle_val3 = scanner.nextDouble();
    
        int circle2_val1 = scanner.nextInt();
        int circle2_val2 = scanner.nextInt();
        double circle2_val3 = scanner.nextDouble();

        scanner.close();

        // Prompt & Name
        prompt = new Label();
        prompt.setText("By: Devin Thomeczek - Determine if the two circles overlap.\n");
        prompt.relocate(0, 40);

        // First Circle
        circle = new Circle();
        circle.setCenterX(circle_val1);
        circle.setCenterY(circle_val2);
        circle.setRadius(circle_val3);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.RED);
        circle.setOpacity(0.5);

        // Second Circle
        circle2 = new Circle();
        circle2.setCenterX(circle2_val1);
        circle2.setCenterY(circle2_val2);
        circle2.setRadius(circle2_val3);
        circle2.setStroke(Color.BLACK);
        circle2.setFill(Color.LIGHTGREEN);
        circle2.setOpacity(0.5);

        // TextField for manual inputs
        tField = new TextField(circle_val1 + " " + circle_val2 + " " + circle_val3 + " " + circle2_val1 + " " + circle2_val2 + " " + circle2_val3);
        tField.setOnAction(new TextFieldHandler());
        tField.setPrefWidth(300);
        tField.relocate(0, 80);

        // Used to determine if the values of the circles are overlapping
        double center_distance = Math.sqrt(((circle2_val1 - circle_val1) * (circle2_val1 - circle_val1)) + ((circle2_val2 - circle_val2) * (circle2_val2 - circle_val2)));
        double radii_sum = circle_val3 + circle2_val3;

        // Used to output whether the circles are overlapping or not
        label = new Label();
        if (center_distance > radii_sum)
        {
            label.setText("These Circles Don\'t Overlap And Are Disjoint.");
        }
        else if (center_distance == radii_sum)
        {
            label.setText("These Circles Touch Each Other From The Outside.");
        }
        else if (center_distance == 0 && circle_val3 == circle2_val3)
        {
            label.setText("Circle 1 And Circle 2 Are Equal.");
        }
        else if (center_distance == circle_val3 - circle2_val3)
        {
            label.setText("Circle 2 Touches And Is Inside Circle 1.");
        }
        else if (center_distance == circle2_val3 - circle_val3)
        {
            label.setText("Circle 1 Touches And Is Inside Circle 2.");
        }
        else if (center_distance < circle_val3 - circle2_val3)
        {
            label.setText("Circle 2 Is Inside But Does Not Touch Circle 1.");
        }
        else if (center_distance < circle2_val3 - circle_val3)
        {
            label.setText("Circle 1 Is Inside But Does Not Touch Circle 2.");
        }
        else
        {
            label.setText("Circle 1 And Circle 2 Have Proper Overlap.");
        }
        
        label.relocate(0, 60);

        pane.getChildren().addAll(circle,circle2,prompt,label,tField);
        return pane;
    }

    // Handles the enter button press for changing values
    public class TextFieldHandler implements 
    EventHandler<ActionEvent>{
        public void handle(ActionEvent e)
        {
            String str = tField.getText();
            String delimiter = " ";
            String[] nums_str;
            nums_str= str.split(delimiter);

            int circle_val1 = Integer.parseInt(nums_str[0]);
            int circle_val2 = Integer.parseInt(nums_str[1]);
            double circle_val3 = Double.parseDouble(nums_str[2]);

            int circle2_val1 = Integer.parseInt(nums_str[3]);
            int circle2_val2 = Integer.parseInt(nums_str[4]);
            double circle2_val3 = Double.parseDouble(nums_str[5]);

            circle.setCenterX(circle_val1);
            circle.setCenterY(circle_val2);
            circle.setRadius(circle_val3);

            circle2.setCenterX(circle2_val1);
            circle2.setCenterY(circle2_val2);
            circle2.setRadius(circle2_val3);

            label.setText("First circle values are " + circle_val1 + ", " + circle_val2 + ", " + circle_val3 + " and second circle values are " + circle2_val1 + ", " + circle2_val2 + ", " + circle2_val3 + "\n");

            double center_distance = Math.sqrt(((circle2_val1 - circle_val1) * (circle2_val1 - circle_val1)) + ((circle2_val2 - circle_val2) * (circle2_val2 - circle_val2)));
            double radii_sum = circle_val3 + circle2_val3;

            // Used to output whether the circles are overlapping or not
            if (center_distance > radii_sum)
            {
                label.setText("These Circles Don\'t Overlap And Are Disjoint.");
            }
            else if (center_distance == radii_sum)
            {
                label.setText("These Circles Touch Each Other From The Outside.");
            }
            else if (center_distance == 0 && circle_val3 == circle2_val3)
            {
                label.setText("Circle 1 And Circle 2 Are Equal.");
            }
            else if (center_distance == circle_val3 - circle2_val3)
            {
                label.setText("Circle 2 Touches And Is Inside Circle 1.");
            }
            else if (center_distance == circle2_val3 - circle_val3)
            {
                label.setText("Circle 1 Touches And Is Inside Circle 2.");
            }
            else if (center_distance < circle_val3 - circle2_val3)
            {
                label.setText("Circle 2 Is Inside But Does Not Touch Circle 1.");
            }
            else if (center_distance < circle2_val3 - circle_val3)
            {
                label.setText("Circle 1 Is Inside But Does Not Touch Circle 2.");
            }
            else
            {
                label.setText("Circle 1 And Circle 2 Have Proper Overlap.");
            }
        }
    }
}